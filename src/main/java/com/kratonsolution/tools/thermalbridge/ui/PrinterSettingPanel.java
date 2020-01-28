package com.kratonsolution.tools.thermalbridge.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kratonsolution.tools.thermalbridge.service.ConfigService;
import com.kratonsolution.tools.thermalbridge.ui.model.Config;

import javafx.print.Printer;

/**
 * @author agung
 *
 */
public class PrinterSettingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel content = new JPanel(new GridLayout(2, 2));
	
	private JPanel control = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	private JComboBox<String> thermalPrintOpt = new JComboBox<String>();
	
	private JComboBox<String> standarPrintOpt = new JComboBox<String>();
	
	private JButton button = new JButton("Save");
	
	private ConfigService service = new ConfigService();
	
	public PrinterSettingPanel() {
		
		setLayout(new BorderLayout());
		
		content.add(new JLabel("Thermal Printer :"));
		content.add(thermalPrintOpt);
		content.add(new JLabel("Standar Printer :"));
		content.add(standarPrintOpt);
		
		control.add(button);
		
		add(content, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		
		Printer.getAllPrinters().forEach(printer -> {
			
			thermalPrintOpt.addItem(printer.getName());
			standarPrintOpt.addItem(printer.getName());
		});
		
		Config con = service.load();
		
		thermalPrintOpt.setSelectedItem(con.thermalPrinterName);
		standarPrintOpt.setSelectedItem(con.thermalPrinterName);
		
		button.addActionListener(event -> {
			
			
			if(standarPrintOpt.getSelectedItem() != null) {
				con.standarPrinterName = standarPrintOpt.getSelectedItem().toString();
			}

			if(thermalPrintOpt.getSelectedItem() != null) {
				con.thermalPrinterName = thermalPrintOpt.getSelectedItem().toString();
			}
			
			service.update(con);
			
			JOptionPane.showMessageDialog(PrinterSettingPanel.this, "Setting saved.");
		});
	}

	public JButton getButton() {
		return button;
	}
}
