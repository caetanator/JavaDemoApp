//******************************************************************************
// Project: Splash Screen Manager v1.00
// URL:     http://slam.sourceforge.net
// File:    SplashScreenManager.java
//
// Description:
//          This class manages the Java 1.6 SplashScreen object.
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



package CaetanoSoft.Utilities.Translation;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;

/**
 * This class manages the Java 1.6 SplashScreen object.
 *
 * @author  José Caetano Silva (jcaetano@users.sourceforge.net)
 * @version %I%, %G%
 */
public class Translation_PT {
    // Singleton instance
    private static Translation_PT instance = null;

    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since   1.00
     * @see #getInstance()
     */
    protected Translation_PT() {
        try {
            String str = UIManager.getLookAndFeel().toString();

			//ResourceBundle props = ResourceBundle.getBundle("sun.print.resources.serviceui");
			UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.basic");
			UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.serviceui");
			
            if (str.indexOf(".metal.") > 0) {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.metal");
                return;
            }

            if (str.indexOf(".windows.") > 0) {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.windows");
                return;
            }

            if (str.indexOf(".gtk.") > 0) {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.gtk");
                return;
            }

            if (str.indexOf(".mac.") > 0) {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.mac");
                return;
            }

            if (str.indexOf(".motif.") > 0) {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.motif");
            }
			else {
                UIManager.getDefaults().addResourceBundle("CaetanoSoft.Utilities.UI.SplashScreenManager.Resources.synth");
            }
        }
		catch (Exception e) {
            // Do nothing
			e.printStackTrace();
        }
    }

	/**
     * Don't permit creating an object by cloning it.
     *
     * @since   1.00
     */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
    /**
     * Creates a new instance of the Translation class.
     *
     * @return A new <i>Translation</i> instance.
     * @since   1.00
     */
    public static synchronized Translation_PT getInstance() {
        if (instance == null) {
            instance = new Translation_PT();
        }
        return instance;
    }
}
