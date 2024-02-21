import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main extends Thread {
    public static Buzon[][] buzones;
    public static Celda[][] celdasNotifican;
    public static Celda[][] celdasReciben;
    public static int n;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el nombre del archivo: ");

        String archivoEntrada = br.readLine();

        boolean[][] mat = leerEstadoInicial(archivoEntrada);
        n = mat.length;

        celdasNotifican = new Celda[n][n];
        celdasReciben = new Celda[n][n];
        Main.buzones = new Buzon[n][n];

        System.out.println("Ingrese el número de generaciones: ");
        int numGeneraciones = Integer.parseInt(br.readLine());
        br.close();

        for (int fila = 0; fila < n; fila++) {
            for (int columna = 0; columna < n; columna++) {
                Main.buzones[fila][columna] = new Buzon(fila+1);
            }
        }

        for (int gen = 0; gen < numGeneraciones; gen++) {

            for (int fila = 0; fila < n; fila++) {
                for (int columna = 0; columna < n; columna++) {
                    Boolean estado;
                    if (gen == 0){
                        estado = mat[fila][columna];
                    }
                    else{
                        estado = celdasReciben[fila][columna].getEstaViva();
                    }
                    celdasNotifican[fila][columna] = new Celda(fila, columna, estado, true);
                    celdasReciben[fila][columna] = new Celda(fila, columna, estado, false);
                }

            }

            if (gen == 0){
                System.out.println("Generacion 0:");
                imprimirMatriz();
            }

            for (int fila = 0; fila < n; fila++) {
                for (int columna = 0; columna < n; columna++) {
                    celdasNotifican[fila][columna].start();
                    celdasReciben[fila][columna].start();
                }
            }

            for (int fila = 0; fila < n; fila++) {
                for (int columna = 0; columna < n; columna++) {
                    try {
                        celdasNotifican[fila][columna].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        celdasReciben[fila][columna].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Generacion " + (gen + 1) + ":");
            imprimirMatriz();
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

   private static void imprimirMatriz() {
       for (Celda[] fila : celdasReciben) {
           for (Celda celda : fila) {
               System.out.print(celda.getEstaViva() ? ":)" : "x(");
               System.out.print(" ");
           }
           System.out.println();
       }
   }


}

