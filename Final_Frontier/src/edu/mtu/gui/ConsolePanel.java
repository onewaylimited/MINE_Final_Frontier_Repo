package edu.mtu.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

/**
 * Creates and handles the Console panel
 * This is separate from the GUIPanel based panels
 * due to the fact that GridBagLayout is used instead of
 * GridLayout for the panel. It also handles the button events.
 * <p> It is very similar to the way the other GUI panels are 
 * setup, and to better understand this Class, look at the 
 * GUIPanel and GUIPanel extended classes.
 * <p>**PLEASE NOTE** This class passes along the outQ for outgoing communications
 * @author Daniel Wagner
 * 
 * @see GuiPanel
 * @see ImagePanel
 * @see WarnPanel
 * @see InfoPanel
 *
 */
public class ConsolePanel implements ActionListener{
	
	protected ArrayList<Component> components = new ArrayList<Component>();
	protected ArrayList<JPanel> panels = new ArrayList<JPanel>();
	protected JPanel logPanel = new JPanel();
	protected GridBagLayout layout = new GridBagLayout();
	private Console log;
	private JLabel inputLabel;
	private JTextField inputField;
	private JButton clearButton, saveButton;
	private Border testBorder;
	private ConcurrentLinkedQueue<String> outQ;
	
	/**
	 * Constructor that creates a few required variables along with
	 * running the init() method to setup the ConsolePanel
	 */
	public ConsolePanel(ConcurrentLinkedQueue<String> outQ){
		this.outQ = outQ;
		testBorder = new BevelBorder(BevelBorder.LOWERED);
		init();
	}
	
	/**
	 * Sets up and paints all of the required components for the console panel
	 * to work. 
	 */
	public void init(){
		// Initialize layout
				logPanel.setLayout(new GridBagLayout());
				logPanel.setBackground(Color.BLUE);

				// Initialize GridBagConstraints
				GridBagConstraints lp = new GridBagConstraints();

				// Initialize JTextArea
				log = new Console();
				log.setEditable(false);
				log.setLineWrap(true);
				log.append("-- Begin Communications Log --\n");
				log.append(LocalDateTime.now() + "\n");
				log.setBorder(testBorder);
				DefaultCaret caret = (DefaultCaret)log.getCaret();
				caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				
				// Initialize JTextField
				inputField = new JTextField(20);
				inputField.addActionListener(new ConsoleListener(log, outQ));
				
				// Initialize JLabel
				inputLabel = new JLabel("Enter Command: ");
				inputLabel.setForeground(Color.WHITE);
				
				// Initialize JButton
				clearButton = new JButton("Reset Log");
				clearButton.addActionListener(this);
				clearButton.setFocusable(false);
				saveButton = new JButton("Save Log");
				saveButton.addActionListener(this);
				saveButton.setFocusable(false);
				
				// Create Scroll Pane
				JScrollPane scrollPane = new JScrollPane(log);
				scrollPane.setFocusable(false);

				// Add Components to panel
				lp.anchor = GridBagConstraints.CENTER;
				lp.fill = GridBagConstraints.BOTH;
				lp.insets = new Insets(10,10,10,10);
				lp.gridwidth = 3;
				lp.weightx = 0.5;
				lp.weighty = 0.9;
				lp.gridx = 0;
				lp.gridy = 0;
				logPanel.add(scrollPane, lp);
				lp.gridwidth = 1;
				lp.weightx = 0.9;
				lp.weighty = 0.1;
				lp.gridx = 0;
				lp.gridy = 1;
				logPanel.add(inputLabel, lp);
				lp.fill = GridBagConstraints.NONE;
				lp.anchor = GridBagConstraints.EAST;
				lp.weightx = 0.1;
				lp.gridx = 1;
				logPanel.add(saveButton, lp);
				lp.gridx = 2;
				logPanel.add(clearButton, lp);
				lp.fill = GridBagConstraints.BOTH;
				lp.anchor = GridBagConstraints.WEST;
				lp.gridwidth = 3;
				lp.weightx = 0.9;
				lp.gridx = 0;
				lp.gridy = 2;
				logPanel.add(inputField, lp);
				
				// Revalidate and repaint
				scrollPane.revalidate();
				scrollPane.repaint();
				logPanel.revalidate();
				logPanel.repaint();
	}
	
	/**
	 * Get list of all components used by this panel.
	 * @see GuiPanel
	 * @return ArrayList of components 
	 */
	public ArrayList<Component> getComp(){
		return components;
	}
	
	/**
	 * Return the Jpanel that is created by this class
	 * @return JPanel 
	 */
	public JPanel getPanel(){
		return logPanel;
	}
	
	/**
	 * Helper method to get an instance of the Console contained 
	 * in this JPanel
	 * @see Console
	 * @return Console The console referred to as "log" throughout the program
	 */
	public Console getLog(){
		return log;
	}
	
	/**
	 * Helper method to automatically refresh and revalidate
	 * the logPanel
	 * <p> <i>One line is less than two, im lazy ok?</i>
	 */
	public void refresh(){
		logPanel.revalidate();
		logPanel.repaint();
	}

	/**
	 * ActionEvent handler for the reset and save log 
	 * buttons contained in this panel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton button = (JButton) e.getSource();
			if(button.getText().equals("Reset Log")){
				log.setText(null);
				log.append("-- Begin Communications Log --\n");
				log.display("");
			}
			else if(button.getText().equals("Save Log")){
				//TODO: Make this actually save the log
			}
			
		}
		
	}
}
