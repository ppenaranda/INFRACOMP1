public class Controller extends Thread{
    int count;
    int totCeldas;
    boolean done;

    public Controller(int count, int totCeldas){
        this.count = count;
        this.totCeldas = totCeldas;
        this.done = false;
        Thread t = new Thread();
        t.start();
    }

    @Override
    public void run(){
        while(count < totCeldas){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        done = true;

    }

}
