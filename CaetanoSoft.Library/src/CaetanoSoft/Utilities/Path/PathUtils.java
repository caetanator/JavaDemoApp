//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    PathUtils.java
//
// Description:
//          This class handles the manipulation of path strings.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
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


package CaetanoSoft.Utilities.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * This class implements a singleton pattern object that handles the
 * manipulation of path strings.
 *
 * @author José Caetano Silva
 * @version 1.03.0001, 2026-04-03
 * @since 1.00
 */
public class PathUtils {
    private static final String STR_OS_WIN = "windows";
    private static final String STR_OS_LIN = "linux";
    private static final String STR_OS_MAC = "mac";
        
    private static String strUserPath = null;
    private static String strPathDesktop = null;
    private static String strPathDocuments = null;
    private static String strPathPictures = null;
    private static String strPathVideos = null;
    private static String strPathMusic = null;
    private static String strPathDownloads = null;
            
    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since 1.00
     */
    private PathUtils() {
        // Singleton pattern class, instantiation not allowed
        //super();
    }

    /**
     * Don't permit creating an object by cloning it.
     *
     * @since 1.00
     * @return A cloned object of the class <code>Object</code>.
     * @throws java.lang.CloneNotSupportedException
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Singleton pattern class, cloning not allowed
        //super.clone();

        throw new CloneNotSupportedException("This instance of the Singleton pattern class cannot be cloned");

        //return null;
    }

    /**
     * Gets the user's Home directory path.
     *
     * @return A string with the user's Home directory.
     */
    public static String getUserHomePath() {
        if ((strUserPath == null) || !(strUserPath.length() > 0)) {
            strUserPath =  System.getProperty("user.home", "");
            strUserPath = strUserPath.trim();
            
            if ((strUserPath == null) || !(strUserPath.length() > 0)) {
                String str = System.getProperty("os.name");
                if (str != null) {
                    str = str.trim();
                    str = str.toLowerCase();
                    if (str.length() > 0) {
                        if (str.startsWith(STR_OS_WIN)) {
                            // Windows OS
                            str = System.getenv("USERPROFILE");
                            // %USERPROFILE Windows <= NT 4 = C:\WINNT\Profiles\[UserName] \Personal\Desktop
                            //                                                             \Personal\My Documents\My Downloads|My Music|My Pictures|My Videos
                            // %USERPROFILE Windows = 2000/XP = C:\Documents and Settings\[UserName] \Desktop
                            //                                                                        \My Documents\My Downloads|My Music|My Pictures|My Videos
                            // %USERPROFILE Windows >= Vista = C:\Users\[UserName] \Desktop|Documents|Downloads|Music|Pictures|Videos
                            if (str != null) {
                                if (str.length() > 0) {
                                    strUserPath = str.trim();
                                } else {
                                    // Invalid Windows User Home path
                                    strUserPath = "";
                                }
                            }
                        } else {
                            // Linux OS, macOS, UNIX
                            str = System.getenv("HOME");
                            // $HOME Linux/UNIX = /home/[UserName]
                            // $HOME macOS = /Users/[UserName]
                            if (str != null) {
                                if (str.length() > 0) {
                                    strUserPath = str.trim();
                                } else {
                                    // Invalid Linux OS, macOS, UNIX User Home path
                                    strUserPath = "";
                                }
                            }
                        }
                    }
                }
            }
        }

        return strUserPath;
    }

