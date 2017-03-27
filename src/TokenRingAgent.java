/**
 * Created by natha on 3/21/2017.
 */
public class TokenRingAgent extends Thread{
    private int id;
    private boolean active;
    private int logicalId;
    private int idPrevious;
    private int idNext;
    private TokenRing tokenRing;
    private Token t;

    public int getIdNext() {
        return idNext;
    }

    public TokenRingAgent(int id){
        this.id = id;
        this.t = null;
        this.logicalId = id;
        this.active = true;
        this.tokenRing = TokenRing.getInstance();
        tokenRing.addAgent(this);
        idNext = tokenRing.getNextAgentIndex(id);
        idPrevious = tokenRing.getPrevAgentIndex(id);
    }

    public boolean hasToken() {
        return(!(t==null));
    }
    public synchronized int receiveToken(Token t){
        // Returns identifier for token receive
        this.t = t;
        System.out.println(id + " received token");
        return t.getId();
    }

    public synchronized void sendToken(){
        // Send t to successor
        tokenRing.giveToken(t, tokenRing.getNextAgentIndex(id));
        t = null;
    }
}
