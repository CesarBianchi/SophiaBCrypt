package sophiabcrypt;
import java.io.*;
import javax.swing.JOptionPane;


/**
 * This class control all details about Encrypt and Decrypt operations.
 * Get all files, folders and subfolders, Count files, Control Process Bar, Set encrypted file extension
 * Control max file lenght, and others!
 * @author CesarBianchi
 * @since Sep/2018
 */
public class SbcPlanFile {
    private int nQtdFiles = 0;
    private int nQtdSubDir = 0;
    private int nFileMax = 1024;
    private String cBarra = "";
    private String aFiles[] = new String[nFileMax];
    private String aSubDir[] = new String[getMaxFiles()];
    private String cSbcExtension = new String();
    private String cBfaExtension = new String();
    private String cOpType = new String();
    private double nMaxLengthMB = 0;
  
    /**
     * 
     * This is a constructor method of class
     * The operations are:
     * 1 - Sets operation type (Encrypt or Decrypt)
     * 2 - Sets kind of The bar of path files
     * 3 - Sets what files are be process
     * 4 - Sets the final extesion of Encrypted files
     * 5 - Sets the maximum lenght of files are be process
     * @author CesarBianchi
     * @param cOperType The type of process (ENC or DEC)
     * @since Sep/2018
    */
    public void init(String cOperType){
        setOperType(cOperType);
        setBarType();
        initFileList();
        setSbcExtension(".sbc");
        setMaxTamFile(60);
    }
    
    /**
     * 
     * Sets operation type class attribute (Encrypt or Decrypt)
     * @author CesarBianchi
     * @param cOp The type of process (ENC or DEC)
     * @since Sep/2018
    */
    public void setOperType(String cOp){
        cOpType = cOp;
    }
    
    /**
     * Return the operation type class attribute (Encrypt or Decrypt)
     * @author CesarBianchi
     * @return String with description of operation type (ENC or DEC)
     * @since Sep/2018
    */
    public String getOperType(){
        return cOpType;
    }
    
    /**
     * Return array with all files to be process (full path filename)
     * @author CesarBianchi
     * @return Array with all files to be process
     * @since Sep/2018
    */
    public String[] getFiles(){
        return aFiles;
    }
    
    /**
     * Return the quantity of files to be process 
     * @author CesarBianchi
     * @return The quantity of files to be process
     * @since Sep/2018
    */
    private int getMaxFiles(){
      return nFileMax;
    }
  
    /**
     * Return the quantity of files to be process 
     * @author CesarBianchi
     * @param nMax The quantity of files to be process
     * @since Sep/2018
    */
    public void setMaxTamFile(double nMax){
        nMaxLengthMB = nMax;
    }
    
    /**
     * Return the maximum file size allowed for processing (in MB)
     * @author CesarBianchi
     * @return The maximum file size allowed
     * @since Sep/2018
    */
    private double getMaxTamFile(){
      return nMaxLengthMB;
    }
    
    /**
     * Sets kind of bar of pathfiles by system in use
     * This method valides the operation system (Windows, Linux or Mac) to provide the bar
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void setBarType(){ 
        String cOperSyst = new String();
        cOperSyst = System.getProperty("os.name").toUpperCase();
        
        if (cOperSyst.contains("WIN")) {
            cBarra = "\\";
        }else{
            cBarra = "/";
        }
    }
    
    /**
     * Return the kind of bar of pathfiles by system in use
     * @author CesarBianchi
     * @return String with bar
     * @since Sep/2018
    */
    private String getBarType(){
        return cBarra;
    }

    /**
     * Sets the quantity files to be process
     * @author CesarBianchi
     * @param nQtd Quantity of files
     * @since Sep/2018
    */
    private void setQtdFiles(int nQtd){
        nQtdFiles = nQtd;
    }

    /**
     * Return the quantity files to be process
     * @author CesarBianchi
     * @return The quantity of files
     * @since Sep/2018
    */
    public int getQtdFiles(){
        return nQtdFiles;
    }

    /**
     * Increments the quantity of files to be process in more 1 (SumOne)
     * @author CesarBianchi
     * @return The quantity of files
     * @since Sep/2018
    */
    private void incFile(){
        nQtdFiles++;
    }

