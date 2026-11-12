import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale; 
import java.util.Random; 
import java.util.Scanner; 

/** Valida entradas, ejecuta partidas y conserva sus resultados en memoria. */
public final class QuizEngine { 
    private static final int MIN_QUESTIONS = 5; 
    private static final Locale DISPLAY_LOCALE = Locale.forLanguageTag("es-ES"); 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss"); 
    private static final String[] FEEDBACK = { 
        "¡Primer reconocimiento! Cada respuesta te da una pista para la próxima partida.", 
        "¡Buen avance! Ya has conquistado una parte del mapa.", 
        "¡Gran partida! El tesoro está a un paso.", 
        "¡Tesoro completo! Has acertado todas las preguntas." // Configura el texto modificable de este resultado.
    }; 
    private final Scanner input; 
    private final QuestionBank bank; 
    private final Random random = new Random(); 
    private final ArrayList<GameRecord> records = new ArrayList<>(); // Define un atributo privado que se asigna una sola vez.

    public QuizEngine(Scanner input, QuestionBank bank) { 
        this.input = input; 
        this.bank = bank; // Guarda el argumento en el atributo del objeto.
    } 

    public String readUsername() { 
        while (true) { 
            System.out.print("Nombre de usuario: "); 
            String username = input.nextLine().trim(); // Obtiene o inicializa el nombre validado o leído del usuario.
            if (!username.isEmpty()) { // Comprueba que el nombre no esté vacío.
                return username;
            } 
            System.out.println("Error: escribe un nombre."); 
        } 
    } 

    public void playGame(String username) { 
        String[] categories = bank.getCategories(); // Obtiene o inicializa una copia de las categorías.
        for (int i = 0; i < categories.length; i++) { 
            System.out.printf("%d. %s%n", i + 1, categories[i]); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
        } 
        int categoryIndex = readIntInRange("Elige categoría: ", 1, categories.length) - 1; 
        int maxQuestions = bank.getQuestionCount(categoryIndex); 
        int count = readIntInRange("¿Cuántas preguntas quieres responder? ", MIN_QUESTIONS, maxQuestions); 
        Question[] selected = bank.selectRandomQuestions(categoryIndex, count, random); 

        int correct = 0; 
        for (int i = 0; i < selected.length; i++) { 
            System.out.printf("%nPregunta %d/%d%n", i + 1, count); // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
            if (askQuestion(selected[i])) { 
                correct++; 
            } 
        } 

        int incorrect = count - correct; // Obtiene o inicializa los fallos restando los aciertos del total.
        double percentage = 100.0 * correct / count; // Obtiene o inicializa el porcentaje usando división decimal.
        records.add(new GameRecord(username, LocalDateTime.now(), correct, incorrect)); // Guarda la partida en el ArrayList con la fecha y hora actuales.
        System.out.printf(DISPLAY_LOCALE, "%n%s%nAciertos: %d | Fallos: %d | Resultado: %.2f%%%n", // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
                          feedbackFor(percentage), correct, incorrect, percentage); // Pasa el mensaje, los aciertos, los fallos y el porcentaje al formato de salida.
    } 

    public boolean wantsReplay() { 
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.print("¿Jugar otra vez? (S/N): "); 
            String choice = input.nextLine().trim(); // Obtiene o inicializa la elección introducida por el usuario.
            if (choice.equalsIgnoreCase("S")) { // Compara la elección sin distinguir mayúsculas de minúsculas.
                return true; // Devuelve true al código que llamó a este método.
            } 
            if (choice.equalsIgnoreCase("N")) { // Compara la elección sin distinguir mayúsculas de minúsculas.
                return false; 
            } 
            System.out.println("Error: escribe S o N."); 
        } 
    } 

    public void showStatistics() { 
        System.out.println("\n=== Historial de partidas ==="); 
        for (GameRecord record : records) { 
            System.out.printf("%s | %s | Aciertos: %d | Fallos: %d%n", record.getUsername(), // Escribe datos con formato; %n salta de línea y %.2f muestra dos decimales.
                              record.getEndedAt().format(DATE_FORMAT), // Formatea la fecha y hora del registro para mostrarla.
                              record.getCorrect(), record.getIncorrect()); // Añade los aciertos y fallos a los datos mostrados.
        } 
    } 

    private int readIntInRange(String prompt, int min, int max) { 
        while (true) {
            System.out.printf("%s[%d-%d] ", prompt, min, max);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } 
            } catch (NumberFormatException ignored) { // Gestiona una entrada que no se puede convertir a entero.
                // Los valores no enteros y fuera de rango comparten el mismo aviso.
            } 
            System.out.printf("Error: escribe un entero entre %d y %d.%n", min, max);
        } 
    } 

    private boolean askQuestion(Question question) { 
        System.out.println(question.getText()); 
        Answer[] answers = question.getAnswers(); 
        for (Answer answer : answers) { 
            System.out.printf("%c. %s%n", answer.getLabel(), answer.getText());
        } 

        char choice = readAnswer();
        Answer selected = answers[choice - 'A'];
        if (selected.isCorrect()) { 
            System.out.println("¡Correcto!"); 
            return true;
        } 

        for (Answer answer : answers) { 
            if (answer.isCorrect()) { 
                System.out.printf("Incorrecto. La respuesta era %c. %s%n", answer.getLabel(), answer.getText());
                break;
            } 
        } 
        return false; 
    } 

    private char readAnswer() { 
        while (true) { // Repite hasta obtener una entrada válida y ejecutar return.
            System.out.print("Respuesta (A/B/C): "); 
            String choice = input.nextLine().trim(); // Obtiene o inicializa la elección introducida por el usuario.
            if (choice.matches("[ABCabc]")) { // Exige exactamente una letra A/B/C, admitiendo también minúsculas.
                return Character.toUpperCase(choice.charAt(0)); // Devuelve la primera letra convertida a mayúsculas.
            } 
            System.out.println("Error: escribe A, B o C."); 
        } 
    } 

    /** Los tramos empiezan en 0, 34, 67 y 100, sin huecos para decimales. */
    private static String feedbackFor(double percentage) { 
        if (percentage == 100.0) { 
            return FEEDBACK[3]; 
        } 
        if (percentage >= 67.0) { 
            return FEEDBACK[2]; 
        } 
        if (percentage >= 34.0) { 
            return FEEDBACK[1]; 
        } 
        return FEEDBACK[0]; 
    } 
} 