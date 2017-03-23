/**
 * Created by natha on 3/21/2017.
 */
public class DSM {
    private BroadcastAgent ba;
    private LocalMemory localMemory;
    private int id;

    public DSM(int id){
        this.id = id;
        this.localMemory = new LocalMemory(id);
        this.ba = new BroadcastAgent(id, this.localMemory);
    }

    public int load(String x){
        // Return value at memory location x
        return localMemory.load(x);
    }

    public void store(String x, int v){
        // Broadcast to store
        ba.broadcast(x, v);

    }
}
