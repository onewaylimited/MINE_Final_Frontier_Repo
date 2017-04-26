package edu.mtu.gui;

import java.time.LocalDateTime;

import javax.swing.JTextArea;

/**
 * Extended JTextArea for use as main console of the program
 * This is the "log" we will display all of the communications
 * incoming and outgoing, along with user input commands, and any
 * internal warnings sent by the program itself.
 * @author Daniel Wagner
 *
 */
@SuppressWarnings("serial")
public class Console extends JTextArea {
	
	/**
	 * Build Strings for the log 
	 * Based on format
	 * 		date: "text" \n
	 * @param string The string to be displayed on the log
	 */
	public void display(String string){
		String output = new String(LocalDateTime.now().toString() + ": ");
		output += string;
		output += "\n";
		this.append(output);
		this.setCaretPosition(this.getDocument().getLength());
	}
}
