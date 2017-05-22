package edu.mtu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * This extended JPanel is intended to display an image
 * that we will use to denote information coming from the rover.
 * @author Daniel Wagner
 *
 */
@SuppressWarnings("serial")
public class DisplayPanel extends JPanel{
	
	public enum Type{
		LABEL, IMAGE, BLANK
	}
	
	private Image image;
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
	
	public DisplayPanel(Type type){
		switch(type){
		case LABEL:
			labelPanel();
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
	 * If we decide to use this as a basic JPanel for
	 * sake of simplicity in other classes.
	 */
	public DisplayPanel(){
		super();
	}
	
	/**
	 * 
	 */
	private void labelPanel(){
		
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
