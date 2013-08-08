package com.gannon.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.gannon.asm.components.BMethod;

public class InputTablePanel extends JPanel {
	private boolean DEBUG = false;

	public InputTablePanel() {
		super();
	}

	public InputTablePanel(Main mainFrame, BMethod selectedMethod) {
		super(new GridLayout(1, 0));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		String[] columnNames = { "Parameter Name", "Type", "Value" };

		Object[][] data = initTable(selectedMethod);

		final JTable table = new JTable(data, columnNames);
		// table.setPreferredSize(new Dimension(450, 100));
		table.setPreferredScrollableViewportSize(new Dimension(50, 50));
		table.setFillsViewportHeight(true);

		if (DEBUG) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				}
			});
		}

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
		
		//
		add(Box.createVerticalStrut(5)); 
		add(new JSeparator(SwingConstants.HORIZONTAL));
		add(Box.createVerticalStrut(5)); 
		add(new JButton("RUN"));
		add(new JButton("Step"));
		add(Box.createVerticalStrut(5)); 
		add(new JSeparator(SwingConstants.HORIZONTAL));
		add(Box.createVerticalStrut(5)); 
	}

	private Object[][] initTable(BMethod selectedMethod) {
		int numberOfParameters = selectedMethod.getNumberOfParameter();
		Object[][] data = new Object[numberOfParameters][3];
		for (int row = 0; row < data.length; row++) {
			data[row][0] = "name" + row;
			data[row][1] = "Integer";
			data[row][2] = new Integer(10);
		}
		return data;
	}

	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

		System.out.println("Value of data: ");
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + model.getValueAt(i, j));
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

}
