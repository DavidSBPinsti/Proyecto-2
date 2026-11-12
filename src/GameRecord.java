import java.time.LocalDateTime;

/** Resultado de una partida completada. */
public final class GameRecord { 
    private final String username; 
    private final LocalDateTime endedAt; 
    private final int correct; 
    private final int incorrect; 

    public GameRecord(String username, LocalDateTime endedAt, int correct, int incorrect) {
        this.username = username; 
        this.endedAt = endedAt; 
        this.correct = correct; 
        this.incorrect = incorrect; 
    }

    public String getUsername() { return username; } 
    public LocalDateTime getEndedAt() { return endedAt; } 
    public int getCorrect() { return correct; } 
    public int getIncorrect() { return incorrect; } 
}