     /**
     * Add a new path file to aFiles class atribute.
     * Before add, validate if file size dont exceds max length allowed. If file size exceds the max size, show
     * a user dialog alert mensage, else go ahed to process
     * @param cPath String with master directory from add files
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void addFile(String cPath){
        if (getOperType().toUpperCase() == "ENC"){        
            
            //Adiciona somente se o tamanho for de no maximo 60MB
            File file = new File(cPath);
            if (CheckFileLength(file.length()) == true) {            
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
    
     /**
     * List in console out all files added to aFiles class atribute
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void listConsoleFiles(){
        int nI = 0;
        for (nI = 0;nI <= getQtdFiles();nI++){
            System.out.println(aFiles[nI]);
        }
    }
    
    /**
     * Sets the quantity of subdirs present in master directory selected by user 
     * @param nQtd Quantity of subdirs
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void setQtdSubDir(int nQtd){
        nQtdSubDir = nQtd;
    }
    
    /**
     * Gets the quantity of subdirs present in master directory selected by user 
     * @return nQtdSubDir The Quantity of subdirs present by class
     * @author CesarBianchi
     * @since Sep/2018
    */
    public int getQtdSubDir(){
        return nQtdSubDir;
    }

    /**
     * This method sum 1 to quantity of subdirs to be processed 
     * @return nQtdSubDir The Quantity of subdirs present by class
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void incSubDir(){
        nQtdSubDir++;
    }
    
    /**
     * This method deduct 1 to quantity of subdirs to be processed 
     * @return nQtdSubDir The Quantity of subdirs present by class
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void decSubDir(){
        nQtdSubDir--;
    }
 
    /**
     * This method add 1 new path to list of paths to be processed
     * @param cPath The path to be added in list of paths
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void AddSubDir(String cPath){
        aSubDir[getQtdSubDir() + 1] = cPath;
        incSubDir();
    }
    
    /**
     * This method remove 1 path to list of paths to be processed.
     * The path to be remove is defined by "index" input param
     * @param nPos The index of the list of paths to be removed
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void removeSubDir(int nPos){
        aSubDir[nPos] = "";
        decSubDir();
    }
    
    /**
     * This method remove the last path of list paths and return your content
     * @return cReturn The last path of the list paths
     * @author CesarBianchi
     * @since Sep/2018
    */
    private String getNextSubDir(){
        String cReturn = aSubDir[getQtdSubDir()];
        removeSubDir(getQtdSubDir());
        return cReturn;
    }
    
    /**
     * List at Console Out all subdirectories presents at List of Paths
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void listConsoleSubDir(){
        int nI = 0;
        for (nI = 0;nI <= getQtdSubDir();nI++){
            System.out.println(aSubDir[nI]);
        }
    }
    
    /**
     * Sets the extension of encrypted files
     * @param cType Three Letters of extension of encrypted files
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void setSbcExtension(String cType){
        cSbcExtension = cType;
        setBfaExtension();
    }
    
    /**
     * Get the extension of encrypted files
     * @return cSbcExtension Three Letters of extension of encrypted files
     * @author CesarBianchi
     * @since Sep/2018
    */
    private String getSbcExtension(){
        return cSbcExtension;
    }
    
    /**
     * Set the extension of encrypted files (for BFA filetype)
     * @deprecated Because the filetype BFA isn't used for SophiaCrypt Tool. 
     * The type is used for Blowfish Advanced CS Tool
     * @author CesarBianchi
     * @since Sep/2018
    */
    private void setBfaExtension(){
        cBfaExtension = ".bfa";
    }
    
    /**
     * Get the extension of encrypted files (for BFA filetype)
     * @deprecated Because the filetype BFA isn't used for SophiaCrypt Tool. 
     * The type is used for Blowfish Advanced CS Tool
     * @return cBfaExtension The letters of BFA filetype
     * @author CesarBianchi
     * @since Sep/2018
    */
    private String getBfaExtension(){
        return cBfaExtension;
    }
    
    /**
     * Init the Path List and Subdir List with zero values
     * Used too for erase lists for restart process
     * @author CesarBianchi
     * @since Sep/2018
    */
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
    
    /**
     * This method can validade if path is a Directory or a file
     * @param cPath The path to be validate
     * @author CesarBianchi
     * @since Sep/2018
    */
    private boolean IsDirectory(String cPath){
        File temp = new File(cPath);
        return temp.isDirectory();
    }
    
    /**
     * This method can discovery all files inside a specific path 
     * and add to List of files. Too can be discovery subdirectoris inside a path 
     * and add to List of SubDirectories
     * @param cPath The path to be searching
     * @author CesarBianchi
     * @since Sep/2018
    */
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
    
   /**
     * This method can validate if filesize is allowed to be process
     * @param nLengthBytes The file size in bytes
     * @return The size is allowed or no
     * @author CesarBianchi
     * @since Sep/2018
    */
   private boolean CheckFileLength(double nLengthBytes){       
       double nMaxTamMB = getMaxTamFile();
       double nLengthMB = (nLengthBytes / 1024 / 1024);
       return nLengthMB <= nMaxTamMB;
   }
}
