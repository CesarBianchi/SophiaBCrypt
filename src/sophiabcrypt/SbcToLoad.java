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
package sophiabcrypt;
import sophiabcrypt.forms.SbcMainWindow;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import sophiabcrypt.language.SbcDictionaryBase;
import sophiabcrypt.language.SbcDictionarySentence;

/**
 * This Class Load Parameters from parameters file and exec the main program
 * @see SbcMainWindow
 * @see SbcPswControl
 * @see SbcPlanFile
 * @see SbcEncEngine
 * @see SbcEncryptor
 * @author CesarBianchi
 * @since October/2018
*/
public class SbcToLoad {
    
    private String cSentenceFile = new String("LanguageFile.xml");
    private String cParamFileName = new String();
    private String cLanguage = new String();
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    
    /**
     * This method load all parameters by file
     * @return lReturn True if parameters did are loaded
     * @author CesarBianchi
     * @throws java.lang.InterruptedException Case isn't possible read param file
     * @since October/2018
     * @version 1.03.1
     * 
    */    
    public boolean LoadParamFile() throws InterruptedException{
        boolean lReturn = false;
        int nMaxTry = 5;
        int nTrying = 1;
        
        
        for (nTrying = 1;nTrying<=nMaxTry;nTrying++){
            try {

                BufferedReader input = new BufferedReader(new FileReader(this.GetParamFileName()));
                
                XStream xStream = new XStream();
                
                Class<?>[] classes = new Class[] {SbcVersion.class};
                xStream.setupDefaultSecurity(xStream);
                xStream.allowTypes(classes);
                
                xStream.alias("Parameters", SbcVersion.class);
                SbcVersion SbcV = (SbcVersion) xStream.fromXML(input);
                input.close();            
                this.setLanguage(SbcV.getLanguage());
                lReturn = true;
                break;
            } catch (IOException ex) {
                Thread.sleep(2000);
            }
        }
        return lReturn;
    }
    
    /**
     * This method load main window before load parameter file and set main attributes
     * @author CesarBianchi
     * @throws java.io.IOException Case not possible read or write language file
     * @throws java.lang.InterruptedException Case not possible read or write language file
     * @since October/2018
     * @version 1.03.1
    */
    public void LoadMainWindow() throws IOException, InterruptedException{        
        
       File LangFile = new File(this.cSentenceFile);
       SbcDictionaryBase NewDictionary = new SbcDictionaryBase();
       
       if(LangFile.exists() == false){
           NewDictionary.CreateFileByDefault(cSentenceFile);
       }else{
           NewDictionary.UpdateSentences(this.cSentenceFile);
           NewDictionary.LoadLanguage(this.cSentenceFile, this.getLanguage());
       }
       SentencesInMemory = (ArrayList<SbcDictionarySentence>) NewDictionary.getSentenceList();
       
       //Show Main Dialog
       SbcMainWindow main = new SbcMainWindow();
       main.setLocationRelativeTo(null);
       main.setResizable(false);
       main.setLanguageFileName(this.cSentenceFile);
       main.setSentences(this.getLanguage(),this.SentencesInMemory);       
       main.setParamFileName(this.cParamFileName);
       main.init();
    }

    /**
     * This method sets the name of the param file
     * @param cFLName The name of the param file
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public void SetParamFileName(String cFLName) {
        this.cParamFileName = cFLName;
    }
    
    /**
     * This method gets the name of the param file
     * @author CesarBianchi
     * @return The name of the param file
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public String GetParamFileName() {
        return this.cParamFileName;
    }

    /**
     * This method sets the language class attribute
     * @param br The language defined in  param file
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private void setLanguage(String br) {
        this.cLanguage = br;
    }
    
    /**
     * This method gets the language define in param file
     * @author CesarBianchi
     * @return The name of the param file
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private String getLanguage() {
        return this.cLanguage;
    }
}


