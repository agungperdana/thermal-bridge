/**
 * 
 */
package com.kratonsolution.tools.thermalbridge.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author agung
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(150, 100);
		setVisible(true);
		
		initMenu();
	}
	
	private void initMenu() {
		
		JMenuItem printer = new JMenuItem("Printer Settings");
		printer.addActionListener((event) -> {
			
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener((event) ->{System.exit(0);});
		
		JMenu menu = new JMenu("Settings");
		menu.add(printer);
		menu.addSeparator();
		menu.add(exit);
		
		JMenuBar bar = new JMenuBar();
		bar.add(menu);
		
		setJMenuBar(bar);
	}
}
