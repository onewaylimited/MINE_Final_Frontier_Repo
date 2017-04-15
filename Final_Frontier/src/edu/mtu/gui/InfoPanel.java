package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Initialize Information Panel 
 * @author Dan Wagner
 *
 */
public class InfoPanel extends GuiPanel {

	private JPanel infoPane;
	private JPanel dirPane, velPane, cBatPane, rBatPane, conStrPane, blankPane;

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
		JLabel dirLabel = new JLabel("Heading (N,S,E,W)");
		JLabel velLabel = new JLabel("Current Velocity");
		JLabel cBatLabel = new JLabel("Control Systems Battery Life");
		JLabel rBatLabel = new JLabel("Rover Battery Life");
		JLabel conStrLabel = new JLabel("Connection Strength");
		JLabel blankLabel = new JLabel("");

		// Initialize sub-panels
		dirPane = new ImagePanel();  // Direction panel
		dirPane.setBackground(Color.MAGENTA);
		dirPane.add(dirLabel);  // TODO: Remove Place holder labels

		velPane = new ImagePanel();  // Velocity panel
		velPane.setBackground(Color.white);
		velPane.add(velLabel);  // TODO: Remove Place holder labels

		cBatPane = new ImagePanel();  // Control Battery panel
		cBatPane.setBackground(Color.blue);
		cBatPane.add(cBatLabel);  // TODO: Remove Place holder labels

		rBatPane = new ImagePanel();  // Rover Battery panel
		rBatPane.setBackground(Color.green);
		rBatPane.add(rBatLabel);  // TODO: Remove Place holder labels

		conStrPane = new ImagePanel();  // connection Strength panel
		conStrPane.setBackground(Color.cyan);
		conStrPane.add(conStrLabel);  // TODO: Remove Place holder labels

		blankPane = new ImagePanel();  // Blank spacer panel
		blankPane.setBackground(Color.gray);
		blankPane.add(blankLabel);  // TODO: Remove Place holder labels

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
