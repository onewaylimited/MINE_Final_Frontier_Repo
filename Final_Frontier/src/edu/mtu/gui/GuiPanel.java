package edu.mtu.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Abstract GUI Panel class
 * @author Dan Wagner
 *
 */
public abstract class GuiPanel {
	protected ArrayList<Component> components = new ArrayList<Component>();
	protected ArrayList<JPanel> panels = new ArrayList<JPanel>();
	protected JPanel panel;
	protected GridLayout layout;
	
	/**
	 * Initialize the GUI Panel
	 */
	public GuiPanel(){
		init();
	}
	
	/**
	 * Initialize the GUI Panel
	 */
	public abstract void init();
	
	/**
	 * Get the Array of components
	 * @return ArrayList<Component> list of components
	 */
	public ArrayList<Component> getComp(){
		return components;
	}
	
	/*
	 * Return the JPanel
	 */
	public abstract JPanel getPanel();
	
	public abstract void refresh();
	
}
