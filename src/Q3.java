/**
 * Created by natha on 3/24/2017.
 */
public class Q3 {

    public static void main(String[] args){
        BroadcastSystem broadcastSystem = BroadcastSystem.getInstance();
        broadcastSystem.start();
        Processor[] pArray = new Processor[10];
        TokenRing[] tokenRings = new TokenRing[10];
        for(int i=0; i <10; i++) {
            tokenRings[i] = new TokenRing(i);
            pArray[i] = new Processor();
            tokenRings[i].giveToken(0);
        }
        try {
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0; i <10; i++) {
            pArray[i].start();
        }

    }
}
