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
package sophiabcrypt.language;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is used to create "LanguageFile.xml" by default program sentences.
 * If LanguageFile.xml do not exists, it's created, else, the file is updated
 * @author CesarBianchi
 * @since October/2018
 * @version 1.03.1
 * @see SbcDictionarySentence
 * @see SbcDictionaryTranslations
 */
public class SbcDictionaryBase {
    private ArrayList<SbcDictionarySentence> AllSentences = new ArrayList<SbcDictionarySentence>();
    private String cLanguageFile = new String();
    private String cLang = new String();
    private String cMasterNode = "Sentence";

    /**
     * This method is the same that of "Constructor of Class"
     * @author CesarBianchi
     * @since Sep/2018
     */
    public SbcDictionaryBase() {
        /*NOTHING*/
    }
    
    /**
     * This method is the same that of "Constructor of Class"
     * Sets the Language defined by user in Language attribute
     * @param cDefinedLang String with language defined by user
     * @author CesarBianchi
     * @since Sep/2018
     */
    public SbcDictionaryBase(String cDefinedLang) {
        this.setLanguage(cDefinedLang);
    }
    
    /**
     * Sets the language file name by default name, load default translations and make a new LanguageFile.xml
     * @author CesarBianchi
     * @param cLanguageFile The name of the xml file language
     * @throws java.io.IOException Case not possible write languagefile
     * @throws java.lang.InterruptedException Case not possible write languagefile
     * @since October/2018
     * @version 1.03.1
    */ 
    public void CreateFileByDefault(String cLanguageFile) throws IOException, InterruptedException{
        this.setLanguageFileName(cLanguageFile);        
        this.LoadDefaultBase();        
        this.MakeLanguageFile();
    }
    
