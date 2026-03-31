//******************************************************************************
// Project: CaetanoSoft.JavaDemoApp
// URL:     https://github.com/caetanator/JavaJavaDemoApp/
// File:    JavaDemoApp.java
//
// Description:
//          This class implements a Java Swing GUI application.
//          It can be used to demonstrate a basic Java GUI interface or
//          as a template for other application.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
//
// License:
//          This file is part of CaetanoSoft.JavaDemoApp.
//
//          CaetanoSoft.JavaDemoApp is free software: you can redistribute it and/or 
//          modify it under the terms of the GNU General Public License as 
//          published by the Free Software Foundation, either version 3 of the 
//          License, or (at your option) any later version.
//
//          CaetanoSoft.JavaDemoApp is distributed in the hope that it will be 
//          useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
//          of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.JavaDemoApp. If not, see 
//          <https://www.gnu.org/licenses/gpl-3.0.html>.
//******************************************************************************


package CaetanoSoft.JavaDemoApp;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import CaetanoSoft.Utilities.Path.PathUtils;
import CaetanoSoft.Utilities.Print.PrintUtils;
import CaetanoSoft.Utilities.Translation.TranslationUtils;
import CaetanoSoft.Utilities.UI.CenterWindow;
import CaetanoSoft.Utilities.UI.FileChooserFilter;
import CaetanoSoft.Utilities.UI.FileChooserHelper;
import CaetanoSoft.Utilities.UI.FileChooserView;
import CaetanoSoft.Utilities.UI.IconUtils;
import CaetanoSoft.Utilities.UI.SplashScreenManager.SplashScreenManager;

/**
 * This class can take a variable number of parameters on the command
 * line. Program execution begins with the main() method. The class
 * constructor is not invoked unless a window frame of type 
 * 'JavaDemoApp' is created in the main() method.
 * 
 * <p></p>
 * Usage:
 * <ul>
 *	<li>[|JAVA|] JavaDemoApp.jar [-h]</li>
 *      <li>&nbsp;</li>
 *      <li>-h: Prints this help screen and exit</li>
 * </ul>
 * <p></p>
 * Returned Error Codes:
 * <ul>
 *	<li> 0: OK</li>
 *	<li>-1: Error: Unhandled Java exception</li>
 *	<li> 1: Error: Invalid number of parameters</li>
 *	<li> 2: Error: Invalid parameter</li>
 *	<li> 3: Error: Duplicated parameter</li>
 *	<li> 4: Error: Configuration file not found or invalid</li>
 *	<li> 5: Error: Invalid or non-existent input file</li>
 *	<li> 6: Error: Invalid or non-existent output file</li>
 *      <li> 7: Error: Invalid database connection or operation</li>
 *      <li> 8: Error: Invalid server IP/Name</li>
 *      <li> 9: Error: Invalid TCP/UDP service port</li>
 * </ul>
 * 
 * @author José Caetano Silva
 * @version 1.3.0, 2026-03-30
 * @since   1.0
 */
public class JavaDemoApp extends JFrame implements WindowListener, ActionListener
{
    // Java class serial version UID
    /**
     * @see java.io.Serializable
     */
    //private static final long serialVersionUID = 7424066865309990219L;
    
    // Application data
    /**
     * A string with the application's name.
     */
    private static final String APP_NAME = "Java Demo App";
    /**
     * A string with the application's version.
     */
    private static final String APP_VERSION = "1.3.0001";
    /**
     * Complete path to configuration file.
     */
    private static String m_strConfigFile = "JavaDemoApp.properties";
     
    // Exit error codes
    public enum ExitErrorCodes {
        /**
         * The application terminated normaly, without errors.
         */
        EXIT_OK(0),
        /**
         * The application terminated abnormaly, with an unhandled Java exception.
         */
        EXIT_ERROR_EXCEPTION(-1),
        /**
         * The application terminated abnormaly, because the number of parameters is wrong.
         */
        EXIT_ERROR_BAD_NUMBER_PARAMETERS(1),
        /**
         * The application terminated abnormaly, because one parameter is wrong.
         */
        EXIT_ERROR_BAD_PARAMETER(2),
        /**
         * The application terminated abnormaly, because one parameter is duplicated.
         */
        EXIT_ERROR_PARAMETER_DUPLICATED(3),
        /**
         * The application terminated abnormaly, because the configuration file was not found or is invalid.
         */
        EXIT_ERROR_BAD_CONFIG_FILE(4),
        /**
         * The application terminated abnormaly, because of invalid or non-existent input file.
         */
        EXIT_ERROR_BAD_INPUT_FILE(5),
        /**
         * The application terminated abnormaly, because of invalid or non-existent output file.
         */
        EXIT_ERROR_BAD_OUTPUT_FILE(6),
        /**
         * The application terminated abnormaly, because of invalid database connection or operation.
         */
        EXIT_ERROR_BAD_DB_OPERATION(7),
        /**
         * The application terminated abnormaly, because of invalid server IP/Name.
         */
        EXIT_ERROR_BAD_SERVER_IP(8),
        /**
         * The application terminated abnormaly, because of invalid TCP/UDP service port.
         */
        EXIT_ERROR_BAD_PORT_NUMBER(9);

