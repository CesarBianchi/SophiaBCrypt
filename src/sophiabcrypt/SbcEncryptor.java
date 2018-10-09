package sophiabcrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class SbcEncryptor {

    private static Base64 base64 = new Base64(true);

    public byte[] encrypt(byte[] Data, String cPsw)throws Exception{
        String strkey = cPsw;
        SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
         Cipher cipher = Cipher.getInstance("Blowfish");
         cipher.init(Cipher.ENCRYPT_MODE, key);
         byte[] cReturn = cipher.doFinal(Data);
         return cReturn;
    }

    public byte[] decrypt(byte[] encrypted, String cPsw)throws Exception{
        String strkey = cPsw;               
         SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
         Cipher cipher = Cipher.getInstance("Blowfish");
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte[] decrypted = cipher.doFinal(encrypted);
         return decrypted;
    }
}