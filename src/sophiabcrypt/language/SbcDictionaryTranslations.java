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

/**
 * This class is used to manager translations defines
 * @author CesarBianchi
 * @since October/2018
 * @version 1.00
 */
public class SbcDictionaryTranslations {
    
    private String portuguese = new String();
    private String english = new String();
    private String spanish = new String();
    private String userDefined = new String();
    
    /**
     * This is a main class (Constructor) for a single use (without input parameters)
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    public void main(){
        
    }
    
    /**
     * This is a main class (Constructor) for a full use (with all input parameters)
     * @author CesarBianchi
     * @param PT The define in portuguese language
     * @param EN The define in english language
     * @param ES The define in spanish language
     * @param UD The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
     */
    public void main(String PT,String EN, String ES, String UD){
        this.setPortuguese(PT);
        this.setEnglish(EN);
        this.setSpanish(ES);
        this.setUserDefined(UD);
    }
    
    /**
     * This class set the portuguese define class attribute
     * @author CesarBianchi
     * @param PT The define in portuguese language
     * @since October/2018
     * @version 1.03.1
     */
    public void setPortuguese(String PT){
        this.portuguese = PT;
    }

    /**
     * This class set the english define class attribute
     * @author CesarBianchi
     * @param EN The define in english language
     * @since October/2018
     * @version 1.03.1
     */
    public void setEnglish(String EN){
        this.english = EN;
    }

    /**
     * This class set the spanish define class attribute
     * @author CesarBianchi
     * @param ES The define in spanish language
     * @since October/2018
     * @version 1.03.1
     */
    public void setSpanish(String ES){
        this.spanish = ES;
    }
    
    /**
     * This class set the User define class attribute
     * @author CesarBianchi
     * @param UD The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
     */
    public void setUserDefined(String UD){
        this.userDefined = UD;
    }
    
    /**
     * This class gets the portuguese define class attribute
     * @author CesarBianchi
     * @return portuguese The define in portuguese language
     * @since October/2018
     * @version 1.03.1
     */
    public String getPortuguese(){
        return this.portuguese;
    }

    /**
     * This class gets the english define class attribute
     * @author CesarBianchi
     * @return english The define in english language
     * @since October/2018
     * @version 1.03.1
     */
    public String getEnglish(){
        return this.english;
    }

    /**
     * This class gets the spanish define class attribute
     * @author CesarBianchi
     * @return spanish The define in spanish language
     * @since October/2018
     * @version 1.03.1
     */
    public String getSpanish(){
        return this.spanish;
    }
    
    /**
     * This class set the User define class attribute
     * @author CesarBianchi
     * @return userDefined The define by User Definitions language (Any language)
     * @since October/2018
     * @version 1.03.1
     */
    public String getUserDefined(){
        return this.userDefined;
    }
    
}