        private int errorCode;

        ExitErrorCodes(int errorCode)
        {
            this.errorCode = errorCode;
        }
        
        public int getErrorCode()
        {
            return this.errorCode;
        }
    }
       
    // Debug levels
    private static final int DEBUG_LEVEL_NONE    = 0x00000000;
    private static final int DEBUG_LEVEL_ERROR   = 0x00000001;
    private static final int DEBUG_LEVEL_WARNING = 0x00000002;
    private static final int DEBUG_LEVEL_INFO    = 0x00000004;
    private static final int DEBUG_LEVEL_ALL     = 0xFFFFFFFF;
    
    /**
     * Application nevel of debuggin: 
     *   0 => Off;
     *   1 => Errors (default value);
     *   2 => Warnings;
     *   3 => Information;
     *  ~0 => All.
     */
    private static int m_fLogLevel = DEBUG_LEVEL_ERROR;
    
    /**
     * Logger object to output errors, warnings, debugging information.
     */
    private static Logger m_objLogger = null;
    
    // Database connection information
    /**
     * JDBC Driver class name.
     */
    private static String m_strJdbcDriver = "";
    /**
     * JDBC connection URL.
     */
    private static String m_strJdbcURL = "";
    /**
     * JDBC connection user name.
     */
    private static String m_strJdbcUsername = "";
    /**
     * JDBC connection user password.
     */
    private static String m_strJdbcPassword = "";
    /**
     * JDBC connection timeout (in milliseconds).
     */
    private static int m_nConnectionTimeout = 5000;
    /**
     * JDBC connection object to be used.
     */
    private static Connection m_dbConnection = null;
    
    // Threads
    /**
     * Controls if all the running threds must terminate:
     *     <code>false</code> if threads can continue running;
     *     <code>true</code> if threads must end.
     */
    private static boolean m_bEndThreads = false;
    /**
     * Array with all the threads.
     */
    private static Thread[] m_arrTreads = null;  
    /**
     * Default time (in milliseconds) to pause the threads.
     */
    private static int m_nThreadSleepTime = 1000;
    
    /**
     * Object for internacional resourses support.
     */
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("CaetanoSoft.JavaDemoApp.Resources.sdResources", getLocale());
    
    // GUI widgets
    /**
     * Menu objects.
     */
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuFile = new JMenu();
    private final JMenu menuEdit = new JMenu();
    private final JMenu menuHelp = new JMenu();
    private final JMenuItem menuFileOpen = new JMenuItem();
    private final JMenuItem menuFileSave = new JMenuItem();
    private final JMenuItem menuFilePrint = new JMenuItem();
    private final JMenuItem menuFileExit = new JMenuItem();
    private final JMenuItem menuEditColor = new JMenuItem();
    private final JMenuItem menuHelpLicense = new JMenuItem();
    private final JMenuItem menuHelpAbout = new JMenuItem();
	
    /**
     * Window objects.
     */
    private final JLabel labelHello = new JLabel();
    
