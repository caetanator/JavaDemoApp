//******************************************************************************
// Project: Splash Screen Manager v1.00
// URL:     http://slam.sourceforge.net
// File:    DemoApp.java
//
// Description:
//          Example of use of the SplashScreenManager Java class.
//
// Copyright (C) 2008:
//          José Caetano Silva (jcaetano@users.sourceforge.net)
//
// License:
//          This file is part of Splash Screen Manager.
//
//          Splash Screen Manager is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          Splash Screen Manager is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with Splash Screen Manager.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************

package CaetanoSoft.DemoApp;
 

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import CaetanoSoft.Utilities.Path.PathUtils;
import CaetanoSoft.Utilities.Print.PrintUtils;
import CaetanoSoft.Utilities.Translation.Translation_PT;
import CaetanoSoft.Utilities.UI.FileChooserFilter;
import CaetanoSoft.Utilities.UI.FileChooserView;
import CaetanoSoft.Utilities.UI.FileChooserHelper;
import CaetanoSoft.Utilities.UI.SplashScreenManager.SplashScreenManager;
import CaetanoSoft.Utilities.UI.IconUtils;


/**
 * This class can take a variable number of parameters on the command
 * line. Program execution begins with the main() method. The class
 * constructor is not invoked unless a window frame of type 
 * 'DemoApp' is created in the main() method.
 * 
 * @author  José Caetano Silva (jcaetano@users.sourceforge.net)
 * 
 * @version 1.00, 2008-06-10
 * 
 * @since   1.00
 */
public class DemoApp extends JFrame implements WindowListener, ActionListener {
    private static final long serialVersionUID = 7424066865309990219L;

    // Internacional resourses support
    private final ResourceBundle m_ResourcesBundle = ResourceBundle.getBundle("CaetanoSoft.DemoApp.Resources.sdResources", getLocale());

    /**
     * Menu objects.
     */
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenu menuEdit = new JMenu();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuFileOpen = new JMenuItem();
    private JMenuItem menuFileSave = new JMenuItem();
    private JMenuItem menuFilePrint = new JMenuItem();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenuItem menuEditColor = new JMenuItem();
    private JMenuItem menuHelpLicense = new JMenuItem();
    private JMenuItem menuHelpAbout = new JMenuItem();
	
    /**
     * Window objects
     */
    private JLabel labelHello = new JLabel();
    
