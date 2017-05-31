package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.mtu.gui.DisplayPanel.Type;

/**
 * This is the warning panel which will display all of the warnings we receive from the
 * rover. This can be expanded upon later for any extra warnings we may need.
 * @author Daniel Wagner
 * @see GuiPanel
 */
public class WarnPanel extends GuiPanel{

	private JPanel warnPane;
	private String conString, powerString, fuseString;
	private JPanel conPane, powerPane, fusePane;

	@Override
	public void init() {
		// Initialze layout
		layout = new GridLayout(1,4);
		
		// Initialize main panel
		warnPane = new JPanel();
		warnPane.setLayout(layout);
		
		// Add panels to list
		panels.add(conPane);
		panels.add(powerPane);
		panels.add(fusePane);
		
		// Set strings
		conString = "Loss of Connection";
		powerString = "Loss of Power";
		fuseString = "Blown Fuse";

		// Initialize panes
		conPane = new DisplayPanel(Type.LABEL, conString);
		conPane.setBackground(Color.red);

		powerPane = new DisplayPanel(Type.LABEL, powerString);
		powerPane.setBackground(Color.orange);

		fusePane = new DisplayPanel(Type.LABEL, fuseString);
		fusePane.setBackground(Color.yellow);

		// Add components to warnPanel
		warnPane.add(conPane);
		warnPane.add(powerPane);
		warnPane.add(fusePane);

		// Revalidate and repaint
		warnPane.revalidate();
		warnPane.repaint();

	}

	@Override
	public JPanel getPanel() {
		return warnPane;
	}

	@Override
	public void refresh() {
		warnPane.revalidate();
		warnPane.repaint();
	}

}