    /**
     * <code>JavaDemoApp</code> constructor.
     * <p>
     * Creates the frame window of the demo.
     */
    public JavaDemoApp()
    {
        super();
        
        // menuFileOpen
        menuFileOpen.setText(resourceBundle.getString("MenuFileOpen_T"));
        menuFileOpen.setToolTipText(resourceBundle.getString("MenuFileOpen_TT"));
        menuFileOpen.setMnemonic((Integer)resourceBundle.getObject("MenuFileOpen_MN"));
        //menuFileOpen.setAccelerator((KeyStroke)resourceBundle.getObject("MenuFileOpen_AK"));
        menuFileOpen.addActionListener(this);
        
        // menuFileSave
        menuFileSave.setText(resourceBundle.getString("MenuFileSave_T"));
        menuFileSave.setToolTipText(resourceBundle.getString("MenuFileSave_TT"));
        menuFileSave.setMnemonic((Integer)resourceBundle.getObject("MenuFileSave_MN"));
        //menuFileSave.setAccelerator(KeyStroke.getKeyStroke(resourceBundle.getString("MenuFileSave_AK")));
        menuFileSave.addActionListener(this);
        
        // menuFilePrint
        menuFilePrint.setText(resourceBundle.getString("MenuFilePrint_T"));
        menuFilePrint.setToolTipText(resourceBundle.getString("MenuFilePrint_TT"));
        menuFilePrint.setMnemonic((Integer)resourceBundle.getObject("MenuFilePrint_MN"));
        menuFilePrint.setAccelerator(KeyStroke.getKeyStroke(resourceBundle.getString("MenuFilePrint_AK")));
        menuFilePrint.addActionListener(this);
        
        // menuFileExit
        menuFileExit.setText(resourceBundle.getString("MenuFileExit_T"));
        menuFileExit.setToolTipText(resourceBundle.getString("MenuFileExit_TT"));
        menuFileExit.setMnemonic((Integer)resourceBundle.getObject("MenuFileExit_MN"));
        //menuFileExit.setAccelerator(KeyStroke.getKeyStroke(resourceBundle.getString("MenuFileExit_AK")));
        menuFileExit.addActionListener(this);
        
        // menuEditColor
        menuEditColor.setText(resourceBundle.getString("MenuEditColor_T"));
        menuEditColor.setToolTipText(resourceBundle.getString("MenuEditColor_TT"));
        menuEditColor.setMnemonic((Integer)resourceBundle.getObject("MenuEditColor_MN"));
        //menuEditColor.setAccelerator(KeyStroke.getKeyStroke(resourceBundle.getString("MenuEditColor_AK")));
        menuEditColor.addActionListener(this);
         
        // menuHelpLicense
        menuHelpLicense.setText(resourceBundle.getString("MenuHelpLicense_T"));
        menuHelpLicense.setToolTipText(resourceBundle.getString("MenuHelpLicense_TT"));
        menuHelpLicense.setMnemonic((Integer)resourceBundle.getObject("MenuHelpLicense_MN")); 
        //menuHelpLicense.setAccelerator((KeyStroke)resourceBundle.getObject("MenuHelpLicense_AK"));     
        menuHelpLicense.addActionListener(this);
        
        // menuHelpAbout
        menuHelpAbout.setText(resourceBundle.getString("MenuHelpAbout_T"));
        menuHelpAbout.setToolTipText(resourceBundle.getString("MenuHelpAbout_TT"));
        menuHelpAbout.setMnemonic((Integer)resourceBundle.getObject("MenuHelpAbout_MN"));
        menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke(resourceBundle.getString("MenuHelpAbout_AK")));
        menuHelpAbout.addActionListener(this);
        
        // menuFile
        menuFile.setText(resourceBundle.getString("MenuFile_T"));
        menuFile.setMnemonic((Integer)resourceBundle.getObject("MenuFile_MN"));
        menuFile.add(menuFileOpen);
        menuFile.addSeparator();
        menuFile.add(menuFileSave);
        menuFile.addSeparator();
        menuFile.add(menuFilePrint);
        menuFile.addSeparator();
        menuFile.add(menuFileExit);
        
        // menuEdit
        menuEdit.setText(resourceBundle.getString("MenuEdit_T"));
        menuEdit.setMnemonic((Integer)resourceBundle.getObject("MenuEdit_MN"));
        menuEdit.addSeparator();
        menuEdit.add(menuEditColor);
                
        // menuHelp
        menuHelp.setText(resourceBundle.getString("MenuHelp_T"));
        menuHelp.setMnemonic((Integer)resourceBundle.getObject("MenuHelp_MN"));
        menuHelp.add(menuHelpLicense);
        menuHelp.addSeparator();
        menuHelp.add(menuHelpAbout);
        
        // menuBar
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
        //menuBar.setHelpMenu(menuHelp);
        
