import java.util.Scanner; // Importa la clase java.util.Scanner para poder utilizarla.

/** Punto de entrada y control del ciclo de partidas. */
public final class Main { // Declara esta clase como no heredable.
    private Main() { // Declara el constructor o método con los parámetros indicados.
    } // Cierra el bloque, método o clase.

    public static void main(String[] args) { // Declara un método que pertenece a la clase.
        QuestionBank bank = new QuestionBank(); // Obtiene o inicializa el banco y sus arrays de preguntas.
        try (Scanner input = new Scanner(System.in)) { // Abre el lector y lo cierra automáticamente al finalizar.
            QuizEngine engine = new QuizEngine(input, bank); // Obtiene o inicializa el motor del juego.
            System.out.println("=== La búsqueda del tesoro ==="); // Muestra el texto o dato indicado al usuario.
            String username = engine.readUsername(); // Obtiene o inicializa el nombre validado o leído del usuario.
            do { // Ejecuta al menos una partida antes de comprobar si se repite.
                engine.playGame(username); // Ejecuta una partida con el nombre del jugador.
            } while (engine.wantsReplay()); // Repite el ciclo cuando el usuario elige jugar otra vez.
            engine.showStatistics(); // Muestra todas las partidas guardadas antes de salir.
        } // Cierra el bloque, método o clase.
    } // Cierra el bloque, método o clase.
} // Cierra el bloque, método o clase.