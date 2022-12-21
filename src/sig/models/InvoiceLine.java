package sig.models;

/**
 * @author Abdelrhman
 *
 */
public class InvoiceLine {

	private String name;
    private double price;
    private int count;
    private InvoiceHeader invoiceHeader;

    public InvoiceLine(String name, double price, int count, InvoiceHeader invoiceHeader) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }

    public double getTotal() {
        return price * count;
    }
    
    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "name=" + name + ", price=" + price + ", count=" + count + '}';
    }
    
    public String getAsCSV() {
        return invoiceHeader.getNumber() + "," + name + "," + price + "," + count;
    }
	
}
