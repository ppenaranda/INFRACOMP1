import java.util.ArrayList;

public class Buzon {
  private int capacidad;
  private ArrayList<Boolean> cola;

  public Buzon(int capacidad) {
    this.capacidad = capacidad;
    this.cola = new ArrayList<Boolean>();
  }

  public synchronized void recibir(Boolean estado){
    while (cola.size() == capacidad){
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    cola.add(estado);
    notifyAll();
  }

  public Boolean retornar(){
    while (cola.size() == 0){
      Thread.yield();
    }
    Boolean estado;
    synchronized(this){
      estado = cola.remove(0);
      notifyAll();
    }
    return estado;
  }
}
