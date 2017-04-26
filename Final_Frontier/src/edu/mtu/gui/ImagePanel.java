package edu.mtu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * This extended JPanel is intended to display an image
 * that we will use to denote information coming from the rover.
 * @author Daniel Wagner
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	
	private Image image;
	
	/**
	 * Image panel to be used as image
	 * @param image .png image to be displayed in this panel
	 */
	public ImagePanel(Image image){
		setImage(image);
		setLayout(new BorderLayout());
	}
	
	/**
	 * If we decide to use this as a basic JPanel for
	 * sake of simplicity in other classes.
	 */
	public ImagePanel(){
		super();
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
