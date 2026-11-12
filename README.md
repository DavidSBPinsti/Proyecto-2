# Quiz Java comentado

Juego de consola con dos categorías de veinte preguntas. Necesita JDK 17 o superior. Todos los archivos Java están dentro de src. Las clases del juego están en src y las pruebas en src/tests. Las líneas de código incluyen comentarios laterales explicativos.

## Ejecutar desde la carpeta del proyecto

```powershell
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

En IntelliJ abre esta carpeta, selecciona un JDK 17 o superior y ejecuta Main.main.

## Ejecutar las pruebas

```powershell
javac -encoding UTF-8 -d out src/*.java src/tests/QuizTests.java
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

La carpeta incluye el historial Git local de la versión inicial y las correcciones realizadas durante la revisión. La autoría técnica de estos commits es Codex, con correo local codex@localhost. Las fechas corresponden a la ejecución real.

El repositorio de entrega es https://github.com/DavidSBPinsti/Proyecto-2. Para subir esta reorganización, desde la carpeta del proyecto ejecuta:

```powershell
git add src README.md uml.md
git commit -m "Mover codigo Java a src"
git push
```

Para abrir el proyecto en IntelliJ, abre la carpeta QuizJava y ejecuta Main desde src. Si IntelliJ no reconoce las clases automáticamente, marca src como Sources Root. Para ver los commits ejecuta `git log --oneline`.
