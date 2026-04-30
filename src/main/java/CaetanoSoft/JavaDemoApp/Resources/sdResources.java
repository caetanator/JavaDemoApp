//******************************************************************************
// Project: CaetanoSoft.JavaDemoApp
// URL:     https://github.com/caetanator/JavaJavaDemoApp/
// File:    sdResources.java
//
// Description:
//          Default and English language resources for CaetanoSoft.JavaDemoApp.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
//
// License:
//          This file is part of CaetanoSoft.JavaDemoApp.
//
//          CaetanoSoft.JavaDemoApp is free software: you can redistribute it 
//          and/or modify it under the terms of the GNU General Public License 
//          as published by the Free Software Foundation, either version 3 of 
//          the License, or (at your option) any later version.
//
//          CaetanoSoft.JavaDemoApp is distributed in the hope that it will be 
//          useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
//          of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//          GNU General Public License for more details.
// 
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.JavaDemoApp. If not, see 
//          <https://www.gnu.org/licenses/gpl-3.0.html>.
//******************************************************************************


package CaetanoSoft.JavaDemoApp.Resources;

import java.awt.event.*;
import java.util.ListResourceBundle;
import javax.swing.*;

/**
 * International resources for 'SplashScreenDemo.class'.<br>
 * <b>Default and English language.</b>
 *
 * @author      José Caetano Silva
 * @version     1.02.0001, 2008-06-10
 * @since       1.00
 */
public class sdResources extends ListResourceBundle {
    static final Object[][] contents = {
        // LOCALIZE THIS
        { "MenuFile_T", "File" },
        { "MenuFile_MN", KeyEvent.VK_F },
        
        { "MenuFileOpen_T", "Open..." },
        { "MenuFileOpen_TT", "Opens a file" }, 
        { "MenuFileOpen_MN", KeyEvent.VK_O },
        // {"MenuFileOpen_AK", null},
        
        { "MenuFileSave_T", "Save..." }, 
        { "MenuFileSave_TT", "Saves a file" },
        { "MenuFileSave_MN", KeyEvent.VK_S },
        // {"MenuFileSave_AK", null},
        
        { "MenuFilePrint_T", "Print..." }, 
        { "MenuFilePrint_TT", "Prints a file" },
        { "MenuFilePrint_MN", KeyEvent.VK_P },
        { "MenuFilePrint_AK", KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK).toString() },
        
        { "MenuFileExit_T", "Exit" }, 
        { "MenuFileExit_TT", "Exits the application" },
        { "MenuFileExit_MN", KeyEvent.VK_X },
        // {"MenuFileExit_AK", null},
        
        
        { "MenuEdit_T", "Edit" },
        { "MenuEdit_MN", KeyEvent.VK_E }, 
        
        { "MenuEditColor_T", "Background Color..." }, 
        { "MenuEditColor_TT", "Choose the text background color" },
        { "MenuEditColor_MN", 0 },
        // {"MenuEditColor_AK", null},
        
        { "MenuHelp_T", "Help" },
        { "MenuHelp_MN", KeyEvent.VK_H }, 
        
        { "MenuHelpLicense_T", "License..." }, 
        { "MenuHelpLicense_TT", "Shows the license" },
        { "MenuHelpLicense_MN", KeyEvent.VK_L },

        // {"MenuHelpLicense_AK", null},
        { "MenuHelpAbout_T", "About..." }, 
        { "MenuHelpAbout_TT", "About this application" },
        { "MenuHelpAbout_MN", KeyEvent.VK_A },
        { "MenuHelpAbout_AK", KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK | ActionEvent.CTRL_MASK).toString() },
        
        { "MBCheckExit_T", "Do you real want to exit this application?" },
                
        { "LabelHello_T", "Hello world!!" }
        // END OF MATERIAL TO LOCALIZE
    };

    /**
     * Returs the list of localized objects.
     * English language (en_*).
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