    /**
     * <i>DemoApp</i> constructor.
     * <p>
     * Creates the frame window of the demo.
     */
    public DemoApp() {
        super();
        
        // menuFileOpen
        menuFileOpen.setText(m_ResourcesBundle.getString("MenuFileOpen_T"));
        menuFileOpen.setToolTipText(m_ResourcesBundle.getString("MenuFileOpen_TT"));
        menuFileOpen.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuFileOpen_MN"));
        //menuFileOpen.setAccelerator((KeyStroke)m_ResourcesBundle.getObject("MenuFileOpen_AK"));
        menuFileOpen.addActionListener(this);
        
        // menuFileSave
        menuFileSave.setText(m_ResourcesBundle.getString("MenuFileSave_T"));
        menuFileSave.setToolTipText(m_ResourcesBundle.getString("MenuFileSave_TT"));
        menuFileSave.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuFileSave_MN"));
        //menuFileSave.setAccelerator(KeyStroke.getKeyStroke(m_ResourcesBundle.getString("MenuFileSave_AK")));
        menuFileSave.addActionListener(this);
        
        // menuFilePrint
        menuFilePrint.setText(m_ResourcesBundle.getString("MenuFilePrint_T"));
        menuFilePrint.setToolTipText(m_ResourcesBundle.getString("MenuFilePrint_TT"));
        menuFilePrint.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuFilePrint_MN"));
        menuFilePrint.setAccelerator(KeyStroke.getKeyStroke(m_ResourcesBundle.getString("MenuFilePrint_AK")));
        menuFilePrint.addActionListener(this);
        
        // menuFileExit
        menuFileExit.setText(m_ResourcesBundle.getString("MenuFileExit_T"));
        menuFileExit.setToolTipText(m_ResourcesBundle.getString("MenuFileExit_TT"));
        menuFileExit.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuFileExit_MN"));
        //menuFileExit.setAccelerator(KeyStroke.getKeyStroke(m_ResourcesBundle.getString("MenuFileExit_AK")));
        menuFileExit.addActionListener(this);
        
        // menuEditColor
        menuEditColor.setText(m_ResourcesBundle.getString("MenuEditColor_T"));
        menuEditColor.setToolTipText(m_ResourcesBundle.getString("MenuEditColor_TT"));
        menuEditColor.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuEditColor_MN"));
        //menuEditColor.setAccelerator(KeyStroke.getKeyStroke(m_ResourcesBundle.getString("MenuEditColor_AK")));
        menuEditColor.addActionListener(this);
         
        // menuHelpLicense
        menuHelpLicense.setText(m_ResourcesBundle.getString("MenuHelpLicense_T"));
        menuHelpLicense.setToolTipText(m_ResourcesBundle.getString("MenuHelpLicense_TT"));
        menuHelpLicense.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuHelpLicense_MN")); 
        //menuHelpLicense.setAccelerator((KeyStroke)m_ResourcesBundle.getObject("MenuHelpLicense_AK"));     
        menuHelpLicense.addActionListener(this);
        
        // menuHelpAbout
        menuHelpAbout.setText(m_ResourcesBundle.getString("MenuHelpAbout_T"));
        menuHelpAbout.setToolTipText(m_ResourcesBundle.getString("MenuHelpAbout_TT"));
        menuHelpAbout.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuHelpAbout_MN"));
        menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke(m_ResourcesBundle.getString("MenuHelpAbout_AK")));
        menuHelpAbout.addActionListener(this);
        
        // menuFile
        menuFile.setText(m_ResourcesBundle.getString("MenuFile_T"));
        menuFile.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuFile_MN"));
        menuFile.add(menuFileOpen);
        menuFile.addSeparator();
        menuFile.add(menuFileSave);
        menuFile.addSeparator();
        menuFile.add(menuFilePrint);
        menuFile.addSeparator();
        menuFile.add(menuFileExit);
        
        // menuEdit
        menuEdit.setText(m_ResourcesBundle.getString("MenuEdit_T"));
        menuEdit.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuEdit_MN"));
        menuEdit.addSeparator();
        menuEdit.add(menuEditColor);
                
        // menuHelp
        menuHelp.setText(m_ResourcesBundle.getString("MenuHelp_T"));
        menuHelp.setMnemonic((Integer)m_ResourcesBundle.getObject("MenuHelp_MN"));
        menuHelp.add(menuHelpLicense);
        menuHelp.addSeparator();
        menuHelp.add(menuHelpAbout);
        
        // menuBar
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
        //menuBar.setHelpMenu(menuHelp);
        
        // labelHello
        labelHello.setText(m_ResourcesBundle.getString("LabelHello_T"));
        labelHello.setVerticalTextPosition(JLabel.CENTER);
        labelHello.setHorizontalTextPosition(JLabel.CENTER);
        labelHello.setVerticalAlignment(JLabel.CENTER);
        labelHello.setHorizontalAlignment(JLabel.CENTER);
        labelHello.setBackground(Color.WHITE);
        labelHello.setMinimumSize(new Dimension(200, 100));
        
