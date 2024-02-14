public class Main extends Thread {
    public static void main(String[] args) {
        //Toca hacer carga de datos.
        int N = 3;
        boolean[][] mat = {
                {true, true, true},
                {true, false, false},
                {false, true, false}
        };

        for (int fila = 0; fila < N; fila++) {
            for (int columna = 0; columna < N; columna++) {
                Celda celda = new Celda(mat, fila, columna, mat[fila][columna], N);
                Thread thread = new Thread(celda);
                thread.start();
            }
        }
    }
}