    /**
     * Sets the all default sentences and translations to make the LanguageFile.xml
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
    */ 
    public void LoadDefaultBase() {
        /*Language Default Definitions*/
        this.AddNewSentence("0001","SbcMainWindow"      ,"Arquivo"              ,"File"                     ,"Ficheiro"                 ,"UNKNOW");
        this.AddNewSentence("0002","SbcMainWindow"      ,"Criptografar"         ,"Encrypt"                  ,"Cifrar"                   ,"UNKNOW");
        this.AddNewSentence("0003","SbcMainWindow"      ,"Descriptografar"      ,"Decrypt"                  ,"Desencriptar"             ,"UNKNOW");
        this.AddNewSentence("0004","SbcMainWindow"      ,"Sair"                 ,"Exit"                     ,"Salida"                   ,"UNKNOW");
        this.AddNewSentence("0005","SbcMainWindow"      ,"Ajuda"                ,"Help"                     ,"Ayuda"                    ,"UNKNOW");
        this.AddNewSentence("0006","SbcMainWindow"      ,"Sobre"                ,"About"                    ,"Acerca"                   ,"UNKNOW");        
        this.AddNewSentence("0007","SbcAboutWindow"     ,"Versão"               ,"Version"                  ,"Versión"                  ,"UNKNOW");
        this.AddNewSentence("0008","SbcAboutWindow"     ,"Desenvolvido por:"    ,"Developed by:"            ,"Desarrollado por:"        ,"UNKNOW");        
        this.AddNewSentence("0009","SbcExitWindow"      ,"Voce deseja sair ?"   ,"You want to leave ?"      ,"Quieres salir ?"          ,"UNKNOW");
        this.AddNewSentence("0010","SbcExitWindow"      ,"Sim"                  ,"Yes"                      ,"Sí"                       ,"UNKNOW");
        this.AddNewSentence("0011","SbcExitWindow"      ,"Não"                  ,"No"                       ,"No"                       ,"UNKNOW");        
        this.AddNewSentence("0012","SbcLanguageWindow"  ,"Selecione o Idioma"   ,"Select language"          ,"Seleccione el idioma"     ,"UNKNOW");        
        this.AddNewSentence("0013","SbcProgressWindow"  ,"Processando Arquivos" ,"Processing files"         ,"Procesando ficheiros"     ,"UNKNOW");
        this.AddNewSentence("0014","SbcProgressWindow"  ,"Aguarde"              ,"Wait"                     ,"Espere"                   ,"UNKNOW");
        this.AddNewSentence("0015","SbcPswWindow"       ,"Deseja Criptografar o arquivo selecionado ?"                      ,"Do you want to Encrypt the selected files ?"                  ,"Desea cifrar los archivos seleccionados ?"                            ,"UNKNOW");
        this.AddNewSentence("0016","SbcPswWindow"       ,"ATENÇÃO: Memorize a sua senha para restauração futura."           ,"WARNING: Memorize your password for future decrypt."          ,"ADVERTENCIA: Memorice su contraseña para descifrar en el futuro."     ,"UNKNOW");
        this.AddNewSentence("0017","SbcPswWindow"       ,"Este arquivo só podera ser restaurado utilizando a mesma senha."  ,"This file can only be decrypetd using the same password."     ,"Este archivo solo puede ser descifrado usando la misma contraseña."   ,"UNKNOW");
        this.AddNewSentence("0018","SbcPswWindow"       ,"Senha:"               ,"Password"                 ,"Contraseña"                ,"UNKNOW");
        this.AddNewSentence("0019","SbcPswWindow"       ,"Confirme a senha:"    ,"Confirm Password"         ,"Confirmar contraseña"      ,"UNKNOW");
        this.AddNewSentence("0020","SbcPswWindow"       ,"Confirmar"            ,"Confirm"                  ,"Confirmar"                 ,"UNKNOW");
        this.AddNewSentence("0021","SbcPswWindow"       ,"Cancelar"             ,"Cancel"                   ,"Cancelar"                  ,"UNKNOW");        
        this.AddNewSentence("0022","SbcProgressWindow"  ,"Convertendo arquivo " ,"Converting file "         ,"Convertir ficheiro "      ,"UNKNOW");
        this.AddNewSentence("0023","SbcProgressWindow"  ," de "                 ," of "                     ," de "                     ,"UNKNOW");        
        this.AddNewSentence("0024","SbcPswWindow"       ,"Deseja descriptografar o arquivo selecionado ?"   ,"Do you want to decrypt the selected file ?"                     ,"Desea descifrar el archivo seleccionado "                           ,"UNKNOW");
        
        //this.AddNewSentence("cID","className","PT","EN","ES","UD");
    }
    
    /**
     * Received by input params all sentence group data and call the method for add group to sentence List
     * @param cID The Sentence ID
     * @param className Class name where the sentence is used
     * @param PT The translation of sentence in Portuguese Language
     * @param EN The translation of sentence in English Language
     * @param ES The translation of sentence in Spanish Language
     * @param US The translation of sentence in User Defined Language
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
    */ 
    private void AddNewSentence(String cID, String className, String PT, String EN, String ES, String UD){
        SbcDictionarySentence Sentence = new SbcDictionarySentence();
        
        Sentence.setSentenceID(cID);
        Sentence.setClassOfUse(className);
        Sentence.setTranslationPortuguese(PT);
        Sentence.setTranslationEnglish(EN);
        Sentence.setTranslationSpanish(ES);
        Sentence.setTranslationUserDefined(UD);
        this.AddSentenceToList(Sentence);
    }

    /**
     * Add a new sentence object to Sentence List
     * @param Sentence The Sentence object with all data
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
    */
    private void AddSentenceToList(SbcDictionarySentence Sentence) {
        this.AllSentences.add(Sentence);
    }
    
