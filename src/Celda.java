
public class Celda extends Thread {
    boolean[][] mat;
    int fila;
    int columna;
    boolean estaViva;
    int n;
    int vecinos;

    public Celda(boolean[][] mat, int fila, int columna, boolean estaViva, int n, int vecinos) {
        this.mat = mat;
        this.fila = fila;
        this.columna = columna;
        this.estaViva = estaViva;
        this.n = n;
        this.vecinos = vecinos;
//        if(fila == 0){
//            this.vecinos++;
//        }
//        if(fila == n){
//            this.vecinos++;
//        }
//        if(columna == 0){
//            this.vecinos++;
//        }
//        if(columna == n){
//            this.vecinos++;
//        }
//        //todo: Creo que no hay un caso para los que tienen tres vecinos.
//        if(fila != 0 && fila != n && columna != 0 && columna != n) this.vecinos = 4;

        notifyNeighbours();
    }

    public synchronized void run(){
        try {
            //System.out.println("Ejecutando hilo en celda (" + fila + ", " + columna + ")");
            Celda celda = this;
            //while(contador < vecinos) wait
            //actualizar estado de vida()
            celda.notifyNeighbours();
            wait();
        }
        catch (InterruptedException e) {}

    }

    public void notifyNeighbours(){
        if(fila == 0){
            //notificar solo abajo
            //notifico al vecino agregandole 1 a su contador
        }
        if(fila == n){
            //notificar solo arriba
        }
        if(columna == 0){
            //notificar solo a la derecha
        }
        if(columna == n){
            //Notificar solo a la izquierda
        }
        if(fila != 0 && fila != n && columna != 0 && columna != n){
            //Notifica a todos los vecinos
        }
    }

    //si (conroller.done) cambia el estado

}
