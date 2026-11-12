/** Una opción de respuesta. */
public final class Answer {
    private final char label;
    private final String text;
    private final boolean correct;

    public Answer(char label, String text, boolean correct) {
        this.label = label;
        this.text = text;
        this.correct = correct;
    } 

    public char getLabel() { return label; }
    public String getText() { return text; }
    public boolean isCorrect() { return correct; }
} 