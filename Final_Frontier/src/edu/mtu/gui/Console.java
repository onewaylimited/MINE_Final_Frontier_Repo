package edu.mtu.gui;

import java.time.LocalDateTime;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Console extends JTextArea {
	
	/**
	 * Build Strings for the log 
	 * Based on format
	 * 		date: "text" \n
	 * @param string
	 */
	public void display(String string){
		String output = new String(LocalDateTime.now().toString() + ": ");
		output += string;
		output += "\n";
		this.append(output);
	}
}
