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
import javax.swing.UIManager;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import com.sophiabcrypt.SbcEncryptor;


/**
 * This class manage a lot of collection to Look and Feel Java Swing themes, to apply in SophiaBCrypt.
 * The collections listed here are: 
 *      FlatLaf - https://www.formdev.com/flatlaf/  - All Rights Reserved.
 *      IntelliJ Platform  - https://www.formdev.com/flatlaf/themes/#intellij_platform_themes - All Rights Reserved.
 * @author Cesar_Bianchi
 * @since January/2021
 * @see SbcEncryptor
 * @see FlatLaf
 * @see IntelliJ
 */
public class SbcLookAndFeel {
	
    /**
     * Constructor Method. Nothing to do.
     * @author CesarBianchi
     * @since January/2021
    */
    public SbcLookAndFeel() {

    }
	
    /**
     * Sets "Nimbus" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelNimbus() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (ClassNotFoundException e) {

                System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (InstantiationException e) {

                System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalAccessException e) {

                System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
    /**
     * Sets "FlatLightLaf" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatLightLaf() {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "FlatDarkLaf" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatDarkLaf() {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "FlatIntelijLaf" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatIntelijLaf() {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "FlatDarculaLaf" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatDarculaLaf() {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "ArcOrange" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelArcOrange() {
        try {
            FlatArcOrangeIJTheme lookF = new FlatArcOrangeIJTheme();
            //lookF.install();	
            lookF.initialize();

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "DarkPurpleIJTheme" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatDarkPurpleIJTheme() {
        try {
            FlatDarkPurpleIJTheme lookF = new FlatDarkPurpleIJTheme();
            //lookF.install();	
            lookF.initialize();


        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
    /**
     * Sets "FlatNordTheme(" Look and Feel Theme
     * @author CesarBianchi
     * @since January/2021
    */
    public static void setLookAndFeelFlatFlatNordTheme() {
        try {
            FlatNordIJTheme lookF = new FlatNordIJTheme();
            //lookF.install();	
            lookF.initialize();


        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
	
	
	
}
