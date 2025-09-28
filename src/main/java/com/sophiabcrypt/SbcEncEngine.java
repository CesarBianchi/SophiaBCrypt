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

package com.sophiabcrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This a main engine class of SophiaBCrypt. It's used for start process encrypt and decrypt files
 * @author CesarBianchi
 * @since Oct/2018
 * @see SbcEncryptor
 * @see javax.crypto.Cipher
 */
public class SbcEncEngine {
    String cOper = new String();
    String[] aFiles = null;
    int nQtdFiles = 0;
    private String cSbcExtension = new String();
     
    /**
    * Load all full path files for Encrpyt and call Encrypt method
    * @author CesarBianchi
    * @param cPsw user defined password for encrypt file
    * @throws java.lang.Exception Cause List of Files is null or zero 
    * @since Oct/2018
    */
    public void GoCryptM(String cPsw) throws Exception{
        int nI = 1;
        String[] aListFiles = getFiles();
        
        for (nI = 1; nI<=getQtdFiles();nI++){
            GoCrypt(cPsw,aListFiles[nI]);
        }
    }
    
    /**
     * Load all full path files for decrpyt and call DeCrypt method
     * @author CesarBianchi
     * @param cPsw user defined password for decrypt file
     * @throws java.lang.Exception  Cause List of Files is null or zero 
     * @since Oct/2018
    */
    public void GoDecryptM(String cPsw) throws Exception{
        int nI = 1;              
        String[] aListFiles = getFiles();
        
        for (nI = 1; nI<=getQtdFiles();nI++){
            GoDecrypt(cPsw,aListFiles[nI]);
        }
    }
    
    /**
     * Read a binary file, create a new encrypt filename, call encrypt class, save new file and erase a primal file
     * @author CesarBianchi
     * @param cPsw user defined password for encrypt file
     * @param cPath full path of primal file
     * @throws java.lang.Exception Cause file read or file create be error
     * @since Sep/2018
    */
    public void GoCrypt(String cPsw, String cPath) throws Exception{
        
        String cNewNameFile = new String();
            
        //1 Le o arquivo de origem
        byte[] cBinFileUn =  ReadBinFile(cPath);

        //2 Confecciona nome do arquivo destino
        cNewNameFile = GetNewName(cPath,getOper());
 
        //3 converte o conteudo binario para BLOWFISH
        SbcEncryptor SbcCry = new SbcEncryptor();
        //byte[] cBinFileEn = SbcCry.encrypt(cBinFileUn,cPsw);
        byte[] cBinFileEn = SbcCry.encrypt(cBinFileUn,cPsw);
        
        //4 Cria o novo arquivo
        SetNewFile(cNewNameFile,cBinFileEn);

        //5 Exclui o arquivo origem
        EraseFile(cPath);
    }
    
    /**
     * Read a encrypted file, create a new decrypt filename, call decrypt class, save new file and erase a encrypted file
     * @author CesarBianchi
     * @param cPsw user defined password for decrypt file
     * @param cPath full path of encrypted file
     * @throws java.lang.Exception Cause file read or file create be error
     * @since Sep/2018
    */
    public void GoDecrypt(String cPsw, String cPath) throws Exception{
        int nI = 1;              
        String cNewNameFile = new String();

        //1 Le o arquivo
        byte[] cBinFileEn =  ReadBinFile(cPath);

        //2 Confecciona novo nome
        cNewNameFile = GetNewName(cPath,getOper());

        //3 Converte o conteudo blowfish para BINARIO
        SbcEncryptor SbcCry = new SbcEncryptor();
        byte[] cBinFileUn = SbcCry.decrypt(cBinFileEn,cPsw);
        
        //5 Cria o novo arquivo
        SetNewFile(cNewNameFile,cBinFileUn);
       
        //6 Excluir o arquivo origem
        EraseFile(cPath);
    }
    
    /**
    * This method return a new encrypted or decrypted filename based on input param
    * @author CesarBianchi
    * @param cPath full path of based file
    * @param cOper The type of operation: ENC equals Encrpyted file name return or DEC equals DECRYPTED file name return
    * @return cReturn The new file name at String type format
    * @since Sep/2018
    */
    private String GetNewName(String cPath, String cOper){
        String cReturn = new String();
        
        if(cOper.toUpperCase() == "ENC" ){
            cReturn = cPath + getSbcExtension();
        }else if(cOper.toUpperCase() == "DEC"){
            cReturn = cPath.replace(getSbcExtension(),"");
        }
        return cReturn;
    }
    
