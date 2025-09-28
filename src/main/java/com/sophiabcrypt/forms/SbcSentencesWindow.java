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

import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import com.sophiabcrypt.language.SbcDictionaryBase;
import com.sophiabcrypt.language.SbcDictionarySentence;

/**
 * This class is used to prepare environment for SophiaBCrypt tool
 * @author CesarBianchi
 * @since October/2018
 * @version 1.03.1
 * @see SbcDictionaryBase
 * @see SbcDictionarySentence
 * @see SbcMainWindow
 */
public class SbcSentencesWindow extends javax.swing.JFrame {
	
	//Graphical interface objects/vars
	private javax.swing.JButton jButConfirmar;
    private javax.swing.JButton jButCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

	//Core objects/vars
	private String cLanguage = new String();
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    private String cLangFileName = new String();
    
    /**
     * This constructor method
     * Provides a translation of the terms present in window
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public SbcSentencesWindow() {
        this.paintSetTranslationsWindow();
    }

    /**
     * Initialize main attributes and show window interface
     * @param cLang The language defined by user
     * @param Sentences The sentenced loaded from language file
     * @param cLangFile The name of language file
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void init(String cLang,ArrayList Sentences,String cLangFile){
        this.setSentences(Sentences);
        this.setLanguage(cLang);        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cLangFileName = cLangFile;
        
        //Set Grid Titles and records
        this.setTranslations();
        this.loadRows(); 
        
        this.show();
    }
    
    /**
     * Set Sentences in Memory before load xml language file
     * @author CesarBianchi
     * @since November/2018
     * @param Sentences ArrayOfList with all sentences loaded from xml language file
     * @version 1.03.3
     */
    private void setSentences(ArrayList Sentences) {
        this.SentencesInMemory = Sentences;
    }
    
    /**
     * This method sets the cLang attribute by according input parameter
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void setLanguage(String cLang) {
        this.cLanguage = cLang;
    }
    
    /**
     * Provide translation of the labels and gets of the window
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void setTranslations() {
        SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.cLanguage);
        Dictionary.setSentenceList(this.SentencesInMemory);        
        this.jButConfirmar.setText(Dictionary.getTranslation("0020"));
        this.jButCancel.setText(Dictionary.getTranslation("0021"));
        this.setGridTitles();
    }
    
    /**
     * Provide translation of the Grid Titles
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void setGridTitles() {
        SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.cLanguage);
        Dictionary.setSentenceList(this.SentencesInMemory);
        
        JTableHeader th = this.jTable1.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        
        TableColumn ID = tcm.getColumn(0);
        ID.setHeaderValue(Dictionary.getTranslation("0028"));
        
        TableColumn Portuguese = tcm.getColumn(1);
        Portuguese.setHeaderValue(Dictionary.getTranslation("0029"));
        
        TableColumn English = tcm.getColumn(2);
        English.setHeaderValue(Dictionary.getTranslation("0030"));
        
        TableColumn Spanish = tcm.getColumn(3);
        Spanish.setHeaderValue(Dictionary.getTranslation("0031"));
        
        TableColumn UserDefined = tcm.getColumn(4);
        UserDefined.setHeaderValue(Dictionary.getTranslation("0032"));
        
        th.repaint();
        
    }
    
    /**
     * Load all rows of grid after reed language file
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void loadRows() {
        
        SbcDictionarySentence Sentence = new SbcDictionarySentence();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        for (int nI = 0;nI<=this.SentencesInMemory.size()-1;nI++){
            Sentence = this.SentencesInMemory.get(nI);            
            Object[] row = {Sentence.getSentenceID(),Sentence.getTranslationPortuguese(),Sentence.getTranslationEnglish(),Sentence.getTranslationSpanish(),Sentence.getTranslationUserDefined()};
            model.addRow(row);    
        }
        jTable1.setModel(model);
    }
    
    /**
     * Write all user defined terms in language file present in window interface 
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void setNewSentences() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   

        //Carrega mensagem de confirmacao ao usuario
        SbcDictionaryBase SbcDic = new SbcDictionaryBase();
        SbcDic.setSentenceList(this.SentencesInMemory);
        String cTitle = SbcDic.getTranslation("0033");
        String cMsg = SbcDic.getTranslation("0034") + "\n" + SbcDic.getTranslation("0035");
        
        //Carrega os dados do grid e atualiza o arquivo de language
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        SbcDictionaryBase NewDic = new SbcDictionaryBase();
        
        ArrayList<SbcDictionarySentence> NewSentecesList = new ArrayList<SbcDictionarySentence>();
        
        for (int nI = 0;nI <= this.jTable1.getModel().getRowCount()-1; nI++ ){
            SbcDictionarySentence NewSentence = new SbcDictionarySentence();    
            NewSentence.setSentenceID((String) this.jTable1.getModel().getValueAt(nI,0));
            NewSentence.setClassOfUse("BLUBLUES");
            NewSentence.setTranslationPortuguese((String) this.jTable1.getModel().getValueAt(nI,1));
            NewSentence.setTranslationEnglish((String) this.jTable1.getModel().getValueAt(nI,2));
            NewSentence.setTranslationSpanish((String) this.jTable1.getModel().getValueAt(nI,3));
            NewSentence.setTranslationUserDefined((String) this.jTable1.getModel().getValueAt(nI,4));
            
            NewSentecesList.add(NewSentence);
        }
        
        //Cria o novo arquivo
        NewDic.setLanguageFileName(this.cLangFileName);
        NewDic.EraseFile();
        NewDic.setSentenceList(NewSentecesList);
        try {
            NewDic.MakeLanguageFile();
            this.setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(rootPane,cMsg, cTitle, JOptionPane.WARNING_MESSAGE, null);
            
            this.dispose();
            
            SbcExitWindow exitDiag = new SbcExitWindow(this.cLanguage,this.SentencesInMemory);
            exitDiag.openExitDialog();
            
        } catch (IOException ex) {
            Logger.getLogger(SbcSentencesWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SbcSentencesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Build the Translations Sentences Dialog
     * @author CesarBianchi
     * @since January/2021
     * @version 1.10.1
    */                        
    private void paintSetTranslationsWindow() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButConfirmar = new javax.swing.JButton();
        jButCancel = new javax.swing.JButton();

        //Set the default Close Operation when dialog is closed by user.
        //Don't stop the application, just close the form
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {            },
            new String [] { "ID", "Portuguese", "English", "Spanish", "User Defined" }
        	) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButConfirmar.setActionCommand("Confirmar");
        jButConfirmar.setLabel("Confirmar");
        jButConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonAction(evt);
            }
        });

        jButCancel.setLabel("Cancelar");
        jButCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonAction(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
     * close the Sentences Translation Dialog
     * @author CesarBianchi
     * @since January/2021
     * @version 1.10.1
    */
    private void cancelButtonAction(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

    /**
     * invoke the method to Set the new Sentences Translation in the XML file
     * @author CesarBianchi
     * @since January/2021
     * @version 1.10.1
    */
    private void confirmButtonAction(java.awt.event.ActionEvent evt) {                                         
        this.setNewSentences();
    }                                        
    

}

