package edu.mtu.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the warning panel which will display all of the warnings we receive from the
 * rover. This can be expanded upon later for any extra warnings we may need.
 * @author Daniel Wagner
 * @see GuiPanel
 */
public class WarnPanel extends GuiPanel{

	private JPanel warnPane;
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
		
		// Initialize ResizeJLabels
		JLabel conLabel = new ResizeJLabel("Loss of Connection!");
		JLabel powerLabel = new ResizeJLabel("Power Loss!");
		JLabel fuseLabel = new ResizeJLabel("Blown Fuse!");
		
		// Add Components to list
		components.add(conLabel);
		components.add(powerLabel);
		components.add(fuseLabel);

		// Initialize panes
		conPane = new ImagePanel();
		conPane.setBackground(Color.red);
		conPane.add(conLabel);  // TODO: Remove Place holder labels

		powerPane = new ImagePanel();
		powerPane.setBackground(Color.orange);
		powerPane.add(powerLabel);  // TODO: Remove Place holder labels

		fusePane = new ImagePanel();
		fusePane.setBackground(Color.yellow);
		fusePane.add(fuseLabel);  // TODO: Remove Place holder labels

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
