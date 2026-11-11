# Informe de pruebas

## Entorno y resultado

Compilación con JDK 27 y destino Java 17 mediante `--release 17`. Ejecución de tests/QuizTests.java. Resultado obtenido tras las correcciones y los comentarios de código, **304 comprobaciones superadas**.

El ejecutor contiene pruebas unitarias de los modelos, el banco, las validaciones y los mensajes, además de una prueba de integración de dos partidas. Usa AssertionError para señalar errores y se ejecuta con las instrucciones de README.md. La reflexión permite probar los métodos privados sin modificar su visibilidad en el juego.

## 1. Particiones de equivalencia

| Entrada | Grupo válido y representantes | Grupo inválido y representantes | Resultado observado |
|---|---|---|---|
| Nombre | Ana, con espacios exteriores | Vacío, solo espacios | Pide de nuevo y acepta Ana |
| Cantidad | Enteros de 5 a 20 | abc, 5.5, 4, 21, 2147483648 | Rechaza los inválidos y alcanza el entero válido |
| Respuesta | A/B/C y a/b/c | X, AB, 1, vacío | Rechaza hasta recibir una letra y la normaliza |
| Repetición | S/s y N/n | tal vez | Pide de nuevo y respeta la elección |
| Categoría | 1 y 2 | 0 | Rechaza 0 y permite cambiar entre categorías |

## 2. Análisis de valores límite

| Caso | Resultado esperado | Resultado observado |
|---|---|---|
| 4 preguntas | Rechazo y nueva solicitud | Correcto |
| 5 y 6 preguntas | Aceptación | Correcto |
| 19 y 20 preguntas | Aceptación | Correcto |
| 21 preguntas | Rechazo y nueva solicitud | Correcto |
| 0 %, 33 %, 33,33 %, 33,99 % | Primer reconocimiento | Correcto |
| 34 %, 66 %, 66,67 %, 66,99 % | Buen avance | Correcto |
| 67 %, 99 %, 99,99 % | Gran partida | Correcto |
| 100 % | Tesoro completo | Correcto |

El enunciado usa porcentajes enteros y deja huecos para decimales. Se adopta y prueba la regla de cortes en 34, 67 y 100. Los valores de frontera se suministran directamente a feedbackFor. Algunos porcentajes no se pueden obtener exactamente jugando entre 5 y 20 preguntas.

## 3. Tabla de decisión

Cada resultado de partida se guarda antes de leer la elección de repetición. La puntuación determina el mensaje y la elección determina la siguiente acción.

| Porcentaje sin redondear | Repetición | Mensaje | Registro guardado | Acción |
|---|---|---|---|---|
| 0 ≤ p < 34 | S | Primer reconocimiento | Sí | Elegir categoría |
| 0 ≤ p < 34 | N | Primer reconocimiento | Sí | Mostrar historial y salir |
| 34 ≤ p < 67 | S | Buen avance | Sí | Elegir categoría |
| 34 ≤ p < 67 | N | Buen avance | Sí | Mostrar historial y salir |
| 67 ≤ p < 100 | S | Gran partida | Sí | Elegir categoría |
| 67 ≤ p < 100 | N | Gran partida | Sí | Mostrar historial y salir |
| p = 100 | S | Tesoro completo | Sí | Elegir categoría |
| p = 100 | N | Tesoro completo | Sí | Mostrar historial y salir |
| Cualquier tramo | Inválida | Se conserva el mensaje mostrado | Se conserva el único registro | Repetir solicitud S/N |

Se probaron por separado todos los tramos de mensajes y ambas elecciones S/N, incluida la recuperación de una entrada inválida. La integración ejecutó específicamente 100 % con S y 0 % con N. La tabla describe las nueve combinaciones y no implica nueve ejecuciones integrales.

## Comprobaciones adicionales

- Cada categoría contiene al menos veinte preguntas.
- Cada pregunta tiene tres opciones ordenadas A/B/C y una sola marcada como correcta.
- Las muestras de 5, 6, 19 y 20 preguntas no contienen repeticiones.
- Dos semillas distintas producen selecciones distintas en los casos probados.
- Las copias de arrays protegen el contenido de Question.
- La integración obtiene cinco aciertos en cinco preguntas y cero aciertos en seis.
- Ambos resultados muestran dos decimales y se guardan en dos registros con usuario, fecha, hora y contadores.
- Se muestra la corrección inmediata tanto de aciertos como de fallos.
