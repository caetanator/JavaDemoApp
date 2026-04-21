//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    StringUtils.java
//
// Description:
//          This class handles the manipulation of arrays data.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
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


package CaetanoSoft.Utilities.String;

/**
 * This class implements methods to manipulate strings.
 *
 * @author José Caetano Silva
 * @version 1.02.0001, 2026-03-30
 * @since 1.02
 */
public class StringUtils {

    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since 1.00
     */
    private StringUtils() {
        // Singleton pattern class, instantiation not allowed
        //super();
    }

    /**
     * Don't permit creating an object by cloning it.
     *
     * @since 1.00
     * @return A cloned object of the class <code>Object</code>.
     * @throws java.lang.CloneNotSupportedException
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Singleton pattern class, cloning not allowed
        //super.clone();

        throw new CloneNotSupportedException("This instance of the Singleton pattern class cannot be cloned");

        //return null;
    }

    /**
     * Escapes the ASCII control characters.
     *
     * @param str string to be escaped
     * @return A string with the ASCII control codes escaped.
     */
    public static String escapeString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '\n':
                    // New Line
                    sb.append("\\n");
                    break;
                
                case '\r':
                    // Carriage Return
                    sb.append("\\r");
                    break;
                
                case '\t':
                    // Horizontal Tab
                    sb.append("\\t");
                    break;
                
                case '\b':
                    // Backspace
                    sb.append("\\b");
                    break;
                
                case '\f':
                    // Form Feed
                    sb.append("\\f");
                    break;
                
                case '\\':
                    // Backslash
                    sb.append("\\\\");
                    break;
                
                case '\'':
                    // Single Quote
                    sb.append("\\'");
                    break;
                
                case '\"':
                    // Double Quote
                    sb.append("\\\"");
                    break;
                    
                default:
                    // Not an ASCII control character
                    sb.append(str.charAt(i));
                    break;
            }
        }
        
        return sb.toString();
    }
}
