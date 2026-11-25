import java.util.Arrays;
import java.util.Random;

/** Carga el banco al crearse y extrae muestras sin repetir preguntas. */
public final class QuestionBank {
    private static final String[] CATEGORIES = {"Informática", "Cultura general"};
    private static final Question[][] QUESTIONS = {
        {
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_1, "Array", "Router", "Monitor", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_2, "String", "boolean", "double", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_3, "return", "break", "new", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_4, "CPU", "SSD", "Fuente de alimentación", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_5, "SSD", "RAM", "Memoria flash", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_6, ".class", ".exe", ".java", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_7, "DNS", "FTP", "SMTP", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_8, "Decimal", "Binario", "Hexadecimal", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_9, "import", "continue", "return", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_10, "==", "=", "!=", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_11, "length", "size()", "count()", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_12, "else", "for", "try", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_13, "int", "char", "boolean", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_14, "java", "javac", "jar", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_15, "HTML", "CSS", "Git", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_16, "Router", "Teclado", "Impresora", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_17, "SQL", "CSS", "PNG", 'B'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_18, "Ubuntu", "Android", "Windows", 'C'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_19, "0", "1", "-1", 'A'),
            createQuestion(CATEGORIES[0], Preguntas.PREGUNTA_20, "implements", "extends", "throws", 'B')
        },
        {
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_21, "Lisboa", "Oporto", "Madrid", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_22, "Venus", "Marte", "Saturno", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_23, "Atlántico", "Índico", "Pacífico", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_24, "6", "7", "8", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_25, "Flauta", "Piano", "Violín", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_26, "Lorca", "Quevedo", "Cervantes", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_27, "Corazón", "Hígado", "Pulmón", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_28, "Grado Celsius", "Kelvin", "Vatio", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_29, "Grecia", "Suecia", "Italia", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_30, "Au", "Ag", "Fe", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_31, "30", "60", "100", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_32, "Tiburón", "Pulpo", "Delfín", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_33, "África", "Europa", "Oceanía", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_34, "Helio", "Dióxido de carbono", "Neón", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_35, "7", "8", "9", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_36, "Mercurio", "Venus", "Tierra", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_37, "Goya", "Velázquez", "Picasso", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_38, "Titán", "Europa", "Luna", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_39, "Portugués", "Español", "Francés", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.PREGUNTA_40, "3", "4", "6", 'B')
        } 
    };

    public QuestionBank() {
    } 

    public String[] getCategories() { return CATEGORIES.clone(); } // Devuelve el atributo solicitado mediante una copia del array.

    public int getQuestionCount(int categoryIndex) {
        return QUESTIONS[categoryIndex].length; // Devuelve el tamaño del banco para esta categoría.
    } 

    public Question[] selectRandomQuestions(int categoryIndex, int count, Random random) {
        Question[] pool = QUESTIONS[categoryIndex].clone(); // Obtiene o inicializa una copia del banco para barajar sin alterar el original.
        if (count < 1 || count > pool.length) { // Evalúa los límites indicados antes de aceptar el valor.
            throw new IllegalArgumentException("Cantidad de preguntas fuera del banco."); // Comunica que la cantidad solicitada no es válida.
        } 

        for (int i = 0; i < count; i++) { // Avanza el índice para recorrer o seleccionar preguntas.
            int selected = i + random.nextInt(pool.length - i); // Obtiene o inicializa un índice aleatorio entre la posición actual y el final.
            Question original = pool[i]; // Obtiene o inicializa la pregunta temporal para realizar el intercambio.
            pool[i] = pool[selected]; // Coloca la pregunta aleatoria en la posición actual.
            pool[selected] = original; // Completa el intercambio para evitar duplicados.
        } 
        return Arrays.copyOf(pool, count); // Devuelve la cantidad solicitada de preguntas del array barajado.
    } 

    private static Question createQuestion(String category, String text, String optionA,
                                           String optionB, String optionC, char correctLabel) {
        return new Question(text, category, new Answer[] {
            new Answer('A', optionA, correctLabel == 'A'),
            new Answer('B', optionB, correctLabel == 'B'),
            new Answer('C', optionC, correctLabel == 'C')
        });
    } 
} 