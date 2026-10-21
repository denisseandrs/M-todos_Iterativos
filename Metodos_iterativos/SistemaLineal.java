/**
 * MÓDULO 1: Definición del Sistema.
 * Estructura y almacena los datos del problema:
 *  - Matriz de coeficientes A
 *  - Vector independiente b
 *  - Vector de aproximación inicial X(0)
 *  - Tolerancia de error epsilon
 */
public class SistemaLineal {

    private final double[][] A;        // Matriz de coeficientes
    private final double[] b;          // Vector independiente
    private final double[] x0;         // Aproximación inicial X(0)
    private final double tolerancia;   // Tolerancia ε
    private final int n;               // Tamaño del sistema

    /** Constructor que recibe todos los datos del sistema. */
    public SistemaLineal(double[][] A, double[] b, double[] x0, double tolerancia) {
        this.A = A;
        this.b = b;
        this.x0 = x0;
        this.tolerancia = tolerancia;
        this.n = b.length;
    }

    /** Sistema de la práctica:
     *  5x +  y +  z = 7
     *   x + 4y +  z = 6
     *   x + 2y + 5z = 8
     *  con X(0) = [0, 0, 0] y ε = 0.0001
     */
    public static SistemaLineal sistemaPractica() {
        double[][] A = {
            {5, 1, 1},
            {1, 4, 1},
            {1, 2, 5}
        };
        double[] b = {7, 6, 8};
        double[] x0 = {0, 0, 0};
        return new SistemaLineal(A, b, x0, 0.0001);
    }

    // ---------- Getters ----------
    public double[][] getA() { return A; }
    public double[] getB() { return b; }
    /** Devuelve una COPIA para que cada método parta del mismo X(0). */
    public double[] getX0() { return x0.clone(); }
    public double getTolerancia() { return tolerancia; }
    public int getN() { return n; }

    /** Muestra el sistema en consola. */
    public void imprimir() {
        System.out.println("Sistema de ecuaciones:");
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder("  ");
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%5.1f ", A[i][j]));
            }
            sb.append(String.format("| %5.1f", b[i]));
            System.out.println(sb);
        }
        System.out.println("Tolerancia ε = " + tolerancia + "\n");
    }
}
