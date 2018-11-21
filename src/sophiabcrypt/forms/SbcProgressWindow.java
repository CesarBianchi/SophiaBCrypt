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
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @author CesarBianchi
     * @author NetBeans 8.1 IDE
     * @since Sep/2018
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Processando arquivos...");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Aguarde");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
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
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Thread t = new Thread(() -> {
                try {
                    transferFiles();
                } catch (Exception ex) {
                    Logger.getLogger(SbcProgressWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            t.start();
        } catch (Exception ex) {
            Logger.getLogger(SbcProgressWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * This method "refresh" (update view) the File Tree Grafic Interface
     * It's used after end of opertations (Encrypt or Decrypt)
     * @author CesarBianchi
     * @author NetBeans 8.1 IDE
     * @since Sep/2018
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SbcProgressWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SbcProgressWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SbcProgressWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SbcProgressWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SbcProgressWindow().setVisible(true);
            }
        });
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
        
        jLabel1.setText(cMsg1);
        jLabel2.setText(cMsg2);
        jProgressBar1.setValue(nStep);        

        jLabel1.repaint();
        jLabel2.repaint();
        jPanel1.repaint();

        jLabel1.updateUI();
        jLabel2.updateUI();
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
        
        this.jLabel1.setText(Dictionary.getTranslation("0013"));
        this.jLabel2.setText(Dictionary.getTranslation("0014"));
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
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
