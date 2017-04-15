package edu.mtu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	
	private Image image;
	
	public ImagePanel(Image image){
		setImage(image);
		setLayout(new BorderLayout());
	}
	
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
