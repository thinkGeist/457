/**
 * Created by natha on 3/23/2017.
 */
public class Q1_2 {
    public static void main(String[] args){
        BroadcastSystem broadcastSystem = BroadcastSystem.getInstance();
        broadcastSystem.start();
        Processor[] pArray = new Processor[10];
        for(int i=0; i <10; i++) {
            pArray[i] = new Processor();
            pArray[i].start();
        }
    }
}
