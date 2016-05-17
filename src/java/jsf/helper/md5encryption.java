/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Yi
 */
public class md5encryption {
    private String str;
    private String digest; 
    
    public md5encryption(){
        
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getDigest() {
        return digest;
    }
    
    public void encrypt() {
        byte[] hash= null; 
	
        try{
            MessageDigest digest= MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            hash = digest.digest();
        }
        
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
	digest=Base64.getEncoder().encodeToString(hash);
    }	
    
    
    
}
