package edu.mtu.gui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import edu.mtu.network.RoverComm;

public class Init {
	
	/**
	 * Constructor for Init class
	 */
	Init(){
		
		
	}
	
	/**
	 * Main method, start GUI thread
	 * @param args DO NOT USE
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException{
//		System.out.println("Start");
		GUI main = new GUI();
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				main.init();
				
			}
			
		});
		
//		System.out.println("End");
	}
}
