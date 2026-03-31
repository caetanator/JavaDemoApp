//******************************************************************************
// Project: CaetanoSoft.JavaDemoApp
// URL:     https://github.com/caetanator/JavaJavaDemoApp/
// File:    sdResources_pt.java
//
// Description:
//          Portuguese language resources for CaetanoSoft.JavaDemoApp.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
//
// License:
//          This file is part of CaetanoSoft.JavaDemoApp.
//
//          CaetanoSoft.JavaDemoApp is free software: you can redistribute it and/or 
//          modify it under the terms of the GNU General Public License as 
//          published by the Free Software Foundation, either version 3 of the 
//          License, or (at your option) any later version.
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
 * <b>Portuguese language.</b>
 *
 * @author      José Caetano Silva
 * @version     1.02.0001, 2008-06-10
 * @since       1.00
 */
public class sdResources_pt extends ListResourceBundle {
    static final Object[][] contents = {
        // LOCALIZE THIS
        { "MenuFile_T", "Ficheiro" },
        { "MenuFile_MN", KeyEvent.VK_F },
        
        { "MenuFileOpen_T", "Abrir..." },
        { "MenuFileOpen_TT", "Abre um ficheiro" }, 
        { "MenuFileOpen_MN", KeyEvent.VK_A },
        // {"MenuFileOpen_AK", null},
        
        { "MenuFileSave_T", "Guardar..." }, 
        { "MenuFileSave_TT", "Guarda um ficheiro" },
        { "MenuFileSave_MN", KeyEvent.VK_G },
        // {"MenuFileSave_AK", null},

        { "MenuFilePrint_T", "Imprimir..." }, 
        { "MenuFilePrint_TT", "Imprime um ficheiro" },
        { "MenuFilePrint_MN", KeyEvent.VK_I },
        // {"MenuFilePrint_AK", null},
        
        { "MenuFileExit_T", "Sair" },
        { "MenuFileExit_TT", "Sai da aplicação" },
        { "MenuFileExit_MN", KeyEvent.VK_A },
        { "MenuFileExit_AK", KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK).toString() },
        
        { "MenuEdit_T", "Editar" },
        { "MenuEdit_MN", KeyEvent.VK_E }, 
        
        { "MenuEditColor_T", "Cor do Fundo..." }, 
        { "MenuEditColor_TT", "Escolhe a cor do fundo do texto" },
        { "MenuEditColor_MN", 0 },
        // {"MenuEditColor_AK", null},
        
        { "MenuHelp_T", "Ajuda" },
        { "MenuHelp_MN", KeyEvent.VK_A },        
       
        { "MenuHelpLicense_T", "Licença..." }, 
        { "MenuHelpLicense_TT", "Mostra a licença" },
        { "MenuHelpLicense_MN", KeyEvent.VK_L },
        // {"MenuHelpLicense_AK", null},

        { "MenuHelpAbout_T", "Acerca..." }, 
        { "MenuHelpAbout_TT", "Acerca desta aplicação" },
        { "MenuHelpAbout_MN", KeyEvent.VK_A },
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
