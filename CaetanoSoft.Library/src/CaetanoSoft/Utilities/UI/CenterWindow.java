
package CaetanoSoft.Utilities.UI;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author JCaetano
 */
public class CenterWindow {
    /**
     * Centers a child window on a parent window.
     *
     * @param		parent		The parent window. If <code>null</code>, the desktop window (the screen) is used insted.
     * @param		child		The child window can't be <code>null</code>! If so, an exception is trown.
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

