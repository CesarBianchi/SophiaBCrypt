
package sophiabcrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author cesarbianchi
 */
public class SbcEncEngine {
    String cOper = new String();
    String[] aFiles = null;
    int nQtdFiles = 0;
    private String cSbcExtension = new String();
     
    public void GoCryptM(String cPsw) throws Exception{
        int nI = 1;
        String[] aListFiles = getFiles();
        
        for (nI = 1; nI<=getQtdFiles();nI++){
            GoCrypt(cPsw,aListFiles[nI]);
        }
    }
    
    public void GoDecryptM(String cPsw) throws Exception{
        int nI = 1;              
        String[] aListFiles = getFiles();
        
        for (nI = 1; nI<=getQtdFiles();nI++){
            GoDecrypt(cPsw,aListFiles[nI]);
        }
    }
    
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
    
    private String GetNewName(String cPath, String cOper){
        String cReturn = new String();
        
        if(cOper.toUpperCase() == "ENC" ){
            cReturn = cPath + getSbcExtension();
        }else if(cOper.toUpperCase() == "DEC"){
            cReturn = cPath.replace(getSbcExtension(),"");
        }
        return cReturn;
    }
    
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
    
    public void setOper(String cOp){
        cOper = cOp;
    }
    
    public String getOper(){
        return cOper;
    }
    
    public void setFiles(String[] aF){
        aFiles = aF;
    }
    
    public String[] getFiles(){
        return aFiles;
    }
    
    public void setQtdFiles(int Qtd){
        nQtdFiles = Qtd;
    }
    
    public int getQtdFiles(){
        return nQtdFiles;
    }
    
    public void setSbcExtension(String cType){
        cSbcExtension = cType;
    }
    
    private String getSbcExtension(){
        return cSbcExtension;
    }
}
