package com.gannon.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabComponent {
	static final Icon CLOSE_TAB_ICON_RED = new ImageIcon(
			Main.class.getResource("/com/gannon/images16x16/tab_close_red.png"));
	static final Icon CLOSE_TAB_ICON_WHITE = new ImageIcon(
			Main.class.getResource("/com/gannon/images16x16/tab_close_white.png"));

	/**
	 * Adds a component to a JTabbedPane with a little "close tab" button on the
	 * right side of the tab.
	 * 
	 * @param tabbedPane
	 *            the JTabbedPane
	 * @param c
	 *            any JComponent
	 * @param title
	 *            the title for the tab
	 * @param icon
	 *            the icon for the tab, if desired
	 */
	public static void addClosableTab(final JTabbedPane tabbedPane, final JComponent c, final String title,
			final Icon icon) {
		// Add the tab to the pane without any label
		tabbedPane.addTab(null, c);
		int pos = tabbedPane.indexOfComponent(c);

		// Create a FlowLayout that will space things 5px apart
		FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);

		// Make a small JPanel with the layout and make it non-opaque
		JPanel pnlTab = new JPanel(f);
		pnlTab.setOpaque(false);

		// Add a JLabel with title and the left-side tab icon
		JLabel lblTitle = new JLabel(title);
		lblTitle.setIcon(icon);

		// Create a JButton for the close tab button
		JButton btnClose = new JButton();
		btnClose.setOpaque(false);

		// Configure icon and rollover icon for button
		btnClose.setRolloverIcon(CLOSE_TAB_ICON_RED);
		btnClose.setRolloverEnabled(true);
		btnClose.setIcon(CLOSE_TAB_ICON_WHITE);

		// Set border null so the button doesn't make the tab too big
		btnClose.setBorder(null);

		// Make sure the button can't get focus, otherwise it looks funny
		btnClose.setFocusable(false);

		// Put the panel together
		pnlTab.add(lblTitle);
		pnlTab.add(btnClose);

		// Add a thin border to keep the image below the top edge of the tab
		// when the tab is selected
		pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

		// Now assign the component for the tab
		tabbedPane.setTabComponentAt(pos, pnlTab);

		// Add the listener that removes the tab
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				tabbedPane.remove(c);
			}
		};
		btnClose.addActionListener(listener);

		// Optionally bring the new tab to the front
		tabbedPane.setSelectedComponent(c);
	}

}
