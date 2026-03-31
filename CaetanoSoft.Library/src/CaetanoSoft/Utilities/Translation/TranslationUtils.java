//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    TranslationUtils.java
//
// Description:
//          This class manages the translation of the Java AWT/Swing UI widgets
//          like the dialogues for Color Chooser and File Chooser.
//
//          For now, only Portuguese is supported.
//
// Copyright:
//          © 2008-2022 José Caetano Silva / CaetanoSoft. All rights reserved.
//
// License:
//          This file is part of CaetanoSoft.Library.
//
//          CaetanoSoft.Library is free software: you can redistribute it and/or 
//          modify it under the terms of the GNU General Public License as 
//          published by the Free Software Foundation, either version 3 of the 
//          License, or (at your option) any later version.
//
//          CaetanoSoft.Library is distributed in the hope that it will be 
//          useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
//          of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.Library. If not, see 
//          <https://www.gnu.org/licenses/gpl-3.0.html>.
//******************************************************************************


package CaetanoSoft.Utilities.Translation;

import javax.swing.*;

/**
 * This class manages the translation of the Java AWT/Swing UI widgets
 * like the dialogues for Color Chooser and Load and Save.
 * <p></p>
 * For now, only the Portuguese language is supported.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class TranslationUtils
{
    // Singleton instance
    private static TranslationUtils instance = null;

    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since   1.00
     * @see #getInstance()
     */
    protected TranslationUtils()
    {
        // Singleton pattern class, instantiation not allowed
        //super();
    }

	  /**
     * Don't permit creating an object by cloning it.
     *
     * @since   1.00
     * @return  A cloned object of the class <code>Object</code>.
     * @throws  java.lang.CloneNotSupportedException
     * @see     java.lang.Object#clone()
     */
	  @Override
	  public Object clone() throws CloneNotSupportedException
    {
		    // Singleton pattern class, cloning not allowed
        //super.clone();

        throw new CloneNotSupportedException("This instance of the Singleton pattern class cannot be cloned");

        //return null;
	  }
	
    /**
     * Creates a new instance of the Translation class.
     *
     * @since   1.00
     * @return A new <i>TranslationUtils</i> instance.
     */
    public static synchronized TranslationUtils getInstance()
    {
        if (instance == null)
        {
            instance = new TranslationUtils();
        }
        return instance;
    }
    
    /**
     * Translate the Java resources strings to a diferent language.
     * <p></p>
     * Supported language codes (BCP 47 tag format):
     * <ul>
     *	<li> <b>en</b>: English</li>
     *	<li> <b>en_GB</b> / <b>en-GB</b>: English (Great Britain)</li>
     *	<li> <b>en_US</b> / <b>en-US</b>: English (United States of America)</li>
     *	<li> <b>pt</b>: Portuguese</li>
     *	<li> <b>pt_PT</b> / <b>pt-PT</b>: Portuguese (Portugal)</li>
     *	<li> <b>pt_BR</b> / <b>pt-BR</b>: Portuguese (Brasil)</li>
     * </ul>
     * @since   1.00
     * @param strLang    string to be translated
     * @return  <code>true</code>, if translation was made. <code>false</code>, otherwise.
     */
    public static boolean translateLanguage(String strLang)
    {
        boolean bRet = false;
        String strResorceLang = null;
        
        if (strLang != null && !(strLang.isBlank()))
        {
            // Validate language
            switch(strLang)
            {
                case "en":
                case "en_US":
                case "en_GB":
                case "en-US":
                case "en-GB":
                    // Default Java resources language
                    strResorceLang = null;
                    bRet = true;
                    break;

                case "pt":
                case "pt_PT":
                case "pt_BR":
                case "pt-PT":
                case "pt-BR":
                    strResorceLang = "pt_PT";
                    bRet = true;
                    break;
                    
                default:
                    bRet = false;
                    strResorceLang = null;
            }
            if (strResorceLang != null)
            {
                // Install the language resources
                strResorceLang = "CaetanoSoft.Utilities.Translation.Resources." + strResorceLang;
                try
                {
                    // Common to all Java Look&Feel
                    //ResourceBundle props = ResourceBundle.getBundle("sun.print.resources.serviceui");
                    UIManager.getDefaults().addResourceBundle(strResorceLang + ".basic");
                    UIManager.getDefaults().addResourceBundle(strResorceLang + ".serviceui");

                    // Specific to the Java Look&Feel
                    String strLF = UIManager.getLookAndFeel().toString();
                    if (strLF.indexOf(".metal.") > 0)
                    {
                        // Java/Metal Look & Feel (All Java platforms, since JRE v1.1)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".metal");
                    }
                    else if (strLF.indexOf(".nimbus.") > 0)
                    {
                        // Nimbus Look & Feel (All Java platforms, since JRE v1.6U10)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".synth");
                    }
                    else if (strLF.indexOf(".motif.") > 0)
                    {
                        // CDE/Motif Look & Feel (All Java platforms, since JRE v1.1)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".motif");
                    }
                    else if (strLF.indexOf(".gtk.") > 0)
                    {
                        // GTK+ Look & Feel (Only on OS with GTK 2.2 GUI installed, since JRE v1.2)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".gtk");
                    }
                    else if (strLF.indexOf(".windows.") > 0)
                    {
                        // Microsoft Windows Look & Feel and Microsoft Windows Classic Look & Feel (Only on Windows OS, since JRE v1.1)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".windows");
                    }
                    else if (strLF.indexOf(".mac.") > 0)
                    {
                        // Apple Macintosh Look & Feel(Only on Mac OS, since JRE v1.1)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".mac");
                    }
                    else
                    {
                        // Any other Look & Feel (All Java platforms)
                        UIManager.getDefaults().addResourceBundle(strResorceLang + ".synth");
                    }
                }
                catch (Exception ex)
                {
                    // Do nothing
                    bRet = false;
                }
            }
        }
        
        return bRet;
    }
}
