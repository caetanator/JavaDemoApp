//******************************************************************************
// Project: Splash Screen Manager Demo v1.00
// URL:     http://slam.sourceforge.net
// File:    sdResources_pt.java
//
// Description:
//          Portuguese language resources.
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
 * <b>Portuguese language.</b>
 *
 * @author      José Caetano Silva (jcaetano@users.sourceforge.net)
 * @version     1.00, 2008-06-10
 * @since       1.00
 */
public class sdResources_pt extends ListResourceBundle {
    static final Object[][] contents = {
        // LOCALIZE THIS
        { "MenuFile_T", "Ficheiro" },
        { "MenuFile_MN", new Integer(KeyEvent.VK_F) },
        
        { "MenuFileOpen_T", "Abrir..." },
        { "MenuFileOpen_TT", "Abre um ficheiro" }, 
        { "MenuFileOpen_MN", new Integer(KeyEvent.VK_A) },
        // {"MenuFileOpen_AK", null},
        
        { "MenuFileSave_T", "Guardar..." }, 
        { "MenuFileSave_TT", "Guarda um ficheiro" },
        { "MenuFileSave_MN", new Integer(KeyEvent.VK_G) },
        // {"MenuFileSave_AK", null},

        { "MenuFilePrint_T", "Imprimir..." }, 
        { "MenuFilePrint_TT", "Imprime um ficheiro" },
        { "MenuFilePrint_MN", new Integer(KeyEvent.VK_I) },
        // {"MenuFilePrint_AK", null},
        
        { "MenuFileExit_T", "Sair" },
        { "MenuFileExit_TT", "Sai da aplicação" },
        { "MenuFileExit_MN", new Integer(KeyEvent.VK_A) },
        { "MenuFileExit_AK", KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK).toString() },
        
        { "MenuEdit_T", "Editar" },
        { "MenuEdit_MN", new Integer(KeyEvent.VK_E) }, 
        
        { "MenuEditColor_T", "Cor do Fundo..." }, 
        { "MenuEditColor_TT", "Escolhe a cor do fundo do texto" },
        { "MenuEditColor_MN", new Integer(0) },
        // {"MenuEditColor_AK", null},
        
        { "MenuHelp_T", "Ajuda" },
        { "MenuHelp_MN", new Integer(KeyEvent.VK_A) },        
       
        { "MenuHelpLicense_T", "Licença..." }, 
        { "MenuHelpLicense_TT", "Mostra a licença" },
        { "MenuHelpLicense_MN", new Integer(KeyEvent.VK_L) },
        // {"MenuHelpLicense_AK", null},

        { "MenuHelpAbout_T", "Acerca..." }, 
        { "MenuHelpAbout_TT", "Acerca desta aplicação" },
        { "MenuHelpAbout_MN", new Integer(KeyEvent.VK_A) },
        { "MenuHelpAbout_AK", KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK | ActionEvent.CTRL_MASK).toString() },
 
        { "MBCheckExit_T", "Quer mesmo sair da aplicação?" },
        
        { "LabelHello_T", "Olá mundo!" }
        // END OF MATERIAL TO LOCALIZE
    };

    /**
     * Returs the list of localized objects.
     * Portuguese language (pt_*).
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
