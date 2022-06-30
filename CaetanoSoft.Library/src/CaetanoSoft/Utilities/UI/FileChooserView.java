
package CaetanoSoft.Utilities.UI;

import CaetanoSoft.Utilities.Path.PathUtils;
import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;


/**
 * @author JCaetano
 * 
 * A generic File View for files, to use in FileChooser.
 */
public class FileChooserView extends FileView {
    private String[] fileExtensions = null;
    private String fileTypeDescription = null;
    private Icon fileIcon = null;
    
    /**
     * Constructor.
     * 
     * @param extentions    The files extensions. Can't be null.
     * @param description   The file type description.
     * @param icon          The file icon.
     */
    public FileChooserView(String[] extensions, String description, Icon icon) {
        fileExtensions = extensions;
        fileTypeDescription = description;
        fileIcon = icon;
        
        // The file extension can't be null
        if (fileExtensions == null) {
            throw new IllegalArgumentException("The file extention can't be null!");
        }
    }

    @Override
    public String getName(File file) {
        return null; // Let the L&F FileView figure this out
    }

    @Override
    public String getDescription(File file) {
        return null; // Let the L&F FileView figure this out
    }

    @Override
    public Boolean isTraversable(File file) {
        return null; // Let the L&F FileView figure this out
    }

    @Override
    public String getTypeDescription(File file) {
        String type = null;
        // Get the extension of a file.
        String extension = PathUtils.getFileExtension(file);
        // Check the extension type
        if (extension != null) {
            for(int i = 0; i < fileExtensions.length; i++) {
                if (extension.equals(fileExtensions[i])) {
                    type = fileTypeDescription;
                    break;
                }
            }
        }
        // Return the file type
        return type;
    }

    @Override
    public Icon getIcon(File file) {
        Icon icon = null;
        // Get the extension of a file.
        String extension = PathUtils.getFileExtension(file);
        // Check the extension type
        if (extension != null) {
            for(int i = 0; i < fileExtensions.length; i++) {
                if (extension.equals(fileExtensions[i])) {
                    icon = fileIcon;
                    break;
                }
            }
        }
        // Return the file icon
        return icon;
    }
}
