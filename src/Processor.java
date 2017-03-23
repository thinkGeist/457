import java.io.PrintWriter;

/**
 * Created by natha on 3/21/2017.
 */


public class Processor extends Thread {
    private DSM dsm;
    private static int idCounter = 0;
    private int id;
    private static boolean multiTest;

    public Processor(){
        this.id = idCounter;
        multiTest = false;
        idCounter++;
        this.dsm = new DSM(id);
    }

    private synchronized void lock(){
        for(int i=0; i< idCounter-1; i++){
            dsm.store("flag", i);
            dsm.store("turn", id);
            while((dsm.load("turn") == id) && (dsm.load("flag") >= i));
        }
        multiTest = true;
        critical();
    }

    private void critical(){
        int x;
        if(id != ( x = dsm.load("flag")))
            System.out.println("PID doesn't match flag. ID: " + id + " flag: " + x);
    }

    private synchronized void unlock(){
        dsm.store("flag", -1);
        multiTest = false;
    }

    public void run(){
        while(true){
            lock();
            critical();
            unlock();
        }

    }
}
