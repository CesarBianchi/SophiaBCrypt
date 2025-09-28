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
package com.sophiabcrypt;
import com.sophiabcrypt.forms.SbcLanguageWindow;
import java.io.File;
import java.io.IOException;

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
            this.ShowLanguageWindow();
            this.isAllowed = false;
        }else{
            this.isAllowed = true;
        }
        return this.isAllowed;
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
            this.cParameterFile = "SBC_Parameters.xml";
        }else{
            this.cParameterFile = cName;
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
        return this.cParameterFile;
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
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     * 
    */
    private void ShowLanguageWindow() {
        SbcLanguageWindow SbcLangWin = new SbcLanguageWindow();
        SbcLangWin.fromLoad(true);
        SbcLangWin.setParamFileName(this.getParameterFileName());
        SbcLangWin.LoadLanguageWindow();
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
         this.cLangInFile = cLang;
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
         return this.cLangInFile;
    }
    
}
