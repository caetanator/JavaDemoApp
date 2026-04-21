//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    OSUtils.java
//
// Description:
//          This class manages OS related stuff.
//
// Copyright:
//          © 2026 José Caetano Silva / CaetanoSoft. All rights reserved.
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
package CaetanoSoft.Utilities.OS;

/**
 * This class manages OS related stuff.
 *
 * @author José Caetano Silva
 * @version 1.03.0001, 2026-04-03
 * @since 1.03
 */
public class OSUtils {

    // Singleton instance
    private static OSUtils instance = null;

    // 
    private static EnumCodesOS osCode = null;

    // 
    private static float osVersion = Float.NaN;

    // 
    private static final String strWin = "windows";
    private static final String strLin = "linux";
    private static final String strMac = "mac";

    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since 1.00
     * @see #getInstance()
     */
    protected OSUtils() {
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
     * Creates a new instance of the Translation class.
     *
     * @since 1.00
     * @return A new <i>TranslationUtils</i> instance.
     */
    public static synchronized OSUtils getInstance() {
        if (instance == null) {
            instance = new OSUtils();
        }
        return instance;
    }

    /**
     * Gets the user's Documents directory path.
     *
     * @return A string with the user's Documents path.
     */
    public static EnumCodesOS getOS_Code() {
        // https://www.mindprod.com/jgloss/properties.html#OSNAME
        if ((osCode == null) || (osCode == EnumCodesOS.UNKNOWN)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim().toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(strWin)) {
                        osCode = EnumCodesOS.WINDOWS;
                    } else if (str.startsWith(strLin)) {
                        osCode = EnumCodesOS.LINUX;
                    } else if (str.startsWith(strMac)) {
                        osCode = EnumCodesOS.LINUX;
                    } else {
                        // UNIX OS, like: AIX, HP-UX, Solaris, BSD
                        osCode = EnumCodesOS.UNIX;
                    }
                } else {
                    // UPS, no valid OS name
                    osCode = EnumCodesOS.UNKNOWN;
                }
            } else {
                // UPS, no OS name
                osCode = EnumCodesOS.UNKNOWN;
            }
        }

        return osCode;
    }
}
