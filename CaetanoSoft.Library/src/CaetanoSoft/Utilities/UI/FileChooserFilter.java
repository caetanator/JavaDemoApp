//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    FileChooserFilter.java
//
// Description:
//          This class manages the File Chooser Filter.
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
import javax.swing.filechooser.FileFilter;


/**
 * A generic File Filter for files, to use in FileChooser.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class FileChooserFilter extends FileFilter {
    private String[] fileExtentions = null;
    private String fileDescription = null;
    
    /**
     * Constructor.
     * 
     * @param extentions    the files extensions (can't be <code>null</code>)
     * @param description   the file description
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
