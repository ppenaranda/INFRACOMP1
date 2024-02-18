import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main extends Thread {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Main <archivo_entrada> <num_generaciones>");
            return;
        }

        String archivoEntrada = args[0];
        int numGeneraciones = Integer.parseInt(args[1]);

        boolean[][] mat = leerEstadoInicial(archivoEntrada);
        int N = mat.length;

        for (int gen = 0; gen < numGeneraciones; gen++) {
            System.out.println("Iniciando generación " + (gen + 1));

            System.out.println("Generacion " + (gen + 1) + ":");
            imprimirMatriz(mat);

            for (int fila = 0; fila <= N; fila++) {
                for (int columna = 0; columna <= N; columna++) {
                    int vecinos = 0;
                    // arriba y abajo
                    if(fila == N || fila == 0){
                        ++vecinos;
                    }else{
                        vecinos = vecinos +2;
                    }
                    // a ambos lados
                    if(columna == N || columna == 0){
                        ++vecinos;
                    }else{
                        vecinos = vecinos +2;
                    }
                    Celda celda = new Celda(mat, fila, columna, mat[fila][columna], N, vecinos);
                    Thread thread = new Thread(celda);
                    thread.start();
                }
            }
        }

    }

    private static boolean[][] leerEstadoInicial(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] mat = new boolean[N][N];

            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null && fila < N) {
                String[] valores = linea.split(",");
                for (int columna = 0; columna < N; columna++) {
                    mat[fila][columna] = Boolean.parseBoolean(valores[columna]);
                }
                fila++;
            }

            return mat;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

//    private static void imprimirMatriz(boolean[][] mat) {
//        for (boolean[] fila : mat) {
//            for (boolean valor : fila) {
//                System.out.print(valor ? "true" : "false");
//                System.out.print(",");
//            }
//            System.out.println();
//        }
//    }

    //Hacer la administración de los turnos
}

