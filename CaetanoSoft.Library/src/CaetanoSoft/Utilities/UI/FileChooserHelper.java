//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    FileChooserHelper.java
//
// Description:
//          This class manages the File Chooser Helper.
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

import java.awt.Component;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import static javax.swing.JFileChooser.*;

/**
 * A generic File Chooser Helper for files, to use in FileChooser.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2022-07-26
 * @since 1.00
 */
public class FileChooserHelper
{
    /**
     * Handles the FileOpenDialog, with a file extension filter.

     * @param windowParent
     * @param strTitle
     * @param strOpenPath
     * @param bMultipleFiles
     * @param nChooseWhat
     * @param bShowAllFiles
     * @param fileFilters
     * @param fileViews
     * @return An array of files to be open.
     */
    public synchronized static File[] showFileOpenDialog(Component windowParent, String strTitle, String strOpenPath, boolean bMultipleFiles, int nChooseWhat, boolean bShowAllFiles, FileFilter[] fileFilters, FileView[] fileViews)
    {
        String[] extensions = {"jpg", "jpeg", "png", "tif", "tiff", "gif", "bmp"};
        File [] returnFiles = null;
        JFileChooser fileChooserDialog = new JFileChooser();
        fileChooserDialog.setDialogType(OPEN_DIALOG);
        FileChooserPreview fileCHP = new FileChooserPreview(fileChooserDialog, extensions);
        
        // Clear the previus file(s) selection
        if (fileChooserDialog.isMultiSelectionEnabled()) {
            fileChooserDialog.setSelectedFiles(null);
        }
        else {
            fileChooserDialog.setSelectedFile(null);
        }
        // Set the initial path
        if (strOpenPath != null) {
            File filePath = new File(strOpenPath);
            fileChooserDialog.setCurrentDirectory(filePath);
        }
        else {
            // Go to the default OS dependent user path
            fileChooserDialog.setCurrentDirectory(null);
        }
        // Set the dialog title
        if (strTitle != null) {
            fileChooserDialog.setDialogTitle(strTitle);
        }
        else {
            // Do nothing
        }
        // Disable the accepting all files if a File View is given
        if (bShowAllFiles) {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(true);
        }
        else {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(false);
        }
        // Add the accepting files if a File Filter is given
        if (fileFilters != null) {
            for(int i = 0; i < fileFilters.length; i++) {
                fileChooserDialog.addChoosableFileFilter(fileFilters[i]);
            }
            fileChooserDialog.setFileFilter(fileFilters[0]);
        }
        else {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(true);
        }
        // Set the File View if it is given
        if (fileViews != null) {
            for(int i = 0; i < fileFilters.length; i++) {
                // TODO: 
            }
            fileChooserDialog.setFileView(fileViews[0]);
        }
        else {
            fileChooserDialog.setFileView(null);
        }
        // Set single or multiple file(s) mode
        fileChooserDialog.setMultiSelectionEnabled(bMultipleFiles);
        // Set the type of selection allowed
        fileChooserDialog.setFileSelectionMode(nChooseWhat);
        // Clear the preview from the previous display of the chooser
        JComponent accessory = fileChooserDialog.getAccessory();
        if (accessory != null) {
            // TODO: 
            ((FileChooserPreview)accessory).loadImage();
        }
        // Shows the dialog
        int retVal = fileChooserDialog.showDialog(windowParent, null);
        switch (retVal) {
            case APPROVE_OPTION:
                // At least one file was choosen
                if (fileChooserDialog.isMultiSelectionEnabled()) {
                    returnFiles = fileChooserDialog.getSelectedFiles();
                }
                else {
                    returnFiles = new File[1];
                    returnFiles[0] = fileChooserDialog.getSelectedFile();
                }
                break;

            case CANCEL_OPTION:
                // User cancelled operation. No file was chosen
                break;

            case ERROR_OPTION:
                // An error occured. No file was chosen
                break;

            default:
                // Unknown operation occured
                break;
        }

        return returnFiles;
    }
    
    /**
     * Handles the FileSaveDialog, with a file extension filter.
     *
     * @param windowParent
     * @param strTitle
     * @param strOpenPath
     * @param strDefaultFile
     * @param bShowAllFiles
     * @param fileFilter
     * @param fileView
     * @return The saved file.
     */
    public synchronized static File showFileSaveDialog(Component windowParent, String strTitle, String strOpenPath, String strDefaultFile, boolean bShowAllFiles, FileFilter fileFilter, FileView fileView)
    {
        String[] extensions = {"jpg", "jpeg", "png", "tif", "tiff", "gif", "bmp"};
        File returnFile = null;
        JFileChooser fileChooserDialog = new JFileChooser();
        fileChooserDialog.setDialogType(JFileChooser.SAVE_DIALOG);
        FileChooserPreview fileCHP = new FileChooserPreview(fileChooserDialog, extensions);
        
        // Clear the previus file(s) selection
        if (fileChooserDialog.isMultiSelectionEnabled()) {
            fileChooserDialog.setSelectedFiles(null);
        }
        else {
            fileChooserDialog.setSelectedFile(null);
        }
        // Set the initial path
        if (strOpenPath != null) {
            File filePath = new File(strOpenPath);
            fileChooserDialog.setCurrentDirectory(filePath);
        }
        else {
            // Go to the default OS dependent user path
            fileChooserDialog.setCurrentDirectory(null);
        }
        // Set the default file
        if (strDefaultFile != null) {
            File file = new File(strDefaultFile);
            fileChooserDialog.setSelectedFile(file);
        }
        else {
            // Do nothing
        }
        // Set the dialog title
        if (strTitle != null) {
            fileChooserDialog.setDialogTitle(strTitle);
        }
        else {
            // Do nothing
        }
        // Disable the accepting all files if a File View is given
        if (bShowAllFiles) {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(true);
        }
        else {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(false);
        }
	      // Add the accepting files if a File Filter is given
        if (fileFilter != null) {
            fileChooserDialog.setFileFilter(fileFilter);
        }
        else {
            fileChooserDialog.setFileFilter(null);
            fileChooserDialog.setAcceptAllFileFilterUsed(true);
        }
	      // Set the File View if it is given
        if (fileView != null) {
            fileChooserDialog.setFileView(fileView);
        }
        else {
            fileChooserDialog.setFileView(null);
        }
        // Set single or multiple file(s) mode
        fileChooserDialog.setMultiSelectionEnabled(false);
        // Set the type of selection allowed
        fileChooserDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Clear the preview from the previous display of the chooser
        JComponent accessory = fileChooserDialog.getAccessory();
        if (accessory != null) {
            // TODO: 
            ((FileChooserPreview)accessory).loadImage();
        }
        // Shows the dialog
        int retVal = fileChooserDialog.showDialog(windowParent, null);
        switch (retVal) {
            case JFileChooser.APPROVE_OPTION:
                // At least one file was choosen
                if (fileChooserDialog.isMultiSelectionEnabled()) {
                    // Returns the first file
                    returnFile = fileChooserDialog.getSelectedFiles()[0];
                }
                else {
                    returnFile = fileChooserDialog.getSelectedFile();
                }
                break;

            case JFileChooser.CANCEL_OPTION:
                // User cancelled operation. No file was chosen
                break;

            case JFileChooser.ERROR_OPTION:
                // An error occured. No file was chosen
                break;

            default:
                // Unknown operation occured
                break;
        }

        return returnFile;
    }
}
