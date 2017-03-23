/**
 * Created by natha on 3/21/2017.
 */
public class TokenRingAgent extends Thread{
    private int id;
    private boolean active;
    private int logicalId;
    private int idPrevious;
    private int idNext;

    public int receiveToken(){
        // Returns identifier for token received
        return -1;
    }

    public void sendToken(Token t){
        // Send t to successor
    }
}
