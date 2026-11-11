import java.time.LocalDateTime; // Importa la clase java.time.LocalDateTime para poder utilizarla.
import java.time.format.DateTimeFormatter; // Importa la clase java.time.format.DateTimeFormatter para poder utilizarla.
import java.util.ArrayList; // Importa la clase java.util.ArrayList para poder utilizarla.
import java.util.Locale; // Importa la clase java.util.Locale para poder utilizarla.
import java.util.Random; // Importa la clase java.util.Random para poder utilizarla.
import java.util.Scanner; // Importa la clase java.util.Scanner para poder utilizarla.

/** Valida entradas, ejecuta partidas y conserva sus resultados en memoria. */
public final class QuizEngine { // Declara esta clase como no heredable.
    private static final int MIN_QUESTIONS = 5; // Define una constante de clase compartida por todas las instancias.
    private static final Locale DISPLAY_LOCALE = Locale.forLanguageTag("es-ES"); // Define una constante de clase compartida por todas las instancias.
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss"); // Define una constante de clase compartida por todas las instancias.
    private static final String[] FEEDBACK = { // Define una constante de clase compartida por todas las instancias.
        "¡Primer reconocimiento! Cada respuesta te da una pista para la próxima partida.", // Configura el texto modificable de este resultado.
        "¡Buen avance! Ya has conquistado una parte del mapa.", // Configura el texto modificable de este resultado.
        "¡Gran partida! El tesoro está a un paso.", // Configura el texto modificable de este resultado.
        "¡Tesoro completo! Has acertado todas las preguntas." // Configura el texto modificable de este resultado.
    }; // Termina el array y, cuando corresponde, la construcción del objeto.
    private final Scanner input; // Define un atributo privado que se asigna una sola vez.
    private final QuestionBank bank; // Define un atributo privado que se asigna una sola vez.
    private final Random random = new Random(); // Define un atributo privado que se asigna una sola vez.
    private final ArrayList<GameRecord> records = new ArrayList<>(); // Define un atributo privado que se asigna una sola vez.

    public QuizEngine(Scanner input, QuestionBank bank) { // Declara el constructor o método con los parámetros indicados.
        this.input = input; // Guarda el argumento en el atributo del objeto.
        this.bank = bank; // Guarda el argumento en el atributo del objeto.
    } // Cierra el bloque, método o clase.

