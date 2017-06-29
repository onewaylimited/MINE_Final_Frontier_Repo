package edu.mtu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This extended JPanel is intended to display an image
 * that we will use to denote information coming from the rover.
 * It also acts as a catch all for any other panels we implement for a 
 * sake of consistency.
 * @author Daniel Wagner
 *
 */
@SuppressWarnings("serial")
public class DisplayPanel extends JPanel{
	
	/**
	 * Enum we can use to 
	 * @author Dan
	 *
	 */
	public enum Type{
		LABEL, 
		IMAGE, 
		BLANK
	}

	
	private boolean created;
	private Font labelFont;
	private Font labelFont2;
	private Image image;
	private JLabel label;
	private JLabel label2;
	private Type type;
	private GridBagConstraints gc = new GridBagConstraints();
	private GridBagLayout layout = new GridBagLayout();
	
//	/**
//	 * Image panel to be used as image
//	 * @param image .png image to be displayed in this panel
//	 */
//	public DisplayPanel(Image image){
//		setImage(image);
//		setLayout(new BorderLayout());
//	}
	
	public DisplayPanel(Type type, String label){
		this.type = type;
		switch(this.type){
		case LABEL:
			labelPanel(label);
			break;
		case BLANK:
			blankPanel();
			break;
		case IMAGE:
			imagePanel();
			break;
		default:
			break;		
		}
	}
	
	/**
	 * For panels that require 2 labels. 
	 * @param type
	 * @param label
	 * @param label2
	 */
	public DisplayPanel(Type type, String label, String label2){
		this.type = type; 
		switch(this.type){
		case LABEL: 
			doubleLabelPanel(label, label2);
			break;
		case BLANK:
			blankPanel();
			break;
		case IMAGE:
			imagePanel();
			break;
		default:
			break;
		}
	}
	
	/**
	 * For if we decide to use this as a basic JPanel for
	 * sake of consistency across other classes.
	 */
	public DisplayPanel(){
		super();
	}
	
	/**
	 * 
	 */
	private void labelPanel(String string){
		// Set JPanel layout
		this.setLayout(layout);
		
		// Set font
		labelFont = new Font("Ariel", Font.BOLD, 18);
		
		// Create JLabel
		label = new ResizeJLabel(string);
		label.setFont(labelFont);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		gc.gridx = 0;
		gc.gridy = 0;
		this.add(label, gc);
		
		repaint();
	}
	
	/**
	 * Method to create a double label panel
	 * @param string
	 * @param string2
	 */
	private void doubleLabelPanel(String string, String string2){
		// Set JPanel layout
		this.setLayout(layout);
		
		// Set font
		labelFont = new Font("Ariel", Font.BOLD, 14);
		labelFont2 = new Font("Ariel", Font.BOLD, 24);
		
		// Create JLabels
		label = new JLabel(string);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(labelFont);
		label2 = new JLabel(string2);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(labelFont2);
		
		// Add components to the panel
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		this.add(label, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(label2, gc);
		
		repaint();
	}
	
	/**
	 * 
	 */
	private void imagePanel(){
		
	}
	
	/**
	 * 
	 */
	private void blankPanel(){
		
	}
	
	/**
	 * Update the text of our JLabel
	 *<p> To be used when this is set to a LABEL Panel
	 * We repaint at the end to refresh the labels text
	 * 
	 * @param string
	 */
	public void setText(String string){
		if(type.equals(Type.LABEL)){
			label.setText(string);
		}
		else return;
		repaint();
	}
	
	/**
	 * Set the image for the panel and repaint
	 * to update the panel
	 * @param image Desired image to display
	 */
	public void setImage(Image image){
		this.image = image;
		repaint();
	}
	
	/**
	 * Overridden paintComponent method to 
	 * resize the image to take up the maximum amount of space in the JPanel
	 */
	@Override 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Check if there is an image to paint
		if(image != null){
			Dimension d = getSize();  // Get the size of the JPanel for scaling purposes
			g.drawImage(image, 0, 0, d.width, d.height, null);
		}
	}
}
