//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    CenterWindow.java
//
// Description:
//          This class manages to center a child window on a parent window.
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

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * This class manages to center a child window on a parent window.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class CenterWindow {
    /**
     * Centers a child window on a parent window.
     *
     * @param		parent	the parent window. If <code>null</code>, the desktop window (the screen) is used insted
     * @param		child		the child window can't be <code>null</code>! If so, an exception is trown
     * @exception	IllegalArgumentException	If the child window is null, this exception is thrown.
     * @since		1.0
     */
    public synchronized static final void doCenterWindow(Window parent, Window child) throws IllegalArgumentException
    {
            Rectangle rectP = null;
            Rectangle rectC = null;
            int x;
            int y;

            // The child window can't be null
            if (child == null) {
                throw new IllegalArgumentException("The child window can't be null!");
            }
            rectC = child.getBounds();
            if (parent == null) {
                // Parent is the desktop screen
                rectP = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            }
            else {
                    rectP = parent.getBounds();
            }
            // Move child to center
            x = rectP.x + (rectP.width - rectC.width) / 2;
            y = rectP.y + (rectP.height - rectC.height) / 2;
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            child.setLocation(x , y);
    }    
}

