# Métodos Iterativos

**Materia:** Métodos Numéricos
**Docente:** Víctor Hugo Vásquez Herrera
**Alumna:** Andrés Garrido Denisse Itzel

## Descripción
Programación modular en Java de los métodos iterativos de Jacobi y Gauss-Seidel para resolver sistemas de ecuaciones lineales de la forma Ax = b.

## Sistema que se resuelve
```
5x +  y +  z = 7
 x + 4y +  z = 6
 x + 2y + 5z = 8
```

## Estructura
- `SistemaLineal.java` – Módulo 1: matriz A, vector b, X(0) y tolerancia.
- `MetodosIterativos.java` – Módulo 2: Jacobi y Gauss-Seidel.
- `Main.java` – Módulo 3: ejecuta ambos métodos y compara iteraciones.

## Compilación y ejecución
Desde la carpeta del proyecto:
```bash
javac *.java
java Main
```
```bash
javac -encoding UTF-8 *.java
java -Dfile.encoding=UTF-8 Main
```