    /**
     * 
     * This method read a binary file and return this contend at byte format
     * @author CesarBianchi
     * @param cPath full path of binary file
     * @return sendBuf The content of input file at byte type format
     * @since Sep/2018
    */
    private byte[] ReadBinFile(String cPath){
        File file = new File(cPath);
        int len     = (int)file.length();  
        byte[] sendBuf = new byte[len];
        FileInputStream inFile  = null;
        try {
            inFile = new FileInputStream(file);         
            inFile.read(sendBuf, 0, len);  
            inFile.close();
        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
      }
        return sendBuf;
    }   
    
    /**
     * 
     * This method create a new binary file based on Name and Content input params
     * @author CesarBianchi
     * @param cName String of full path of the new file
     * @param cContent A file content at byte type format.
     * @return Nil
     * @since Sep/2018
    */
    private void SetNewFile(String cName,byte[] cContent){
        
        try {
            OutputStream output = new FileOutputStream(cName);
            output.flush();
            output.write(cContent);
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
    * This method erase a specific file using a security recursive trying
    * @author CesarBianchi
    * @param cPath String of full path of file to be erased
    * @return excluiu The result of attempting to delete the operation (in a boolean type format)
    * @since Sep/2018
    */
    private boolean EraseFile(String cPath){
        int nI = 1;
        int nTry = 5;
        int nDelay = 500;
        boolean excluiu = false;
        
        File cF = new File(cPath);

        for (nI = 1; nI <= nTry; nI++) {
            excluiu = cF.delete();
            if (excluiu == false){
                //Thread.sleep(nDelay);
            }
        }

        return excluiu;
    }
    
    /**
    * This method set the private cOper attibute of this class (ENC or DEC)
    * @author CesarBianchi
    * @param cOp String of type operation. ENC equals "Encrypt" and DEC equals "Decrypt"
    * @since Sep/2018
    */
    public void setOper(String cOp){
        cOper = cOp;
    }
    
    /**
    * This method return the private cOper attibute of this class (ENC or DEC)
    * @author CesarBianchi
    * @return cOper String of type operation. ENC equals "Encrypt" and DEC equals "Decrypt"
    * @since Sep/2018
    */
    public String getOper(){
        return cOper;
    }
    
    /**
    * This method set the private aFiles array attibute of this class with all files for processing
    * @author CesarBianchi
    * @param aF Array of String with all files to processing
    * @since Sep/2018    
    */
    public void setFiles(String[] aF){
        aFiles = aF;
    }
    
    /**
    * This method returns the private aFiles array attibute of this class with all files for processing
    * @author CesarBianchi
    * @return aFiles Array of String with all files to processing
    * @since Sep/2018
    */
    public String[] getFiles(){
        return aFiles;
    }
    
    /**
    * This method set the private nQtdFiles attibute of this class with quantity of files for processing
    * @author CesarBianchi
    * @param Qtd The quantity of files for processing (at integer type format)
    * @since Sep/2018
    */
    public void setQtdFiles(int Qtd){
        nQtdFiles = Qtd;
    }
    
    /**
    * This method returns the private nQtdFiles attibute of this class with quantity of files for processing
    * @author CesarBianchi
    * @return nQtdFiles The quantity of files for processing (at integer type format)
    * @since Sep/2018
    */
    public int getQtdFiles(){
        return nQtdFiles;
    }
    
    /**
    * This method set the private cSbcExtension attibute of this class with three letters about file type of encrypted files
    * @author CesarBianchi
    * @param cType The three characters of type encrypted files
    * @since Sep/2018
    */
    public void setSbcExtension(String cType){
        cSbcExtension = cType;
    }
    
    /**
    * This method returns the private cSbcExtension attibute of this class with three letters about file type of encrypted files
    * @author CesarBianchi
    * @return cSbcExtension The three characters of type encrypted files
    * @since Sep/2018
    */
    private String getSbcExtension(){
        return cSbcExtension;
    }
}
