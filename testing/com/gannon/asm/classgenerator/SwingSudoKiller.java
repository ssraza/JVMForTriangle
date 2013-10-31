package com.gannon.asm.classgenerator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Graphical Sudoku game solver.
 * The user should fill the board with the puzzle to solve and click the
 * 'Start' button (or type 'ALT-s') to get the solution.
 *
 * @author Daniele Mazzocchio
 * @version 1.0
 */
public class SwingSudoKiller {

	private SwingSudokuBoard sb;    // Puzzle to solve;
    /**
     * Draw the game board.
     * @param ssb The puzzle to solve.
     */
    
    public void setBoard(SwingSudokuBoard ssb){
    	this.sb = ssb;
    	final JPanel panel = ssb.getPanel();
    	
    	Runnable runner = new Runnable() {
            public void run() {
                final JFrame frame = new JFrame("SudoKiller");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                ActionListener al = new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        if (! guess(0, 0))
                            JOptionPane.showMessageDialog(frame, "Solution not found!");
                    }
                };
                frame.setLayout(new GridBagLayout());
                addComponent(frame, panel, 0, 0, 1, 1);
                
                JButton b = new JButton("Start!");
                b.setMnemonic(KeyEvent.VK_S);
                b.addActionListener(al);
                addComponent(frame, b, 0, 1, 1, 1);

                frame.setSize(240, 280);
                frame.setVisible(true);
            }
        };
        
        EventQueue.invokeLater(runner);
    }
    
    /**
     * Add a component to the GUI.
     * @param container Container to add the component to.
     * @param component The component to be added.
     * @param gridx Horizontal cell position inside the grid.
     * @param gridy Vertical cell position inside the grid.
     * @param gridwidth Number of cells in a row for the text field.
     * @param gridheight Number of cells in a column for the text field.
     */
    private static void addComponent(Container container, Component component,
        int gridx, int gridy, int gridwidth, int gridheight) {
        Insets insets = new Insets(0, 0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
                gridheight, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, insets, 0, 0);
        container.add(component, gbc);
    }
    
    /**
     * Test all candidate numbers for a given cell until the board is complete.
     * @param row Cell's row.
     * @param col Cell's column.
     * @return &lt;false&gt; if no legal numbers are found for this cell.
     */
    public boolean guess(int row, int col) {
        int nextCol = (col + 1) % sb.size;
        int nextRow = (nextCol == 0) ? row + 1 : row;
        
        //try {
            if (sb.getCell(row, col) != sb.EMPTY)
                return guess(nextRow, nextCol);
        //}
        //catch (ArrayIndexOutOfBoundsException e) {
        //        return true;
        //}
        
        for (int i = 1; i <= sb.size; i++) {
            if (check(i, row, col)) {
                sb.setCell(i, row, col);
                if (guess(nextRow, nextCol)) {
                    return true;
                }
            }
        }
        sb.setCell(sb.EMPTY, row, col);
        return false;
    }
    
    /**
     * Check if a number is, according to Sudoku rules, a legal candidate for
     * the given cell.
     * @param num Number to check.
     * @param row Cell's row.
     * @param col Cell's column.
     * @return &lt;false&gt; if &lt;num&gt; already appears in the row, column
     *         or box the cell belongs to or &lt;true&gt; otherwise.
     */
    private boolean check(int num, int row, int col) {
        int r = (row / sb.box_size) * sb.box_size;
        int c = (col / sb.box_size) * sb.box_size;
        
        for (int i = 0; i < sb.size; i++) {
            if (sb.getCell(row, i) == num ||
                sb.getCell(i, col) == num ||
                sb.getCell(r + (i % sb.box_size), c + (i / sb.box_size)) == num) {
                return false;
            }
        }
        return true;
    }
}
