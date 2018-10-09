package sophiabcrypt;
import java.io.*;
import javax.swing.JOptionPane;


//@author cesarbianchi
public class SbcPlanFile {
    public int nQtdFiles = 0;
    public int nQtdSubDir = 0;
    private int nFileMax = 1024;
    private String cBarra = "";
    private String aFiles[] = new String[nFileMax];
    private String aSubDir[] = new String[getMaxFiles()];
    private String cSbcExtension = new String();
    private String cBfaExtension = new String();
    private String cOpType = new String();
    private double nMaxLengthMB = 0;
  
    public void init(String cOperType){
        setOperType(cOperType);
        setBarType();
        initFileList();
        setSbcExtension(".sbc");
        setMaxTamFile(60);
    }
    
    public void setOperType(String cOp){
        cOpType = cOp;
    }
    
    public String getOperType(){
        return cOpType;
    }
    
    public String[] getFiles(){
        return aFiles;
    }
    
    private int getMaxFiles(){
      return nFileMax;
    }
  
    public void setMaxTamFile(double nMax){
        nMaxLengthMB = nMax;
    }
    
    private double getMaxTamFile(){
      return nMaxLengthMB;
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
            
            //Adiciona somente se o tamanho for de no maximo 60MB
            File file = new File(cPath);
            if (CheckFileLength( file.length()) == true) {            
                //Adiciona somente se nao for um arquivo de extensao .sbc ou .bfa
                if ((cPath.contains(getSbcExtension()) == false) && (cPath.contains(getBfaExtension()) == false)) {                
                    aFiles[getQtdFiles() + 1] = cPath;
                    incFile();
                }
            }else{
                String cMsg = "O arquivo " + cPath + " excede o tamanho maximo permitido de " + Double.toString(getMaxTamFile()) + "MB";
                JOptionPane.showMessageDialog(null,cMsg,"Arquivo excede tamanho maximo permitido",JOptionPane.INFORMATION_MESSAGE);
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
    
    public void DiscoveryFiles(String cPath){
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
    
   private boolean CheckFileLength(double nLengthBytes){       
       double nMaxTamMB = getMaxTamFile();
       double nLengthMB = (nLengthBytes / 1024 / 1024);
       
       return nLengthMB <= nMaxTamMB;
   }
}
