import java.util.Scanner;

/** Punto de entrada y control del ciclo de partidas. */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        QuestionBank bank = new QuestionBank();
        try (Scanner input = new Scanner(System.in)) {
            QuizEngine engine = new QuizEngine(input, bank);
            System.out.println("=== La búsqueda del tesoro ===");
            String username = engine.readUsername();
            do {
                engine.playGame(username);
            } while (engine.wantsReplay());
            engine.showStatistics();
        }
    }
}