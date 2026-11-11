/** Una opción de respuesta. */
public final class Answer { // Declara esta clase como no heredable.
    private final char label; // Define un atributo privado que se asigna una sola vez.
    private final String text; // Define un atributo privado que se asigna una sola vez.
    private final boolean correct; // Define un atributo privado que se asigna una sola vez.

    public Answer(char label, String text, boolean correct) { // Declara el constructor o método con los parámetros indicados.
        this.label = label; // Guarda el argumento en el atributo del objeto.
        this.text = text; // Guarda el argumento en el atributo del objeto.
        this.correct = correct; // Guarda el argumento en el atributo del objeto.
    } // Cierra el bloque, método o clase.

    public char getLabel() { return label; } // Devuelve el atributo solicitado.
    public String getText() { return text; } // Devuelve el atributo solicitado.
    public boolean isCorrect() { return correct; } // Devuelve el atributo solicitado.
} // Cierra el bloque, método o clase.