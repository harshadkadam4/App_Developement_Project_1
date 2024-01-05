import java.awt.print.*;
import java.awt.*;
import javax.swing.JTable;

public class TablePrinter implements Printable {

    private JTable table;
	
    public TablePrinter(JTable table) {
		System.out.println("calling printer");
        this.table = table;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        table.print(g2d);

        return PAGE_EXISTS;
    }

    public void printTable() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                System.err.println("Error printing: " + e.getMessage());
            }
        }
    }
}