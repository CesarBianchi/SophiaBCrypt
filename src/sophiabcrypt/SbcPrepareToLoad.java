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
import java.io.File;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is used to prepare environment for SophiaBCrypt tool
 * @author CesarBianchi
 * @since October/2018
 * @version 1.03.1
 * 
 */
public class SbcPrepareToLoad {
    
    private boolean isAllowed = false;
    private String cParameterFile = "";
    private String cLangInFile = new String();
    
    /**
     * This method can validate all pre-requisites are allowed to load SophiaBCrypt without errors
     * 1 - Sets the parameter filename
     * 2 - Check the existing of the parameter file;
     * 3 - Check the existing of the language parameter on parameter file;
     * @return isAllowed True if application to be run
     * @author CesarBianchi
     * @throws java.io.IOException Case File parameter don't be created
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public boolean CanBeLoad() throws IOException{        
        this.SetParameterFileName("");
        
        //1 - Se o arquivo nao existe, entao exibe interface para selecionar o Idioma e cria o arquivo.
        if (this.CheckParameterFile() == false){
            String cLang = new String();
            cLang = this.ShowLanguageWindow();
            isAllowed = this.CreateParameterFile(cLang);
        }else{
            isAllowed = true;
        }
        return isAllowed;
    }
    
    /**
     * Sets the parameter filename
     * @param cName The Parameters Filename
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public void SetParameterFileName(String cName){
        if (cName.equals("")){
            cParameterFile = "SBC_Parameters.xml";
        }else{
            cParameterFile = cName;
        }
    }
    
    /**
     * Get the parameter filename
     * @return cParameterFile The Parameters Filename
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public String getParameterFileName(){
        return cParameterFile;
    }
        
    /**
     * This method check the existing of the parameter file and load it
     * @return lexistFile True for file load succesfully or False for fail to load
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private boolean CheckParameterFile(){
        boolean lexistFile = false;
        
        File paramFile = new File(this.getParameterFileName());
        if(paramFile.exists()){
            lexistFile = true;
        }else{
            lexistFile = false;
        }
        return lexistFile;
    }

    /**
     * Load a new "Select Language Interface" for user select your favourite language
     * @return cLang Two letters to represents the language choice
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private String ShowLanguageWindow() {
        String cLang = new String();
        SbcLanguageWindow SbcLangWin = new SbcLanguageWindow();
        SbcLangWin.LoadLanguageWindow();
        cLang = SbcLangWin.GetLanguage();
        return cLang;
    }
        
    /**
     * This method create a new parameter file and write it
     * @return lreturn True for succesfully file created or False for fail to create parameters file
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private boolean CreateParameterFile(String cLang) throws IOException{
        
        boolean lreturn = false;
        SbcVersion SbcVer = new SbcVersion();
        SbcVer.setLanguage(cLang);
        
        XStream xstream = new XStream();
        xstream.alias("Parameters",SbcVersion.class);
        String cXMLParams = xstream.toXML(SbcVer);
        
        FileWriter fw = new FileWriter(this.getParameterFileName(), false);
        BufferedWriter bw = new BufferedWriter( fw );
        bw.write(cXMLParams);
        bw.close();
        fw.close();
        lreturn = true;
        
        return lreturn;
    }

    
    /**
     * This method load all parameters by file
     * @return lReturn True if parameters did are loaded
     * @author CesarBianchi
     * @throws java.io.IOException Case XML file don't exists
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public boolean LoadParametersByFile() throws IOException{
        boolean lreturn = false;
        
        //String cFileContent = new String();
        //cFileContent = this.ReadParameterFile();
        File cFileXML = new File(this.getParameterFileName());
        XStream xstream = new XStream();
        //xstream.fromXML(cFileXML);      
        //this.setLangInFile("BR");
        lreturn = true;
        
        return lreturn;
    }
    
    /**
     * This method reads a parameter file and return in string format
     * @return  cFileContent The content of file parameter
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private String ReadParameterFile() throws IOException {
        String cFileContent = new String();
        String cFileName = new String();
        cFileName = this.getParameterFileName();
       
        FileReader arq = new FileReader(cFileName);
        BufferedReader lerArq = new BufferedReader(arq);
 
        String linha = lerArq.readLine(); 
        cFileContent = linha;
        while (linha != null) {
            linha = lerArq.readLine();
            cFileContent = cFileContent + linha;
        } 
        arq.close();
       
        return cFileContent;
    }

    /**
     * This method set in class attribute the language define in Parameter File
     * @param cLang The Language reading of Parameter File
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private void setLangInFile(String cLang) {
         cLangInFile = cLang;
    }
    
    /**
     * This method get the class attribute of language define in Parameter File
     * @return cLangInFile The Language reading of Parameter File
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    public String getLanguage() {
         return cLangInFile;
    }
    
}
