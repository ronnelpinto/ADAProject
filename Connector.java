
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raghuveer Pai M
 */
public class Connector {
    
    public static void generateKeys(String keyloc) throws IOException{
        int bitLength = 1024;
        int sBitLength = 256;
        Random rGen = new Random();
        BigInteger p, q, n, phip, phiq, phin, k, d;
        p = BigInteger.probablePrime(bitLength, rGen);
        do{
            q = BigInteger.probablePrime(bitLength, rGen);
        }
        while(q.equals(p));
        n = p.multiply(q);
        phip = p.subtract(BigInteger.ONE);
        phiq = q.subtract(BigInteger.ONE);
        phin = phip.multiply(phiq);
        do{
            k = BigInteger.probablePrime(sBitLength, rGen);
        }
        while(!k.gcd(phin).equals(BigInteger.ONE));
        d = k.modInverse(phin);
        PublicKey pukey = new PublicKey();
        pukey.setk(k);
        pukey.setn(n);
        PrivateKey pikey = new PrivateKey();
        pikey.setd(d);
        pikey.setn(n);
        java.util.Date currentdate = new Date();
        String date = currentdate.toString();
        String pukeyloc, pikeyloc;
        if(keyloc.endsWith("\\"))
            pukeyloc = keyloc+date+".pukey";
        else
            pukeyloc = keyloc+"\\"+date+".pukey";
        pukeyloc = pukeyloc.replace(":", "-");
        pukeyloc = pukeyloc.replaceFirst("-", ":");
        File pukeyfile = new File(pukeyloc);
        pukeyfile.createNewFile();
        ObjectOutputStream writer;
        writer = new ObjectOutputStream(new FileOutputStream(pukeyfile));
        writer.writeObject(pukey);
        writer.close();
        if(keyloc.endsWith("\\"))
            pikeyloc = keyloc+date+".pikey";
        else
            pikeyloc = keyloc+"\\"+date+".pikey";
        pikeyloc = pikeyloc.replace(":", "-");
        pikeyloc = pikeyloc.replaceFirst("-", ":");
        File pikeyfile = new File(pikeyloc);
        pikeyfile.createNewFile();
        writer = new ObjectOutputStream(new FileOutputStream(pikeyfile));
        writer.writeObject(pikey);
        writer.close();
    }
    
    public static void encrypt(String pukeyloc, String destination, String msg) throws FileNotFoundException, IOException, ClassNotFoundException{
        File pukeyfile = new File(pukeyloc);
        ObjectInputStream reader;
        reader = new ObjectInputStream(new FileInputStream(pukeyfile));
        PublicKey pukey = (PublicKey)reader.readObject();
        byte[] intmsg = msg.getBytes();
        Byte[] intmsg1 = new Byte[intmsg.length];
        for( int i=0;i<intmsg.length;i++){
            intmsg1[i] = intmsg[i];
        }
        BigInteger[] cipherText = new BigInteger[intmsg.length];
        for(int i = 0; i < intmsg1.length; i++){
            cipherText[i] = new BigInteger(intmsg1[i].toString()).modPow(pukey.getk(), pukey.getn());
        }
        java.util.Date currentdate = new Date();
        String date = currentdate.toString();
        if(destination.endsWith("\\"))
            destination = destination+date+".rgvr";
        else
            destination = destination+"\\"+date+".rgvr";
        destination = destination.replace(":", "-");
        destination = destination.replaceFirst("-", ":");
        File encrypted = new File(destination);
        encrypted.createNewFile();
        ObjectOutputStream writer;
        writer = new ObjectOutputStream(new FileOutputStream(encrypted));
        writer.writeObject(cipherText);
        writer.close();
    }
    
    public static String decrypt(String cipherloc,String pikeyloc) throws java.lang.NumberFormatException,ClassNotFoundException, IOException{
        File pikeyfile = new File(pikeyloc);
        ObjectInputStream reader;
        reader = new ObjectInputStream(new FileInputStream(pikeyfile));
        PrivateKey pikey = (PrivateKey)reader.readObject();
        reader = new ObjectInputStream(new FileInputStream(cipherloc));
        BigInteger[] cipherText = (BigInteger[])reader.readObject();
        BigInteger[] decrypted = new BigInteger[cipherText.length];
        byte[] bytedecrypted = new byte[decrypted.length];
        for(int i = 0; i < cipherText.length;i++){
            decrypted[i] = cipherText[i].modPow(pikey.getd(), pikey.getn());
        }
        for(int i = 0; i < decrypted.length;i++){
            bytedecrypted[i] = Byte.parseByte(decrypted[i].toString());
        }
        String msg = new String(bytedecrypted);
        return msg;
    }
}
