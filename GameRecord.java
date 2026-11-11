import java.time.LocalDateTime; // Importa la clase java.time.LocalDateTime para poder utilizarla.

/** Resultado de una partida completada. */
public final class GameRecord { // Declara esta clase como no heredable.
    private final String username; // Define un atributo privado que se asigna una sola vez.
    private final LocalDateTime endedAt; // Define un atributo privado que se asigna una sola vez.
    private final int correct; // Define un atributo privado que se asigna una sola vez.
    private final int incorrect; // Define un atributo privado que se asigna una sola vez.

    public GameRecord(String username, LocalDateTime endedAt, int correct, int incorrect) { // Declara el constructor o método con los parámetros indicados.
        this.username = username; // Guarda el argumento en el atributo del objeto.
        this.endedAt = endedAt; // Guarda el argumento en el atributo del objeto.
        this.correct = correct; // Guarda el argumento en el atributo del objeto.
        this.incorrect = incorrect; // Guarda el argumento en el atributo del objeto.
    } // Cierra el bloque, método o clase.

    public String getUsername() { return username; } // Devuelve el atributo solicitado.
    public LocalDateTime getEndedAt() { return endedAt; } // Devuelve el atributo solicitado.
    public int getCorrect() { return correct; } // Devuelve el atributo solicitado.
    public int getIncorrect() { return incorrect; } // Devuelve el atributo solicitado.
} // Cierra el bloque, método o clase.