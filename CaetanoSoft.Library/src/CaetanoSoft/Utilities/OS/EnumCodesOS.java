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
 *
 */
public enum EnumCodesOS {
    /**
     * Linux OS, like UNBUTU, Mint, Red Hat, Debian.
     */
    UNKNOWN(0),
    /**
     * Linux OS, like UNBUTU, Mint, Red Hat, Debian.
     */
    LINUX(1),
    /**
     * Windows OS, like Windows 2000, Windows XP, Windows 7, Windows 10.
     */
    WINDOWS(2),
    /**
     * macOS OS. Apple OS X.
     */
    MACOS(3),
    /**
     * UNIX OS, like BSD, AIX, HP-UX.
     */
    UNIX(4);

    private int osCode;

    /**
     * Constructer.
     *
     * @param osCode The integer value o the exit enumeration.
     */
    EnumCodesOS(int osCode) {
        this.osCode = osCode;
    }

    /**
     *
     * @return The integer value o the exit enumeration.
     */
    public int getOS_Code() {
        return this.osCode;
    }
}
