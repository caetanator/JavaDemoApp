//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    FileChooserView.java
//
// Description:
//          This class manages the File Chooser View.
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

import CaetanoSoft.Utilities.Path.PathUtils;
import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

/**
 * A generic File View for files, to use in FileChooser.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class FileChooserView extends FileView
{
    private String[] fileExtensions = null;
    private String fileTypeDescription = null;
    private Icon fileIcon = null;
    
    /**
     * Constructor.
     * 
     * @param extensions    the files extensions (can't be <code>null</code>)
     * @param description   the file type description
     * @param icon          the file icon
     */
    public FileChooserView(String[] extensions, String description, Icon icon)
    {
        fileExtensions = extensions;
        fileTypeDescription = description;
        fileIcon = icon;
        
        // The file extension can't be null
        if (fileExtensions == null) {
            throw new IllegalArgumentException("The file extention can't be null!");
        }
    }

    // FileView methods
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