    public String readUsername() { // Declara el constructor o método con los parámetros indicados.
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.print("Nombre de usuario: "); // Muestra el texto o dato indicado al usuario.
            String username = input.nextLine().trim(); // Obtiene o inicializa el nombre validado o leído del usuario.
            if (!username.isEmpty()) { // Comprueba que el nombre no esté vacío.
                return username; // Devuelve username al código que llamó a este método.
            } // Cierra el bloque, método o clase.
            System.out.println("Error: escribe un nombre."); // Muestra el texto o dato indicado al usuario.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.

    public void playGame(String username) { // Declara el constructor o método con los parámetros indicados.
        String[] categories = bank.getCategories(); // Obtiene o inicializa una copia de las categorías.
        for (int i = 0; i < categories.length; i++) { // Avanza el índice para recorrer o seleccionar preguntas.
            System.out.printf("%d. %s%n", i + 1, categories[i]); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
        } // Cierra el bloque, método o clase.
        int categoryIndex = readIntInRange("Elige categoría: ", 1, categories.length) - 1; // Obtiene o inicializa el índice de categoría validado, empezando en cero.
        int maxQuestions = bank.getQuestionCount(categoryIndex); // Obtiene o inicializa el número de preguntas disponibles.
        int count = readIntInRange("¿Cuántas preguntas quieres responder? ", MIN_QUESTIONS, maxQuestions); // Obtiene o inicializa la cantidad validada entre cinco y el máximo.
        Question[] selected = bank.selectRandomQuestions(categoryIndex, count, random); // Obtiene o inicializa las preguntas aleatorias sin repetir.

        int correct = 0; // Obtiene o inicializa el contador de aciertos inicialmente a cero.
        for (int i = 0; i < selected.length; i++) { // Avanza el índice para recorrer o seleccionar preguntas.
            System.out.printf("%nPregunta %d/%d%n", i + 1, count); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
            if (askQuestion(selected[i])) { // Comprueba si la respuesta es correcta.
                correct++; // Suma un acierto al contador.
            } // Cierra el bloque, método o clase.
        } // Cierra el bloque, método o clase.

        int incorrect = count - correct; // Obtiene o inicializa los fallos restando los aciertos del total.
        double percentage = 100.0 * correct / count; // Obtiene o inicializa el porcentaje usando división decimal.
        records.add(new GameRecord(username, LocalDateTime.now(), correct, incorrect)); // Guarda la partida en el ArrayList con la fecha y hora actuales.
        System.out.printf(DISPLAY_LOCALE, "%n%s%nAciertos: %d | Fallos: %d | Resultado: %.2f%%%n", // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
                          feedbackFor(percentage), correct, incorrect, percentage); // Pasa el mensaje, los aciertos, los fallos y el porcentaje al formato de salida.
    } // Cierra el bloque, método o clase.

    public boolean wantsReplay() { // Declara el constructor o método con los parámetros indicados.
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.print("¿Jugar otra vez? (S/N): "); // Muestra el texto o dato indicado al usuario.
            String choice = input.nextLine().trim(); // Obtiene o inicializa la elección introducida por el usuario.
            if (choice.equalsIgnoreCase("S")) { // Compara la elección sin distinguir mayúsculas de minúsculas.
                return true; // Devuelve true al código que llamó a este método.
            } // Cierra el bloque, método o clase.
            if (choice.equalsIgnoreCase("N")) { // Compara la elección sin distinguir mayúsculas de minúsculas.
                return false; // Devuelve false al código que llamó a este método.
            } // Cierra el bloque, método o clase.
            System.out.println("Error: escribe S o N."); // Muestra el texto o dato indicado al usuario.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.

    public void showStatistics() { // Declara el constructor o método con los parámetros indicados.
        System.out.println("\n=== Historial de partidas ==="); // Muestra el texto o dato indicado al usuario.
        for (GameRecord record : records) { // Recorre uno a uno los elementos del array o historial.
            System.out.printf("%s | %s | Aciertos: %d | Fallos: %d%n", record.getUsername(), // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
                              record.getEndedAt().format(DATE_FORMAT), // Formatea la fecha y hora del registro para mostrarla.
                              record.getCorrect(), record.getIncorrect()); // Añade los aciertos y fallos a los datos mostrados.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.

    private int readIntInRange(String prompt, int min, int max) { // Declara el constructor o método con los parámetros indicados.
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.printf("%s[%d-%d] ", prompt, min, max); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
            try { // Intenta convertir y validar la entrada del usuario.
                int value = Integer.parseInt(input.nextLine().trim()); // Obtiene o inicializa el entero leído desde la consola.
                if (value >= min && value <= max) { // Evalúa los límites indicados antes de aceptar el valor.
                    return value; // Devuelve value al código que llamó a este método.
                } // Cierra el bloque, método o clase.
            } catch (NumberFormatException ignored) { // Gestiona una entrada que no se puede convertir a entero.
                // Los valores no enteros y fuera de rango comparten el mismo aviso.
            } // Cierra el bloque, método o clase.
            System.out.printf("Error: escribe un entero entre %d y %d.%n", min, max); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.

    private boolean askQuestion(Question question) { // Declara el constructor o método con los parámetros indicados.
        System.out.println(question.getText()); // Muestra el texto o dato indicado al usuario.
        Answer[] answers = question.getAnswers(); // Obtiene o inicializa una copia de las opciones de respuesta.
        for (Answer answer : answers) { // Recorre uno a uno los elementos del array o historial.
            System.out.printf("%c. %s%n", answer.getLabel(), answer.getText()); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
        } // Cierra el bloque, método o clase.

        char choice = readAnswer(); // Obtiene o inicializa la elección introducida por el usuario.
        Answer selected = answers[choice - 'A']; // Obtiene o inicializa la opción elegida convirtiendo A/B/C en 0/1/2.
        if (selected.isCorrect()) { // Comprueba si la respuesta es correcta.
            System.out.println("¡Correcto!"); // Muestra el texto o dato indicado al usuario.
            return true; // Devuelve true al código que llamó a este método.
        } // Cierra el bloque, método o clase.

        for (Answer answer : answers) { // Recorre uno a uno los elementos del array o historial.
            if (answer.isCorrect()) { // Comprueba si la respuesta es correcta.
                System.out.printf("Incorrecto. La respuesta era %c. %s%n", // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
                                  answer.getLabel(), answer.getText()); // Añade la letra y el texto de la respuesta correcta a la salida.
                break; // Abandona el bucle tras encontrar la solución.
            } // Cierra el bloque, método o clase.
        } // Cierra el bloque, método o clase.
        return false; // Devuelve false al código que llamó a este método.
    } // Cierra el bloque, método o clase.

    private char readAnswer() { // Declara el constructor o método con los parámetros indicados.
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.print("Respuesta (A/B/C): "); // Muestra el texto o dato indicado al usuario.
            String choice = input.nextLine().trim(); // Obtiene o inicializa la elección introducida por el usuario.
            if (choice.matches("[ABCabc]")) { // Exige exactamente una letra A/B/C, admitiendo también minúsculas.
                return Character.toUpperCase(choice.charAt(0)); // Devuelve la primera letra convertida a mayúsculas.
            } // Cierra el bloque, método o clase.
            System.out.println("Error: escribe A, B o C."); // Muestra el texto o dato indicado al usuario.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.

    /** Los tramos empiezan en 0, 34, 67 y 100, sin huecos para decimales. */
    private static String feedbackFor(double percentage) { // Declara un método que pertenece a la clase.
        if (percentage == 100.0) { // Comprueba el límite de este tramo usando el porcentaje sin redondear.
            return FEEDBACK[3]; // Devuelve el mensaje correspondiente al tramo de puntuación.
        } // Cierra el bloque, método o clase.
        if (percentage >= 67.0) { // Comprueba el límite de este tramo usando el porcentaje sin redondear.
            return FEEDBACK[2]; // Devuelve el mensaje correspondiente al tramo de puntuación.
        } // Cierra el bloque, método o clase.
        if (percentage >= 34.0) { // Comprueba el límite de este tramo usando el porcentaje sin redondear.
            return FEEDBACK[1]; // Devuelve el mensaje correspondiente al tramo de puntuación.
        } // Cierra el bloque, método o clase.
        return FEEDBACK[0]; // Devuelve el mensaje correspondiente al tramo de puntuación.
    } // Cierra el bloque, método o clase.
} // Cierra el bloque, método o clase.