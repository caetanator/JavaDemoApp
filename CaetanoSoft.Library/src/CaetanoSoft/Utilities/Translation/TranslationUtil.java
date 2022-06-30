//******************************************************************************
// Project: Splash Screen Manager v1.00
// URL:     http://slam.sourceforge.net
// File:    TranslationUtil.java
//
// Description:
//          This class manages the Java 1.6 SplashScreen object.
//
// Copyright (C) 2008:
//          José Caetano Silva (jcaetano@users.sourceforge.net)
//
// License:
//          This file is part of Splash Screen Manager.
//
//          Splash Screen Manager is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          Splash Screen Manager is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with Splash Screen Manager.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************



package CaetanoSoft.Utilities.Translation;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;

/**
 * This class manages the Java 1.6 SplashScreen object.
 *
 * @author  José Caetano Silva (jcaetano@users.sourceforge.net)
 * @version %I%, %G%
 */
public class TranslationUtil {
    // Singleton instance
    private static TranslationUtil instance = null;

    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since   1.00
     * @see #getInstance()
     */
    protected TranslationUtil() {
        // Do nothing
    }

	/**
     * Don't permit creating an object by cloning it.
     *
     * @since   1.00
     */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
    /**
     * Creates a new instance of the Translation class.
     *
     * @return A new <i>Translation</i> instance.
     * @since   1.00
     */
    public static synchronized TranslationUtil getInstance() {
        if (instance == null) {
            instance = new TranslationUtil();
        }
        return instance;
    }
    
    public static boolean translateLanguage(String strLang)
    {
        boolean bRet = false;
        
        if (strLang != null && !(strLang.isBlank()))
        {
            // Validate language
            switch(strLang)
            {
                case "pt":
                case "pt_PT":
                case "pt_BR":
                    bRet = true;
                    break;
                    
                default:
                    bRet = false;
            }
            
            // Install the language resources
            try {
                // Common to all Java Look&Feel
                //ResourceBundle props = ResourceBundle.getBundle("sun.print.resources.serviceui");
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.basic");
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.serviceui");

                // Specific to the Java Look&Feel
                String strLF = UIManager.getLookAndFeel().toString();

                if (strLF.indexOf(".metal.") > 0) {
                    // Java/Metal Look & Feel (All Java platforms)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.metal");
                }
                else if (strLF.indexOf(".nimbus.") > 0) {
                    // Nimbus Look & Feel (All Java platforms)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.synth");
                }
                else if (strLF.indexOf(".motif.") > 0) {
                    // CDE/Motif Look & Feel (All Java platforms)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.motif");
                }
                else if (strLF.indexOf(".gtk.") > 0) {
                    // GTK Look & Feel (Only on OS with GTK GUI installed)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.gtk");
                }
                else if (strLF.indexOf(".windows.") > 0) {
                    // Microsoft Windows Look & Feel and Microsoft Windows Classic Look & Feel (Only on Windows OS)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.windows");
                }
                else if (strLF.indexOf(".mac.") > 0) {
                    // Apple Macintosh Look & Feel(Only on Mac OS)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.mac");
                }
                else {
                    // Any other Look & Feel (All Java platforms)
                    UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.synth");
                }
            }
            catch (Exception ex) {
                // Do nothing
                bRet = false;
            }
        }
        
        return bRet;
    }
}
