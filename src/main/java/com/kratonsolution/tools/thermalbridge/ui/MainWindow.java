/**
 * 
 */
package com.kratonsolution.tools.thermalbridge.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

/**
 * @author Agung Dodi Perdana
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tab;

	public MainWindow() {
		
		setTitle("Printer Bridge v-1.0");
		setDefaultCloseOperation(ICONIFIED);
		initMenu();
		setSize(300, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	private void initMenu() {
		
		tab = new JTabbedPane();

		add(tab);
		
		JMenuItem printer = new JMenuItem("Printer Settings");
		printer.addActionListener((event) -> {
			tab.add("Printer Settings", new PrinterSettingPanel());
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener((event) -> {setState(ICONIFIED);});
		
		JMenu menu = new JMenu("Settings");
		menu.add(printer);
		menu.addSeparator();
		menu.add(exit);
		
		JMenuBar bar = new JMenuBar();
		bar.add(menu);
		
		setJMenuBar(bar);
	}
}
