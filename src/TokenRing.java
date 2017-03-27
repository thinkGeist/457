import java.util.ArrayList;

/**
 * Created by natha on 3/21/2017.
 */
public class TokenRing extends Thread {
    private static TokenRing tokenRing = new TokenRing();
    private ArrayList<TokenRingAgent> ringAgent;

    private TokenRing(){
        ringAgent = new ArrayList<TokenRingAgent>();
    }

    public static TokenRing getInstance(){
        return tokenRing;
    }


    public void addAgent(TokenRingAgent x){
        ringAgent.add(x);
    }

    public int getNextAgentIndex(int id){
        if(id++ >= ringAgent.size()-1)
            return 0;
        else
            return id++;
    }

    public int getPrevAgentIndex(int id){
        if(id-- == -1)
            return ringAgent.size()-1;
        else
            return id--;
    }

    public synchronized void giveToken(Token t, int id){
        System.out.println("Giving token to " + id);
        ringAgent.get(id).receiveToken(t);
    }
}
