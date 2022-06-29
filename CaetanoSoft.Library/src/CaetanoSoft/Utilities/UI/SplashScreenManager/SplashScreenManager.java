//******************************************************************************
// Project: Splash Screen SplashScreenManager v1.00
// URL:     http://slam.sourceforge.net
// File:    SplashScreenManager.java
//
// Description:
//          This class manages the Java 1.6 SplashScreen object.
//
// Copyright (C) 2008:
//          José Caetano Silva (jcaetano@users.sourceforge.net)
//
// Licence:
//          This file is part of Splash Screen SplashScreenManager.
//
//          Splash Screen SplashScreenManager is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          Splash Screen SplashScreenManager is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with Splash Screen SplashScreenManager.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************

package CaetanoSoft.Utilities.UI.SplashScreenManager;

import java.awt.*;


/**
 * This class manages the Java 1.6 SplashScreen object.
 * 
 * @author  José Caetano Silva (jcaetano@users.sourceforge.net)
 * @version 1.00, 2008-06-10
 */
public class SplashScreenManager {
    // Singleton instance
    private static SplashScreenManager instance = null;
    // The splash screen Java object
    private static SplashScreen splash = null;
    // An object that represents the splash screen image
    private static Graphics2D graphics = null;
    // Color to use drawing progress bar foreground
    private static Color progressBarColor = Color.GREEN;
    // Color to use drawing progress bar foreground
    private static Color progressBarBackground = Color.WHITE;
    //The retangle of the progress bar
    private static Rectangle progressBarRet = null;
    //The retangle of the text field
    private static Rectangle textFieldRet = null;
    // The font name to be used
    private static String fontName = null;
    // The font size in points to be used (1 point = 1/72 inches)
    private static int fontSize = 8;
    // The font stlyle to be used (Font.PLAIN, Font.BOLD, Font.ITALIC)
    private static int fontStyle = Font.PLAIN;
    // Font to use drawing text
    private static Font textFont = null;
    // Color to use drawing text foreground
    private static Color textColor = Color.BLACK;
    // Color to use drawing text background
    private static Color textBackground = Color.LIGHT_GRAY;

    /**
     * Default constructor.
     * 
     * Not to be called by applications.
     * 
     * @since   1.00
     * @see #getInstance()
     */
    protected SplashScreenManager() 
    {
        // Exists only to defeat instantiation.
    }
    
