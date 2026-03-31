//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    FileChooserPreview.java
//
// Description:
//          This class manages the File Chooser Preview.
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * A generic File Chooser Preview for files, to use in FileChooser.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class FileChooserPreview extends JComponent implements PropertyChangeListener
{
    private JFileChooser fileChooserDialog = null;
    private String[] fileExtensions = null;
    private ImageIcon thumbnail = null;
    private File file = null;

    public FileChooserPreview(JFileChooser fc, String[] extensions)
    {
        fileChooserDialog = fc;
        fileExtensions = extensions;
        // The file extension can't be null
        if (fileExtensions == null) {
            throw new IllegalArgumentException("The file extention can't be null!");
        }
        setPreferredSize(new Dimension(100, 50));
        fileChooserDialog.addPropertyChangeListener(this);
        fileChooserDialog.setAccessory(this);
    }

    public void loadImage()
    {
        if (file == null) {
            thumbnail = null;
            return;
        }

        // Don't use createImageIcon (which is a wrapper for getResource)
        // because the image we're trying to load is probably not one
        // of this program's own resources.
        ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > 90) {
                thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
            } 
            else {
                //no need to miniaturize
                thumbnail = tmpIcon;
            }
        }
    }
    
    // PropertyChangeListener methods
    /**
     * @see java.beans.PropertyChangeListener
     */
    @Override
    public void propertyChange(PropertyChangeEvent e)
    {
        boolean update = false;
        String prop = e.getPropertyName();

        // If the directory changed, don't show an image
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            file = null;
            update = true;
        } 
        else { 
            // If a file became selected, find out which one
            if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
                file = (File) e.getNewValue();
                update = true;
            }
        }

        // Update the preview accordingly
        if (update) {
            thumbnail = null;
            if (isShowing()) {
                loadImage();
                repaint();
            }
        }
    }

    /**
     * @see javax.swing.JComponent#paintComponent
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        // Paint a preview of the selected file
        if (thumbnail == null) {
            loadImage();
        }
        if (thumbnail != null) {
            int x = getWidth()/2 - thumbnail.getIconWidth()/2;
            int y = getHeight()/2 - thumbnail.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumbnail.paintIcon(this, g, x, y);
        }
    }    
}
