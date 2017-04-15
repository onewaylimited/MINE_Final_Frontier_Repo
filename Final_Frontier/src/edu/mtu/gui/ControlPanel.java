package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.ColorConvertOp;

import javax.swing.JButton;
import javax.swing.JPanel;

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
		
		// Add components to list
		components.add(shutDownButton);
		components.add(conButton);
		components.add(mapButton);
		components.add(bastionButton);
		
		// Initialize control panel buttons
		shutDownButton = new JButton("Shutdown Control");
		conButton = new JButton("Conect/Reconnect");
		mapButton =  new JButton("Controller Mappings");
		bastionButton = new JButton("Bastion Button");
		
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