import java.util.ArrayList;

/**
 * Created by natha on 3/21/2017.
 */
public class TokenRing extends Thread {
    private ArrayList<TokenRingAgent> ringAgents;
    private int index;
    private Token myToken;

    public TokenRing(int index){
        this.index = index;
        myToken = new Token(index, this);
        ringAgents = TokenRingAgent.ringAgents;
    }


    public int getNextAgentIndex(int id){
        if(id++ >= ringAgents.size()-1)
            return 0;
        else
            return id++;
    }

    public int getPrevAgentIndex(int id){
        if(id-- == -1)
            return ringAgents.size()-1;
        else
            return id--;
    }

    public synchronized void giveToken(int id){
        //System.out.println("Giving token to " + id);
        myToken.setOwner(ringAgents.get(id));
        ringAgents.get(id).receiveToken(myToken);
    }
}
