import java.io.ByteArrayOutputStream; // Permite capturar la salida de consola.
import java.io.PrintStream; // Permite redirigir la salida durante las pruebas.
import java.lang.reflect.Field; // Permite inspeccionar atributos privados sin cambiar la aplicación.
import java.lang.reflect.Method; // Permite probar métodos privados de validación.
import java.nio.charset.StandardCharsets; // Define UTF-8 para capturar caracteres españoles.
import java.time.LocalDateTime; // Representa la fecha usada en la prueba del registro.
import java.util.Arrays; // Permite convertir un array en una lista.
import java.util.HashSet; // Permite detectar preguntas repetidas.
import java.util.Random; // Proporciona aleatoriedad reproducible mediante una semilla.
import java.util.Scanner; // Simula las entradas del usuario.

/** Pruebas unitarias y de integración ejecutables con Java 17, sin dependencias externas. */
public final class QuizTests { // Agrupa las pruebas del proyecto.
    private static int checks = 0; // Cuenta las comprobaciones que pasan.
    private QuizTests() {} // Impide crear instancias del ejecutor de pruebas.

    public static void main(String[] args) throws Exception { // Ejecuta todas las pruebas y propaga cualquier fallo.
        PrintStream console = System.out; // Conserva la consola original.
        ByteArrayOutputStream output = new ByteArrayOutputStream(); // Reserva un búfer para las salidas del juego.
        try (PrintStream capture = new PrintStream(output, true, StandardCharsets.UTF_8)) { // Crea una salida UTF-8 que se cerrará al finalizar.
            System.setOut(capture); // Evita que los avisos del juego llenen la consola de pruebas.
            testModels(); // Comprueba los modelos y la protección de sus arrays.
            testBank(); // Comprueba las categorías, opciones y selección aleatoria.
            testValidation(); // Comprueba entradas válidas e inválidas.
            testFeedback(); // Comprueba todos los límites de los mensajes.
            testSession(output); // Comprueba dos partidas y su historial.
        } finally { // Ejecuta la restauración incluso si una prueba falla.
            System.setOut(console); // Recupera la salida original.
        } // Termina el bloque de captura.
        System.out.println("OK: " + checks + " comprobaciones superadas."); // Informa del total real de comprobaciones.
    } // Termina el punto de entrada.

    private static void check(boolean condition, String description) { // Centraliza las comprobaciones.
        if (!condition) { // Detecta un resultado distinto del esperado.
            throw new AssertionError(description); // Detiene la ejecución con una explicación.
        } // Termina la detección de fallo.
        checks++; // Cuenta únicamente las comprobaciones superadas.
    } // Termina el método de comprobación.

    private static Object invoke(QuizEngine engine, String name, Class<?>[] types, Object... arguments) throws Exception { // Invoca un método privado para probarlo de manera aislada.
        Method method = QuizEngine.class.getDeclaredMethod(name, types); // Localiza el método por nombre y tipos.
        method.setAccessible(true); // Habilita el acceso privado dentro de las pruebas.
        return method.invoke(engine, arguments); // Ejecuta el método con los argumentos de la prueba.
    } // Termina el acceso de prueba.

    private static void testModels() { // Prueba los datos almacenados en las clases.
        Answer answer = new Answer('B', "Ejemplo", true); // Crea una opción conocida.
        check(answer.getLabel() == 'B' && answer.getText().equals("Ejemplo") && answer.isCorrect(), "Answer"); // Comprueba sus tres datos.
        Answer[] options = {answer, new Answer('A', "Otra", false), new Answer('C', "Otra más", false)}; // Prepara un array para probar las copias.
        Question question = new Question("Pregunta", "Tema", options); // Crea una pregunta con esos datos.
        options[0] = null; // Modifica el array externo para comprobar que la pregunta está protegida.
        check(question.getAnswers()[0] == answer, "Copia en constructor"); // Comprueba que el constructor copió el array.
        question.getAnswers()[0] = null; // Modifica una copia devuelta por el getter.
        check(question.getAnswers()[0] == answer, "Copia en getter"); // Comprueba que el array interno sigue intacto.
        check(question.getText().equals("Pregunta") && question.getCategory().equals("Tema"), "Question"); // Verifica el texto y la categoría.
        LocalDateTime date = LocalDateTime.of(2026, 11, 11, 12, 30); // Fija una fecha conocida.
        GameRecord record = new GameRecord("Ana", date, 4, 1); // Crea un resultado conocido.
        check(record.getUsername().equals("Ana") && record.getEndedAt().equals(date), "Identidad y fecha"); // Verifica usuario y momento de finalización.
        check(record.getCorrect() == 4 && record.getIncorrect() == 1, "Contadores del registro"); // Verifica los resultados almacenados.
    } // Termina la prueba de modelos.