        // labelHello
        labelHello.setText(resourceBundle.getString("LabelHello_T"));
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
        this.setTitle("Java Demo App");
        this.setMinimumSize(new Dimension(300, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.addWindowListener(this);
        this.add(labelHello, BorderLayout.CENTER);
        this.pack();
        // Center the main window widget on the screen
        CenterWindow.doCenterWindow(null, this);
    }
    
    // ActionListener methods
    /**
     * Handles the menu events.
     * 
     * @param event
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent event)
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object objSource = event.getSource();
        // Menu Items
	if (objSource instanceof JMenuItem)
        {
            JMenuItem m = (JMenuItem)objSource;
            if (m.equals(menuFileOpen))
            {
                doMenuFileOpen();
            }
            else if (m.equals(menuFileSave))
            {
                doMenuFileSave();
            }
            else if (m.equals(menuFilePrint))
            {
                doMenuFilePrint();
            }
            else if (m.equals(menuFileExit))
            {
                doMenuFileExit();
            }
            else if (m.equals(menuEditColor))
            {
                doMenuEditColor();
            }
            else if (m.equals(menuHelpLicense))
            {
                doMenuHelpLicense();
            }
            else if (m.equals(menuHelpAbout))
            {
                doMenuHelpAbout();
            }
            else
            {
                // Do nothing
                // TODO: Make error?
            }
	}
    }

    // WindowListener methods
    /**
     * Frame window is opened.
     * 
     * @param event
     * @see java.awt.event.WindowListener#windowOpened(WindowEvent event)
     */
    @Override
    public void windowOpened(WindowEvent event)
    {
        // Do nothing
    }
    
    /**
     * Frame window is closing.
     * <p>
     * Call <i>dispose()</i> to confirm window destruction.
     *  
     * @param event
     * @see java.awt.event.WindowListener#windowClosing(WindowEvent event)
     */
    @Override
    public void windowClosing(WindowEvent event)
    {
        if (event.getWindow().equals(this))
        {
            int i = JOptionPane.showConfirmDialog(this, resourceBundle.getString("MBCheckExit_T"), this.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION)
            {
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
     * @param event
     * @see java.awt.event.WindowListener#windowClosed(WindowEvent event)
     */
    @Override
    public void windowClosed(WindowEvent event)
    {
        if (event.getWindow().equals(this))
        {
            this.setVisible(false);
            System.exit(ExitErrorCodes.EXIT_OK.getErrorCode());
        }
    };

    /**
     * Frame window is activated.
     * 
     * @param event
     * @see java.awt.event.WindowListener#windowActivated(WindowEvent event)
     */
    @Override
    public void windowActivated(WindowEvent event)
    {
        // Do nothing
    }

    /**
     * Frame window is deactivated.
     * 
     * @param event
     * @see java.awt.event.WindowListener#windowDeactivated(WindowEvent event)
     */
    @Override
    public void windowDeactivated(WindowEvent event)
    {
        // Do nothing
    }

    /**
     * Frame window is iconified.
     * 
     * @param event
     * @see java.awt.event.WindowListener#windowIconified(WindowEvent event)
     */
    @Override
    public void windowIconified(WindowEvent event)
    {
        // Do nothing
    }

    /**
     * Frame window is deiconified.
     * 
     * @param event
     * @see java.awt.event.WindowListener#windowDeiconified(WindowEvent event)
     */
    @Override
    public void windowDeiconified(WindowEvent event)
    {
        // Do nothing
    }
    
    /**
     * Executes de Menu File Open.
     */
    private void doMenuFileOpen()
    {
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

        if (files != null && files.length > 0)
        {
            // Set's the wait application cursor
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            // Do stuff
        }
        // Set's the normal/arrow application cursor
        this.setCursor(Cursor.getDefaultCursor());
        updateMainMenu();
    }
    
    /**
     * Executes de Menu File Savevent.
     */
    private void doMenuFileSave()
    {
        String strOpenPath = PathUtils.getApplicationPath(this);
        String strFile = "SaveMevent.txt";
        String[] strExtentions = {"txt"};
        FileFilter fileFilter = new FileChooserFilter(strExtentions, "Text file");
        FileView fileView = new FileChooserView(strExtentions, "Text file type", IconUtils.createImageIcon(this, "Resources/pngIcon.png"));

        File file = FileChooserHelper.showFileSaveDialog((JFrame)this, "Save a File", strOpenPath, strFile, true, fileFilter, fileView);

        if (file != null)
        {
            if (file.exists())
            {
                int n = JOptionPane.showConfirmDialog(this, "O ficheiro já existevent.\nDeseja substituir-lo?", "Erro Ficheiro Existente", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.NO_OPTION)
                {
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
    private void doMenuFilePrint()
   {
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
    private void doMenuFileExit()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    /**
     * Executes de Menu Edit Color
     */
    private void doMenuEditColor()
    {
        Color newColor = JColorChooser.showDialog(this, "Choose Background Color", labelHello.getBackground());
        labelHello.setBackground(newColor);
        labelHello.setOpaque(true);
        labelHello.repaint();
    }
    
    /**
     * Executes de Menu Help License
     */
    private void doMenuHelpLicense()
    {
        JOptionPane.showMessageDialog(this, 
                "Java Demo App is licensed under the GNU General Public License version 3.\n" + 
                "For more information, please visit:\n  https://www.gnu.org/licenses/gpl-3.0.html .\n\n" +
                "(c) 2008-2026 CaetanoSoft. All rights reserved.", 
                this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Executes de Menu Help About
     */
    private void doMenuHelpAbout()
    {
        JOptionPane.showMessageDialog(this, "CaetanoSoft\nJava Demo App v1.01", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateMainMenu()
    {
        // Do nothing
    }
    
    /**
     * Runs the demo.
     * 
     * @param arrStrArgs  The command line parameters/arguments
     */
    public static void main(String arrStrArgs[])
    {
        Runnable runnerMainThread = new Runnable()
        {
            @Override
            public void run()
            {
                // Splash Screen Manager
                final SplashScreenManager ssManager = SplashScreenManager.getInstance();
                if (ssManager != null) {
                    ssManager.setTextRectangle(new Rectangle(15, 140, 270, 22));
                    ssManager.setFontName("Arial");
                    ssManager.setFontSize(16);
                    //sm.setFontStyle(Font.BOLD | Font.ITALIC);
                    ssManager.setFontStyle(Font.PLAIN);
                    ssManager.setFontColor(Color.BLACK);
                    ssManager.setFontBackground(Color.LIGHT_GRAY);
                    ssManager.setBarRectangle(new Rectangle(15, 170, 270, 15));
                    ssManager.setBarColor(Color.GREEN);
                    ssManager.setBarBackground(Color.WHITE);
                    
                    ssManager.render("Loading Java base classes...", 30);
                }
                
                // Reads the program default settings
                if (ssManager != null) {
                    ssManager.render("Reading configuration file...", 50);
                }
                try {
                    readConfig();
                } catch (Exception ex) {
                    Logger.getLogger(JavaDemoApp.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Processes the command line parameters
                if (ssManager != null) {
                    ssManager.render("Parsing the command line parameters...", 55);
                }
                parseArguments(arrStrArgs);

                // Creates the error log file
                if (ssManager != null) {
                    ssManager.render("Creating error log file...", 60);
                }
                if (m_fLogLevel != DEBUG_LEVEL_NONE)
                {
                    Formatter formatter = new SimpleFormatter()
                    {
                        @Override
                        public String format(LogRecord record)
                        {
                            final String strNewLine = System.getProperty("line.separator");
                            final SimpleDateFormat logTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                            Calendar cal = new GregorianCalendar();
                            cal.setTimeInMillis(record.getMillis());
                            String strMsg = logTime.format(cal.getTime()) + "\t" + record.getLevel().getLocalizedName() + "\t";
                            Throwable throwable = record.getThrown();
                            if (throwable != null) {
                                strMsg = strMsg +
                                        record.getSourceClassName().substring(
                                            record.getSourceClassName().lastIndexOf(".") + 1,
                                            record.getSourceClassName().length()) +
                                        "." + record.getSourceMethodName() + "()" + "\t" +
                                        "Exception:" + throwable.getClass().getName() + " " +
                                        "Message:" + throwable.getLocalizedMessage();
                            }
                            else {
                                strMsg = strMsg +
                                        record.getSourceClassName().substring(
                                            record.getSourceClassName().lastIndexOf(".") + 1,
                                            record.getSourceClassName().length()) +
                                        "." + record.getSourceMethodName() + "()" + "\t" +
                                        record.getMessage();
                            }
                            return strMsg + strNewLine;
                        }
                    };
                    
                    FileHandler fh = null;
                    try {
                        fh = new FileHandler("logs" + File.separator + "JavaDemoApp%g.log", 10 * 1024 * 1024, 30, false);
                        fh.setFormatter(formatter);
                        fh.setEncoding("UTF-8");
                    }
                    catch (IOException ex) {
                        System.getLogger(JavaDemoApp.class.getName()).log(System.Logger.Level.ERROR, "Fail to create log file", ex);
                    }
                    
                    m_objLogger = Logger.getLogger(JavaDemoApp.class.getName());
                    m_objLogger.setUseParentHandlers(false);
                    if (fh != null) {
                        m_objLogger.addHandler(fh);
                    }
                    if (m_fLogLevel == DEBUG_LEVEL_NONE) {
                        m_objLogger.setLevel(Level.OFF);
                    }
                    else if ((m_fLogLevel & DEBUG_LEVEL_ALL) != 0) {
                        m_objLogger.setLevel(Level.ALL);
                        m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                    }
                    else if ((m_fLogLevel & DEBUG_LEVEL_WARNING) != 0) {
                        m_objLogger.setLevel(Level.WARNING);
                        m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                    }
                    else if ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0) {
                        m_objLogger.setLevel(Level.SEVERE);
                    }
                    else if ((m_fLogLevel & DEBUG_LEVEL_INFO) != 0) {
                        m_objLogger.setLevel(Level.INFO);
                        m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                    }
                }
                // Code to be executed on program's exit
                Runtime.getRuntime().addShutdownHook(new Thread() 
                    {
                        @Override
                        public void run() {
                            try {
                                // For multithread applications, end them before exit
                                synchronized(m_arrTreads) {
                                    if ((m_arrTreads != null) && (m_arrTreads.length > 0)) {
                                        for (Thread m_arrTread : m_arrTreads) {
                                            m_arrTread.interrupt();
                                        }
                                    }
                                }

                                // For database applications, end the conection before exit
                                synchronized(m_dbConnection)
                                {
                                    if((m_dbConnection != null) && !m_dbConnection.isClosed())
                                    {
                                        m_dbConnection.close();
                                    }
                                }

                                // Logs the program exit
                                synchronized(m_objLogger)
                                {
                                    if(m_objLogger != null)
                                    {
                                        if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
                                        {
                                            m_objLogger.exiting(JavaDemoApp.class.getName(), "main");
                                        }
                                    }
                                }
                            }
                            catch(Exception ex)
                            {
                                // Ignores
                            }
                            finally
                            {
                                m_bEndThreads = true;
                            }
                        }
                    });
				
                // Log's configuration
                if((m_strConfigFile != null) && !m_strConfigFile.isEmpty()) {
                    if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)) {
                        m_objLogger.config("Java Version: " + System.getProperty("java.version"));
                        m_objLogger.config("Java Specification Version: " + System.getProperty("java.specification.version"));

                        m_objLogger.config("Configuration File: '" + m_strConfigFile + "'");

                        m_objLogger.config("Debug Level: " + m_fLogLevel);

                        m_objLogger.config("JDBC Driver: " + m_strJdbcDriver);
                        m_objLogger.config("JDBC URL: " + m_strJdbcURL);
                        m_objLogger.config("DB User: " + m_strJdbcUsername);
                        m_objLogger.config("DB Password: " + m_strJdbcPassword);                   
                        m_objLogger.config("Slave Threads Stop: " + m_nThreadSleepTime);
                    }
                }
				
                // Loads the JDBC driver, if exists
                try {
                    Class.forName(m_strJdbcDriver).getDeclaredConstructor().newInstance();
                }
                catch(ClassNotFoundException ex) {
                    // An exception was detected; the program terminates with an error
                    if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
                    {
                        m_objLogger.log(Level.SEVERE, null, ex);
                    }

                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_DB_OPERATION.getErrorCode(), "Error: Invalid JDBC driver \"" + m_strJdbcDriver + "\"!");
                }
                catch(Exception ex) {
                    // An exception was detected; the program terminates with an error
                    if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
                    {
                        m_objLogger.log(Level.SEVERE, null, ex);
                    }

                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_DB_OPERATION.getErrorCode(), "Error: Invalid JDBC driver \"" + m_strJdbcDriver + "\"!");
                }

                // Connects to database
                try {
                    m_dbConnection = DriverManager.getConnection(m_strJdbcURL, m_strJdbcUsername, m_strJdbcPassword);
                }
                catch(SQLException ex) {
                    // An exception was detected; the program terminates with an error
                    if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
                    {
                        m_objLogger.log(Level.SEVERE, null, ex);
                    }

                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_DB_OPERATION.getErrorCode(), "Error: Invalid JDBC connection URL \"" + m_strJdbcURL + "\"!");
                }
                catch(Exception ex) {
                    // An exception was detected; the program terminates with an error
                    if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
                    {
                        m_objLogger.log(Level.SEVERE, null, ex);
                    }

                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_DB_OPERATION.getErrorCode(), "Error: Invalid JDBC connection URL \"" + m_strJdbcURL + "\"!");
                }
    
                // Swing translations
                if (ssManager != null) {
                    ssManager.render("Loading GUI translations...", 95);
                }
                final TranslationUtils translations = TranslationUtils.getInstance();
                String strLang = Locale.getDefault().toString();
                TranslationUtils.translateLanguage(strLang);

                // Main window widget
                if (ssManager != null) {
                    ssManager.render("Creating Main Window...", 98);
                }
                JFrame frame = new JavaDemoApp();
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                if (ssManager != null) {
                    ssManager.close();
                }
                frame.setVisible(true);
                frame.toFront();
            }
        };

       EventQueue.invokeLater(runnerMainThread);
    }
    
    /**
     * Exit the Java application with an error code and message sended to <i>stderr</i> 
     * or the logging file.
     * 
     * @since   1.1
     * @param iExitCode     Error code
     * @param strMessage    Error mensage
     */
    private static void doExit(int iExitCode, String strMessage)
    {
        if ((m_fLogLevel & (DEBUG_LEVEL_INFO | DEBUG_LEVEL_WARNING | DEBUG_LEVEL_ERROR)) != 0)
        {
            if(m_objLogger != null)
            {
                if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
                {
                    m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
                }
                m_objLogger.severe("" + strMessage);
                m_objLogger.severe("Ended with error " + iExitCode + "!");
            }
            else
            {
                System.err.println("JavaDemoApp: Ended with error " + iExitCode + ", " + strMessage + "!");
            }
        }

        System.exit(iExitCode);
    }
    
    /**
     * Prints the use of the command, with the respective parameters.
     * 
     * @since   1.1
     */
    public static void printUsage()
    {
        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
        
        System.out.println("");
        System.out.println("(c) 2008-2026 José Caetno Silva / CaetanoSoft");
        System.out.println("");
        System.out.println("Usage:");
        System.out.println("\t[|JAVA|] JavaDemoApp.jar -h");
        System.out.println("\t[|JAVA|] JavaDemoApp.jar");
        System.out.println("\t\t-h: Prints this help screen and exit");
        System.out.println("");
        System.out.println("Returned Error Codes:");
        System.out.println("\t  0: OK");
        System.out.println("\t -1: Error: Java Exception");
        System.out.println("\t  1: Error: Invalid number of parameters");
        System.out.println("\t  2: Error: Invalid parameter");
        System.out.println("\t  3: Error: Duplicated parameter");
        System.out.println("\t  4: Error: Configuration file not found or invalid");
        System.out.println("\t  5: Error: Invalid or non-existent input file");
        System.out.println("\t  6: Error: Invalid or non-existent output file");
        System.out.println("\t  7: Error: Invalid database connection or operation");
        System.out.println("\t  8: Error: Invalid server IP/Name");
        System.out.println("\t  9: Error: Invalid TCP/UDP service port");
        System.out.println("");

        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.exiting(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
    }
    
    /**
     * Processes command-line parameters.
     *
     * @param arrStrArgs
     */
    public static void parseArguments(String[] arrStrArgs)
    {
        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
                
        // Check if the number of parameters is valid
        if(arrStrArgs.length == 0)
        {
            return;
        }

        // Processes the parameters
        int i = 0;
        while(i < arrStrArgs.length)
        {
            if(arrStrArgs[i].equals("-h"))
            {
                // Parameter -h : show help
                // Prints the command usage
                printUsage();

                doExit(ExitErrorCodes.EXIT_OK.getErrorCode(), "");
            }
            else {
                // Error, Unknown parameter
                doExit(ExitErrorCodes.EXIT_ERROR_BAD_PARAMETER.getErrorCode(), "Error: Invalid parameter \"" + arrStrArgs[i] + "\"!");
            }

            ++i;
        }

        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.exiting(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
    }
            
    /**
     * Reads the program's configuration file.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     * @since   1.0
     * @see readConfig(String)
     */
    private static void readConfig() throws FileNotFoundException, IOException, Exception
    {
        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.entering(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }

        String strAppPath = PathUtils.getApplicationPath(new JavaDemoApp());
        m_strConfigFile = strAppPath + File.separator + m_strConfigFile;
        try
        {
            File fileConf = new File(m_strConfigFile);
            if(fileConf.exists())
            {
                fileConf = null;
                readConfig(m_strConfigFile);
            }
            else
            {
                doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Ficheiro de configuração por defeito \"" + m_strConfigFile + "\" inexistente!");
            }
        }
        catch(FileNotFoundException ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
            {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Default configuration file \"" + m_strConfigFile + "\" not found!");
            throw ex;
        }
	catch(IOException ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
            {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid default configuration file \"" + m_strConfigFile + "\"!");
            throw ex;
        }
        catch(Exception ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0))
            {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid default configuration file \"" + m_strConfigFile + "\"!");
            throw ex;
        }

        if(m_objLogger != null)
        {
            if((m_fLogLevel & DEBUG_LEVEL_INFO) != 0)
            {
                m_objLogger.exiting(JavaDemoApp.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
    }

    
    /**
     * 
     * @param strConfigFile     Configuration file to be read
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     * @since   1.0
     */
    private static void readConfig(String strConfigFile) throws FileNotFoundException, IOException, Exception
    {
        // Load properties from file
        Properties properties = new Properties();
        try {
            File fileProperties = new File(strConfigFile);
            synchronized(fileProperties)
            {
                FileInputStream inStream = new FileInputStream(fileProperties);
                properties.load(new InputStreamReader(inStream, "UTF-8"));
                inStream.close();
            }
            
            String debugLevel = properties.getProperty("DEBUG_LEVEL", String.valueOf(m_fLogLevel));
            if((debugLevel != null) && !debugLevel.trim().isEmpty())
            {
                try {
                    m_fLogLevel = Integer.parseInt(debugLevel);
                }
                catch(NumberFormatException ex) {
                    m_fLogLevel = DEBUG_LEVEL_ERROR;
                }
            }
            
            String jdbcDriver = properties.getProperty("JDBC_DRIVER", m_strJdbcDriver);
            if(jdbcDriver != null) {
                m_strJdbcDriver = jdbcDriver.trim();
            }
            
            String jdbcURL = properties.getProperty("JDBC_URL", m_strJdbcURL);
            if(jdbcURL != null) {
                m_strJdbcURL = jdbcURL.trim();
            }
            
            String jdbcUsername = properties.getProperty("JDBC_USERNAME", m_strJdbcUsername);
            if(jdbcUsername != null) {
                m_strJdbcUsername = jdbcUsername.trim();
            }
            
            String jdbcPassword = properties.getProperty("JDBC_PASSWORD", m_strJdbcPassword);
            if(jdbcPassword != null) {
                m_strJdbcPassword = jdbcPassword.trim();
            }

            String connectionTimeoutInMilliseconds = properties.getProperty("JDBC_CONNECTTIMEOUT", "5000");
            if((connectionTimeoutInMilliseconds != null) && !(connectionTimeoutInMilliseconds.trim().isEmpty())) {
                connectionTimeoutInMilliseconds = connectionTimeoutInMilliseconds.trim();
                try {
                    m_nConnectionTimeout  = Integer.parseInt(connectionTimeoutInMilliseconds);
                    if((m_nConnectionTimeout < 1000) || (m_nConnectionTimeout > 65535)) {
                        // Error, Invalid connection timeout value
                        doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid connection timeout value!");
                    }
                }
                catch(NumberFormatException ex)
                {
                    // Error, Invalid connection timeout value
                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid connection timeout value!");
                }
            }
            else {
                // Error, Invalid connection timeout value
                doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid connection timeout value!");
            }
            
            String threadTimeInSeconds = properties.getProperty("THREAD_SLEEP_TIME", "1");
            if((threadTimeInSeconds != null) && !(threadTimeInSeconds.trim().isEmpty())) {
                threadTimeInSeconds = threadTimeInSeconds.trim();
                try {
                    m_nThreadSleepTime  = Integer.parseInt(threadTimeInSeconds) * 1000;
                    if((m_nThreadSleepTime < 1) || (m_nThreadSleepTime > 65535)) {
                        // Error, Invalid threads stop time
                        doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid threads sleeep time!");
                    }
                }
                catch(NumberFormatException ex)
                {
                    // Error, Invalid threads sleep time
                    doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid threads sleeep time!");
                }
            }
            else {
                // Error, Invalid threads sleep time
                doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid threads sleeep time!");
            }
        }
	catch(FileNotFoundException ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0)) {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Configuration file \"" + strConfigFile + "\" not found!");
        }
	catch(IOException ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0)) {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid configuration file \"" + strConfigFile + "\"!");
        }
        catch(Exception ex)
        {
            if((m_objLogger != null) && ((m_fLogLevel & DEBUG_LEVEL_ERROR) != 0)) {
                m_objLogger.log(Level.SEVERE, null, ex);
            }

            doExit(ExitErrorCodes.EXIT_ERROR_BAD_CONFIG_FILE.getErrorCode(), "Error: Invalid configuration file \"" + strConfigFile + "\"!");
        }
    }
}
