/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CaetanoSoft.Utilities.Path;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author JCaetano
 */
public class PathUtils {
    /**
     * Get'name the base path for this application.
     *
     * @return  A string with the application path
     */
    public synchronized static String getApplicationPath(Object app)
    {
        String strPathApplication = app.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

        // Decodes the URL formated string path
        try {
            strPathApplication = URLDecoder.decode(strPathApplication, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            // Replaces the '%20' by the ' '
            strPathApplication = strPathApplication.replace("%20", " ");
        }

        // Replaces the '/' by the '\' on Windows
        strPathApplication = strPathApplication.replace('/', File.separatorChar);
        // Removes *.jar
        if (strPathApplication.endsWith(".jar")) {
            int i = strPathApplication.lastIndexOf(File.separatorChar);
            if (i > 0) {
                strPathApplication = strPathApplication.substring(0, i);
            }
        }
        // Removes the first '\' if necessary on Windows
        if ((strPathApplication.charAt(0) == File.separatorChar) && (strPathApplication.indexOf(":") >= 0)) {
            strPathApplication = strPathApplication.substring(1);
        }
        // Removes the separator in the end of the path if necessary
        if (strPathApplication.charAt(strPathApplication.length() -1) == File.separatorChar) {
            strPathApplication = strPathApplication.substring(0, strPathApplication.length() -1);
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
