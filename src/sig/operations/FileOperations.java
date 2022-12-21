package sig.operations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import sig.SalesInvoiceGeneratorFrame;
import sig.exceptions.FileFormatException;
import sig.models.HeaderTableModel;
import sig.models.InvoiceHeader;
import sig.models.InvoiceLine;

/**
 * @author Abdelrhman
 *
 */
public class FileOperations {

	private SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame;

	public FileOperations(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame) {
		this.salesInvoiceGeneratorFrame = salesInvoiceGeneratorFrame;
	}

	public FileOperations() {
	}

	public void load(String headerPath, String linePath) throws Exception {
		File headerFile = null;
		File lineFile = null;
		if (headerPath == null && linePath == null) {
			JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame,
					"Select header file first, then select line file.", "Invoice files", JOptionPane.WARNING_MESSAGE);
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("CSV files (*csv)", "csv"));
			int result = fc.showOpenDialog(salesInvoiceGeneratorFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				validateCSVFileFormat(fc.getSelectedFile());
				headerFile = fc.getSelectedFile();
				result = fc.showOpenDialog(salesInvoiceGeneratorFrame);
				if (result == JFileChooser.APPROVE_OPTION) {
					validateCSVFileFormat(fc.getSelectedFile());
					lineFile = fc.getSelectedFile();
				}
			}
		} else {
			headerFile = new File(headerPath);
			lineFile = new File(linePath);
		}

		if (headerFile != null && lineFile != null) {
			try {

				List<String> headerLines = Files.lines(Paths.get(headerFile.getAbsolutePath()))
						.collect(Collectors.toList());

				List<String> lineLines = Files.lines(Paths.get(lineFile.getAbsolutePath()))
						.collect(Collectors.toList());

				salesInvoiceGeneratorFrame.getInvoices().clear();
				for (String headerLine : headerLines) {
					String[] parts = headerLine.split(",");
					String numString = parts[0];
					String dateString = parts[1];
					String name = parts[2];
					int num = Integer.parseInt(numString);
					Date date = salesInvoiceGeneratorFrame.sdf.parse(dateString);
					InvoiceHeader inv = new InvoiceHeader(num, name, date);
					salesInvoiceGeneratorFrame.getInvoices().add(inv);
				}
				for (String lineLine : lineLines) {
					String[] parts = lineLine.split(",");
					int num = Integer.parseInt(parts[0]);
					String name = parts[1];
					double price = Double.parseDouble(parts[2]);
					int count = Integer.parseInt(parts[3]);
					InvoiceHeader inv = salesInvoiceGeneratorFrame.getInvoiceByNum(num);
					InvoiceLine line = new InvoiceLine(name, price, count, inv);
					inv.getLines().add(line);
				}
				salesInvoiceGeneratorFrame
						.setHeaderTableModel(new HeaderTableModel(salesInvoiceGeneratorFrame.getInvoices()));
			} catch (Exception ex) {
				if(ex instanceof NoSuchFileException) {
					JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame, "File not found", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void save() {
		JFileChooser fc = new JFileChooser();
		File headerFile = null;
		File lineFile = null;
		int result = fc.showSaveDialog(salesInvoiceGeneratorFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
			headerFile = fc.getSelectedFile();
			result = fc.showSaveDialog(salesInvoiceGeneratorFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				lineFile = fc.getSelectedFile();
			}
		}

		if (headerFile != null && lineFile != null) {
			String headerData = "";
			String lineData = "";
			for (InvoiceHeader inv : salesInvoiceGeneratorFrame.getInvoices()) {
				headerData += inv.getAsCSV();
				headerData += "\n";
				for (InvoiceLine line : inv.getLines()) {
					lineData += line.getAsCSV();
					lineData += "\n";
				}
			}
			try {
				FileWriter headerFW = new FileWriter(headerFile);
				FileWriter lineFW = new FileWriter(lineFile);
				headerFW.write(headerData);
				lineFW.write(lineData);
				headerFW.flush();
				lineFW.flush();
				headerFW.close();
				lineFW.close();

			} catch (IOException ex) {
				JOptionPane.showMessageDialog(salesInvoiceGeneratorFrame, "Error while writing file(s)", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public ArrayList<InvoiceHeader> readFile(File headerFile, File lineFile) throws IOException, ParseException {

		ArrayList<InvoiceHeader> invoiceHeaders = null;
		
		if (headerFile != null && lineFile != null) {

			invoiceHeaders = new ArrayList<InvoiceHeader>();

			List<String> headerLines = Files.lines(Paths.get(headerFile.getAbsolutePath()))
					.collect(Collectors.toList());

			List<String> lineLines = Files.lines(Paths.get(lineFile.getAbsolutePath())).collect(Collectors.toList());

			for (String headerLine : headerLines) {
				String[] parts = headerLine.split(",");
				String numString = parts[0];
				String dateString = parts[1];
				String name = parts[2];
				int num = Integer.parseInt(numString);
				Date date = SalesInvoiceGeneratorFrame.sdf.parse(dateString);
				InvoiceHeader inv = new InvoiceHeader(num, name, date);
				invoiceHeaders.add(inv);
			}
			for (String lineLine : lineLines) {
				String[] parts = lineLine.split(",");
				int num = Integer.parseInt(parts[0]);
				String name = parts[1];
				double price = Double.parseDouble(parts[2]);
				int count = Integer.parseInt(parts[3]);
				InvoiceHeader inv = invoiceHeaders.get(num - 1);
				InvoiceLine line = new InvoiceLine(name, price, count, inv);
				inv.getLines().add(line);
			}
		}
		return invoiceHeaders;

	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}
	
	private void validateCSVFileFormat(File file) throws FileFormatException{
		if(!"csv".equalsIgnoreCase(getFileExtension(file))) {
			throw new FileFormatException("Wrong file format");
		}
	}
	
	public static void main(String[] args) {

		FileOperations fileOperations = new FileOperations();
		try {

			File invoiceHeaderFile = new File("src/resources/InvoiceHeader.csv");
			File invoiceLineFile = new File("src/resources/InvoiceLine.csv");

			ArrayList<InvoiceHeader> invoiceHeaders = fileOperations.readFile(invoiceHeaderFile, invoiceLineFile);

			printInvoices(invoiceHeaders);

		} catch (Exception e) {
			System.out.println("Something went wrong while reading invoices file");
		}
	}

	private static void printInvoices(ArrayList<InvoiceHeader> invoiceHeaders) {
		for (int index = 0; index < invoiceHeaders.size(); index++) {
			InvoiceHeader invoiceHeader = invoiceHeaders.get(index);
			System.out.println(invoiceHeader.getNumber());
			System.out.println("{");
			SimpleDateFormat DateFormat
		      = new SimpleDateFormat("MM/dd/yyyy");
			System.out.print(DateFormat.format(invoiceHeader.getDate()) + ",");
			System.out.println(invoiceHeader.getName());
			for (int innerIndex = 0; innerIndex < invoiceHeader.getLines().size(); innerIndex++) {
				InvoiceLine invoiceLine = invoiceHeader.getLines().get(innerIndex);
				System.out.print(invoiceLine.getName() + ",");
				System.out.print(invoiceLine.getPrice() + ",");
				System.out.println(invoiceLine.getCount());
			}
			System.out.println("}\n");
		}
	}

}
