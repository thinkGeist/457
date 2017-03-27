import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by natha on 3/21/2017.
 */


public class Processor extends Thread {
    private DSM dsm;
    private static int idCounter = 0;
    private int id;
    private TokenRingAgent ringAgent;
    private static boolean multiTest;
    private static DateFormat dateFormat;


    public Processor(){
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.id = idCounter;
        multiTest = false;
        idCounter++;
        this.dsm = new DSM(id);

    }

    private boolean wait(int j) {
        if (dsm.load("turn", j) != id)
            return false;

        int[] testArray = dsm.retArray("flag");
        for (int i = 0; i < testArray.length; i++) {
            if (i == id)
                continue;
            if (testArray[i] >= j) {
                return true;

            }
        }
        return false;
    }

    private synchronized void lock(){
        for(int j=0; j< idCounter-1; j++){
            dsm.store("flag", id, j);
            dsm.store("turn", j, id);
            boolean exit = false;
            while(true){
                if(wait(j))
                    Thread.yield();
                else
                    break;
            }
        }

    }

    private synchronized void critical(){
        Date date = new Date();
        System.out.println(id+": in Critical @ " + dateFormat.format(date));
    }

    private synchronized void unlock(){
        dsm.store("flag", id, -1);
        dsm.sendToken();
    }

    public synchronized void run(){
        while(true){
            lock();
            critical();
            unlock();
        }

    }
}
