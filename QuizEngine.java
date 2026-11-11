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
        "¡Tesoro completo! Has acertado todas las preguntas."
    };
    private final Scanner input;
    private final QuestionBank bank;
    private final Random random = new Random();
    private final ArrayList<GameRecord> records = new ArrayList<>();

    public QuizEngine(Scanner input, QuestionBank bank) {
        this.input = input;
        this.bank = bank;
    }

    public String readUsername() {
        while (true) {
            System.out.print("Nombre de usuario: ");
            String username = input.nextLine().trim();
            if (!username.isEmpty()) {
                return username;
            }
            System.out.println("Error: escribe un nombre.");
        }
    }

    public void playGame(String username) {
        String[] categories = bank.getCategories();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categories[i]);
        }
        int categoryIndex = readIntInRange("Elige categoría: ", 1, categories.length) - 1;
        int maxQuestions = bank.getQuestionCount(categoryIndex);
        int count = readIntInRange("¿Cuántas preguntas quieres responder? ", MIN_QUESTIONS, maxQuestions);
        Question[] selected = bank.selectRandomQuestions(categoryIndex, count, random);

        int correct = 0;
        for (int i = 0; i < selected.length; i++) {
            System.out.printf("%nPregunta %d/%d%n", i + 1, count);
            if (askQuestion(selected[i])) {
                correct++;
            }
        }

        int incorrect = count - correct;
        double percentage = 100.0 * correct / count;
        records.add(new GameRecord(username, LocalDateTime.now(), correct, incorrect));
        System.out.printf(DISPLAY_LOCALE, "%n%s%nAciertos: %d | Fallos: %d | Resultado: %.2f%%%n",
                          feedbackFor(percentage), correct, incorrect, percentage);
    }

    public boolean wantsReplay() {
        while (true) {
            System.out.print("¿Jugar otra vez? (S/N): ");
            String choice = input.nextLine().trim();
            if (choice.equalsIgnoreCase("S")) {
                return true;
            }
            if (choice.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Error: escribe S o N.");
        }
    }

    public void showStatistics() {
        System.out.println("\n=== Historial de partidas ===");
        for (GameRecord record : records) {
            System.out.printf("%s | %s | Aciertos: %d | Fallos: %d%n", record.getUsername(),
                              record.getEndedAt().format(DATE_FORMAT),
                              record.getCorrect(), record.getIncorrect());
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
            } catch (NumberFormatException ignored) {
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
                System.out.printf("Incorrecto. La respuesta era %c. %s%n",
                                  answer.getLabel(), answer.getText());
                break;
            }
        }
        return false;
    }

    private char readAnswer() {
        while (true) {
            System.out.print("Respuesta (A/B/C): ");
            String choice = input.nextLine().trim();
            if (choice.matches("[ABCabc]")) {
                return Character.toUpperCase(choice.charAt(0));
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