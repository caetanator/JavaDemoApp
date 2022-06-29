
package CaetanoSoft.Utilities.UI;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author JCaetano
 */
public class IconUtils {
    /** Loads a Image Icon.
     * 
     * @param path  a string with the icon full path
     * @return      An ImageIcon, or null if the path was invalid
     */
    public static ImageIcon createImageIcon(Object obj, String path) {
        URL imgURL = obj.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /** Loads a Image Icon.
     * 
     * @param path  a string with the icon full path
     * @return      An ImageIcon, or null if the path was invalid
     */
    public static ImageIcon createImageIcon(String path) {
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
