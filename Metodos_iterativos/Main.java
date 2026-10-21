import java.util.Arrays;

/**
 * MÓDULO 3: Ejecución (clase principal).
 * Ejecuta de forma secuencial Jacobi y Gauss-Seidel sobre el mismo sistema,
 * con el mismo X(0) y la misma tolerancia, y compara las iteraciones.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Definir el sistema (Módulo 1)
        SistemaLineal sistema = SistemaLineal.sistemaPractica();
        sistema.imprimir();

        // 2. Resolver con ambos métodos (Módulo 2)
        MetodosIterativos.Resultado rJ = MetodosIterativos.jacobi(sistema);
        System.out.println();
        MetodosIterativos.Resultado rGS = MetodosIterativos.gaussSeidel(sistema);

        // 3. Mostrar resultados y comparación
        System.out.println("\n=== RESUMEN ===");
        System.out.printf("Jacobi       : %d iteraciones, X ≈ %s%n",
                rJ.iteraciones, formato(rJ.solucion));
        System.out.printf("Gauss-Seidel : %d iteraciones, X ≈ %s%n",
                rGS.iteraciones, formato(rGS.solucion));
        System.out.println("Diferencia   : " + Math.abs(rJ.iteraciones - rGS.iteraciones)
                + " iteraciones a favor de "
                + (rGS.iteraciones < rJ.iteraciones ? "Gauss-Seidel" : "Jacobi"));
    }

    private static String formato(double[] x) {
        String[] s = new String[x.length];
        for (int i = 0; i < x.length; i++) s[i] = String.format("%.6f", x[i]);
        return Arrays.toString(s);
    }
}

