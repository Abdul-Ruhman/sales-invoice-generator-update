package sig.dialogs;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sig.SalesInvoiceGeneratorFrame;

/**
 * @author Abdelrhman
 *
 */
public class InvoicesHeaderDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4242257913063810015L;
	
	private JTextField customerNameField;
	private JTextField invDateField;
	private JLabel customerNameLbl;
	private JLabel invDateLbl;
	private JButton okBtn;
	private JButton cancelBtn;
	
	public InvoicesHeaderDialog(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame) {
        customerNameLbl = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newInvoiceOK");
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        okBtn.addActionListener(salesInvoiceGeneratorFrame.getListener());
        cancelBtn.addActionListener(salesInvoiceGeneratorFrame.getListener());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDateField);
        add(customerNameLbl);
        add(customerNameField);
        add(okBtn);
        add(cancelBtn);
        setModal(true);
        pack();
        
    }
	
    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }

}
