/*
 * Copyright (C) 2018 CesarBianchi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package sophiabcrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * This class has two simple methods: encrypt and decrypt.
 * They are used for process any kind file, and both used a Cipher Generic Class. Please, don't forget read about Cipher Generic Class on
 * https://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#BlowKeyEx and
 * https://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA
 * Cipher Class is property of Oracle Inc. - All Rights Reserved.
 * @see javax.crypto.Cipher
 * @author CesarBianchi
 * @since Sep/2018
 */
public class SbcEncryptor {

    private static Base64 base64 = new Base64(true);

    /**
     * 
     * This method takes a given binary and transforms into encrypted data in blowfish, using a personal key
     * More questions about this feature, please, read the oficial document:
     * https://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#BlowKeyEx
     * @author CesarBianchi
     * @param Data a binary content data
     * @param cPsw a user personal key
     * @return cReturn The Data inputed after encrypted in Blowfish mode
     * @throws java.lang.Exception Cause Cipher Class be error
     * @since Sep/2018
    */
    public byte[] encrypt(byte[] Data, String cPsw)throws Exception{
        String strkey = cPsw;
        SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
         Cipher cipher = Cipher.getInstance("Blowfish");
         cipher.init(Cipher.ENCRYPT_MODE, key);
         byte[] cReturn = cipher.doFinal(Data);
         return cReturn;
    }

    /**
     * 
     * This method takes a given encrypt data in blowfish and transforms into commom binary data, using a 
     * personal key for decrypt process
     * More questions about this feature, please, read the oficial document:
     * https://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#BlowKeyEx
     * @author CesarBianchi
     * @param encrypted a encrypetd content data
     * @param cPsw a user personal key
     * @return decrypted The Data inputed after decrypted process
     * @throws java.lang.Exception Cause Cipher Class be error
     * @since Sep/2018
    */
    public byte[] decrypt(byte[] encrypted, String cPsw)throws Exception{
        String strkey = cPsw;               
         SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
         Cipher cipher = Cipher.getInstance("Blowfish");
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte[] decrypted = cipher.doFinal(encrypted);
         return decrypted;
    }
}