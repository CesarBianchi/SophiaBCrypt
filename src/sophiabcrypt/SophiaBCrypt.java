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
import java.io.IOException;

import sophiabcrypt.forms.SbcLookAndFeel;

/**
 * This is a Main Class of SophiaBCrypt
 * @see SbcPswControl
 * @see SbcPlanFile
 * @see SbcEncEngine
 * @see SbcEncryptor
 * @author CesarBianchi
 * @since October/2018
 */public class SophiaBCrypt {

    /**
     * This is a Main Method os class
     * It's exec a Prepare Classes and SbcMainClass
     * @param args the command line arguments
     * @author CesarBianchi
     * @throws java.io.IOException Case can't read param file
     * @throws java.lang.InterruptedException Case isn't load param file
     * @since October/2018
    */
    public static void main(String[] args) throws IOException, InterruptedException {    
    
    	//Set Look and Feel Theme
    	SbcLookAndFeel lookAndFeel = new SbcLookAndFeel();
    	lookAndFeel.setLookAndFeelFlatDarculaLaf();
    	
        SbcPrepareToLoad SbcLoading = new SbcPrepareToLoad();
        if (SbcLoading.CanBeLoad()){
            SbcToLoad SbcLoad = new SbcToLoad();
            SbcLoad.SetParamFileName(SbcLoading.getParameterFileName());
            if (SbcLoad.LoadParamFile()){
            	SbcLoad.LoadMainWindow();
            }
        }
        
    }
    
}
