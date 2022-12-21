package sig.models;

import java.util.ArrayList;
import java.util.Date;

import sig.SalesInvoiceGeneratorFrame;

/**
 * @author Abdelrhman
 *
 */
public class InvoiceHeader {

	private int number;
    private String name;
    private Date date;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader(int number, String name, Date date) {
        this.number = number;
        this.name = name;
        this.date = date;
    }

    public double getTotal() {
        double total = 0;
        
        for (InvoiceLine line : getLines()) {
            total += line.getTotal();
        }
        
        return total;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "num=" + number + ", name=" + name + ", date=" + date + '}';
    }
    
    public String getAsCSV() {
        return number + "," + SalesInvoiceGeneratorFrame.sdf.format(date) + "," + name;
    }
	
}
