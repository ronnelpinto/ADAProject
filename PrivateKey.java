
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class PrivateKey implements java.io.Serializable{
    
    private BigInteger d;
    private BigInteger n;
    
    public void setd(BigInteger dValue){
        this.d = dValue;
    }
    
    public void setn(BigInteger nValue){
        this.n = nValue;
    }
    
    public BigInteger getd(){
        return d;
    }
    
    public BigInteger getn(){
        return n;
    }
}
