/**
 * Created by natha on 3/21/2017.
 */
public class Token {
    private int index;
    private TokenRingAgent owner;
    private TokenRing parent;

    public Token(int id, TokenRing parent){
        this.index = index;
        this.owner = null;
        this.parent = parent;
    }

    public void passToken(){
        parent.giveToken(parent.getNextAgentIndex(owner.returnId()));
    }

    public int getIndex(){
        return index;
    }

    public void setOwner(TokenRingAgent owner){
        this.owner = owner;
    }

    public TokenRingAgent getOwner(){
        return owner;
    }
}