    /**
     * Gets the user's Desktop directory path.
     *
     * @return A string with the user's Desktop directory.
     */
    public static String getUserDesktopPath() {
        if ((strPathDesktop == null) || !(strPathDesktop.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathDesktop = str + System.getProperty("file.separator") + "Desktop";
                            } else {
                                // UPS, empty %USERPROFILE path
                                strPathDesktop = getUserHomePath();
                            }
                        } else {
                            // UPS, no valid %USERPROFILE path
                            strPathDesktop = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS, UNIX, OS/2 or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_VIDEOS_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir DESKTOP");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathDesktop = strOut.trim();
                                } catch (IOException ex) {
                                    // reader.readLine();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    // process.waitFor();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                // processBuilder.start();
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[UserName]
                                        str = str.trim();
                                        strPathDesktop = str + System.getProperty("file.separator") + "Desktop";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathDesktop = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathDesktop = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathDesktop = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathDesktop = "";
                }
            } else {
                // UPS, no OS name
                strPathDesktop = "";
            }
        }

        return strPathDesktop;
    }
    
    /**
     * Gets the user's Documents directory path.
     *
     * @return A string with the user's Documents directory.
     */
    public static String getUserDocumentsPath() {
        if ((strPathDocuments == null) || !(strPathDocuments.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathDocuments = str + System.getProperty("file.separator") + "Documents";
                            } else {
                                // Invalid Windows Documents path
                                strPathDocuments = getUserHomePath();
                            }
                        } else {
                            // No Windows Documents path
                            strPathDocuments = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS, UNIX, OS/2 or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_DOCUMENTS_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir DOCUMENTS");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathDocuments = strOut.trim();
                                } catch (IOException ex) {
                                    // reader.readLine();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    // process.waitFor();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                // processBuilder.start();
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[UserName]
                                        str = str.trim();
                                        strPathDocuments = str + System.getProperty("file.separator") + "Documents";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathDocuments = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathDocuments = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathDocuments = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathDocuments = "";
                }
            } else {
                // UPS, no OS name
                strPathDocuments = "";
            }
        }

        return strPathDocuments;
    }
    
    /**
     * Gets the user's Pictures directory path.
     *
     * @return A string with the user's Pictures directory.
     */
    public static String getUserPicturesPath() {
        if ((strPathPictures == null) || !(strPathPictures.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathPictures = str + System.getProperty("file.separator") + "Pictures";
                            } else {
                                // Invalid Windows Pictures path
                                strPathPictures = getUserHomePath();
                            }
                        } else {
                            // No Windows Pictures path
                            strPathPictures = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS, UNIX, OS/2 or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_PICTURES_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir PICTURES");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathPictures = strOut.trim();
                                } catch (IOException ex) {
                                    // reader.readLine();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    // process.waitFor();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                // processBuilder.start();
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[username]
                                        str = str.trim();
                                        strPathPictures = str + System.getProperty("file.separator") + "Pictures";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathPictures = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathPictures = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathPictures = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathPictures = "";
                }
            } else {
                // UPS, no OS name
                strPathPictures = "";
            }
        }

        return strPathPictures;
    }
    
    /**
     * Gets the user's Videos directory path.
     *
     * @return A string with the user's Videos directory.
     */
    public static String getUserVideosPath() {
        if ((strPathVideos == null) || !(strPathVideos.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathVideos = str + System.getProperty("file.separator") + "Videos";
                            } else {
                                // Invalid Windows Videos path
                                strPathVideos = getUserHomePath();
                            }
                        } else {
                            // No Windows Videos path
                            strPathVideos = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS, UNIX, OS/2 or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_VIDEOS_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir VIDEOS");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathVideos = strOut.trim();
                                } catch (IOException ex) {
                                    // reader.readLine();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    // process.waitFor();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                // processBuilder.start();
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[username]
                                        str = str.trim();
                                        strPathVideos = str + System.getProperty("file.separator") + "Movies";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathVideos = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathVideos = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathVideos = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathVideos = "";
                }
            } else {
                // UPS, no OS name
                strPathVideos = "";
            }
        }

        return strPathVideos;
    }
    
    /**
     * Gets the user's Music directory path.
     *
     * @return A string with the user's Music directory.
     */
    public static String getUserMusicPath() {
        if ((strPathMusic == null) || !(strPathMusic.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathMusic = str + System.getProperty("file.separator") + "Music";
                            } else {
                                // Invalid Windows Documents path
                                strPathMusic = getUserHomePath();
                            }
                        } else {
                            // No Windows Documents path
                            strPathMusic = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_MUSIC_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir MUSIC");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathMusic = strOut.trim();
                                } catch (IOException ex) {
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[username]
                                        str = str.trim();
                                        strPathMusic = str + System.getProperty("file.separator") + "Music";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathMusic = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathMusic = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathMusic = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathMusic = "";
                }
            } else {
                // UPS, no OS name
                strPathMusic = "";
            }
        }

        return strPathMusic;
    }
    
    /**
     * Gets the user's Downloads directory path.
     *
     * @return A string with the user's Downloads directory.
     */
    public static String getUserDownloadsPath() {
        if ((strPathDownloads == null) || !(strPathDownloads.length() > 0)) {
            String str = System.getProperty("os.name");
            if (str != null) {
                str = str.trim();
                str = str.toLowerCase();
                if (str.length() > 0) {
                    if (str.startsWith(STR_OS_WIN)) {
                        // Windows OS
                        str = System.getenv("USERPROFILE");
                        if (str != null) {
                            if (str.length() > 0) {
                                str = str.trim();
                                strPathDownloads = str + System.getProperty("file.separator") + "Downloads";
                            } else {
                                // Invalid Windows Documents path
                                strPathDownloads = getUserHomePath();
                            }
                        } else {
                            // No Windows Documents path
                            strPathDownloads = getUserHomePath();
                        }
                    } else {
                        // Linux, macOS, UNIX, OS/2 or other OS
                        if (str.startsWith(STR_OS_LIN)) {
                            // Linux OS
                            try {
                                // System.getenv("XDG_DOWNLOAD_DIR")
                                ProcessBuilder processBuilder = new ProcessBuilder();
                                processBuilder.command("sh", "-c", "xdg-user-dir DOWNLOAD");
                                processBuilder.directory(new File(System.getProperty("user.home")));
                                Process process = processBuilder.start();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String strOut = null;
                                try {
                                    strOut = reader.readLine();
                                    strPathDownloads = strOut.trim();
                                } catch (IOException ex) {
                                    // reader.readLine();
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                                int exitCode = 0;
                                try {
                                    exitCode = process.waitFor();
                                } catch (InterruptedException ex) {
                                    System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                }
                            } catch (IOException ex) {
                                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        } else {
                            // macOS, UNIX or other OS
                            if (str.startsWith(STR_OS_MAC)) {
                                // macOS
                                str = System.getenv("HOME");
                                if (str != null) {
                                    if (str.length() > 0) {
                                        // $HOME = /Users/[username]
                                        str = str.trim();
                                        strPathDownloads = str + System.getProperty("file.separator") + "Downloads";
                                    } else {
                                        // UPS, empty $HOME path
                                        strPathDownloads = getUserHomePath();
                                    }
                                } else {
                                    // UPS, no valid $HOME path
                                    strPathDownloads = getUserHomePath();
                                }
                            } else {
                                // UNIX or other OS
                                strPathDownloads = getUserHomePath();
                            }
                        }
                    }
                } else {
                    // UPS, no valid OS name
                    strPathDownloads = "";
                }
            } else {
                // UPS, no OS name
                strPathDownloads = "";
            }
        }

        return strPathDownloads;
    }
    
    /**
     * Gets the directory path for this application.
     *
     * @param app the application object
     * @return A string with the application directory.
     */
    public static synchronized String getApplicationPath(Object app) {
        String strPathApplication = "";
        if (app != null) {
            // Decodes the URL formatted string path
            try {
                strPathApplication = app.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            } catch (URISyntaxException ex) {
                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (Exception ex) {
                System.getLogger(PathUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

            // Replaces the '/' by the '\' on Windows
            if (File.separatorChar != '/') {
                strPathApplication = strPathApplication.replace('/', File.separatorChar);
            }

            // Removes *.jar and .class
            if (strPathApplication.endsWith(".jar") || strPathApplication.endsWith(".class")) {
                int i = strPathApplication.lastIndexOf(File.separatorChar);
                if (i > 0) {
                    strPathApplication = strPathApplication.substring(0, i);
                }
            }

            // Removes the first '\' if necessary on Windows
            if ((strPathApplication.charAt(0) == File.separatorChar) && (strPathApplication.indexOf(":") >= 0)) {
                strPathApplication = strPathApplication.substring(1);
            }

            // Removes the separator in the end of the path if necessary
            if (strPathApplication.charAt(strPathApplication.length() - 1) == File.separatorChar) {
                strPathApplication = strPathApplication.substring(0, strPathApplication.length() - 1);
            }
        }

        return strPathApplication;
    }

    /**
     * Get the extension from a file name.
     *
     * @param file the file object
     * @return A string with file extension or "" if none is found
     */
    public static String getFileExtension(File file) {
        // Get the extension of a file
        String extension = "";
        String name = file.getName();
        int lastPoint = name.lastIndexOf('.');
        if (lastPoint > 0 && lastPoint < name.length() - 1) {
            extension = name.substring(lastPoint + 1).toLowerCase();
        }
        return extension;
    }
}
