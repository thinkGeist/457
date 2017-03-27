/**
 * Created by natha on 3/21/2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
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

    private synchronized void broadcast(String loc, int index, int val, int skipId){
        for(BroadcastAgent agent : agents){
            if(agent.getId() != skipId)
                agent.receive(loc, index, val);
        }
    }

    public synchronized void run(){
        while(alive){
            // Listen for broadcasts
            try {
                client = socket.accept();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String msg = in.readLine();
                String[] msgArray = msg.split(":");
                int index = Integer.parseInt(msgArray[1]);
                int val = Integer.parseInt(msgArray[2]);
                int skipId = Integer.parseInt(msgArray[3]);
                broadcast(msgArray[0], index, val, skipId);
                client.close();

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
    }
}
