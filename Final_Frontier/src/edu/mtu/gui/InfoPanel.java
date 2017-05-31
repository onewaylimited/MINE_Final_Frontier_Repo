package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.mtu.gui.DisplayPanel.Type;

/**
 * Initialize Information Panel. 
 * <p> This is the panel that will display all of the information that
 * will be displayed based on info from the rover. This is where we will
 * add new panels for newer information we need in the future. 
 * <p> Please note that currently there is a blank panel in this panel
 * to be used as padding. If you need to add a new panel, replace 
 * blankPane and update this JavaDoc. 
 * @author Daniel Wagner
 * @see GuiPanel
 *
 */
public class InfoPanel extends GuiPanel {

	private JPanel infoPane;
	private JPanel dirPane, velPane, cBatPane, rBatPane, conStrPane, blankPane;
	private String dirString, velString, cBatString, rBatString, conStrString;

	/**
	 * Initialize infoPane
	 */
	@Override
	public void init() {
		// Initialize layout
		layout = new GridLayout(2,3);

		// Initialize mainPanel
		infoPane = new JPanel();
		infoPane = new JPanel(layout);
		
		// Add panels to list
		panels.add(dirPane);
		panels.add(velPane);
		panels.add(cBatPane);
		panels.add(rBatPane);
		panels.add(conStrPane);
		panels.add(blankPane);

		// Initialize JLabels
		dirString = "Heading (N,S,E,W)";
		velString = "Current Velocity";
		cBatString = "Control Systems Battery Life";
		rBatString = "Rover Battery Life";
		conStrString = "Connection Strength";


		// Initialize sub-panels
		dirPane = new DisplayPanel(Type.LABEL, dirString);  // Direction panel
		dirPane.setBackground(Color.MAGENTA);
		
		velPane = new DisplayPanel(Type.LABEL, velString);  // Velocity panel
		velPane.setBackground(Color.white);

		cBatPane = new DisplayPanel(Type.LABEL, cBatString);  // Control Battery panel
		cBatPane.setBackground(Color.blue);

		rBatPane = new DisplayPanel(Type.LABEL, rBatString);  // Rover Battery panel
		rBatPane.setBackground(Color.green);

		conStrPane = new DisplayPanel(Type.LABEL, conStrString);  // connection Strength panel
		conStrPane.setBackground(Color.cyan);

		blankPane = new DisplayPanel();  // Blank spacer panel
		blankPane.setBackground(Color.gray);

		// Add components to infoPanel
		infoPane.add(dirPane);  // grid 1,1
		infoPane.add(velPane);  // grid 1,2
		infoPane.add(cBatPane);  // grid 1,3
		infoPane.add(rBatPane);  // grid 2,1
		infoPane.add(conStrPane);  // grid 2,2
		infoPane.add(blankPane);  // grid 2,3
		
		// Revalidate and repaint
		infoPane.revalidate();
		infoPane.repaint();
	}

	@Override
	public JPanel getPanel() {
		return infoPane;
	}

	@Override
	public void refresh() {
		infoPane.revalidate();
		infoPane.repaint();
	}

}
