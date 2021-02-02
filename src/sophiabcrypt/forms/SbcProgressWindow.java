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

package sophiabcrypt.forms;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sophiabcrypt.SbcEncEngine;
import sophiabcrypt.language.SbcDictionaryBase;
import sophiabcrypt.language.SbcDictionarySentence;

/**
 * This class create a new "Process Window with progress bar and labels"
 * They are used while SophiaBTool encrypt or decrypt data
 * @see SbcMainWindow
 * @see SbcEncEngine
 * @author CesarBianchi
 * @since Sep/2018
 */
public class SbcProgressWindow extends javax.swing.JFrame {
   
	//Graphical Interface objects/vars
    private javax.swing.JLabel jLabProcFilesTitle;
    private javax.swing.JLabel jLabWait;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;                

	//Core objects/vars	
	private int nQtdFiles = 0;
    private String[] aFiles = null;
    private String cOper  = new String();    
    private String cPsw = new String();
    private String cLanguage = new String();
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    
    
    /**
     * Creates new form SbcProgressWindow
     * @author CesarBianchi
     * @since Sep/2018
    */
    public SbcProgressWindow() {
        this.paintProgressWindow();
    }

    /**
     * This method "paint" the ProgressWindow Dialog Interface.
     * Used while the application is Encrypt/Decrypt selected files or directories
     * @author CesarBianchi
     * @author NetBeans 8.1 IDE
     * @since Sep/2018
     */                   
    private void paintProgressWindow() {

        jPanel1 = new javax.swing.JPanel();
        jLabProcFilesTitle = new javax.swing.JLabel();
        jLabWait = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        
        //Set the default close operation and the methods who will called when open and close dialog.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabProcFilesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabProcFilesTitle.setText("Processando arquivos...");
        jLabWait.setFont(new java.awt.Font("Lucida Grande", 0, 10));
        jLabWait.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabWait.setText("Aguarde");

        //Paint the panels
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabProcFilesTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabWait, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabProcFilesTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabWait)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
    
    /**
     * This method invoke the other methods to Encrypt or Decrypt files and
     * "refresh" (update view) the File Tree Grafic Interface
     * It's used after end of opertations (Encrypt or Decrypt)
     * @author CesarBianchi
     * @author NetBeans 8.1 IDE
     * @since Sep/2018
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        try {
            Thread t = new Thread(() -> {
                try {
                    this.transferFiles();
                } catch (Exception ex) {
                    Logger.getLogger(SbcProgressWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            t.start();
        } catch (Exception ex) {
            Logger.getLogger(SbcProgressWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                 

    /**
     * This method "refresh" (update view) the File Tree Grafic Interface
     * It's used after end of opertations (Encrypt or Decrypt)
     * @author CesarBianchi
     * @author NetBeans 8.1 IDE
     * @since Sep/2018
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  

    }                                 

    /**
     * This method is the same that of "Constructor of Class"
     * It's used for define some grafical properties and show form
     * @param Max Is the length of Progress Bar (In integer type)
     * @author CesarBianchi
     * @since Sep/2018
     */
    public void init(int Max){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setProgressBarLength(Max);
        this.setAlwaysOnTop(true);
        this.setAutoRequestFocus(true);
        this.setTranslates();
        this.setVisible(true);        
        
    }
    
    /**
     * This method sets the maximum size (length) of Progress Bar
     * @param Max Is the length of Progress Bar (In integer type)
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void setProgressBarLength(int Max){
        this.jProgressBar1.setMaximum(Max);
    }
    
    /**
     * This method repaint the "Window" while files are processed
     * It's sets new labels (Usually about file in processing), increments the progress bar and update GUI Components
     * @param cMsg1 Text using em label 1
     * @param cMsg2 Text using em label 2
     * @param nStep The number of current step of Progress Bar
     * @author CesarBianchi
     * @throws java.lang.InterruptedException Case reload GUI be error
     * @since Sep/2018
    */
    public void incProc(String cMsg1, String cMsg2, int nStep) throws InterruptedException{
        
        jLabProcFilesTitle.setText(cMsg1);
        jLabWait.setText(cMsg2);
        jProgressBar1.setValue(nStep);        

        jLabProcFilesTitle.repaint();
        jLabWait.repaint();
        jPanel1.repaint();

        jLabProcFilesTitle.updateUI();
        jLabWait.updateUI();
        jPanel1.repaint();
        
    }
    
