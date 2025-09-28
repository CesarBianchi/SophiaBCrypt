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
package com.sophiabcrypt.forms;

import com.thoughtworks.xstream.XStream;
import java.awt.Cursor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.sophiabcrypt.SbcToLoad;
import com.sophiabcrypt.SbcVersion;
import com.sophiabcrypt.language.SbcDictionaryBase;
import com.sophiabcrypt.language.SbcDictionarySentence;

/**
 * This class is used to user select a prefered language for SophiaBCrypt
 * Show a window interface with all sentences in all languages. The users can input your favorite language using "User Defined" option
 * @author CesarBianchi
 * @since November/2018
 * @version 1.03.2
 */
public class SbcLanguageWindow extends javax.swing.JFrame {

	//Graphical Interfaces vars/objects
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
	
    //Core vars/objects
    private String cLang = new String();
    private String cParamFileName = new String();
    private boolean fromLoad = false;
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    
    /**
     * Creates new form SbcLanguageWindow
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    public SbcLanguageWindow() {    	
    	this.paintLanguageWindowDialog();
    }

    /**
     * Set if class was instanced from Load modules
     * @param IsLoad Define if class was instanced by Load Modules
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
     */    
    public void fromLoad(boolean IsLoad){
        this.fromLoad = IsLoad;
    }
    
    /**
     * Set if class was instanced from Load modules
     * @param IsLoad Define if class was instanced by Load Modules
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
     */  
    private void paintLanguageWindowDialog() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        //Set the default Close Operation when dialog is closed by user.
        //Don't stop the application, just close the form
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Selecione o Idioma:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Portugues - Brasil (BR)", "English - United States (US)", "Spanish - Spain (ES)", "User Defined - (UD)" }));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }                     
    
    
    /**
     * This method sets the cLang attribute by according user language choice using the local method
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        if (jComboBox1.getSelectedIndex() == 0 ){
            this.setLanguage("BR");
        } else if (jComboBox1.getSelectedIndex() == 1){
            this.setLanguage("EN");
        } else if (jComboBox1.getSelectedIndex() == 2){
            this.setLanguage("ES");
        } else {
            this.setLanguage("UD");
        }
        
        if (this.fromLoad){
            try {
                this.CreateParameterFile();

                SbcToLoad SbcLoad = new SbcToLoad();    
                SbcLoad.SetParamFileName(this.getParamFileName());
                if (SbcLoad.LoadParamFile()){  
                    this.dispose();
                    SbcLoad.LoadMainWindow();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SbcLanguageWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SbcLanguageWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //Chamado de dentro do main window
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));            
            
            //Carrega mensagem de confirmacao ao usuario
            SbcDictionaryBase SbcDic = new SbcDictionaryBase();
            SbcDic.setSentenceList(this.SentencesInMemory);
            String cTitle = "";
            String cMsg = SbcDic.getTranslation("0036") + "\n" + SbcDic.getTranslation("0035");
            
            this.eraseParamFile();
            try {
                this.CreateParameterFile();
                this.setCursor(Cursor.getDefaultCursor());
                JOptionPane.showMessageDialog(rootPane,cMsg, cTitle, JOptionPane.WARNING_MESSAGE, null);
                this.dispose();
                
                SbcExitWindow exitDiag = new SbcExitWindow(this.GetLanguage(),this.SentencesInMemory);
                exitDiag.openExitDialog();
                
            } catch (IOException ex) {
                Logger.getLogger(SbcLanguageWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }                                        

    /**
     * Set Sentences in Memory before load xml language file
     * @author CesarBianchi
     * @since October/2018
     * @param Sentences ArrayOfList with all sentences loaded from xml language file
     * @version 1.03.1
     */
    public void setSentences(ArrayList<SbcDictionarySentence> Sentences) {
        this.SentencesInMemory = Sentences;
    }
    
     /**
     * This method create a new parameter file and write it
     * @return lreturn True for succesfully file created or False for fail to create parameters file
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private boolean CreateParameterFile() throws IOException{
        
        boolean lreturn = false;
        SbcVersion SbcVer = new SbcVersion();
        SbcVer.setLanguage(this.GetLanguage());
        
        XStream xstream = new XStream();
        
        
        Class<?>[] classes = new Class[] { SbcVersion.class };
        xstream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        
        xstream.alias("Parameters",SbcVersion.class);
        String cXMLParams = xstream.toXML(SbcVer);
        
        FileWriter fw = new FileWriter(this.getParamFileName(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cXMLParams);
        bw.close();
        fw.close();       
        lreturn = true;
        
        return lreturn;
    }

    /**
     * This method show the Window interface of this class
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public void LoadLanguageWindow(){
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    /**
     * This method returns the language defined by user
     * @return The language defined by user in String Type
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public String GetLanguage(){
        return cLang;
    }
    
    /**
     * This method sets the cLang attribute by according input parameter
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private void setLanguage(String Language) {
        cLang = Language;
    }
    
    /**
     * This method sets the ParamFileName
     * @param cFileName The name of the file param
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public void setParamFileName(String cFileName) {
        cParamFileName = cFileName;
    }
    
    /**
     * This method gets the ParamFileName
     * @return cParamFileName The name of the file param
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private String getParamFileName() {
        return cParamFileName;
    }
    
    /**
     * Erase the Param File from disk
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
     */ 
    private void eraseParamFile() {
        int nI = 1;
        int nTry = 5;
        int nDelay = 500;
        boolean excluiu = false;
        
        File cF = new File(this.getParamFileName());

        for (nI = 1; nI <= nTry; nI++) {
            excluiu = cF.delete();
            if (excluiu == false){
                try {
                    Thread.sleep(nDelay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SbcDictionaryBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
