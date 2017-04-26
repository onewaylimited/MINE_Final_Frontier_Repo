package edu.mtu.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;

/**
 * Automatically resizeable JLabel. This should guarantee that the
 * label occupies the most space possible in its current JPanel. 
 * @author Daniel Wagner
 *
 */
@SuppressWarnings("serial")
public class ResizeJLabel extends JLabel{
	public static final int MIN_FONT_SIZE=3;
	public static final int MAX_FONT_SIZE=240;
	Graphics g;

	/**
	 * Run the super constructor to display the text, and then
	 * initialize the label.
	 * @param text String we wish to display in the label
	 */
	public ResizeJLabel(String text) {
		super(text);
		init();
	}
	
	/**
	 * Add a component listener to the JLabel itself to run
	 * the adaptLabelFont method automatically when the component
	 * is resized.
	 */
	protected void init() {
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				adaptLabelFont(ResizeJLabel.this);
			}
		});
	}

	/**
	 * Change the labels font to take up as much of the
	 * JPanel as possible
	 * @param l JLabel to resize 
	 */
	protected void adaptLabelFont(JLabel l) {
		if (g==null) {
			return;
		}
		Font labelFont = l.getFont();
		String labelText = l.getText();

		int stringWidth = l.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = l.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = l.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		l.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		repaint();
	}

	/**
	 * Get the text size of the label
	 * @param l JLabel to check
	 * @param f Font to use on text
	 * @return Dimension describing bounding box of JLabel size. 
	 */
	private Dimension getTextSize(JLabel l, Font f) {
		Dimension size=new Dimension();
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics(f);
		size.width=fm.stringWidth(l.getText());
		size.height=fm.getHeight();

		return size;
	}

	/**
	 * Overridden paintComponent method to paint the text correctly
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g=g;
	}
}
