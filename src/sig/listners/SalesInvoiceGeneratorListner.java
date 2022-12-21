package sig.listners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sig.SalesInvoiceGeneratorFrame;
import sig.dialogs.InvoiceLineItemsDialog;
import sig.dialogs.InvoicesHeaderDialog;
import sig.models.InvoiceHeader;
import sig.models.InvoiceLine;
import sig.models.LineTableModel;
import sig.operations.FileOperations;

/**
 * @author Abdelrhman
 *
 */
public class SalesInvoiceGeneratorListner implements ActionListener, ListSelectionListener {

	private SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame;
	private InvoicesHeaderDialog headerDialog;
	private InvoiceLineItemsDialog lineItemsDialog;

	public SalesInvoiceGeneratorListner(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame) {
		this.salesInvoiceGeneratorFrame = salesInvoiceGeneratorFrame;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int selectedRow = salesInvoiceGeneratorFrame.getInvoicesTable().getSelectedRow();
		if (selectedRow > -1) {
			InvoiceHeader inv = salesInvoiceGeneratorFrame.getInvoices().get(selectedRow);
			salesInvoiceGeneratorFrame.getInvNumLbl().setText("" + inv.getNumber());
			salesInvoiceGeneratorFrame.getInvDateLbl().setText(SalesInvoiceGeneratorFrame.sdf.format(inv.getDate()));
			salesInvoiceGeneratorFrame.getInvCustomerNameLbl().setText(inv.getName());
			salesInvoiceGeneratorFrame.getInvTotalLbl().setText("" + inv.getTotal());
			ArrayList<InvoiceLine> lines = inv.getLines();
			salesInvoiceGeneratorFrame.setLineTableModel(new LineTableModel(lines));
		} else {
			salesInvoiceGeneratorFrame.getInvNumLbl().setText("");
			salesInvoiceGeneratorFrame.getInvDateLbl().setText("");
			salesInvoiceGeneratorFrame.getInvCustomerNameLbl().setText("");
			salesInvoiceGeneratorFrame.getInvTotalLbl().setText("");
			salesInvoiceGeneratorFrame.setLineTableModel(new LineTableModel());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case "Load":
			load(null, null);
			break;
		case "Save":
			save();
			break;
		case "Create Invoice":
			createInvoice();
			break;
		case "Delete Invoice":
			deleteInvoice();
			break;
		case "Create Item":
			createItem();
			break;
		case "Delete Item":
			deleteItem();
			break;
		case "newInvoiceOK":
			newInvoiceOK();
			break;
		case "newInvoiceCancel":
			newInvoiceCancel();
			break;
		case "newLineOK":
			newLineOK();
			break;
		case "newLineCancel":
			newLineCancel();
			break;
		}
	}

	private void save() {
		FileOperations fileOperations = new FileOperations(salesInvoiceGeneratorFrame);
		fileOperations.save();	
	}

	public void load(String headerPath, String linePath) {
		FileOperations fileOperations = new FileOperations(salesInvoiceGeneratorFrame);
		try {
			fileOperations.load(headerPath, linePath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame, e.getMessage(), "Wrong file format", JOptionPane.ERROR_MESSAGE);
		}	
	}

	private void createInvoice() {
		headerDialog = new InvoicesHeaderDialog(salesInvoiceGeneratorFrame);
		headerDialog.setLocation(300, 300);
		headerDialog.setVisible(true);
	}

	private void deleteInvoice() {

		int selectedRow = salesInvoiceGeneratorFrame.getInvoicesTable().getSelectedRow();
		if (selectedRow > -1) {
			salesInvoiceGeneratorFrame.getInvoices().remove(selectedRow);
			salesInvoiceGeneratorFrame.getHeaderTableModel().fireTableDataChanged();
		}
	}

	private void createItem() {
		if (salesInvoiceGeneratorFrame.getInvoicesTable().getSelectedRow() > -1) {
			lineItemsDialog = new InvoiceLineItemsDialog(salesInvoiceGeneratorFrame);
			lineItemsDialog.setLocation(300, 300);
			lineItemsDialog.setVisible(true);
		}
	}

	private void deleteItem() {
		int selectedInvoice = salesInvoiceGeneratorFrame.getInvoicesTable().getSelectedRow();
		int selectedItem = salesInvoiceGeneratorFrame.getLinesTable().getSelectedRow();

		if (selectedInvoice > -1 && selectedItem > -1) {
			salesInvoiceGeneratorFrame.getInvoices().get(selectedInvoice).getLines().remove(selectedItem);
			salesInvoiceGeneratorFrame.getLineTableModel().fireTableDataChanged();
			salesInvoiceGeneratorFrame.getHeaderTableModel().fireTableDataChanged();
			salesInvoiceGeneratorFrame.getInvoicesTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
		}
	}

	private void newInvoiceOK() {
		String name = headerDialog.getCustomerNameField().getText();
		String dateStr = headerDialog.getInvDateField().getText();
		newInvoiceCancel();
		try {
			Date date = salesInvoiceGeneratorFrame.sdf.parse(dateStr);
			InvoiceHeader inv = new InvoiceHeader(salesInvoiceGeneratorFrame.getNextInvNum(), name, date);
			salesInvoiceGeneratorFrame.getInvoices().add(inv);
			salesInvoiceGeneratorFrame.getHeaderTableModel().fireTableDataChanged();
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame, "Invalid Date Format", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newInvoiceCancel() {
		headerDialog.setVisible(false);
		headerDialog.dispose();
		headerDialog = null;
	}

	private void newLineOK() {
		String name = lineItemsDialog.getItemNameField().getText();
		String countStr = lineItemsDialog.getItemCountField().getText();
		String priceStr = lineItemsDialog.getItemPriceField().getText();
		newLineCancel();
		try {
			int count = Integer.parseInt(countStr);
			double price = Double.parseDouble(priceStr);
			int currentInv = salesInvoiceGeneratorFrame.getInvoicesTable().getSelectedRow();
			InvoiceHeader inv = salesInvoiceGeneratorFrame.getInvoices().get(currentInv);
			InvoiceLine line = new InvoiceLine(name, price, count, inv);
			inv.getLines().add(line);
			salesInvoiceGeneratorFrame.getHeaderTableModel().fireTableDataChanged();
			salesInvoiceGeneratorFrame.getInvoicesTable().setRowSelectionInterval(currentInv, currentInv);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame, "Invalid Number Format", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newLineCancel() {
		lineItemsDialog.setVisible(false);
		lineItemsDialog.dispose();
		lineItemsDialog = null;
	}

}
