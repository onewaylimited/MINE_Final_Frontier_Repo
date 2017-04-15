package edu.mtu.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsoleListener implements ActionListener {
	
	private Console log;
	private JTextField field;
	private String input;
	private ConcurrentLinkedQueue<String> outQ;
	
	public ConsoleListener(Console log, ConcurrentLinkedQueue<String> outQ){
		this.log = log;
	}

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
	
	public void printOutQ(){
		System.out.println("New addition to outQ: " + outQ.peek());
	}

}
