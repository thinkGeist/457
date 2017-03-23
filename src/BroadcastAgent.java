/**
 * Created by natha on 3/21/2017.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BroadcastAgent extends Thread{
    private Socket socket;
    private PrintWriter os;
    private int id;
    private LocalMemory localMemory;
    public BroadcastAgent(int id, LocalMemory lm){
        this.id = id;
        this.localMemory = lm;
        BroadcastSystem broadcastSystem = BroadcastSystem.getInstance();
        broadcastSystem.addAgent(this);
    }

    public void broadcast(String message, int v){
        // Send a store
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
            socket = new Socket("localhost", 6969);
            os = new PrintWriter(socket.getOutputStream());
            String toSend = message+":"+v;
            os.print(toSend);
            os.close();
            socket.close();
        }
        catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void receive(String loc, int val){
        // Receive a store
        localMemory.store(loc, val);

    }

    public void run(){
        // Listen for incoming messages
        while(true){

        }

    }

}
