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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


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
    private void LoadDefaultBase() {
        /*Language Default Definitions*/
        this.AddNewSentence("0001","SbcMainWindow"      ,"Arquivo"              ,"File"        ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0002","SbcMainWindow"      ,"Criptografar"         ,"Encrypt"     ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0003","SbcMainWindow"      ,"Descriptografar"      ,"Decrypt"     ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0004","SbcMainWindow"      ,"Sair"                 ,"Exit"        ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0005","SbcMainWindow"      ,"Ajuda"                ,"Help"        ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0006","SbcMainWindow"      ,"Sobre"                ,"About"       ,"ES"           ,"UNKNOW");        
        this.AddNewSentence("0007","SbcAboutWindow"     ,"Versão"               ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0008","SbcAboutWindow"     ,"Desenvolvido por:"    ,"EN"          ,"ES"           ,"UNKNOW");        
        this.AddNewSentence("0009","SbcExitWindow"      ,"Voce deseja sair ?"   ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0010","SbcExitWindow"      ,"Sim"                  ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0011","SbcExitWindow"      ,"Não"                  ,"EN"          ,"ES"           ,"UNKNOW");        
        this.AddNewSentence("0012","SbcLanguageWindow"  ,"Selecione o Idioma"   ,"EN"          ,"ES"           ,"UNKNOW");        
        this.AddNewSentence("0013","SbcProgressWindow"  ,"Processando Arquivos" ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0014","SbcProgressWindow"  ,"Aguarde"              ,"EN"          ,"ES"           ,"UNKNOW");        
        this.AddNewSentence("0015","SbcPswWindow"       ,"Deseja Criptografar o arquivo selecionado ?"                      ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0016","SbcPswWindow"       ,"ATENÇÃO: Memorize a sua senha para restauração futura."           ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0017","SbcPswWindow"       ,"Este arquivo só podera ser restaurado utilizando a mesma senha."  ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0018","SbcPswWindow"       ,"Senha:"               ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0019","SbcPswWindow"       ,"Confirme a senha:"    ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0020","SbcPswWindow"       ,"Confirmar"            ,"EN"          ,"ES"           ,"UNKNOW");
        this.AddNewSentence("0020","SbcPswWindow"       ,"Cancelar"             ,"EN"          ,"ES"           ,"UNKNOW");
        
        
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
    private void MakeLanguageFile() throws IOException, InterruptedException{
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
                    
                    switch (cLg) {
                        case "UD":
                            cReturn = Sentence.getTranslationUserDefined();
                        case "EN":
                            cReturn = Sentence.getTranslationEnglish();
                        case "ES":
                            cReturn = Sentence.getTranslationSpanish();
                        default:
                            cReturn = Sentence.getTranslationPortuguese();
                    }
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
    private Object getSentenceList() {
        return this.AllSentences;
    }

    /**
     * Sets the Sentence Object List
     * @author CesarBianchi
     * @param SentecesLoaded Sentence Object List
     * @since October/2018
     * @version 1.03.1
    */
    private void setSentenceList(ArrayList<SbcDictionarySentence> SentecesLoaded) {
        this.AllSentences = SentecesLoaded;
    }

    /**
     * Sets the Langage Defined by User
     * @author CesarBianchi
     * @param cLg Defined Language
     * @since October/2018
     * @version 1.03.1
    */ 
    private void setLanguage(String cLg) {
        this.cLang = cLg;
    }
    
    /**
     * Gets the Langage Defined by User
     * @author CesarBianchi
     * @return cLang Defined Language
     * @since October/2018
     * @version 1.03.1
    */ 
    private String getLanguage(){
        return this.cLang;
    }
    
}
