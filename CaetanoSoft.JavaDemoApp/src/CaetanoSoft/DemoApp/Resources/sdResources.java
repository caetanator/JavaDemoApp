//******************************************************************************
// Project: Splash Screen Manager Demo v1.00
// URL:     http://slam.sourceforge.net
// File:    sdResources.java
//
// Description:
//          Default and English language resources.
//
// Copyright (C) 2008:
//          José Caetano Silva (jcaetano@users.sourceforge.net)
//
// License:
//          This file is part of Splash Screen Manager Demo.
//
//          Splash Screen Manager Demo is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          Splash Screen Manager Demo is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with Splash Screen Manager Demo.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************



package CaetanoSoft.DemoApp.Resources;

import java.awt.event.*;
import java.util.ListResourceBundle;
import javax.swing.*;

/**
 * International resources for 'SplashScreenDemo.class'.<br>
 * <b>Default and English language.</b>
 *
 * @author      José Caetano Silva (jcaetano@users.sourceforge.net)
 * @version     1.00, 2008-06-10
 * @since       1.00
 */
public class sdResources extends ListResourceBundle {
    static final Object[][] contents = {
        // LOCALIZE THIS
        { "MenuFile_T", "File" },
        { "MenuFile_MN", new Integer(KeyEvent.VK_F) },
        
        { "MenuFileOpen_T", "Open..." },
        { "MenuFileOpen_TT", "Opens a file" }, 
        { "MenuFileOpen_MN", new Integer(KeyEvent.VK_O) },
        // {"MenuFileOpen_AK", null},
        
        { "MenuFileSave_T", "Save..." }, 
        { "MenuFileSave_TT", "Saves a file" },
        { "MenuFileSave_MN", new Integer(KeyEvent.VK_S) },
        // {"MenuFileSave_AK", null},
        
        { "MenuFilePrint_T", "Print..." }, 
        { "MenuFilePrint_TT", "Prints a file" },
        { "MenuFilePrint_MN", new Integer(KeyEvent.VK_P) },
        { "MenuFilePrint_AK", KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK).toString() },
        
        { "MenuFileExit_T", "Exit" }, 
        { "MenuFileExit_TT", "Exits the application" },
        { "MenuFileExit_MN", new Integer(KeyEvent.VK_X) },
        // {"MenuFileExit_AK", null},
        
        
        { "MenuEdit_T", "Edit" },
        { "MenuEdit_MN", new Integer(KeyEvent.VK_E) }, 
        
        { "MenuEditColor_T", "Background Color..." }, 
        { "MenuEditColor_TT", "Choose the text background color" },
        { "MenuEditColor_MN", new Integer(0) },
        // {"MenuEditColor_AK", null},
        
        { "MenuHelp_T", "Help" },
        { "MenuHelp_MN", new Integer(KeyEvent.VK_H) }, 
        
        { "MenuHelpLicense_T", "License..." }, 
        { "MenuHelpLicense_TT", "Shows the license" },
        { "MenuHelpLicense_MN", new Integer(KeyEvent.VK_L) },

        // {"MenuHelpLicense_AK", null},
        { "MenuHelpAbout_T", "About..." }, 
        { "MenuHelpAbout_TT", "About this application" },
        { "MenuHelpAbout_MN", new Integer(KeyEvent.VK_A) },
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
