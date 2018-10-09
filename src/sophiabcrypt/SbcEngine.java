package sophiabcrypt;
import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.apache.commons.codec.binary.Base64;

//@author cesarbianchi
public class SbcEngine {
    public int nQtdFiles = 0;
    public int nQtdSubDir = 0;
    private int nFileMax = 1024;
    private String cBarra = "";
    private String aFiles[] = new String[nFileMax];
    private String aSubDir[] = new String[getMaxFiles()];
    private String cSbcExtension = new String();
    private String cBfaExtension = new String();
    private String cOpType = new String();
  
    public void init(String cOperType){
        setOperType(cOperType);
        setBarType();
        initFileList();
        setSbcExtension(".sbc");
    }
    
    public void setOperType(String cOp){
        cOpType = cOp;
    }
    
    public String getOperType(){
        return cOpType;
    }
    
    public void GoCrypt(String cMainPath, String cPsw){
        DiscoveryFiles(cMainPath);
        try {
            prepCrypt(cPsw);
        } catch (Exception ex) {
            Logger.getLogger(SbcEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GoDecrypt(String cMainPath, String cPsw){
        DiscoveryFiles(cMainPath);
        try {
            prepDecrypt(cPsw);
        } catch (Exception ex) {
            Logger.getLogger(SbcEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getMaxFiles(){
      return nFileMax;
    }
  
    private void setBarType(){ 
        String cOperSyst = new String();
        cOperSyst = System.getProperty("os.name").toUpperCase();
        
        if (cOperSyst.contains("WIN")) {
            cBarra = "\\";
        }else{
            cBarra = "/";
        }
    }
    
    private String getBarType(){
        return cBarra;
    }

    private void setQtdFiles(int nQtd){
        nQtdFiles = nQtd;
    }
    
    public int getQtdFiles(){
        return nQtdFiles;
    }

    private void incFile(){
        nQtdFiles++;
    }

    private void addFile(String cPath){
        if (getOperType().toUpperCase() == "ENC"){        
            //Adiciona somente se nao for um arquivo de extensao .sbc ou .bfa
            if ((cPath.contains(getSbcExtension()) == false) && (cPath.contains(getBfaExtension()) == false)) {                
                aFiles[getQtdFiles() + 1] = cPath;
                incFile();
            }
        }else{
            //Adiciona somente se for um arquivo de extensao .sbc ou .bfa
            if ((cPath.contains(getSbcExtension()) == true) || (cPath.contains(getBfaExtension()) == true)) {                
                aFiles[getQtdFiles() + 1] = cPath;
                incFile();
            }
        }
    }
    
    public void listConsoleFiles(){
        int nI = 0;
        for (nI = 0;nI <= getQtdFiles();nI++){
            System.out.println(aFiles[nI]);
        }
    }
    
    private void setQtdSubDir(int nQtd){
        nQtdSubDir = nQtd;
    }
    
    public int getQtdSubDir(){
        return nQtdSubDir;
    }

    private void incSubDir(){
        nQtdSubDir++;
    }
    
    private void decSubDir(){
        nQtdSubDir--;
    }
    
    private void AddSubDir(String cPath){
        aSubDir[getQtdSubDir() + 1] = cPath;
        incSubDir();
    }
    
    private void removeSubDir(int nPos){
        aSubDir[nPos] = "";
        decSubDir();
    }
    
    private String getNextSubDir(){
        String cReturn = aSubDir[getQtdSubDir()];
        removeSubDir(getQtdSubDir());
        return cReturn;
    }
    
    
    public void listConsoleSubDir(){
        int nI = 0;
        for (nI = 0;nI <= getQtdSubDir();nI++){
            System.out.println(aSubDir[nI]);
        }
    }
    
    private void setSbcExtension(String cType){
        cSbcExtension = cType;
        setBfaExtension();
    }
    
    private String getSbcExtension(){
        return cSbcExtension;
    }
    
    private void setBfaExtension(){
        cBfaExtension = ".bfa";
    }
    
    private String getBfaExtension(){
        return cBfaExtension;
    }
    
    private void initFileList(){
        setQtdFiles(0);
        int nI;
        int nTam = getMaxFiles();
                
        //1* Seta o array de arquivos com <vazio>
        for (nI = 0; nI <= nTam-1; nI++){
            aFiles[nI] = "";
            aSubDir[nI] = "";
        }        
    }
    
    private boolean IsDirectory(String cPath){
        File temp = new File(cPath);
        return temp.isDirectory();
    }
    
    private void DiscoveryFiles(String cPath){
        int nJ = 0;
        boolean searchOk = false;
        String cPathExp = new String();
        
        cPathExp = cPath;
        while (searchOk == false){
        
            //1* Retorna o nome dos arquivos existentes dentro do diretorio
            File mainPath = new File(cPathExp);

            //Controle se selecionou arquivo ou se selecionou diretorio
            if(mainPath.isDirectory()){
                String[] files = mainPath.list();

                //3* Passa o nome dos arquivos para o array aFiles
                for (nJ = 0; nJ <= files.length-1; nJ++){                
                    
                    if (IsDirectory(cPathExp + cBarra + files[nJ])){
                        AddSubDir(cPathExp + cBarra + files[nJ]);
                    }else{                
                        addFile(cPathExp + cBarra + files[nJ]);
                    }
                }
            }else{
                addFile(cPathExp);
            }
            
            if (getQtdSubDir() <= 0){
                searchOk = true;
            }else{
                cPathExp = getNextSubDir();
            }
        }
    }
    
    private void prepCrypt(String cPsw) throws Exception{
        int nI = 1;
        String cNewNameFile = new String();
        
        //Dialog de Processamento
        SbcProgressWindow SbcProgress = new SbcProgressWindow();
        SbcProgress.init(getQtdFiles());
        Thread.sleep(2000);
        
        for (nI = 1; nI<=getQtdFiles();nI++){
            //Rotulos da Dialog de Processamento
            SbcProgress.incProc("Processando arquivo " + Integer.toString(nI) + " de " + Integer.toString(getQtdFiles()),aFiles[nI],nI);
            
            //1 Le o arquivo de origem
            byte[] cBinFileUn =  ReadBinFile(aFiles[nI]);
            
            //2 confecciona nome do arquivo destino
            cNewNameFile = GetNewName(aFiles[nI],getOperType());
            
            //3 converte o conteudo binario para BLOWFISH
            SbcEncryptor SbcCry = new SbcEncryptor();
            byte[] cBinFileEn = SbcCry.encrypt(cBinFileUn,cPsw);
            
            //4 Cria o novo arquivo
            SetNewFile(cNewNameFile,cBinFileEn);
            
            //5 Exclui o arquivo origem
            EraseFile(aFiles[nI]);
        }
        SbcProgress.dispose();
    }
    
    private void prepDecrypt(String cPsw) throws Exception{
        int nI = 1;              
        String cNewNameFile = new String();
        
        //Dialog de Processamento
        SbcProgressWindow SbcProgress = new SbcProgressWindow();
        SbcProgress.init(getQtdFiles());
        Thread.sleep(2000);
                
        for (nI = 1; nI<=getQtdFiles();nI++){
            //Rotulos da Dialog de Processamento
            SbcProgress.incProc("Processando arquivo " + Integer.toString(nI) + " de " + Integer.toString(getQtdFiles()),aFiles[nI],nI);
            
            //Le o arquivo
            byte[] cBinFileEn =  ReadBinFile(aFiles[nI]);
            
            //confecciona novo nome
            cNewNameFile = GetNewName(aFiles[nI],getOperType());
            
            //converte o conteudo blowfish para BINARIO
            SbcEncryptor SbcCry = new SbcEncryptor();
            byte[] cBinFileUn = SbcCry.decrypt(cBinFileEn,cPsw);
            
            //Cria o novo arquivo
            SetNewFile(cNewNameFile,cBinFileUn);
            
            //Excluir o arquivo origem
            EraseFile(aFiles[nI]);
        }
        SbcProgress.dispose();
    }
    
    private String GetNewName(String cPath, String cOper){
        String cReturn = new String();
        
        if(cOper.toUpperCase() == "ENC" ){
            cReturn = cPath + getSbcExtension();
        }else if(cOper.toUpperCase() == "DEC"){
            cReturn = cPath.replace(getSbcExtension(),"");
            cReturn = cReturn.replace(getBfaExtension(),"");
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
 
}
