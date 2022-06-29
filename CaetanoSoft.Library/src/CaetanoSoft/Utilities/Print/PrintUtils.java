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

/** A simple utility class that lets you very simply print
 *  an arbitrary component. Just pass the component to the
 *  PrintUtilities.printComponent. The component you want to
 *  print doesn't need a print method and doesn't have to
 *  implement any interface or do anything special at all.
 *  <P>
 *  If you are going to be printing many times, it is marginally more 
 *  efficient to first do the following:
 *  <PRE>
    PrintUtils printHelper = new PrintUtils(theComponent);
  </PRE>
  then later do printHelper.print(). But this is a very tiny
  difference, so in most cases just do the simpler
  PrintUtils.printComponent(componentToBePrinted).

  7/99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
  May be freely used or adapted.
 */

public class PrintUtils implements Printable {
	// The component to be printed
	private Component componentToBePrinted;

  public static void printComponent(Component c) {
    new PrintUtils(c).print();
  }
  
  public PrintUtils(Component componentToBePrinted) {

    this.componentToBePrinted = componentToBePrinted;
  }

	public void print() {

		/*PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		PrintService selection = ServiceUI.printDialog(null, 100, 100, services, svc, null, attrs);*/


    PrinterJob printJob = PrinterJob.getPrinterJob();
	PageFormat pf = printJob.defaultPage();
    printJob.setPrintable(this, pf);
	PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
	attr.add(MediaSizeName.ISO_A4);
	attr.add(OrientationRequested.PORTRAIT);
	attr.add(new Copies(1));
    if (printJob.printDialog(attr))
      try {
        printJob.print();
      } catch(PrinterException pe) {
        System.out.println("Error printing: " + pe);
      }
  }

	@Override
  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return Printable.NO_SUCH_PAGE;
    }
	else {
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      disableDoubleBuffering(componentToBePrinted);
      componentToBePrinted.paint(g2d);
      enableDoubleBuffering(componentToBePrinted);
      return Printable.PAGE_EXISTS;
    }
  }

  /** The speed and quality of printing suffers dramatically if
   *  any of the containers have double buffering turned on.
   *  So this turns if off globally.
   *  @see enableDoubleBuffering
   */
  public static void disableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }

  /** Re-enables double buffering globally. */
  
  public static void enableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }
}
