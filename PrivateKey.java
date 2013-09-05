
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raghuveer Pai M
 */
public class PrivateKey implements java.io.Serializable{
    
    private BigInteger d;
    private BigInteger n;
    
    public void setd(BigInteger d){
        this.d = d;
    }
    
    public void setn(BigInteger n){
        this.n = n;
    }
    
    public BigInteger getd(){
        return d;
    }
    
    public BigInteger getn(){
        return n;
    }
}
