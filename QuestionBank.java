import java.util.Arrays; // Importa la clase java.util.Arrays para poder utilizarla.
import java.util.Random; // Importa la clase java.util.Random para poder utilizarla.

/** Carga el banco al crearse y extrae muestras sin repetir preguntas. */
public final class QuestionBank { // Declara esta clase como no heredable.
    private static final String[] CATEGORIES = {"Informática", "Cultura general"}; // Define una constante de clase compartida por todas las instancias.
    private static final Question[][] QUESTIONS = { // Define una constante de clase compartida por todas las instancias.
        { // Delimita las preguntas de una categoría.
            createQuestion(CATEGORIES[0], "¿Qué estructura guarda elementos por índice?", "Array", "Router", "Monitor", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué tipo representa verdadero o falso en Java?", "String", "boolean", "double", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué palabra crea un objeto en Java?", "return", "break", "new", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué componente ejecuta instrucciones?", "CPU", "SSD", "Fuente de alimentación", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué memoria pierde su contenido al apagar el equipo?", "SSD", "RAM", "Memoria flash", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué extensión suele tener un archivo fuente de Java?", ".class", ".exe", ".java", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué protocolo resuelve nombres de dominio?", "DNS", "FTP", "SMTP", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué sistema numérico usa dos símbolos?", "Decimal", "Binario", "Hexadecimal", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué instrucción devuelve un valor desde un método Java?", "import", "continue", "return", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué operador comprueba si dos valores int son iguales?", "==", "=", "!=", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué método obtiene el tamaño de un ArrayList?", "length", "size()", "count()", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué palabra inicia un bloque de gestión de excepciones?", "else", "for", "try", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué tipo de Java representa un entero de 32 bits?", "int", "char", "boolean", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué comando compila código fuente de Java?", "java", "javac", "jar", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué tecnología permite mantener versiones de código?", "HTML", "CSS", "Git", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué dispositivo conecta redes distintas?", "Router", "Teclado", "Impresora", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué formato suele usarse para estilos web?", "SQL", "CSS", "PNG", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué sistema operativo incluye el explorador de archivos de Microsoft?", "Ubuntu", "Android", "Windows", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Cuál es el primer índice de un array en Java?", "0", "1", "-1", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[0], "¿Qué palabra permite heredar de una clase en Java?", "implements", "extends", "throws", 'B') // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
        }, // Delimita las preguntas de una categoría.
        { // Delimita las preguntas de una categoría.
            createQuestion(CATEGORIES[1], "¿Cuál es la capital de Portugal?", "Lisboa", "Oporto", "Madrid", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué planeta es conocido como el planeta rojo?", "Venus", "Marte", "Saturno", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuál es el océano más grande?", "Atlántico", "Índico", "Pacífico", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuántos lados tiene un hexágono?", "6", "7", "8", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué instrumento tiene teclas y cuerdas golpeadas por martillos?", "Flauta", "Piano", "Violín", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Quién escribió Don Quijote de la Mancha?", "Lorca", "Quevedo", "Cervantes", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué órgano bombea la sangre?", "Corazón", "Hígado", "Pulmón", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué unidad mide la temperatura en el Sistema Internacional?", "Grado Celsius", "Kelvin", "Vatio", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué país tiene forma de bota en el mapa?", "Grecia", "Suecia", "Italia", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuál es el símbolo químico del oro?", "Au", "Ag", "Fe", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuántos minutos tiene una hora?", "30", "60", "100", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué animal es un mamífero?", "Tiburón", "Pulpo", "Delfín", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿En qué continente está Egipto principalmente?", "África", "Europa", "Oceanía", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué gas absorben las plantas durante la fotosíntesis?", "Helio", "Dióxido de carbono", "Neón", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuál es la raíz cuadrada de 81?", "7", "8", "9", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué planeta es el más cercano al Sol?", "Mercurio", "Venus", "Tierra", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Quién pintó Las meninas?", "Goya", "Velázquez", "Picasso", 'B'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuál es el satélite natural de la Tierra?", "Titán", "Europa", "Luna", 'C'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Qué lengua se habla principalmente en Brasil?", "Portugués", "Español", "Francés", 'A'), // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
            createQuestion(CATEGORIES[1], "¿Cuántas cuerdas tiene un violín estándar?", "3", "4", "6", 'B') // Crea una pregunta con sus opciones A/B/C y la letra de la respuesta correcta.
        } // Cierra el bloque, método o clase.
    }; // Termina el array y, cuando corresponde, la construcción del objeto.

    public QuestionBank() { // Declara el constructor o método con los parámetros indicados.
    } // Cierra el bloque, método o clase.

    public String[] getCategories() { return CATEGORIES.clone(); } // Devuelve el atributo solicitado mediante una copia del array.

    public int getQuestionCount(int categoryIndex) { // Declara el constructor o método con los parámetros indicados.
        return QUESTIONS[categoryIndex].length; // Devuelve el tamaño del banco para esta categoría.
    } // Cierra el bloque, método o clase.

    public Question[] selectRandomQuestions(int categoryIndex, int count, Random random) { // Declara el constructor o método con los parámetros indicados.
        Question[] pool = QUESTIONS[categoryIndex].clone(); // Obtiene o inicializa una copia del banco para barajar sin alterar el original.
        if (count < 1 || count > pool.length) { // Evalúa los límites indicados antes de aceptar el valor.
            throw new IllegalArgumentException("Cantidad de preguntas fuera del banco."); // Comunica que la cantidad solicitada no es válida.
        } // Cierra el bloque, método o clase.

        // Fisher–Yates parcial. Cada posición elige una pregunta aún disponible.
        for (int i = 0; i < count; i++) { // Avanza el índice para recorrer o seleccionar preguntas.
            int selected = i + random.nextInt(pool.length - i); // Obtiene o inicializa un índice aleatorio entre la posición actual y el final.
            Question original = pool[i]; // Obtiene o inicializa la pregunta temporal para realizar el intercambio.
            pool[i] = pool[selected]; // Coloca la pregunta aleatoria en la posición actual.
            pool[selected] = original; // Completa el intercambio para evitar duplicados.
        } // Cierra el bloque, método o clase.
        return Arrays.copyOf(pool, count); // Devuelve la cantidad solicitada de preguntas del array barajado.
    } // Cierra el bloque, método o clase.

    private static Question createQuestion(String category, String text, String optionA, // Declara un método que pertenece a la clase.
                                           String optionB, String optionC, char correctLabel) { // Completa los parámetros con las opciones restantes y la letra correcta.
        return new Question(text, category, new Answer[] { // Construye la pregunta y el array de respuestas.
            new Answer('A', optionA, correctLabel == 'A'), // Crea la opción y determina si su letra coincide con la solución.
            new Answer('B', optionB, correctLabel == 'B'), // Crea la opción y determina si su letra coincide con la solución.
            new Answer('C', optionC, correctLabel == 'C') // Crea la opción y determina si su letra coincide con la solución.
        }); // Termina el array y, cuando corresponde, la construcción del objeto.
    } // Cierra el bloque, método o clase.
} // Cierra el bloque, método o clase.