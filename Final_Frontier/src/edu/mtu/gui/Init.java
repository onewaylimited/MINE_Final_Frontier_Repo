package edu.mtu.gui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import edu.mtu.network.RoverComm;

/**
 * Initializes the entire application, set this to the start 
 * parameter when creating the executable .JAR file
 * @author Daniel Wagner
 *
 */
public class Init {
	
	/**
	 * Constructor for Init class
	 */
	Init(){
		//TODO: Add anything we need in here maybe?
		
	}
	
	/**
	 * Main method, start GUI thread. 
	 * Keep in mind this will be the EDT thread
	 * @param args DO NOT USE
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException{
		GUI main = new GUI();
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				main.init();
				
			}
			
		});
	}
}