    private static void testBank() { // Prueba el banco completo y sus muestras.
        QuestionBank bank = new QuestionBank(); // Carga el banco real.
        for (int category = 0; category < bank.getCategories().length; category++) { // Repite para cada categoría.
            int total = bank.getQuestionCount(category); // Obtiene el tamaño del banco.
            check(total >= 20, "Mínimo por categoría"); // Exige al menos veinte preguntas.
            Question[] all = bank.selectRandomQuestions(category, total, new Random(1)); // Obtiene todas las preguntas en orden reproducible.
            for (Question question : all) { // Inspecciona cada pregunta.
                Answer[] answers = question.getAnswers(); // Obtiene sus opciones.
                check(answers.length == 3, "Tres opciones"); // Verifica que haya tres opciones.
                int correct = 0; // Inicia el contador de opciones correctas.
                for (int i = 0; i < answers.length; i++) { // Recorre las opciones en orden.
                    check(answers[i].getLabel() == 'A' + i, "Letras A/B/C"); // Comprueba que sus letras coincidan con los índices.
                    if (answers[i].isCorrect()) { // Identifica una opción correcta.
                        correct++; // Suma esa opción al contador.
                    } // Termina la condición.
                } // Termina el recorrido de opciones.
                check(correct == 1, "Una solución"); // Exige exactamente una opción marcada como correcta.
                check(question.getCategory().equals(bank.getCategories()[category]), "Categoría consistente"); // Comprueba que la pregunta pertenezca a la categoría.
            } // Termina el recorrido del banco.
            for (int count : new int[]{5, 6, 19, 20}) { // Prueba tamaños próximos a ambos límites.
                Question[] sample = bank.selectRandomQuestions(category, count, new Random(42)); // Extrae una muestra con semilla fija.
                check(sample.length == count, "Tamaño de muestra"); // Comprueba la cantidad solicitada.
                check(new HashSet<>(Arrays.asList(sample)).size() == count, "Sin repeticiones"); // Comprueba que todas las preguntas sean distintas.
            } // Termina las muestras.
            Question[] first = bank.selectRandomQuestions(category, 5, new Random(1)); // Obtiene la primera muestra.
            Question[] second = bank.selectRandomQuestions(category, 5, new Random(2)); // Obtiene una muestra con otra semilla.
            check(!Arrays.equals(first, second), "La selección varía"); // Comprueba que estos dos sorteos produzcan órdenes distintos.
            for (int count : new int[]{0, 21}) { // Prueba cantidades inválidas para el método del banco.
                boolean rejected = false; // Inicializa el indicador de rechazo.
                try { // Intenta extraer una cantidad inválida.
                    bank.selectRandomQuestions(category, count, new Random(1)); // Llama al método con un valor fuera de rango.
                } catch (IllegalArgumentException expected) { // Captura el error previsto.
                    rejected = true; // Registra que la validación actuó.
                } // Termina la captura.
                check(rejected, "Rechazo de muestra inválida"); // Verifica que no se haya aceptado la cantidad.
            } // Termina los valores inválidos.
        } // Termina las categorías.
    } // Termina la prueba del banco.

    private static void testValidation() throws Exception { // Prueba la lectura y validación de datos.
        QuestionBank bank = new QuestionBank(); // Crea el banco para los motores de prueba.
        for (String letter : new String[]{"A", "B", "C", "a", "b", "c"}) { // Cubre todas las letras admitidas.
            QuizEngine engine = new QuizEngine(new Scanner("X\nAB\n1\n\n" + letter + "\n"), bank); // Antecede entradas inválidas a una válida.
            char result = (char) invoke(engine, "readAnswer", new Class<?>[0]); // Ejecuta la validación aislada.
            check(result == Character.toUpperCase(letter.charAt(0)), "Validación de respuesta"); // Verifica la normalización de la letra.
        } // Termina las letras.
        for (int value : new int[]{5, 6, 19, 20}) { // Cubre los valores válidos próximos a los límites.
            QuizEngine engine = new QuizEngine(new Scanner("abc\n4\n21\n5.5\n2147483648\n" + value + "\n"), bank); // Incluye texto, valores fuera de rango, decimal y desbordamiento.
            int result = (int) invoke(engine, "readIntInRange", new Class<?>[]{String.class, int.class, int.class}, "Cantidad", 5, 20); // Ejecuta la validación numérica.
            check(result == value, "Límites de cantidad"); // Verifica que alcance la entrada válida.
        } // Termina los límites numéricos.
        QuizEngine names = new QuizEngine(new Scanner("\n   \n Ana \n"), bank); // Simula nombres vacíos y uno válido.
        check(names.readUsername().equals("Ana"), "Validación del nombre"); // Verifica el rechazo de vacíos y la eliminación de espacios.
        for (String choice : new String[]{"S", "s", "N", "n"}) { // Cubre ambas elecciones en sus dos formas.
            QuizEngine engine = new QuizEngine(new Scanner("tal vez\n" + choice + "\n"), bank); // Incluye una respuesta inválida anterior.
            check(engine.wantsReplay() == choice.equalsIgnoreCase("S"), "Repetición"); // Verifica el resultado de cada elección.
        } // Termina las elecciones.
    } // Termina la prueba de validación.

