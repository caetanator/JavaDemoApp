//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    PrintUtils.java
//
// Description:
//          This class handles the printing of an arbitrary Java GUI component.
//
//          It's base on the code of:
//              7/99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
//
// Copyright (C) 2008-2022:
//          José Caetano Silva / CaetanoSoft
//
// License:
//          This file is part of CaetanoSoft.Library.
//
//          CaetanoSoft.Library is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          CaetanoSoft.Library is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.Library.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************

package CaetanoSoft.Utilities.Print;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;

/**
 * This class handles the printing of an arbitrary Java GUI component.
 * <p>
 * It's base on the code of:<br>
 * <&nbsp;><&nbsp;><&nbsp;><&nbsp;>7/99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 * 
 * @author José Caetano Silva
 * @version 1.2.0, 2022-07-04
 * @since   1.0
 */
public class PrintUtils implements Printable
{
    /**
     * The Java GUI component to be printed.
     */
    private Component componentToBePrinted;

    /**
     * Print the Java GUI component.
     * 
     * @param comp  The Java GUI component to be printed
     */
    public static void printComponent(Component comp)
    {
        new PrintUtils(comp).print();
    }

    /**
     * 
     * @param comp  The Java GUI component to be printed
     */
    public PrintUtils(Component comp)
    {
        this.componentToBePrinted = comp;
    }

    /**
     * Print the Java GUI component.
     */
    public void print()
    {

        /*PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        PrintService selection = ServiceUI.printDialog(null, 100, 100, services, svc, null, attrs);*/

        PrinterJob printJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printJob.defaultPage();
        printJob.setPrintable(this, pageFormat);
        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(MediaSizeName.ISO_A4);
        attr.add(OrientationRequested.PORTRAIT);
        attr.add(new Copies(1));
        if (printJob.printDialog(attr))
        {
            try
            {
                printJob.print();
            }
            catch(PrinterException printEx)
            {
                System.out.println("Error printing: " + printEx);
            }
        }
    }

    /**
     * 
     * @see print(Graphics, PageFormat, int)
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
    {
        if (pageIndex > 0)
        {
            return Printable.NO_SUCH_PAGE;
        }
        else
        {
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            disableDoubleBuffering(componentToBePrinted);
            componentToBePrinted.paint(graphics2D);
            enableDoubleBuffering(componentToBePrinted);
            return Printable.PAGE_EXISTS;
        }
    }

    /**
     * Disables double buffering globally.
     * The speed and quality of printing suffers dramatically if
     * any of the containers have double buffering turned on.
     * So this turns it off globally.
     *
     * @see enableDoubleBuffering
     * 
     * @param comp  The Java GUI component to be printed 
     */
    public static void disableDoubleBuffering(Component comp)
    {
        RepaintManager currentManager = RepaintManager.currentManager(comp);
        currentManager.setDoubleBufferingEnabled(false);
    }

    /**
     * Enables double buffering globally.
     *
     * @see disableDoubleBuffering
     * 
     * @param comp  The Java GUI component to be printed
     */
    public static void enableDoubleBuffering(Component comp)
    {
        RepaintManager currentManager = RepaintManager.currentManager(comp);
        currentManager.setDoubleBufferingEnabled(true);
    }
}
