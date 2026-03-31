//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    IconUtils.java
//
// Description:
//          This class manages the loading of images to icons.
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


package CaetanoSoft.Utilities.UI;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Creates an Image Icon from a file or an <code>Object</code>.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class IconUtils
{
    /**
     * Loads a Image Icon, from a resource.
     * 
     * @param obj   an object with a resource manager
     * @param path  a string with the icon full path
     * @return      An ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(Object obj, String path)
    {
        URL imgURL = obj.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Loads a Image Icon, from a file.
     * 
     * @param path  a string with the icon full path
     * @return      An ImageIcon, or null if the path was invalid-
     */
    public static ImageIcon createImageIcon(String path)
    {
        URL imgURL = IconUtils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