    /**
     * Don't permit creating an object by cloning it.
     *
     * @since   1.00
     */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
                // super.clone();
	}
	
    /**
     * Creates a new instance of the SplashScreenManager class.
     * 
     * @return A new <i>SplashScreenManager</i> instance.
     * @since   1.00
     */
    public static synchronized SplashScreenManager getInstance()
    {
        if (instance == null) {
            instance = new SplashScreenManager();
            try {
                splash = SplashScreen.getSplashScreen();
                if (splash == null) {
                    System.err.println("SplashScreen.getSplashScreen() returned null");
                }
                else {
                    try {
                        graphics = splash.createGraphics();
                        if (graphics == null) {
                            System.err.println("splash.createGraphics() returned null");
                        }
                        else {
                            if (progressBarRet != null) {
                                if ((progressBarRet.width <= 0) || (progressBarRet.height <= 0) ||
                                    (progressBarRet.x <= 0) || (progressBarRet.y <= 0) ||
                                    (textFieldRet.width <= 0) || (textFieldRet.height <= 0) ||
                                    (textFieldRet.x <= 0) || (textFieldRet.y <= 0)) {
                                    System.err.println("Splash screen image to small");
                                }
                            }
                            // Font
                            if (fontName == null) {
                                fontName = "Default";
                                if (fontSize <= 0)
                                    fontSize = 8;
                                try {
                                    textFont = new Font(fontName, fontStyle, fontSize);
                                }
                                catch(Exception ex) {
                                    // Do nothing
                                }
                            }
                        }
                    }
                    catch (IllegalStateException ex) {
                        // If the splash screen has already been closed
                    }
                    catch (Exception ex) {
                        // Do nothing
                    }
                }
            }
            catch (HeadlessException ex) {
                // If GraphicsEnvironment.isHeadless() returns true
            }
            catch (UnsupportedOperationException ex) {
                // If the splash screen feature is not supported by the current toolkit
            }
            catch (Exception ex) {
                // Do nothing
            }
        }
        
        return instance;
    }

    /**
     * Close the splash screen image.
     *
     * @since   1.00
     */
    public void close()
    {
        if (splash != null) {
            render("", 100);
            splash.close();
        }
    }

    /**
     * Draws on the splash screen text and a progress bar.
     * 
     * @param message       The message to display
     * @param percentage    The percentage of the progress bar
     * @since   1.00
     */
    public void render(String message, int percentage)
    {
        if ((percentage < 0) || (percentage > 100)) {
            System.err.println("Splash screen percentage must be betwen 0 and 100");
        }
        if (graphics != null) {
            graphics.setComposite(AlphaComposite.Clear);
            
            if ((textFieldRet != null) && !textFieldRet.isEmpty()) {
                // Clears the text rectangle
                graphics.setColor(textBackground);
                graphics.setBackground(textBackground);
                graphics.setPaintMode();
                graphics.fillRect(textFieldRet.x, textFieldRet.y, textFieldRet.width, textFieldRet.height);
                // Draw the text
                graphics.setFont(textFont);
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(textColor);
                graphics.setBackground(textBackground);
                graphics.setPaintMode();
                graphics.drawString(message, textFieldRet.x, textFieldRet.y + textFont.getSize());
            }
            
            if ((progressBarRet != null) && !progressBarRet.isEmpty()) {
                // Draw the progress bar
                graphics.setColor(progressBarBackground);
                graphics.setBackground(progressBarBackground);
                graphics.setPaintMode();
                graphics.fillRect(progressBarRet.x, progressBarRet.y, textFieldRet.width, progressBarRet.height);
                graphics.setColor(progressBarColor);
                graphics.setPaintMode();
                int bar = (int)((float)textFieldRet.width * ((float)percentage/100.0));
                graphics.fillRect(progressBarRet.x, progressBarRet.y, bar , progressBarRet.height);
                graphics.setColor(Color.BLACK);
                graphics.setPaintMode();
                graphics.drawRect(progressBarRet.x, progressBarRet.y, textFieldRet.width, progressBarRet.height);
            }
            
            if (splash != null) {
                splash.update();
            }
        }
    }
    
    /**
     * Set the font name witch the text will be draw.
     * 
     * @param font  The font name.
     * @since   1.00
     */
    public void setFontName(String font)
    {
        if (font == null)
            fontName = "Default";
        else
            fontName = font;
        try {
            textFont = new Font(fontName, fontStyle, fontSize);
        }
        catch(Exception ex) {
            // Use the default font
            fontName = "Default";
            textFont = new Font(fontName, fontStyle, fontSize);
        }
    }
    
    /**
     * Set the font point size witch the text will be draw.
     * <p>
     * <b>Note:</b> 1 point = 1/72 inches.
     * 
     * @param size  The point size of the font to be used (in 1/72 of an inch units).
     * @since   1.00
     */
    public void setFontSize(int size)
    {
        if (size <= 0)
            fontSize = size;
        else
            fontSize = size;
        try {
            textFont = new Font(fontName, fontStyle, fontSize);
        }
        catch(Exception ex) {
            // Do nothing
        }
    }
    
    /**
     * Set the font style witch the text will be draw.
     * <p>
     * <b>Note:</b> Can be any of the combinations Font.PLAIN, Font.BOLD, Font.ITALIC.
     * 
     * @param style  The font stlyle to be used (Font.PLAIN, Font.BOLD, Font.ITALIC).
     * @since   1.00
     * @see     java.awt.Font
     */
    public void setFontStyle(int style)
    {
        if ((fontStyle & ~(Font.PLAIN | Font.BOLD | Font.ITALIC)) != 0)
            fontStyle = Font.PLAIN;
        else
            fontStyle = style;
        try {
            textFont = new Font(fontName, fontStyle, fontSize);
        }
        catch(Exception ex) {
            // Do nothing
        }
    }
    
    /**
     * Set the font foreground color witch the text will be draw.
     * 
     * @param fontColor  The font foreground color to be used.
     * @since   1.00
     * @see     java.awt.Color
     */
    public void setFontColor(Color fontColor)
    {
        textColor = fontColor;
    }
    
    /**
     * Set the font background color witch the text will be draw.
     * 
     * @param fontBackground  The font background color to be used.
     * @since   1.00
     * @see     java.awt.Color
     */
    public void setFontBackground(Color fontBackground)
    {
        textBackground = fontBackground;
    }
    
    /**
     * Set the rectangle where the text will be draw.
     * 
     * @param textRectangle  The rectangle where the text will be draw.
     * @since   1.00
     * @see     java.awt.Rectangle
     */
    public void setTextRectangle(Rectangle textRectangle)
    {
        textFieldRet = textRectangle;
    }
    
    /**
     * Set the rectangle where the text will be draw.
     * 
     * @param x  The x position of the rectangle where the text will be draw.
     * @param y  The y position of the rectangle where the text will be draw.
     * @param with  The with of the rectangle where the text will be draw.
     * @param height  The height of the rectangle where the text will be draw.
     * @since   1.00
     * @see     java.awt.Rectangle
     */
    public void setTextRectangle(int x, int y, int with, int height)
    {
        textFieldRet = new Rectangle(x, y, with, height);
    }
    
    /**
     * Set the progress bar foreground color witch the text will be draw.
     * 
     * @param barColor  The progress bar foreground color to be used.
     * @since   1.00
     * @see     java.awt.Color
     */
    public void setBarColor(Color barColor)
    {
        progressBarColor = barColor;
    }
    
    /**
     * Set the progress bar background color witch the text will be draw.
     * 
     * @param barBackground  The progress bar background color to be used.
     * @since   1.00
     * @see     java.awt.Color
     */
    public void setBarBackground(Color barBackground)
    {
        progressBarBackground = barBackground;
    }
    
    /**
     * Set the rectangle where the progress bar will be draw.
     * 
     * @param textRectangle  The rectangle where the text will be draw.
     * @since   1.00
     * @see     java.awt.Rectangle
     */
    public void setBarRectangle(Rectangle textRectangle)
    {
        progressBarRet = textRectangle;
    }
    
    /**
     * Set the rectangle where the progress bar will be draw.
     * 
     * @param x  The x position of the rectangle where the progress bar will be draw.
     * @param y  The y position of the rectangle where the progress bar will be draw.
     * @param with  The with of the rectangle progress bar.
     * @param height  The height of the rectangle progress bar.
     * @since   1.00
     * @see     java.awt.Rectangle
     */
    public void setBarRectangle(int x, int y, int with, int height)
    {
        progressBarRet = new Rectangle(x, y, with, height);
    }
}
