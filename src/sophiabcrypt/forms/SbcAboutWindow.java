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
import java.awt.Cursor;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sophiabcrypt.SbcVersion;
import sophiabcrypt.language.SbcDictionaryBase;
import sophiabcrypt.language.SbcDictionarySentence;
//import sophiabcrypt.images.*;

/**
 * This class is used to show a "About Program Window"
 * @author CesarBianchi
 * @since October/2018
 * @version 1.00
 * 
 */
public class SbcAboutWindow extends javax.swing.JFrame {

	//Graphical variables/objects
	//Used to paint the dialog/from
	private javax.swing.JLabel jLabAuthorTitle;
    private javax.swing.JLabel jLabAuthorName;
    private javax.swing.JLabel jLabAuthorMail;
    private javax.swing.JLabel jLabProgramName;
    private javax.swing.JLabel jLabVersion;
    private javax.swing.JLabel jLabVersionInfo;
    private javax.swing.JLabel labelIconLabel;
    private javax.swing.JLabel jLabHomeURL;
    private javax.swing.JLabel jLabCodeURL;
    private javax.swing.JLabel jLabLicensed;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    
    
	
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    private String cLanguage = new String();
            
    /**
     * Creates new form SbcAboutWindow
     * @author CesarBianchi
     * @since October/2018
     * @version 1.00
     * @throws IOException 
     */
    public SbcAboutWindow() {
    	try {
			this.paintAboutDialog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * This methods sets the language defined by user for use in translations
     * @author CesarBianchi
     * @param cLG The language defined by user
     * @since October/2018
     * @version 1.03.1
     */
    public void setLanguage(String cLG){
        this.cLanguage = cLG;
    }
            
	/**
	* Set the picture of logo, load version information and show About Window
	* @author CesarBianchi
	* @since Oct/2018
	*/
    public void init(){
        ImageIcon icon = new ImageIcon("images/logo_about_little.png");
  
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Define a versao na Dialog a partir da classe que controla a versao
        SbcVersion sbcVer = new SbcVersion();        
        this.jLabVersionInfo.setText(sbcVer.getVersion().concat(" - ").concat(sbcVer.getBuildDate()));
        this.setTranslates();
        
        this.show();
    }
    
    /**
     * This method sets all words to language defined by user
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    private void setTranslates() {
        SbcDictionaryBase DictionaryBase = new SbcDictionaryBase(this.cLanguage);
        DictionaryBase.setSentenceList(this.SentencesInMemory);
        this.jLabVersion.setText(DictionaryBase.getTranslation("0007"));
        this.jLabAuthorTitle.setText(DictionaryBase.getTranslation("0008"));
    }
    
    /**
     * Set Sentences in Memory before load xml language file
     * @author CesarBianchi
     * @since October/2018
     * @param cLang String with language defined by user
     * @param Sentences ArrayOfList with all sentences loaded from xml language file
     * @version 1.03.1
     */
    public void SetSentences(String cLang,ArrayList<SbcDictionarySentence> Sentences) {
        this.setLanguage(cLang);
        this.SentencesInMemory = Sentences;
    }
    
    
    /**
     * Build the "About" Dialog
     * @author CesarBianchi
     * @since January/2021
     * @version 1.10.1
     * @throws IOException 
    */
    private void paintAboutDialog() throws IOException {

    	String htmlBegin = new String("<html><a href=''>");
    	String htmlEnd = new String("</a></html>");
    	
        jPanel1 = new javax.swing.JPanel();
        jLabAuthorTitle = new javax.swing.JLabel();
        jLabAuthorName = new javax.swing.JLabel();
        jLabAuthorMail = new javax.swing.JLabel();
        jLabProgramName = new javax.swing.JLabel();
        jLabVersion = new javax.swing.JLabel();
        jLabVersionInfo = new javax.swing.JLabel();
        labelIconLabel  = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabHomeURL = new javax.swing.JLabel();
        jLabCodeURL = new javax.swing.JLabel();
        jLabLicensed  = new javax.swing.JLabel();
        
        //Set the default Close Operation when dialog is closed by user.
        //Don't stop the application, just close the form
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        
        //Set the operations will be executed when form is loaded
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        //Set and load SophiaBCrypt logo (Dog Icon)
        //BufferedImage img = ImageIO.read(new File("src/dog_icon.png"));
        //ImageIcon icon = new ImageIcon(img);
        //labelIconLabel = new javax.swing.JLabel(icon);
        labelIconLabel = new javax.swing.JLabel();
        
        //Set labels texts
        jLabProgramName.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        jLabProgramName.setText("SophiaBCrypt");
        jLabVersion.setText("Versao:");
        jLabVersionInfo.setText("X.XX.X");
        
        
        jLabAuthorTitle.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        jLabAuthorTitle.setText("Criado e Desenvolvido por:");
        jLabAuthorName.setText("Cesar Bianchi");
        jLabLicensed.setText("Licenciado sob GNU GPL-2.0");
        
        
        jLabAuthorMail.setText(htmlBegin.concat("cesar_bianchi@hotmail.com").concat(htmlEnd));       
        jLabAuthorMail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabAuthorMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("mailto:cesar_bianchi@hotmail.com"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    
        jLabHomeURL.setText(htmlBegin.concat("sophiacrypt.com").concat(htmlEnd));
        jLabHomeURL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabHomeURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.sophiacrypt.com"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        jLabCodeURL.setText(htmlBegin.concat("github.com/CesarBianchi/SophiaBCrypt").concat(htmlEnd));
        jLabCodeURL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabCodeURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.github.com/CesarBianchi/SophiaBCrypt"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
                
        //Paint Panels and Dialog.
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabAuthorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jLabAuthorName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jLabAuthorMail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabProgramName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabVersionInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(172, 172, 172))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabHomeURL)
                    .addComponent(jLabCodeURL)
                    .addComponent(jLabLicensed, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabProgramName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabVersion)
                    .addComponent(jLabVersionInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabLicensed)
                .addGap(18, 18, 18)
                .addComponent(jLabAuthorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabAuthorName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabAuthorMail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabHomeURL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabCodeURL)
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIconLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        
        pack();
    }                

    /**
     * Run a specific function when "About Dialog" is loaded
     * @author CesarBianchi
     * @since January/2021
     * @version 1.10.1
    */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {                                     
        // TODO add your handling code here:
    } 
    
}
