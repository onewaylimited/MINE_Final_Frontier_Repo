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
	private String dirTitle, velTitle, cBatTitle, rBatTitle, conStrTitle;
	private String dirVal, velVal, cBatVal, rBatVal, conStrVal;

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
		dirTitle = "Heading (N,S,E,W)";
		dirVal = "N"; 
		velTitle = "Current Velocity (m/s)";
		velVal = "2.7";
		cBatTitle = "Control Systems Battery Life (%)";
		cBatVal = "78";
		rBatTitle = "Rover Battery Life (%)";
		rBatVal = "86";
		conStrTitle = "Connection Strength (poor, fair, good, great)";
		conStrVal = "Good";


		// Initialize sub-panels
		dirPane = new DisplayPanel(Type.LABEL, dirTitle, dirVal);  // Direction panel
		
		velPane = new DisplayPanel(Type.LABEL, velTitle, velVal);  // Velocity panel

		cBatPane = new DisplayPanel(Type.LABEL, cBatTitle, cBatVal);  // Control Battery panel

		rBatPane = new DisplayPanel(Type.LABEL, rBatTitle, rBatVal);  // Rover Battery panel

		conStrPane = new DisplayPanel(Type.LABEL, conStrTitle, conStrVal);  // connection Strength panel

		blankPane = new DisplayPanel();  // Blank spacer panel

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
