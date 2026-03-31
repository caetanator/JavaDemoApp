//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    PathUtils.java
//
// Description:
//          This class handles the manipulation of path strings.
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


package CaetanoSoft.Utilities.Path;

import java.io.File;
import java.net.URISyntaxException;

/**
 * This class implements a singleton pattern object that handles the 
 * manipulation of path strings.
 * 
 * @author José Caetano Silva
 * @version 1.02.0001, 2022-07-04
 * @since   1.00
 */
public class PathUtils
{
    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since   1.00
     */
    private PathUtils()
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
     * Gets the user's directory path.
     *
     * @return  A string with the user's path.
     */
    public static String getUserPath()
    {
        String strUserPath = System.getProperty("user.dir");

        if((strUserPath != null))
        {
            strUserPath = strUserPath.trim();
        }
        else
        {
            strUserPath = "";
        }

        return strUserPath;
    }
    
    /**
     * Gets the directory path for this application.
     *
     * @param app   the application object
     * @return  A string with the application path
     */
    public static synchronized String getApplicationPath(Object app)
    {
        String strPathApplication = "";
        if(app != null)
        {
            // Decodes the URL formatted string path
            try
            {
                strPathApplication = app.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            }
            catch(URISyntaxException ex)
            {
                //Logger.getLogger(PathUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(Exception ex)
            {
                //Logger.getLogger(PathUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Replaces the '/' by the '\' on Windows
            if (File.separatorChar != '/')
            {
                strPathApplication = strPathApplication.replace('/', File.separatorChar);
            }
            
            // Removes *.jar and .class
            if(strPathApplication.endsWith(".jar") || strPathApplication.endsWith(".class"))
            {
                int i = strPathApplication.lastIndexOf(File.separatorChar);
                if(i > 0)
                {
                    strPathApplication = strPathApplication.substring(0, i);
                }
            }
            
            // Removes the first '\' if necessary on Windows
            if((strPathApplication.charAt(0) == File.separatorChar) && (strPathApplication.indexOf(":") >= 0))
            {
                strPathApplication = strPathApplication.substring(1);
            }
            
            // Removes the separator in the end of the path if necessary
            if(strPathApplication.charAt(strPathApplication.length() -1) == File.separatorChar)
            {
                strPathApplication = strPathApplication.substring(0, strPathApplication.length() -1);
            }
        }

        return strPathApplication;
    }
   
    /**
     * Get the extension from a file name.
     *
     * @param file  the file object
     * @return      A string with file extension or null if none is found
     */
    public static String getFileExtension(File file) {
        // Get the extension of a file
        String extension = null;
        String name = file.getName();
        int lastPoint = name.lastIndexOf('.');
        if (lastPoint > 0 &&  lastPoint < name.length() - 1) {
            extension = name.substring(lastPoint + 1).toLowerCase();
        }
        return extension;
    }
}

