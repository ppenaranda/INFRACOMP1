
public class Celda extends Thread {
    private int fila;
    private int columna;
    private boolean estaViva;
    private int vecinos = 0;
    private boolean notifica;

    public Celda(int fila, int columna, boolean estaViva, boolean notifica) {
        this.fila = fila;
        this.columna = columna;
        this.estaViva = estaViva;
        this.notifica = notifica;

        for (int i = fila - 1; i <= fila + 1; i++){
            for (int j = columna - 1; j <= columna + 1; j++){
                if (i < 0 || j < 0 || i >= Main.n || j >= Main.n  || (i == fila && j == columna)){
                    continue;
                }
                vecinos++;
            }
        }

    }

    public void run(){
        if (notifica){
            notificarVecinos();
        }
        else{
            cambiarEstado();
        }

    }

    public void notificarVecinos(){
        for (int i = fila - 1; i <= fila + 1; i++){
            for (int j = columna - 1; j <= columna + 1; j++){
                if (i < 0 || j < 0 || i >= Main.n  || j >= Main.n  || (i == fila && j == columna)){
                    continue;
                }
                Main.buzones[i][j].recibir(estaViva);
            }
        }
    }

    public void cambiarEstado(){
        int contador = 0;
        int contadorVivos = 0;

        while (contador < vecinos){
            Boolean estado = Main.buzones[fila][columna].retornar();
            if (estado == true){
                contadorVivos++;
            }
            contador++;
        }

        if (estaViva){
            if (contadorVivos == 0 || contadorVivos > 3){
                estaViva = false;
            }
        }
        else{
            if (contadorVivos == 3){
                estaViva = true;
            }
        }
    }

    public Boolean getEstaViva(){
        return estaViva;
    }

}
