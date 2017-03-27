import java.util.ArrayList;

/**
 * Created by natha on 3/21/2017.
 */
public class TokenRingAgent extends Thread{
    private int id;

    public static ArrayList<TokenRingAgent> ringAgents = new ArrayList<TokenRingAgent>();
    private ArrayList<Token> tokens;

    public int returnId() {
        return id;
    }

    public TokenRingAgent(int id){
        this.id = id;
        this.tokens = new ArrayList<Token>();
        ringAgents.add(id, this);
    }

    public boolean hasToken(int index) {
        try {
            if (tokens.get(index) != null) {
                if (tokens.get(index).getOwner() == this) {
                    return true;
                }
                return false;
            }
            return false;
        } catch(IndexOutOfBoundsException e){
            return false;
        }
    }
    public synchronized int receiveToken(Token t){
        // Returns identifier for token receive
        tokens.add(t.getIndex(), t);
        //System.out.println(id + " received token");
        return t.getIndex();
    }

    public synchronized void sendToken(){
        // Send t to successor
        for(int i=0; i < tokens.size(); i++){
            if(tokens.get(i) != null){
                tokens.get(i).passToken();
                tokens.add(i, null);
            }

        }
    }
}