        // JFrame
        URL urlImage = getClass().getResource("Resources/AppIcon.gif");
        Image im = Toolkit.getDefaultToolkit().getImage(urlImage);
        this.setIconImage(im);
        this.setTitle("Splash Sreen Demo");
        this.setMinimumSize(new Dimension(300, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.addWindowListener(this);
        this.add(labelHello, BorderLayout.CENTER);
        this.pack();
    }
    
    // ActionListener methods
    /**
     * Handles the menu events.
     * 
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent e)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object objSource = e.getSource();
        // Menu Items
		if (objSource instanceof JMenuItem) {
            JMenuItem m = (JMenuItem)objSource;
            if (m.equals(menuFileOpen)) {
                doMenuFileOpen();
                return;
            }
            
            if (m.equals(menuFileSave)) {
                doMenuFileSave();
                return;
            }

			if (m.equals(menuFilePrint)) {
                doMenuFilePrint();
                return;
            }
            
            if (m.equals(menuFileExit)) {
                doMenuFileExit();
                return;
            }
            
            if (m.equals(menuEditColor)) {
                doMenuEditColor();
                return;
            }
            
            if (m.equals(menuHelpLicense)) {
                doMenuHelpLicense();
                return;
            }
            
            if (m.equals(menuHelpAbout)) {
                doMenuHelpAbout();
                return;
            }
	}
    }

    // WindowListener methods
    /**
     * Frame window is opened.
     * 
     * @see java.awt.event.WindowListener#windowOpened(WindowEvent e)
     */
    @Override
    public void windowOpened(WindowEvent e) {
    }
    
    /**
     * Frame window is closing.
     * <p>
     * Call <i>dispose()</i> to confirm window destruction.
     *  
     * @see java.awt.event.WindowListener#windowClosing(WindowEvent e)
     */
    @Override
    public void windowClosing(WindowEvent e){
        if (e.getWindow().equals(this)) {
            int i = JOptionPane.showConfirmDialog(this, m_ResourcesBundle.getString("MBCheckExit_T"), this.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                this.dispose();
            }
        }
    }
    
    /**
     * Frame window is closed.
     * <p>
     * Call <i>System.exit()</i> to exit the application.
     * 
     * @see java.awt.event.WindowListener#windowClosed(WindowEvent e)
     */
    @Override
    public void windowClosed(WindowEvent e) {
        if (e.getWindow().equals(this)) {
            this.setVisible(false);
            System.exit(0);
        }
    };

    /**
     * Frame window is activated.
     * 
     * @see java.awt.event.WindowListener#windowActivated(WindowEvent e)
     */
    @Override
    public void windowActivated(WindowEvent e) {
    }

    /**
     * Frame window is deactivated.
     * 
     * @see java.awt.event.WindowListener#windowDeactivated(WindowEvent e)
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    /**
     * Frame window is iconified.
     * 
     * @see java.awt.event.WindowListener#windowIconified(WindowEvent e)
     */
    @Override
    public void windowIconified(WindowEvent e) {
    }

    /**
     * Frame window is deiconified.
     * @see java.awt.event.WindowListener#windowDeiconified(WindowEvent e)
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    
    /**
     * Executes de Menu File Open.
     */
    private void doMenuFileOpen() {
        String strOpenPath = PathUtils.getApplicationPath(this);
        String[] strExtentions1 = {"txt"};
        String[] strExtentions2 = {"c", "cpp", "h", "hpp"};
        String[] strExtentions3 = {"c", "java", "cpp"};
        
        FileFilter[] fileFilters = new FileFilter[3];
        fileFilters[0] = new FileChooserFilter(strExtentions1, "Text file");
        fileFilters[1] = new FileChooserFilter(strExtentions2, "C/C++ source file");
        fileFilters[2] = new FileChooserFilter(strExtentions3, "Programing source file");
        
        FileView[] fileViews = new FileView[3];
        fileViews[0] = new FileChooserView(strExtentions1, "Text file type", IconUtils.createImageIcon(this, "Resources/gifIcon.gif"));
        fileViews[1] = new FileChooserView(strExtentions2, "C/C++ source file type", IconUtils.createImageIcon(this, "Resources/jpgIcon.gif"));
        
        // Select the file(s)
        File[] files = FileChooserHelper.showFileOpenDialog((JFrame)this, "Open a File", strOpenPath, false, JFileChooser.FILES_ONLY, true, fileFilters, fileViews);

        if (files != null && files.length > 0) {
            // Set's the wait application cursor
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            // Do stuff
        }
        // Set's the normal/arrow application cursor
        this.setCursor(Cursor.getDefaultCursor());
        updateMainMenu();
    }
    
    /**
     * Executes de Menu File Save.
     */
    private void doMenuFileSave() {
        String strOpenPath = PathUtils.getApplicationPath(this);
        String strFile = "SaveMe.txt";
        String[] strExtentions = {"txt"};
        FileFilter fileFilter = new FileChooserFilter(strExtentions, "Text file");
        FileView fileView = new FileChooserView(strExtentions, "Text file type", IconUtils.createImageIcon(this, "Resources/pngIcon.png"));

        File file = FileChooserHelper.showFileSaveDialog((JFrame)this, "Save a File", strOpenPath, strFile, true, fileFilter, fileView);

        if (file != null) {
            if (file.exists()) {
                int n = JOptionPane.showConfirmDialog(this, "O ficheiro já existe.\nDeseja substituir-lo?", "Erro Ficheiro Existente", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.NO_OPTION) {
                    // Set's the normal/arrow application cursor
                    this.setCursor(Cursor.getDefaultCursor());
                    updateMainMenu();
                    return;
                }
            }
            // Set's the wait application cursor
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            // Do stuff
        }
        // Set's the normal/arrow application cursor
        this.setCursor(Cursor.getDefaultCursor());
        updateMainMenu();
    }

    /**
     * Executes de Menu File Print
     */
    private void doMenuFilePrint() {
            PrintUtils.printComponent(labelHello);

            /*
            PrinterJob printJob = PrinterJob.getPrinterJob();
            PageFormat pf = printJob.defaultPage();
            printJob.setPrintable(new PrintableClass(), pf);
            //printJob.setPrintable(this);
            if (printJob.printDialog()) {
                    try {
                            printJob.print();
                    }
                    catch(PrinterException ex) {
                            ex.printStackTrace();
                    }
            }
             */
    }
    
    /**
     * Executes de Menu File Exit
     */
    private void doMenuFileExit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    /**
     * Executes de Menu Edit Color
     */
    private void doMenuEditColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose Background Color", labelHello.getBackground());
        labelHello.setBackground(newColor);
        labelHello.setOpaque(true);
        labelHello.repaint();
    }
    
