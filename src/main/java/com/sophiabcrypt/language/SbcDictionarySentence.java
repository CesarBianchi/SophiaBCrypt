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
package com.sophiabcrypt.language;

/**
 * This is class is used for manage a Dictionary Sentence
 * Is the same of the xml language structure
 * @author CesarBianchi
 * @since October/2018
 * @see SbcDictionaryTranslations
 * @version 1.03.1
 */       
public class SbcDictionarySentence {
        
    private String sentenceID = new String();
    private String ClassOfUse = new String();
    private SbcDictionaryTranslations translations = new SbcDictionaryTranslations();

    /**
     * A single constructor without parameters
     * @author CesarBianchi
     * @param sentenceCode The id of sentence
     * @param classOrigin The name of the class where sentence is used
     * @param PT The define in portuguese language
     * @param EN The define in english language
     * @param ES The define in spanish language
     * @param UD The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
     */
    public void SbcDictionarySentence() {
    	
    }
    
    /**
     * This is a Constructor for a full use (with all input parameters)
     * @author CesarBianchi
     * @param sentenceCode The id of sentence
     * @param classOrigin The name of the class where sentence is used
     * @param PT The define in portuguese language
     * @param EN The define in english language
     * @param ES The define in spanish language
     * @param UD The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
     */
    public void SbcDictionarySentence(String sentenceCode, String classOrigin, String PT, String EN, String ES, String UD){
        this.setSentenceID(sentenceCode);
        this.setClassOfUse(classOrigin);
        this.setTranslationPortuguese(PT);
        this.setTranslationEnglish(EN);
        this.setTranslationSpanish(ES);
        this.setTranslationUserDefined(UD);
    }

    /**
     * Sets ID of Sentence
     * @author CesarBianchi
     * @param sentenceCode The id of sentence
     * @since October/2018
     * @version 1.03.1
     */
    public void setSentenceID(String sentenceCode) {
        this.sentenceID = sentenceCode;
    }

    /**
     * Sets the name of the class where sentence is used
     * @author CesarBianchi
     * @param classOrigin The name of the class where sentence is used
     * @since October/2018
     * @version 1.03.1
    */
    public void setClassOfUse(String classOrigin) {
        this.ClassOfUse = classOrigin;
    }

    /**
     * Sets the translation object with all languages define
     * @author CesarBianchi
     * @param Trnsl The SbcDictionaryTranslations object with all languages define
     * @since October/2018
     * @version 1.03.1
    */
     public void setTranslations(SbcDictionaryTranslations Trnsl) {
        this.translations = Trnsl;
    }

     /**
     * Sets the define in portuguese language
     * @author CesarBianchi
     * @param PT The define in portuguese language
     * @since October/2018
     * @version 1.03.1
    */
    public void setTranslationPortuguese(String PT) {
        this.translations.setPortuguese(PT);
    }

    /**
     * Sets the define in english language
     * @author CesarBianchi
     * @param EN The define in english language
     * @since October/2018
     * @version 1.03.1
    */
    public void setTranslationEnglish(String EN) {
        this.translations.setEnglish(EN);
    }

    /**
     * Sets the define in spanish language
     * @author CesarBianchi
     * @param ES The define in spanish language
     * @since October/2018
     * @version 1.03.1
    */
    public void setTranslationSpanish(String ES) {
        this.translations.setSpanish(ES);
    }


    /**
     * Sets the define by User Definitions language (Any language)
     * @author CesarBianchi
     * @param UD The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
    */
    public void setTranslationUserDefined(String UD) {
        this.translations.setUserDefined(UD);
    }
    
     /**
     * Gets the ID of Sentence
     * @author CesarBianchi
     * @return The id of sentence
     * @since October/2018
     * @version 1.03.1
     */
    public String getSentenceID() {
        return this.sentenceID;
    }

    /**
     * Gets the name of the class where sentence is used
     * @author CesarBianchi
     * @return ClassOfUse The name of the class where sentence is used
     * @since October/2018
     * @version 1.03.1
    */
    public String getClassOfUse() {
        return this.ClassOfUse;
    }

    /**
     * Gets the translation object with all languages define
     * @author translations
     * @return Trnsl The SbcDictionaryTranslations object with all languages define
     * @since October/2018
     * @version 1.03.1
    */
     public SbcDictionaryTranslations getTranslations() {
        return this.translations;
    }

     /**
     * Gets the define in portuguese language
     * @return the define in portuguese language
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
    */
    public String getTranslationPortuguese() {
        return this.translations.getPortuguese();
    }

    /**
     * Gets the define in english language
     * @author CesarBianchi
     * @return The define in english language
     * @since October/2018
     * @version 1.03.1
    */
    public String getTranslationEnglish() {
        return this.translations.getEnglish();
    }

    /**
     * Gets the define in spanish language
     * @author CesarBianchi
     * @return The define in spanish language
     * @since October/2018
     * @version 1.03.1
    */
    public String getTranslationSpanish() {
        return this.translations.getSpanish();
    }


    /**
     * Gets the define by User Definitions language (Any language)
     * @author CesarBianchi
     * @return The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
    */
    public String getTranslationUserDefined() {
        return this.translations.getUserDefined();
    }
}
