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

/**
 * This class store the information about version and build date of last SophiaBCrypt
 * @author CesarBianchi
 * @since October/2018
 */
public class SbcVersion {

    private String cVersion = "1.10.1";
    private String cBuildDate = "28 Jan 2021";
    private String cLang = "";
    
    /**
     * Get this SophiaBCrypt Version
     * @return cVersion Version of this SophiaBCrypt
     * @author CesarBianchi
     * @since October/2018
    */
    public String getVersion(){
        return cVersion;
    }

    /**
     * Get this SophiaBCrypt Build Date
     * @return cBuildDate Build Date of this SophiaBCrypt
     * @author CesarBianchi
     * @since October/2018
    */
    public String getBuildDate(){
        return cBuildDate;
    }
    
    /**
     * Get this SophiaBCrypt Defined Language
     * @return cLange THe language defined by user
     * @author CesarBianchi
     * @since October/2018
    */
    public String getLanguage(){
        return cLang;
    }
    
    /**
     * Set this SophiaBCrypt Defined Language
     * @param cLanguage The letters representatives of language choice by user
     * @author CesarBianchi
     * @since October/2018
    */
    public void setLanguage(String cLanguage){
        cLang = cLanguage;
    }

}
