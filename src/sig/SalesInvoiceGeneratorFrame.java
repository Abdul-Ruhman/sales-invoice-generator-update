package sig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sig.listners.SalesInvoiceGeneratorListner;
import sig.models.HeaderTableModel;
import sig.models.InvoiceHeader;
import sig.models.LineTableModel;

/**
 * @author Abdelrhman
 *
 */
public class SalesInvoiceGeneratorFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3176310461565769362L;
	
	private JButton createInvBtn;
    private JButton createItemBtn;
    private JButton deleteInvBtn;
    private JButton deleteItemBtn;
    private JLabel invCustNameLbl;
    private JLabel invDateLbl;
    private JLabel invNumLbl;
    private JLabel invTotalLbl;
    private JTable invoicesTable;
    private JLabel invoiceNumberLbl;
    private JLabel invoiceDateLbl;
    private JLabel customerNameLbl;
    private JLabel invoiceTotalLbl;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable linesTable;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private SalesInvoiceGeneratorListner listener = new SalesInvoiceGeneratorListner(this);
    private ArrayList<InvoiceHeader> invoices;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private HeaderTableModel headerTableModel;
    private LineTableModel lineTableModel;
	
	public SalesInvoiceGeneratorFrame() {
		initState();
	}
	
	public static void main(String[] args) {
		
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(SalesInvoiceGeneratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(SalesInvoiceGeneratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(SalesInvoiceGeneratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(SalesInvoiceGeneratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	SalesInvoiceGeneratorFrame frame = new SalesInvoiceGeneratorFrame();
	                frame.setLocation(300, 300);
	                frame.setVisible(true);
	                frame.listener.load("src/resources/InvoiceHeader.csv", "src/resources/InvoiceLine.csv");
	            }
	        });
		
	}

	private void initState() {
		
		    jScrollPane1 = new JScrollPane();
		    
	        invoicesTable = new JTable();
	        invoicesTable.getSelectionModel().addListSelectionListener(listener);
	        
	        createInvBtn = new JButton();
	        createInvBtn.addActionListener(listener);
	        deleteInvBtn = new JButton();
	        deleteInvBtn.addActionListener(listener);
	      
	        invoiceNumberLbl = new JLabel();
	        invoiceDateLbl = new JLabel();
	        customerNameLbl = new JLabel();
	        invoiceTotalLbl = new JLabel();
	        
	        jScrollPane2 = new JScrollPane();
	        linesTable = new JTable();
	        
	        createItemBtn = new JButton();
	        createItemBtn.addActionListener(listener);
	        deleteItemBtn = new JButton();
	        deleteItemBtn.addActionListener(listener);
	        
	        invCustNameLbl = new JLabel();
	        invTotalLbl = new JLabel();
	        invDateLbl = new JLabel();
	        invNumLbl = new JLabel();
	        
	        jMenuBar1 = new JMenuBar();
	        jMenu1 = new JMenu();
	        
	        loadMenuItem = new JMenuItem();
	        loadMenuItem.addActionListener(listener);
	        saveMenuItem = new JMenuItem();
	        saveMenuItem.addActionListener(listener);

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        invoicesTable.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {

	            }
	        ));
	        jScrollPane1.setViewportView(invoicesTable);

	        createInvBtn.setText("Create Invoice");

	        deleteInvBtn.setText("Delete Invoice");

	        invoiceNumberLbl.setText("Invoice Number");

	        invoiceDateLbl.setText("Invoice Date");

	        customerNameLbl.setText("Customer Name");

	        invoiceTotalLbl.setText("Invoice Total");

	        linesTable.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {

	            }
	        ));
	        jScrollPane2.setViewportView(linesTable);

	        createItemBtn.setText("Create Item");

	        deleteItemBtn.setText("Delete Item");

	        invCustNameLbl.setText(".");

	        invTotalLbl.setText(".");

	        invDateLbl.setText(".");

	        invNumLbl.setText(".");

	        jMenu1.setText("File");

	        loadMenuItem.setText("Load");
	        jMenu1.add(loadMenuItem);

	        saveMenuItem.setText("Save");
	        jMenu1.add(saveMenuItem);

	        jMenuBar1.add(jMenu1);

	        setJMenuBar(jMenuBar1);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(72, 72, 72)
	                        .addComponent(createInvBtn)
	                        .addGap(45, 45, 45)
	                        .addComponent(deleteInvBtn)))
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(customerNameLbl)
	                                    .addComponent(invoiceTotalLbl)
	                                    .addComponent(invoiceDateLbl)
	                                    .addComponent(invoiceNumberLbl))
	                                .addGap(18, 18, 18)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(invNumLbl)
	                                    .addComponent(invDateLbl)
	                                    .addComponent(invTotalLbl)
	                                    .addComponent(invCustNameLbl))))
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(62, 62, 62)
	                        .addComponent(createItemBtn)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(deleteItemBtn)
	                        .addGap(96, 96, 96))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(invoiceNumberLbl)
	                            .addComponent(invNumLbl))
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(invoiceDateLbl)
	                            .addComponent(invDateLbl))
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(customerNameLbl)
	                            .addComponent(invCustNameLbl))
	                        .addGap(18, 18, 18)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(invoiceTotalLbl)
	                            .addComponent(invTotalLbl))
	                        .addGap(18, 18, 18)
	                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(createInvBtn)
	                        .addComponent(createItemBtn)
	                        .addComponent(deleteItemBtn))
	                    .addComponent(deleteInvBtn))
	                .addContainerGap(53, Short.MAX_VALUE))
	        );

	        pack();
		
	}
	
	 public HeaderTableModel getHeaderTableModel() {
	        return headerTableModel;
	    }

	    public LineTableModel getLineTableModel() {
	        return lineTableModel;
	    }

	    public void setLineTableModel(LineTableModel lineTableModel) {
	        this.lineTableModel = lineTableModel;
	        getLinesTable().setModel(lineTableModel);
	    }

	    public JLabel getInvCustomerNameLbl() {
	        return invCustNameLbl;
	    }

	    public void setHeaderTableModel(HeaderTableModel headerTableModel) {
	        this.headerTableModel = headerTableModel;
	        getInvoicesTable().setModel(headerTableModel);
	    }
	    
	    public JLabel getInvDateLbl() {
	        return invDateLbl;
	    }

	    public JLabel getInvNumLbl() {
	        return invNumLbl;
	    }

	    public JLabel getInvTotalLbl() {
	        return invTotalLbl;
	    }

	    public JTable getInvoicesTable() {
	        return invoicesTable;
	    }

	    public JTable getLinesTable() {
	        return linesTable;
	    }

	    public SalesInvoiceGeneratorListner getListener() {
	        return listener;
	    }

	    public ArrayList<InvoiceHeader> getInvoices() {
	        if (invoices == null) {
	            invoices = new ArrayList<>();
	        }
	        return invoices;
	    }

	    public InvoiceHeader getInvoiceByNum(int num) {
	        InvoiceHeader inv = null;
	        
	        for (InvoiceHeader header : getInvoices()) {
	            if (header.getNumber() == num) {
	                inv = header;
	                break;
	            }
	        }
	        return inv;
	    }
	    
	    public int getNextInvNum() {
	        int num = -1;
	        for (InvoiceHeader inv : getInvoices()) {
	            num = Math.max(num, inv.getNumber());
	        }
	        return ++num;
	    }
	
}
