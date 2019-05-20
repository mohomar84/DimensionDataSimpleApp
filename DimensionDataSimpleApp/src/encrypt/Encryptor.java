package encrypt;


import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import util.ConstantsUtil;

public class Encryptor {
   
    public  static  String  encrypt(String txt){
        BasicTextEncryptor  textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(ConstantsUtil.ENCRYPT_PASSWORD);
     return textEncryptor.encrypt(txt);   
    }
    
    public static String decryptText(String txt) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(ConstantsUtil.ENCRYPT_PASSWORD);
        String decryptProperty = textEncryptor.decrypt(txt);
        return decryptProperty;
    }
    
}