/** Pregunta con tres opciones y su categoría. */
public final class Question { // Declara esta clase como no heredable.
    private final String text; // Define un atributo privado que se asigna una sola vez.
    private final String category; // Define un atributo privado que se asigna una sola vez.
    private final Answer[] answers; // Define un atributo privado que se asigna una sola vez.

    public Question(String text, String category, Answer[] answers) { // Declara el constructor o método con los parámetros indicados.
        this.text = text; // Guarda el argumento en el atributo del objeto.
        this.category = category; // Guarda el argumento en el atributo del objeto.
        this.answers = answers.clone(); // Guarda el argumento en el atributo del objeto, copiando el array para protegerlo.
    } // Cierra el bloque, método o clase.

    public String getText() { return text; } // Devuelve el atributo solicitado.
    public String getCategory() { return category; } // Devuelve el atributo solicitado.
    public Answer[] getAnswers() { return answers.clone(); } // Devuelve el atributo solicitado mediante una copia del array.
} // Cierra el bloque, método o clase.