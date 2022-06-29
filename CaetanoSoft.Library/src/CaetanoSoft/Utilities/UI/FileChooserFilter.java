
package CaetanoSoft.Utilities.UI;

import CaetanoSoft.Utilities.Path.PathUtils;
import java.io.File;
import javax.swing.filechooser.FileFilter;


/**
 * @author JCaetano
 *
 * A generic File Filter for files, to use in FileChooser.
 */
public class FileChooserFilter extends FileFilter {
    private String[] fileExtentions = null;
    private String fileDescription = null;
    
    /**
     * Constructer.
     * 
     * @param extentions    the files extensions. Can't be null
     * @param description   The file description
     */
    public FileChooserFilter(String[] extentions, String description) {
        fileExtentions = extentions;
        fileDescription = description;
        
        // The file extention can't be null
        if (fileExtentions == null) {
            throw new IllegalArgumentException("The file extention can't be null!");
        }
    }
            
    @Override
    public boolean accept(File file) {
        // Accept all directories
        if (file.isDirectory()) {
            return true;
        }
        // Get the extension of a file
        String extension = PathUtils.getFileExtension(file);
        // Check the extension type
        if (extension != null) {
            for(int i = 0; i < fileExtentions.length; i++) {
                if (extension.equals(fileExtentions[i])) {
                    return true;
                }
            }
        }
        // Not a supported file
        return false;
    }

    // The description of this filter
    @Override
    public String getDescription() {
        return fileDescription;
    }
}
