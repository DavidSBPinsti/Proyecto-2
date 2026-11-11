import java.util.Arrays;
import java.util.Random;

/** Carga el banco al crearse y extrae muestras sin repetir preguntas. */
public final class QuestionBank {
    private static final String[] CATEGORIES = {"Informática", "Cultura general"};
    private static final Question[][] QUESTIONS = {
        {
            createQuestion(CATEGORIES[0], "¿Qué estructura guarda elementos por índice?", "Array", "Router", "Monitor", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué tipo representa verdadero o falso en Java?", "String", "boolean", "double", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué palabra crea un objeto en Java?", "return", "break", "new", 'C'),
            createQuestion(CATEGORIES[0], "¿Qué componente ejecuta instrucciones?", "CPU", "SSD", "Fuente de alimentación", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué memoria pierde su contenido al apagar el equipo?", "SSD", "RAM", "Memoria flash", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué extensión suele tener un archivo fuente de Java?", ".class", ".exe", ".java", 'C'),
            createQuestion(CATEGORIES[0], "¿Qué protocolo resuelve nombres de dominio?", "DNS", "FTP", "SMTP", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué sistema numérico usa dos símbolos?", "Decimal", "Binario", "Hexadecimal", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué instrucción devuelve un valor desde un método Java?", "import", "continue", "return", 'C'),
            createQuestion(CATEGORIES[0], "¿Qué operador compara dos valores int en Java?", "==", "=", "!=", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué método obtiene el tamaño de un ArrayList?", "length", "size()", "count()", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué palabra inicia un bloque de gestión de excepciones?", "else", "for", "try", 'C'),
            createQuestion(CATEGORIES[0], "¿Qué tipo de Java representa un entero de 32 bits?", "int", "char", "boolean", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué comando compila código fuente de Java?", "java", "javac", "jar", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué tecnología permite mantener versiones de código?", "HTML", "CSS", "Git", 'C'),
            createQuestion(CATEGORIES[0], "¿Qué dispositivo conecta redes distintas?", "Router", "Teclado", "Impresora", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué formato suele usarse para estilos web?", "SQL", "CSS", "PNG", 'B'),
            createQuestion(CATEGORIES[0], "¿Qué sistema operativo incluye el explorador de archivos de Microsoft?", "Ubuntu", "Android", "Windows", 'C'),
            createQuestion(CATEGORIES[0], "¿Cuál es el primer índice de un array en Java?", "0", "1", "-1", 'A'),
            createQuestion(CATEGORIES[0], "¿Qué palabra permite heredar de una clase en Java?", "implements", "extends", "throws", 'B')
        },
        {
            createQuestion(CATEGORIES[1], "¿Cuál es la capital de Portugal?", "Lisboa", "Oporto", "Madrid", 'A'),
            createQuestion(CATEGORIES[1], "¿Qué planeta es conocido como el planeta rojo?", "Venus", "Marte", "Saturno", 'B'),
            createQuestion(CATEGORIES[1], "¿Cuál es el océano más grande?", "Atlántico", "Índico", "Pacífico", 'C'),
            createQuestion(CATEGORIES[1], "¿Cuántos lados tiene un hexágono?", "6", "7", "8", 'A'),
            createQuestion(CATEGORIES[1], "¿Qué instrumento tiene teclas y cuerdas golpeadas por martillos?", "Flauta", "Piano", "Violín", 'B'),
            createQuestion(CATEGORIES[1], "¿Quién escribió Don Quijote de la Mancha?", "Lorca", "Quevedo", "Cervantes", 'C'),
            createQuestion(CATEGORIES[1], "¿Qué órgano bombea la sangre?", "Corazón", "Hígado", "Pulmón", 'A'),
            createQuestion(CATEGORIES[1], "¿Qué unidad mide la temperatura en el Sistema Internacional?", "Grado Celsius", "Kelvin", "Vatio", 'B'),
            createQuestion(CATEGORIES[1], "¿Qué país tiene forma de bota en el mapa?", "Grecia", "Suecia", "Italia", 'C'),
            createQuestion(CATEGORIES[1], "¿Cuál es el símbolo químico del oro?", "Au", "Ag", "Fe", 'A'),
            createQuestion(CATEGORIES[1], "¿Cuántos minutos tiene una hora?", "30", "60", "100", 'B'),
            createQuestion(CATEGORIES[1], "¿Qué animal es un mamífero?", "Tiburón", "Pulpo", "Delfín", 'C'),
            createQuestion(CATEGORIES[1], "¿En qué continente está Egipto principalmente?", "África", "Europa", "Oceanía", 'A'),
            createQuestion(CATEGORIES[1], "¿Qué gas absorben las plantas durante la fotosíntesis?", "Helio", "Dióxido de carbono", "Neón", 'B'),
            createQuestion(CATEGORIES[1], "¿Cuál es la raíz cuadrada de 81?", "7", "8", "9", 'C'),
            createQuestion(CATEGORIES[1], "¿Qué planeta es el más cercano al Sol?", "Mercurio", "Venus", "Tierra", 'A'),
            createQuestion(CATEGORIES[1], "¿Quién pintó Las meninas?", "Goya", "Velázquez", "Picasso", 'B'),
            createQuestion(CATEGORIES[1], "¿Cuál es el satélite natural de la Tierra?", "Titán", "Europa", "Luna", 'C'),
            createQuestion(CATEGORIES[1], "¿Qué lengua se habla principalmente en Brasil?", "Portugués", "Español", "Francés", 'A'),
            createQuestion(CATEGORIES[1], "¿Cuántas cuerdas tiene un violín estándar?", "3", "4", "6", 'B')
        }
    };

    public QuestionBank() {
    }

    public String[] getCategories() { return CATEGORIES.clone(); }

    public int getQuestionCount(int categoryIndex) {
        return QUESTIONS[categoryIndex].length;
    }

    public Question[] selectRandomQuestions(int categoryIndex, int count, Random random) {
        Question[] pool = QUESTIONS[categoryIndex].clone();
        if (count < 1 || count > pool.length) {
            throw new IllegalArgumentException("Cantidad de preguntas fuera del banco.");
        }

        // Fisher–Yates parcial. Cada posición elige una pregunta aún disponible.
        for (int i = 0; i < count; i++) {
            int selected = i + random.nextInt(pool.length - i);
            Question original = pool[i];
            pool[i] = pool[selected];
            pool[selected] = original;
        }
        return Arrays.copyOf(pool, count);
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