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
    class Preguntas {
        +String PREGUNTA_1$
        +String PREGUNTA_2$
        +String PREGUNTA_3$
        +String PREGUNTA_4$
        +String PREGUNTA_5$
        +String PREGUNTA_6$
        +String PREGUNTA_7$
        +String PREGUNTA_8$
        +String PREGUNTA_9$
        +String PREGUNTA_10$
        +String PREGUNTA_11$
        +String PREGUNTA_12$
        +String PREGUNTA_13$
        +String PREGUNTA_14$
        +String PREGUNTA_15$
        +String PREGUNTA_16$
        +String PREGUNTA_17$
        +String PREGUNTA_18$
        +String PREGUNTA_19$
        +String PREGUNTA_20$
        +String PREGUNTA_21$
        +String PREGUNTA_22$
        +String PREGUNTA_23$
        +String PREGUNTA_24$
        +String PREGUNTA_25$
        +String PREGUNTA_26$
        +String PREGUNTA_27$
        +String PREGUNTA_28$
        +String PREGUNTA_29$
        +String PREGUNTA_30$
        +String PREGUNTA_31$
        +String PREGUNTA_32$
        +String PREGUNTA_33$
        +String PREGUNTA_34$
        +String PREGUNTA_35$
        +String PREGUNTA_36$
        +String PREGUNTA_37$
        +String PREGUNTA_38$
        +String PREGUNTA_39$
        +String PREGUNTA_40$
        +Preguntas()
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
    QuizEngine ..> Question : presenta
    QuizEngine ..> Answer : evalúa
    QuizEngine "1" *-- "0..*" GameRecord : historial
    QuestionBank --> "40" Question : banco estático
    QuestionBank ..> Preguntas : usa los enunciados
    QuestionBank ..> Answer : crea opciones
    Question --> "3" Answer : opciones
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

El diagrama incluye las siete clases del juego y el ejecutor de pruebas. Java genera el constructor público sin argumentos de `Preguntas`. En el código de pruebas, types es Class<?>[] y arguments usa varargs Object..., representado aquí como Object[]. Los métodos de prueba que usan reflexión declaran throws Exception. Los atributos de las clases del juego se declaran con `final`. Los miembros estáticos se indican con el sufijo $ en el código Mermaid. Los modelos inmutables exponen getters y se inicializan mediante sus constructores.
