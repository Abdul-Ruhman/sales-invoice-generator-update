package sig.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sig.SalesInvoiceGeneratorFrame;

/**
 * @author Abdelrhman
 *
 */
public class HeaderTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 986030804650880224L;
	
	private String[] columns = {"Invoice Number", "Invoice Date", "Customer Name", "Invoice Total"};
    private ArrayList<InvoiceHeader> invoices;
    
    public HeaderTableModel(ArrayList<InvoiceHeader> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoices.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return inv.getNumber();
            case 1:
                return SalesInvoiceGeneratorFrame.sdf.format(inv.getDate());
            case 2:
                return inv.getName();
            case 3:
                return inv.getTotal();
            default:
                return "";
        }
    }
}
