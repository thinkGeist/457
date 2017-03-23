/**
 * Created by natha on 3/21/2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BroadcastSystem extends Thread{
    private static BroadcastSystem broadcastSystem = new BroadcastSystem();
    private ArrayList<BroadcastAgent> agents;
    private boolean alive;
    private ServerSocket socket;
    private Socket client;
    private BufferedReader in;

    private BroadcastSystem(){
        try{
            socket = new ServerSocket(6969);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        alive = true;
        agents = new ArrayList<BroadcastAgent>();
    }

    public static BroadcastSystem getInstance(){
        return broadcastSystem;
    }

    public void addAgent(BroadcastAgent ba){
        agents.add(ba);
    }

    private void broadcast(String loc, int val){
        for(BroadcastAgent agent : agents){
            agent.receive(loc, val);
        }
    }

    public void run(){
        while(alive){
            // Listen for broadcasts
            try {
                client = socket.accept();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String msg = in.readLine();
                String[] msgArray = msg.split(":");
                int val = Integer.parseInt(msgArray[1]);
                broadcast(msgArray[0], val);
                client.close();

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
    }
}
