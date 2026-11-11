# Quiz Java comentado

Juego de consola con dos categorías de veinte preguntas. Necesita JDK 17 o superior. Los seis archivos Java de la raíz contienen el juego y tests/QuizTests.java contiene las pruebas. Las líneas de código incluyen comentarios laterales explicativos.

## Ejecutar desde la carpeta del proyecto

```powershell
javac -encoding UTF-8 -d out Answer.java Question.java GameRecord.java QuestionBank.java QuizEngine.java Main.java
java -cp out Main
```

En IntelliJ abre esta carpeta, selecciona un JDK 17 o superior y ejecuta Main.main.

## Ejecutar las pruebas

```powershell
javac -encoding UTF-8 -d out Answer.java Question.java GameRecord.java QuestionBank.java QuizEngine.java Main.java tests/QuizTests.java
java -cp out QuizTests
```

El resultado esperado es `OK: 304 comprobaciones superadas.`. Las pruebas lanzan AssertionError y terminan con error si algo falla. Son pruebas unitarias escritas en Java, junto con una prueba de integración de dos partidas. No requieren bibliotecas adicionales. Si el profesor exige específicamente JUnit, habrá que adaptar el ejecutor a ese framework.

## Decisiones de implementación

- Preguntas en arrays estáticos de tamaño fijo. Historial en ArrayList.
- Se solicitan entre 5 y 20 preguntas para evitar repeticiones.
- El nombre se mantiene durante la sesión. Categoría y cantidad se eligen en cada partida.
- Los cuatro tramos son 0 ≤ p < 34, 34 ≤ p < 67, 67 ≤ p < 100 y p = 100. Se evalúa el porcentaje sin redondear. La presentación tiene dos decimales.
- Los mensajes se modifican en FEEDBACK, dentro de QuizEngine.
- Los modelos son inmutables y proporcionan getters. Los arrays se devuelven mediante copias.
- El historial se conserva durante la ejecución del programa.
- Las ampliaciones de rachas y bonus son opcionales y esta entrega implementa el juego base.

## Diagramas y pruebas

flujo.md contiene el diagrama principal de ejecución. uml.md incluye las seis clases del juego y un diagrama adicional del ejecutor de pruebas. GitHub muestra los bloques Mermaid al abrir estos archivos. pruebas.md documenta las técnicas, los casos y los resultados de la ejecución.

## Git y publicación pendiente

La carpeta incluye un repositorio Git local. Sus commits registran la versión inicial existente y las correcciones realizadas a partir de la revisión. La autoría técnica de estos commits es Codex, con correo local codex@localhost. Las fechas corresponden a la ejecución real.

La publicación en GitHub está pendiente de conexión. El requisito de subir avances desde el comienzo del desarrollo no queda demostrado por este historial local. Una vez creado un repositorio vacío en tu cuenta, ejecuta estas órdenes sustituyendo USUARIO y REPOSITORIO:

```powershell
git remote add origin https://github.com/USUARIO/REPOSITORIO.git
git push -u origin main
```

Entrega la URL real de ese repositorio junto con el ZIP, los diagramas y los casos de prueba. El ZIP incluye la carpeta .git para conservar el historial. Para ver los commits ejecuta `git log --oneline`.