    /**
     * This method defines the amount of files that must be processed
     * @param Qtd The amount of files
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void setQtdFiles(int Qtd){
        nQtdFiles = Qtd;
    }
    
    /**
     * This method get the amount of files that must be processed
     * @return nQtdFiles The amount of files
     * @author CesarBianchi
     * @since Sep/2018
    */
    public int getQtdFiles(){
        return nQtdFiles;
    }
    
    /**
     * This method sets a new list of files for use from other class
     * @param aF The new list of files
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void setFiles(String[] aF){
        aFiles = aF;
    }
    
    /**
     * This method get a list of files for use from other class
     * @return aFiles The new list of files
     * @author CesarBianchi
     * @since Sep/2018
    */
    public String[] getFiles(){
        return aFiles;
    }
    
    /**
     * This method sets a type of operation (ENC or DEC)
     * @param cOp The operation type
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void setOper(String cOp){
        cOper = cOp;
    }
    
    /**
     * This method get a type of operation (ENC or DEC)
     * @return cOper The operation type
     * @author CesarBianchi
     * @since Sep/2018
    */
    public String getOper(){
        return cOper;
    }
    
    /**
     * This method receives a user password and set in class property for use from other class
     * @param cPassword The user password
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void setPsw(String cPassword){
        cPsw = cPassword;
    }
    
    /**
     * This method get a user password for use from other class
     * @return cPsw The user password
     * @author CesarBianchi
     * @since Sep/2018
    */
    public String getPsw(){
        return cPsw;
    }
    
    /**
     * This method start the process (ENC or DEC) for all selected files
     * It's exec The SbcEncEngine class 
     * @author CesarBianchi
     * @throws java.lang.Exception Case file read or file create be error
     * @since Sep/2018
    */
    public void transferFiles() throws Exception{
        SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.getLanguage());
        Dictionary.setSentenceList(this.getSentences());
        String cOp = getOper();
        String[] aFl = getFiles();
        String cPassword = getPsw();
        
        //Define tamanho da regua de processamento
        this.jProgressBar1.setMaximum(getQtdFiles());
        
        //Instancia o motor de transformacao de arquivos
        SbcEncEngine SbcEngine = new SbcEncEngine();
        SbcEngine.setOper(cOp);
        SbcEngine.setFiles(aFl);
        SbcEngine.setQtdFiles(getQtdFiles());
        SbcEngine.setSbcExtension(".sbc");
        
        //Converte os arquivos
        for (int nI = 1; nI <= getQtdFiles();nI++){
            //Atualiza o rotulo
            String cMsg1 =  Dictionary.getTranslation("0022") + Integer.toString(nI) + Dictionary.getTranslation("0023") + Integer.toString(getQtdFiles());
            String cMsg2 =  aFl[nI];
            this.incProc(cMsg1,cMsg2,nI);
 
            //Converte o arquivo
            if (cOp == "ENC") {
                SbcEngine.GoCrypt(cPassword, aFl[nI]);
            }else{
                SbcEngine.GoDecrypt(cPassword, aFl[nI]);
            }
            
        }
        this.dispose();
            
    }
    
    /**
     * Set Sentences in Memory before load xml language file
     * @author CesarBianchi
     * @since October/2018
     * @param cLang String with language defined by user
     * @param Sentences ArrayOfList with all sentences loaded from xml language file
     * @version 1.03.1
     */
    public void setSentences(String cLang, ArrayList<SbcDictionarySentence> Sentences) {
        this.cLanguage = cLang;
        this.SentencesInMemory = Sentences;
    }
    
    /**
     * This method sets all words to language defined by user
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    private void setTranslates() {
        SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.getLanguage());
        Dictionary.setSentenceList(this.getSentences());
        
        this.jLabProcFilesTitle.setText(Dictionary.getTranslation("0013"));
        this.jLabWait.setText(Dictionary.getTranslation("0014"));
    }
    
    /**
     * This methods get the language defined by user
     * @author CesarBianchi
     * @since October/2018
     * @return cLanguage The language defined by user
     * @version 1.03.1
    */
    private String getLanguage(){
        return this.cLanguage;
    }
    
    /**
     * Get List of Sentences in Memory
     * @author CesarBianchi
     * @since October/2018
     * @return ArrayOfList with all sentences stored in memory
     * @version 1.03.1
     */
    private ArrayList getSentences(){
        return this.SentencesInMemory;
    }
              
}
