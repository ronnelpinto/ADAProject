
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raghuveer Pai M
 */
public class PublicKey implements java.io.Serializable{
    
    private BigInteger k;
    private BigInteger n;
    
    public void setk(BigInteger k){
        this.k = k;
    }
    
    public void setn(BigInteger n){
        this.n = n;
    }
    
    public BigInteger getk(){
        return k;
    }
    
    public BigInteger getn(){
        return n;
    }
}