    /**
     * Executes de Menu Help License
     */
    private void doMenuHelpLicense() {
        JOptionPane.showMessageDialog(this, 
                "Splash Demo is licensed under the GNU General Public License version 3.\n" + 
                "For more information, please visit www.gnu.org.\n\n" +
                "CaetanoSoft (c) 2008-2009 All rights reserved.", 
                this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Executes de Menu Help About
     */
    private void doMenuHelpAbout() {
        JOptionPane.showMessageDialog(this, "CaetanoSoft\nSplash Demo v1.00", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateMainMenu() {
        
    }
    
    /**
     * Runs the demo.
     * 
     * @param args  The command line parameters
     */
    public static void main(String args[]) {
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                // Swing translations
                final Translation_PT translations = Translation_PT.getInstance();
                // Splash Screen Manager
                final SplashScreenManager ssManager = SplashScreenManager.getInstance();
                if (ssManager != null)
                {
                    ssManager.setTextRectangle(new Rectangle(15, 140, 270, 22));
                    ssManager.setFontName("Verdana");
                    ssManager.setFontSize(16);
                    //sm.setFontStyle(Font.BOLD | Font.ITALIC);
                    ssManager.setFontStyle(Font.PLAIN);
                    ssManager.setFontColor(Color.BLACK);
                    ssManager.setFontBackground(Color.LIGHT_GRAY);
                    ssManager.setBarRectangle(new Rectangle(15, 170, 270, 15));
                    ssManager.setBarColor(Color.GREEN);
                    ssManager.setBarBackground(Color.WHITE);
                    ssManager.render("Loading Java Base Classes...", 50);
                    // Do nothing
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) {
                    }

                    ssManager.render("Connecting to SQL DB...", 60);
                    // Do nothing
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) {
                    }

                    ssManager.render("Creating GUI...", 95);
                    // Do nothing
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) {
                    }
                }
                JFrame frame = new DemoApp();
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                ssManager.close();
                frame.setVisible(true);
                frame.toFront();
         }
       };

       EventQueue.invokeLater(runner);
    }
}
