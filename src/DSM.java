/**
 * Created by natha on 3/21/2017.
 */
public class DSM {
    private BroadcastAgent ba;
    private LocalMemory localMemory;
    private TokenRingAgent ringAgent;
    private int id;

    public DSM(int id){
        this.id = id;
        this.localMemory = new LocalMemory(id);
        this.ba = new BroadcastAgent(id, this.localMemory);
        this.ringAgent = new TokenRingAgent(id);
    }

    public synchronized int[] retArray(String loc){
        return localMemory.retArray(loc);
    }
    public synchronized int load(String loc, int index){
        // Return value at memory location x
        return localMemory.load(loc, index);
    }

    public synchronized void sendToken(){
        ringAgent.sendToken();
    }

    public synchronized void store(String loc, int index, int v){
        // Store to localmemory
        if(loc.equals("turn")) {
            while (!ringAgent.hasToken(index)) {
                Thread.yield();
            }
            System.out.println(id + " storing " + loc);
        }

        localMemory.store(loc, index, v);
        // Broadcast to store
        ba.broadcast(loc, index, v, id);

    }
}
