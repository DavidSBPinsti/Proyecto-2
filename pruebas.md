# Pruebas del quiz

## Ejecución

Abre la carpeta en IntelliJ, selecciona un JDK 17 o superior y ejecuta `Main.java`. Desde la terminal, puedes usar:

```powershell
javac -encoding UTF-8 Answer.java Question.java GameRecord.java QuestionBank.java QuizEngine.java Main.java
java Main
```

## 1. Particiones de equivalencia

| Entrada | Partición válida → resultado | Partición inválida → resultado |
|---|---|---|
| Usuario | `David` → continúa | Línea vacía → muestra error y vuelve a pedirlo |
| Categoría | `1` → Informática | `0` → vuelve a pedir la categoría |
| Cantidad | `5` → inicia la partida | `abc` → vuelve a pedir un entero |
| Respuesta | `a` → evalúa como A | `X` → vuelve a pedir respuesta a la misma pregunta |
| Repetir | `s` → vuelve a elegir categoría | `tal vez` → vuelve a pedir S/N |

## 2. Análisis de valores límite

| Regla | Valor | Resultado esperado |
|---|---:|---|
| Cantidad mínima | 4 | Rechaza |
| Cantidad mínima | 5 | Acepta |
| Cantidad mínima | 6 | Acepta |
| Cantidad máxima | 19 | Acepta |
| Cantidad máxima | 20 | Acepta |
| Cantidad máxima | 21 | Rechaza |
| Cambio al segundo mensaje | 33,00 % / 33,99 % / 34,00 % | Primero / primero / segundo |
| Cambio al tercer mensaje | 66,00 % / 66,99 % / 67,00 % | Segundo / segundo / tercero |
| Puntuación perfecta | 99,00 % / 99,99 % / 100,00 % | Tercero / tercero / cuarto |

Los porcentajes exactos de frontera son casos para probar directamente el método privado `feedbackFor`, ya que el banco actual solo permite entre 5 y 20 preguntas. En una partida real de 6 preguntas, 2 aciertos dan 33,33 % y 4 aciertos dan 66,67 %. La selección del mensaje usa el porcentaje antes de formatear a dos decimales.

## 3. Tabla de decisión

Cada partida completa se guarda una vez antes de preguntar si se quiere repetir. Una opción de repetición inválida mantiene al usuario en ese mismo aviso.

| Puntuación | ¿Repetir? | Mensaje mostrado | ¿Guardar? | Acción siguiente |
|---|---|---|---|---|
| 0 a menos de 34 % | S | Primer reconocimiento | Sí | Elegir categoría |
| 0 a menos de 34 % | N | Primer reconocimiento | Sí | Mostrar historial y salir |
| 34 a menos de 67 % | S | Buen avance | Sí | Elegir categoría |
| 34 a menos de 67 % | N | Buen avance | Sí | Mostrar historial y salir |
| 67 a menos de 100 % | S | Gran partida | Sí | Elegir categoría |
| 67 a menos de 100 % | N | Gran partida | Sí | Mostrar historial y salir |
| 100 % | S | Tesoro completo | Sí | Elegir categoría |
| 100 % | N | Tesoro completo | Sí | Mostrar historial y salir |

## Resultado de la comprobación

La compilación con destino Java 17 y la prueba de consola con dos partidas de 5 y 6 preguntas pasaron. Se verificaron siete avisos de validación, el cambio de categoría y dos registros finales. Las tablas también recogen los resultados esperados de los casos de frontera que no formaron parte de esa ejecución.
