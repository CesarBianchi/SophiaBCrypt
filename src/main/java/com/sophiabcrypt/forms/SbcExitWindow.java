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

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sophiabcrypt.language.SbcDictionaryBase;
import com.sophiabcrypt.language.SbcDictionarySentence;

/**
 * This class is used to show a new form "Do you really want quit of program"
 * @author CesarBianchi
 * @since October/2018
 * @see SbcMainWindow
 */
public class SbcExitWindow {

    private String cLanguage = new String();
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    
    /**
     * Creates new form ExitConfirm
     * @author CesarBianchi
     * @since October/2018
     */
    public SbcExitWindow(String cLang, ArrayList<SbcDictionarySentence> Sentences) {
    	this.setSentences(cLang, Sentences);;
    }
    
    /**
     * Show the new Dialog Exit model, based in JOptionPane.showConfirmationDialog
     * @author CesarBianchi
     * @see JOptionPane
     * @since October/2018
     */
    public void openExitDialog() {
    	SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.getLanguage());
        Dictionary.setSentenceList(this.SentencesInMemory);
        String cMsg = new String(Dictionary.getTranslation("0009"));
        String cTitle = new String(Dictionary.getTranslation("0037"));
           	
    	if (JOptionPane.showConfirmDialog(null, cMsg, cTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    		System.exit(0);
    	} else {
    	    // Nothing to do! 
    	}
    }
    
    /**
     * force the exit program, without user dialog confirmation
     * @author CesarBianchi
     * @see JOptionPane
     * @since October/2018
     */                   
    private void forceExit(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
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
