import java.util.HashMap;
import java.util.Map;

/**
 * Created by natha on 3/21/2017.
 */
public class LocalMemory {
    private HashMap<String, Integer> memory;
    private int id;

    public LocalMemory(int id){
        memory = new HashMap<String, Integer>();
        this.id = id;
    }
    public int load(String x){
        // Loads value from memory location x
        return memory.get(x);

    }

    public void store(String x, int v){
        // Stores value v and memory location x
        //System.out.println(id+": Storing " + v + " @ " + x);
        memory.put(x, v);
    }

}
