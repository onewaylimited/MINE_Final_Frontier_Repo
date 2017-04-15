package edu.mtu.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTextField;

/**
 * Handles JTextField that is used as input for console
 * @author Daniel Wagner
 *
 */
public class ConsoleListener implements ActionListener {
	
	private Console log;
	private JTextField field;
	private String input;
	private ConcurrentLinkedQueue<String> outQ;
	
	/**
	 * @param log Console where our input/output is displayed
	 * @param outQ ConcurrentLinkedQueue<String> Thread safe queue 
	 * 			for adding our outputs to. This output queue is shared with
	 * 			the RoverComm class which sends and receives messages from 
	 * 			the Arduino
	 * 
	 * @see RoverComm 
	 */
	public ConsoleListener(Console log, ConcurrentLinkedQueue<String> outQ){
		this.log = log;
	}

	/**
	 * ActionEvent handler for the JTextField 
	 * that is used for input to the log.
	 * Also sends outputs to the outQ for the 
	 * RoverComm class to send to the Arduino later
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		field = (JTextField) e.getSource();
		input = field.getText();
		field.setText(null);
		outQ.add(input);
		printOutQ();
		log.display(input);
		log.repaint();
	}
	
	/**
	 * Helper method to print out the head of the Queue to console
	 * for sanity and debugging sake
	 */
	public void printOutQ(){
		System.out.println("New addition to outQ: " + outQ.peek());
	}

}
