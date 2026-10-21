import java.util.Arrays;

/**
 * MÓDULO 2: Métodos Iterativos.
 * Contiene la lógica de cálculo de:
 *  - Método de Jacobi (usa un vector auxiliar para los nuevos valores)
 *  - Método de Gauss-Seidel (actualización inmediata de variables)
 */
public class MetodosIterativos {

    /** Límite de seguridad para evitar ciclos infinitos si no converge. */
    private static final int MAX_ITERACIONES = 1000;

    /** Resultado de un método: solución aproximada y número de iteraciones. */
    public static class Resultado {
        public final double[] solucion;
        public final int iteraciones;
        public final boolean convergio;

        public Resultado(double[] solucion, int iteraciones, boolean convergio) {
            this.solucion = solucion;
            this.iteraciones = iteraciones;
            this.convergio = convergio;
        }
    }

    /**
     * MÉTODO DE JACOBI
     * x_i^(k+1) = ( b_i - Σ_{j≠i} a_ij * x_j^(k) ) / a_ii
     * Todos los valores nuevos se calculan con los valores de la iteración
     * ANTERIOR; por eso se guardan en un vector auxiliar (xNuevo).
     */
    public static Resultado jacobi(SistemaLineal s) {
        double[][] A = s.getA();
        double[] b = s.getB();
        int n = s.getN();
        double[] x = s.getX0();            // Iteración k
        double[] xNuevo = new double[n];   // Vector auxiliar (iteración k+1)
        int k = 0;
        double error;

        System.out.println("=== MÉTODO DE JACOBI ===");
        do {
            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) suma += A[i][j] * x[j];   // usa x VIEJO
                }
                xNuevo[i] = (b[i] - suma) / A[i][i];
            }

            error = errorMaximo(x, xNuevo);
            k++;
            imprimirIteracion(k, xNuevo, error);

            x = xNuevo.clone();   // x pasa a ser la nueva aproximación
        } while (error >= s.getTolerancia() && k < MAX_ITERACIONES);

        return new Resultado(x, k, error < s.getTolerancia());
    }

    /**
     * MÉTODO DE GAUSS-SEIDEL
     * x_i^(k+1) = ( b_i - Σ_{j<i} a_ij * x_j^(k+1) - Σ_{j>i} a_ij * x_j^(k) ) / a_ii
     * Cada variable se actualiza INMEDIATAMENTE y se usa en el mismo ciclo.
     */
    public static Resultado gaussSeidel(SistemaLineal s) {
        double[][] A = s.getA();
        double[] b = s.getB();
        int n = s.getN();
        double[] x = s.getX0();
        int k = 0;
        double error;

        System.out.println("=== MÉTODO DE GAUSS-SEIDEL ===");
        do {
            double[] xAnterior = x.clone();   // Solo para calcular el error

            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) suma += A[i][j] * x[j];   // x ya actualizado para j<i
                }
                x[i] = (b[i] - suma) / A[i][i];           // actualización inmediata
            }

            error = errorMaximo(xAnterior, x);
            k++;
            imprimirIteracion(k, x, error);
        } while (error >= s.getTolerancia() && k < MAX_ITERACIONES);

        return new Resultado(x, k, error < s.getTolerancia());
    }

    /** Criterio de paro: norma infinito de la diferencia ||x(k+1) - x(k)||∞. */
    private static double errorMaximo(double[] anterior, double[] nuevo) {
        double max = 0;
        for (int i = 0; i < anterior.length; i++) {
            max = Math.max(max, Math.abs(nuevo[i] - anterior[i]));
        }
        return max;
    }

    /** Imprime el vector X y el error de cada iteración. */
    private static void imprimirIteracion(int k, double[] x, double error) {
        StringBuilder sb = new StringBuilder(String.format("Iter %2d: X = [", k));
        for (int i = 0; i < x.length; i++) {
            sb.append(String.format("%.6f%s", x[i], i < x.length - 1 ? ", " : ""));
        }
        sb.append(String.format("]  error = %.6f", error));
        System.out.println(sb);
    }
}