    private static void testFeedback() throws Exception { // Prueba los límites de los cuatro mensajes.
        double[] percentages = {0, 33, 33.33, 33.99, 34, 66, 66.67, 66.99, 67, 99, 99.99, 100}; // Define valores interiores y fronteras.
        String[] expected = {"Primer reconocimiento", "Primer reconocimiento", "Primer reconocimiento", "Primer reconocimiento", "Buen avance", "Buen avance", "Buen avance", "Buen avance", "Gran partida", "Gran partida", "Gran partida", "Tesoro completo"}; // Especifica el mensaje esperado en cada caso.
        for (int i = 0; i < percentages.length; i++) { // Recorre los casos.
            String message = (String) invoke(null, "feedbackFor", new Class<?>[]{double.class}, percentages[i]); // Invoca el método estático con el porcentaje.
            check(message.contains(expected[i]), "Mensaje para " + percentages[i]); // Comprueba el tramo elegido.
        } // Termina los casos de puntuación.
    } // Termina la prueba de mensajes.

    private static void testSession(ByteArrayOutputStream output) throws Exception { // Comprueba respuestas, formato e historial en dos partidas.
        QuestionBank bank = new QuestionBank(); // Crea el banco real.
        StringBuilder input = new StringBuilder("0\n1\n4\n5\n"); // Simula categoría y cantidad con un error previo en cada campo.
        Question[] selected = bank.selectRandomQuestions(0, 5, new Random(123)); // Predice el sorteo usando la misma semilla que el motor.
        for (Question question : selected) { // Recorre las preguntas de la primera partida.
            for (Answer answer : question.getAnswers()) { // Busca la solución de cada pregunta.
                if (answer.isCorrect()) { // Detecta la solución.
                    input.append(answer.getLabel()).append('\n'); // Introduce la respuesta correcta.
                } // Termina la selección.
            } // Termina las opciones.
        } // Termina la primera partida.
        input.append("s\n2\n6\n"); // Repite y elige la segunda categoría con seis preguntas.
        for (Question question : bank.selectRandomQuestions(1, 6, new Random(456))) { // Predice las preguntas de la segunda partida.
            for (Answer answer : question.getAnswers()) { // Busca una respuesta incorrecta.
                if (!answer.isCorrect()) { // Detecta una opción incorrecta.
                    input.append(answer.getLabel()).append('\n'); // Introduce esa opción.
                    break; // Asegura una respuesta por pregunta.
                } // Termina la elección incorrecta.
            } // Termina las opciones.
        } // Termina la preparación.
        input.append("n\n"); // Solicita salir después de la segunda partida.
        QuizEngine engine = new QuizEngine(new Scanner(input.toString()), bank); // Crea el motor con la entrada simulada.
        Field randomField = QuizEngine.class.getDeclaredField("random"); // Localiza el generador privado.
        randomField.setAccessible(true); // Permite fijar su semilla desde las pruebas.
        Random random = (Random) randomField.get(engine); // Recupera el generador utilizado por el motor.
        output.reset(); // Descarta salidas de pruebas anteriores.
        random.setSeed(123); // Sincroniza el primer sorteo.
        engine.playGame("Ana"); // Ejecuta la partida de pleno.
        check(engine.wantsReplay(), "Repetición de la primera partida"); // Comprueba la respuesta S.
        random.setSeed(456); // Sincroniza el segundo sorteo.
        engine.playGame("Ana"); // Ejecuta la partida de todos los fallos.
        check(!engine.wantsReplay(), "Salida tras segunda partida"); // Comprueba la respuesta N.
        engine.showStatistics(); // Imprime el historial completo.
        String result = output.toString(StandardCharsets.UTF_8); // Recupera la salida capturada.
        check(result.contains("Resultado: 100,00%") && result.contains("Resultado: 0,00%"), "Formato de porcentaje"); // Comprueba los dos decimales.
        check(result.contains("Aciertos: 5 | Fallos: 0") && result.contains("Aciertos: 0 | Fallos: 6"), "Resultados"); // Verifica los contadores.
        check(result.lines().filter(line -> line.startsWith("Ana | ")).count() == 2, "Historial completo"); // Exige un registro por partida.
        check(result.lines().filter(line -> line.startsWith("Ana | ")).allMatch(line -> line.matches("Ana \\| \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2} \\| Aciertos: \\d+ \\| Fallos: \\d+")), "Fecha del historial"); // Verifica el formato de cada fecha y sus contadores.
        check(result.contains("¡Correcto!") && result.contains("Incorrecto. La respuesta era"), "Corrección inmediata"); // Verifica ambos tipos de respuesta.
    } // Termina la prueba de sesión.
} // Termina el ejecutor de pruebas.
