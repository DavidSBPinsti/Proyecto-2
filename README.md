# Quiz Java comentado

Juego de consola con 40 preguntas repartidas entre Informática y Cultura general. Cada partida selecciona al azar entre 5 y 20 preguntas de la categoría elegida, sin repetirlas. Las respuestas A, B y C se aceptan en mayúscula o minúscula. Al salir, el juego muestra el historial de la sesión.

## Ejecutar el juego

Necesitas un JDK 17 o superior. Abre PowerShell en la carpeta que contiene este README y `src`.

```powershell
$fuentes = (Get-ChildItem -Path src -Filter *.java).FullName
javac --release 17 -encoding UTF-8 -d out $fuentes
java -cp out Main
```

En IntelliJ IDEA, abre la carpeta contenedora `QuizJava_comentado`, selecciona un JDK 17 o superior y ejecuta `Main.main`.

## Ejecutar las pruebas

```powershell
$fuentes = (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
javac --release 17 -encoding UTF-8 -d out $fuentes
java -cp out QuizTests
```

El resultado esperado es `OK: 304 comprobaciones superadas.`. El ejecutor incluye pruebas unitarias y una prueba de integración de dos partidas. Solo requiere el JDK.

## Código y documentación

- `src/Preguntas.java` guarda los enunciados como constantes `PREGUNTA_1` a `PREGUNTA_40`. `src/QuestionBank.java` los combina con las opciones y las respuestas correctas en arrays estáticos.
- `src/QuizEngine.java` gestiona las partidas y guarda el historial de la sesión en un `ArrayList`. Los cuatro mensajes de resultado se editan en `FEEDBACK`. El juego compara el porcentaje sin redondear con los cortes de 34, 67 y 100 y lo muestra con dos decimales.
- [UML](uml.md) contiene las siete clases del juego y `QuizTests`. [Diagrama de flujo](flujo.md) describe una sesión completa. [Informe de pruebas](pruebas.md) recoge los casos y resultados.

## Historial Git y publicación

El ZIP incluye la carpeta oculta `.git` con el historial local. Desde la carpeta que contiene este README, puedes consultar los commits con `git log --oneline`.

Tras confirmar la URL de GitHub y disponer de permiso de escritura, configura el repositorio remoto y revisa su estado. Sustituye `URL_CONFIRMADA` por la URL del repositorio de destino.

```powershell
git remote add origin URL_CONFIRMADA
git fetch origin
git branch -r
git log --oneline --graph --all
```

Si `origin` ya está configurado, comprueba la URL con `git remote -v` antes de continuar. Si el repositorio está vacío o su rama `main` es antecesora de la rama local, publica el historial con `git push -u origin main`. Si las ramas han divergido, revisa e integra el historial remoto antes de subir cambios. Con permisos limitados, solicita acceso o usa un fork y abre una pull request si el destino lo admite.
