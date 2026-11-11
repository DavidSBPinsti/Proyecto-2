# UML de clases

```mermaid
classDiagram
    direction TB
    class Answer {
        -char label
        -String text
        -boolean correct
        +Answer(char label, String text, boolean correct)
        +getLabel() char
        +getText() String
        +isCorrect() boolean
    }
    class Question {
        -String text
        -String category
        -Answer[] answers
        +Question(String text, String category, Answer[] answers)
        +getText() String
        +getCategory() String
        +getAnswers() Answer[]
    }
    class GameRecord {
        -String username
        -LocalDateTime endedAt
        -int correct
        -int incorrect
        +GameRecord(String username, LocalDateTime endedAt, int correct, int incorrect)
        +getUsername() String
        +getEndedAt() LocalDateTime
        +getCorrect() int
        +getIncorrect() int
    }
    class QuestionBank {
        -String[] CATEGORIES$
        -Question[][] QUESTIONS$
        +QuestionBank()
        +getCategories() String[]
        +getQuestionCount(int categoryIndex) int
        +selectRandomQuestions(int categoryIndex, int count, Random random) Question[]
        -createQuestion(String category, String text, String optionA, String optionB, String optionC, char correctLabel) Question$
    }
    class QuizEngine {
        -int MIN_QUESTIONS$
        -Locale DISPLAY_LOCALE$
        -DateTimeFormatter DATE_FORMAT$
        -String[] FEEDBACK$
        -Scanner input
        -QuestionBank bank
        -Random random
        -ArrayList~GameRecord~ records
        +QuizEngine(Scanner input, QuestionBank bank)
        +readUsername() String
        +playGame(String username) void
        +wantsReplay() boolean
        +showStatistics() void
        -readIntInRange(String prompt, int min, int max) int
        -askQuestion(Question question) boolean
        -readAnswer() char
        -feedbackFor(double percentage) String$
    }
    class Main {
        -Main()
        +main(String[] args) void$
    }
    Main ..> QuestionBank : crea
    Main ..> QuizEngine : ejecuta
    QuizEngine --> QuestionBank : consulta
    QuizEngine "1" *-- "0..*" GameRecord : historial
    QuestionBank "1" --> "40" Question : banco estático
    Question "1" *-- "3" Answer : opciones
```

## Ejecutor de pruebas

```mermaid
classDiagram
    class QuizTests {
        -int checks$
        -QuizTests()
        +main(String[] args) void$
        -check(boolean condition, String description) void$
        -invoke(QuizEngine engine, String name, Class[] types, Object[] arguments) Object$
        -testModels() void$
        -testBank() void$
        -testValidation() void$
        -testFeedback() void$
        -testSession(ByteArrayOutputStream output) void$
    }
    QuizTests ..> QuizEngine : valida e integra
    QuizTests ..> QuestionBank : prueba selección
    QuizTests ..> Answer : verifica datos
    QuizTests ..> Question : verifica copias
    QuizTests ..> GameRecord : verifica resultados
```

En el código de pruebas, types es Class<?>[] y arguments usa varargs Object..., representado aquí como Object[]. Los métodos de prueba que usan reflexión declaran throws Exception. Los atributos del juego son final. Los miembros estáticos se indican con el sufijo $ en el código Mermaid. Los modelos inmutables exponen getters y se inicializan mediante sus constructores.
