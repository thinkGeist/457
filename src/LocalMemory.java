import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by natha on 3/21/2017.
 */
public class LocalMemory {
    private HashMap<String, Integer[]> memory;
    private int[] flag;
    private int[] turn;
    private int id;

    public LocalMemory(int id){
        memory = new HashMap<String, Integer[]>();
        flag = new int[10];
        turn = new int[10];
        Arrays.fill(flag, -1);
        Arrays.fill(turn, -1);
        this.id = id;
    }
    public int load(String loc, int index){
        // Loads value from memory location x
        //return memory.get(x);
        if(loc.equals("flag"))
            return flag[index];
        return turn[index];

    }

    public int[] retArray(String loc){
        if(loc.equals("flag"))
            return flag;
        return turn;
    }
    public void store(String loc, int index, int v){
        // Stores value v and memory location x
        //System.out.println(id+": Storing " + v + " @ " + x);
        if (loc.equals("flag"))
            flag[index] = v;
        else
            turn[index] = v;
    }

}
