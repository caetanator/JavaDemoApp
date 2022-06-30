/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CaetanoSoft.Utilities.UI;

import java.awt.Component;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import static javax.swing.JFileChooser.*;

/**
 *
 * @author JCaetano
 */
public class FileChooserHelper {
    /**
     * 
     * @param windowParent
     * @param strTitle
     * @param strOpenPath
     * @param bMultipleFiles
     * @param nChooseWhat
     * @param bShowAllFiles
     * @param fileFilters
     * @param fileViews
     * @return 
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
                // TODO
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
            // TODO
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
     * 
     * @param windowParent
     * @param strTitle
     * @param strOpenPath
     * @param strDefaultFile
     * @param bShowAllFiles
     * @param fileFilter
     * @param fileView
     * @return 
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
            // TODO
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