    /**
     * Create a new LanguageFile.xml from Sentence Object List
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
    */ 
    public void MakeLanguageFile() throws IOException, InterruptedException{
        XStream xstream = new XStream();
        
        Class<?>[] classes = new Class[] { SbcDictionarySentence.class };
        xstream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        
        xstream.alias(this.cMasterNode,SbcDictionarySentence.class);
        String cXMLParams = xstream.toXML(this.getSentenceList());
        
        try (FileWriter fw = new FileWriter(this.getLanguageFileName(), false)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cXMLParams);
            bw.close();
        }
    }

    /**
     * These method can be use for only read the file and load de sentences in memory for future use
     * @author CesarBianchi
     * @param cLanguageFile The name of the xml file language
     * @param cLg The language choiced by user
     * @throws java.io.IOException Case not possible write languagefile
     * @throws java.lang.InterruptedException Case not possible write languagefile
     * @since October/2018
     * @version 1.03.1
    */ 
    public void LoadLanguage(String cLanguageFile, String cLg) throws IOException, InterruptedException{
        this.setLanguage(cLg);
        this.setLanguageFileName(cLanguageFile);        
        this.LoadLanguageSentences();
    }
    
    /**
     * Load a LanguageFile.xml to Memory for future use
     * @author CesarBianchi
     * @throws java.io.FileNotFoundException Case Language File doesn't exist
     * @throws java.io.IOException Case not possible write languagefile
     * @throws java.lang.InterruptedException Case not possible write languagefile
     * @since October/2018
     * @version 1.03.1
    */ 
    public void LoadLanguageSentences() throws FileNotFoundException, IOException, InterruptedException{
        ArrayList<SbcDictionarySentence> SentecesLoaded = new ArrayList<SbcDictionarySentence>();
        int nMaxTry = 5;
        
        for (int nTry = 1;nTry<=nMaxTry;nTry++){
            try (BufferedReader input = new BufferedReader(new FileReader(this.getLanguageFileName()))) {
                XStream xStream = new XStream();
                Class<?>[] classes = new Class[] { SbcDictionarySentence.class };
                xStream.setupDefaultSecurity(xStream);
                xStream.allowTypes(classes);

                xStream.alias(this.cMasterNode, SbcDictionarySentence.class);
                SentecesLoaded = (ArrayList<SbcDictionarySentence>) xStream.fromXML(input);
                this.setSentenceList(SentecesLoaded);
                break;
            } catch (IOException ex) {
                Thread.sleep(2000);
            }
        }
        
    }
  
    /**
     * Get a Translate from SentenceID, by according language defined by user
     * @author CesarBianchi
     * @param cID The identifier of sentence searching
     * @param cDefaultContent The Default Content case cID does not exist
     * @return The translation of SentenceID
     * @since October/2018
     * @version 1.03.1
    */ 
    public String getTranslation(String cID,String cDefaultContent){
        SbcDictionarySentence Sentence = new SbcDictionarySentence();
        String cReturn = new String();
        
        if (!cID.equals("")) {
            for (int nI = 0;nI<=this.AllSentences.size();nI++){
                Sentence = this.AllSentences.get(nI);
                
                if (Sentence.getSentenceID().equals(cID)){
                    String cLg = this.getLanguage();
                    
                    if (cLg.equals("UD"))
                        cReturn = Sentence.getTranslationUserDefined();
                    else if (cLg.equals("EN"))
                        cReturn = Sentence.getTranslationEnglish();
                    else if (cLg.equals("ES"))
                        cReturn = Sentence.getTranslationSpanish();
                    else 
                        cReturn = Sentence.getTranslationPortuguese();
                    break;    
                }
            }
        }
        
        if (cReturn.equals("")){
            cReturn = cDefaultContent;
        }

        return cReturn;
    }
    
      /**
     * Get a Translate from SentenceID, by according language defined by user
     * @author CesarBianchi
     * @param cID The identifier of sentence searching
     * @return The translation of SentenceID
     * @since October/2018
     * @version 1.03.1
    */ 
    public String getTranslation(String cID){
        String cReturn = this.getTranslation(cID,"");
        return cReturn;
    }
    
    /**
     * Sets the language file name
     * @author CesarBianchi
     * @param cName Language File Name
     * @since October/2018
     * @version 1.03.1
    */ 
    public void setLanguageFileName(String cName){
        this.cLanguageFile = cName;
    }
    
    /**
     * Gets the language file name
     * @author CesarBianchi
     * @return cLanguageFile Language File Name
     * @since October/2018
     * @version 1.03.1
    */ 
    private String getLanguageFileName() {
        return this.cLanguageFile;
    }

    /**
     * Gets the Sentence Object List
     * @author CesarBianchi
     * @return AllSentences Sentence Object List
     * @since October/2018
     * @version 1.03.1
    */ 
    public Object getSentenceList() {
        return this.AllSentences;
    }

    /**
     * Sets the Sentence Object List
     * @author CesarBianchi
     * @param SentecesLoaded Sentence Object List
     * @since October/2018
     * @version 1.03.1
    */
    public void setSentenceList(ArrayList<SbcDictionarySentence> SentecesLoaded) {
        this.AllSentences = SentecesLoaded;
    }

    /**
     * Sets the Langage Defined by User
     * @author CesarBianchi
     * @param cLg Defined Language
     * @since October/2018
     * @version 1.03.1
    */ 
    public void setLanguage(String cLg) {
        this.cLang = cLg;
    }
    
    /**
     * Gets the Langage Defined by User
     * @author CesarBianchi
     * @return cLang Defined Language
     * @since October/2018
     * @version 1.03.1
    */ 
    public String getLanguage(){
        return this.cLang;
    }

    /**
     * Update Sentences in LanguageFile.xml
     * This method is used for incremente languagefile.xml of older versions with new sentences
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.2
    */ 
    public void UpdateSentences(String cLangFile) {
        boolean hasDiff = false;
        boolean located = false;
        
        
        //Load Sentences from file
        SbcDictionaryBase BaseFromFile = new SbcDictionaryBase();
        BaseFromFile.setLanguageFileName(cLangFile);
        try {
            BaseFromFile.LoadLanguageSentences();
            
            //Load Sentences from code
            SbcDictionaryBase BaseFromCode = new SbcDictionaryBase();
            BaseFromCode.setLanguageFileName(cLangFile);
            BaseFromCode.LoadDefaultBase();
            
            
            //Compare one to one
            ArrayList<SbcDictionarySentence> ListFromFile = (ArrayList<SbcDictionarySentence>) BaseFromFile.getSentenceList();
            ArrayList<SbcDictionarySentence> ListFromCode = (ArrayList<SbcDictionarySentence>) BaseFromCode.getSentenceList();
            SbcDictionarySentence SentenceFromFile = new SbcDictionarySentence();
            SbcDictionarySentence SentenceFromCode = new SbcDictionarySentence();
            String cIdFromFile = new String();
            String cIdFromCode = new String();

            for (int nI = 0;nI<=ListFromCode.size()-1;nI++){
                SentenceFromCode = ListFromCode.get(nI);
                cIdFromCode = SentenceFromCode.getSentenceID();

                located=false;
                hasDiff=false;
                
                for (int nJ = 0;nJ<=ListFromFile.size()-1;nJ++){
                    SentenceFromFile = ListFromFile.get(nJ);
                    cIdFromFile = SentenceFromFile.getSentenceID();

                    if (cIdFromCode.equals(cIdFromFile)){
                        located = true;
                        if (!(SentenceFromFile.getTranslationUserDefined().equals("UNKNOW"))){
                            SentenceFromCode.setTranslationUserDefined(SentenceFromFile.getTranslationUserDefined());
                            ListFromCode.set(nI,SentenceFromCode);
                            hasDiff = true;
                        }
                        break;
                    }
                }
            }
            
            //Se tem diferenca, exclui o arquivo e recria
            if (!located){
                BaseFromCode.EraseFile();
                BaseFromCode.setSentenceList(ListFromCode);
                BaseFromCode.MakeLanguageFile();
                System.out.println("The LanguageFile.xml for SophiaBCrypt is updated !!!");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SbcDictionaryBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SbcDictionaryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * This method exclude LanguageFile.xml WARNING: Can be used only in update Process
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.2
    */ 
    public boolean EraseFile() {
        int nI = 1;
        int nTry = 5;
        int nDelay = 500;
        boolean excluiu = false;
        
        File cF = new File(this.cLanguageFile);

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
        return excluiu;
    }
    
}
