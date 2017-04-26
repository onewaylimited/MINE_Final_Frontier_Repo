package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.ColorConvertOp;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Create and populate the ControlPanel for the GUI. This panel
 * is the one with all of the buttons intended to be used by the operator
 * (besides the xbox controller)
 * <p> This class extends GuiPanel, check out that doc for more complete information on 
 * this class and its methods
 * @author Daniel Wagner
 *
 * @see GuiPanel
 */
public class ControlPanel extends GuiPanel{

	private JPanel controlPane;
	private JButton shutDownButton, conButton, mapButton, bastionButton;

	@Override
	public void init() {
		// Initialize layout
		layout = new GridLayout(1,4);

		// Initialize mainPanel
		controlPane = new JPanel();
		controlPane.setLayout(layout);

		// Initialize control panel buttons
		shutDownButton = new JButton("Shutdown");
		conButton = new JButton("Connect/Reconnect");
		mapButton =  new JButton("Controller Mappings");
		bastionButton = new JButton("Bastion Button");

		// Add components to list
		components.add(shutDownButton);
		components.add(conButton);
		components.add(mapButton);
		components.add(bastionButton);

		// Add components to control panel
		controlPane.add(shutDownButton);
		controlPane.add(conButton);
		controlPane.add(mapButton);
		controlPane.add(bastionButton);

		// Revalidate and repaint
		controlPane.revalidate();
		controlPane.repaint();

	}

	@Override
	public JPanel getPanel() {
		return controlPane;
	}

	@Override
	public void refresh() {
		controlPane.revalidate();
		controlPane.repaint();
	}

}
