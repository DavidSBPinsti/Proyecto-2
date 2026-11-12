import java.util.Arrays;
import java.util.Random;

/** Carga el banco al crearse y extrae muestras sin repetir preguntas. */
public final class QuestionBank {
    private static final String[] CATEGORIES = {"Informática", "Cultura general"};
    private static final Question[][] QUESTIONS = {
        {
            createQuestion(CATEGORIES[0], Preguntas.pregunta1, "Array", "Router", "Monitor", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta2, "String", "boolean", "double", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta3, "return", "break", "new", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta4, "CPU", "SSD", "Fuente de alimentación", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta5, "SSD", "RAM", "Memoria flash", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta6, ".class", ".exe", ".java", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta7, "DNS", "FTP", "SMTP", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta8, "Decimal", "Binario", "Hexadecimal", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta9, "import", "continue", "return", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta10, "==", "=", "!=", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta11, "length", "size()", "count()", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta12, "else", "for", "try", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta13, "int", "char", "boolean", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta14, "java", "javac", "jar", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta15, "HTML", "CSS", "Git", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta16, "Router", "Teclado", "Impresora", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta17, "SQL", "CSS", "PNG", 'B'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta18, "Ubuntu", "Android", "Windows", 'C'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta19, "0", "1", "-1", 'A'), 
            createQuestion(CATEGORIES[0], Preguntas.pregunta20, "implements", "extends", "throws", 'B') 
        },
        {
        createQuestion(CATEGORIES[1], Preguntas.pregunta21, "Lisboa", "Oporto", "Madrid", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta22, "Venus", "Marte", "Saturno", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta23, "Atlántico", "Índico", "Pacífico", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta24, "6", "7", "8", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta25, "Flauta", "Piano", "Violín", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta26, "Lorca", "Quevedo", "Cervantes", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta27, "Corazón", "Hígado", "Pulmón", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta28, "Grado Celsius", "Kelvin", "Vatio", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta29, "Grecia", "Suecia", "Italia", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta30, "Au", "Ag", "Fe", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta31, "30", "60", "100", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta32, "Tiburón", "Pulpo", "Delfín", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta33, "África", "Europa", "Oceanía", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta34, "Helio", "Dióxido de carbono", "Neón", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta35, "7", "8", "9", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta36, "Mercurio", "Venus", "Tierra", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta37, "Goya", "Velázquez", "Picasso", 'B'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta38, "Titán", "Europa", "Luna", 'C'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta39, "Portugués", "Español", "Francés", 'A'),
        createQuestion(CATEGORIES[1], Preguntas.pregunta40, "3", "4", "6", 'B')
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