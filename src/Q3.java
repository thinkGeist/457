/**
 * Created by natha on 3/24/2017.
 */
public class Q3 {

    public static void main(String[] args){
        BroadcastSystem broadcastSystem = BroadcastSystem.getInstance();
        broadcastSystem.start();
        Processor[] pArray = new Processor[10];
        for(int i=0; i <10; i++) {
            pArray[i] = new Processor();
            pArray[i].start();
        }
        TokenRing tr = TokenRing.getInstance();
        Token t1 = new Token(1);
        tr.giveToken(t1, 0);

    }
}
