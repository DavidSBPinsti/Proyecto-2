/** Pregunta con tres opciones y su categoría. */
public final class Question {
    private final String text;
    private final String category;
    private final Answer[] answers;

    public Question(String text, String category, Answer[] answers) {
        this.text = text;
        this.category = category;
        this.answers = answers.clone();
    }

    public String getText() { return text; }
    public String getCategory() { return category; }
    public Answer[] getAnswers() { return answers.clone(); }
}