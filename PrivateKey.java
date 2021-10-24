
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class PrivateKey implements java.io.Serializable{
    
    private BigInteger mainKey;
    private BigInteger subKey;
    
    public void setd(BigInteger mainKeyValue){
        this.mainKey = mainKeyValue;
    }
    
    public void setn(BigInteger subKeyValue){
        this.subKey = subKeyValue;
    }
    
    public BigInteger getd(){
        return mainKey;
    }
    
    public BigInteger getn(){
        return subKey;
    }
}
