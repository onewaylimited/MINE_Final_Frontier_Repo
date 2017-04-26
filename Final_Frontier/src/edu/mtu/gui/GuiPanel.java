package edu.mtu.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Abstract GUI Panel class, this is what we base all the three
 * classes that extend it, ControlPanel, InfoPanel, and WarnPanel
 * <p> This class is to make it easier to implement similarly structured panels
 * in the future, such as panels for payload controls, controller mappings, or other
 * possible future features.
 * @author Daniel Wagner
 *
 */
public abstract class GuiPanel {
	protected ArrayList<Component> components = new ArrayList<Component>();
	protected ArrayList<JPanel> panels = new ArrayList<JPanel>();
	protected JPanel panel;
	protected GridLayout layout;
	
	/**
	 * Initialize the GUI Panel on creation
	 * of this class
	 */
	public GuiPanel(){
		init();
	}
	
	/**
	 * Initialize the GUI Panel
	 * <p> Place all of the required components and place them
	 * in the correct locations in the JPanel.
	 */
	public abstract void init();
	
	/**
	 * Get the Array of components that are within the JPanel
	 * @return ArrayList<Component> list of components
	 */
	public ArrayList<Component> getComp(){
		return components;
	}
	
	/**
	 * Return the JPanel that is created by this class
	 */
	public abstract JPanel getPanel();
	
	/**
	 * refresh() and revalidate() the JPanel constructed 
	 * by this class. 
	 */
	public abstract void refresh();
	
}
