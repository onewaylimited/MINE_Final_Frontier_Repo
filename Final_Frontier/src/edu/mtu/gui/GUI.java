package edu.mtu.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import edu.mtu.network.RoverComm;

/**
 * Initialize the GUI at startup
 * <p>You should only need to call this at startup, since we are creating and populating
 * the GUI for the operator. This GUI class is the backbone of our program, spawning 
 * all of the other processes and classes. 
 * <p>Keep in mind that all GUI elements are 
 * running in the EDT, and will need to be modified by a SwingWorker thread 
 * to prevent the GUI from hanging
 * 
 * @author Dan Wagner
 *
 */
public class GUI implements ActionListener{

	// Declare mainWindow, this is where GUI will reside and be displayed
	private JFrame mainWindow;
	// Declare GridBagConstraints
	private GridBagConstraints m;
	// Create contentPane gridLayout
	private GridLayout contentLayout;
	// Declare JPanels
	private JPanel contentPane;
	private JPanel mainPanel;
	//private JPanel infoPanel, warnPanel, controlPanel;
	// Declare borders
	private Border testBorder;

	// Declare RoverComm for threading
	private RoverComm roverComm;
	private ConcurrentLinkedQueue<String> outQ = new ConcurrentLinkedQueue<String>();

	// Initialize panels
	private InfoPanel infoPanel = new InfoPanel();
	private WarnPanel warnPanel = new WarnPanel();
	private ControlPanel controlPanel = new ControlPanel();
	private ConsolePanel logPanel = new ConsolePanel(outQ);



	/**
	 * Constructor for the GUI, this initializes the main window along with
	 * the content pane. It also sets the parameters for the JFrame such as its
	 * title, DefaultCloseOperation and its content pane.
	 * <p>The reason we do not call init() from this constructor
	 * is that we want to have control over when this runs when creating the 
	 * GUI in the Init class.
	 * 
	 * @see Init
	 * @see InfoPanel
	 * @see WarnPanel
	 * @see ControlPanel
	 * @see ConsolePanel
	 */
	GUI() {

		// Initialize GUI objects
		this.mainWindow = new JFrame();  
		this.m = new GridBagConstraints();  // Initialize constraints
		this.contentPane = new JPanel();  // Initialize JPanels
		this.mainPanel = new JPanel();
		//		this.logPanel = new JPanel();

		// Initialize Borders
		testBorder = new BevelBorder(BevelBorder.LOWERED);

		// Initialize GridLayout
		this.contentLayout = new GridLayout(1,1);

		//Initialize mainWindow

		mainWindow.setContentPane(contentPane);
		mainWindow.setTitle("Final Frontier Control Program");   // Set title of screen
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Sets behavior of "x" button
		mainWindow.setLocationRelativeTo(null);   // Create window in center of screen

	}

	/**
	 * Construct the basic layout of the GUI, placing all of the JPanels in their correct places. 
	 * <p>This also adds the button listeners to the ControlPanel buttons since it is more convenient
	 * to handle those events in this class.
	 */
	public void init() {

		// Initialize contentPane
		contentPane.setLayout(contentLayout);

		// Initialize right and left panes
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(testBorder);

		contentPane.add(mainPanel);

		/**
		 * Initialize r constraints, and add components
		 * PANE: rightPanel
		 * CHILD PANES: infoPanel, warnPanel, controlPanel
		 */
		m.anchor = GridBagConstraints.CENTER;
		m.fill = GridBagConstraints.BOTH;
		m.weightx = 0.5;
		m.weighty = 0.72;
		m.gridx = 0;
		m.gridy = 0;
		mainPanel.add(infoPanel.getPanel(), m);
		m.weighty = 0.5;
		m.gridy = 1;
		mainPanel.add(warnPanel.getPanel(), m);
		m.gridy = 2;
		mainPanel.add(controlPanel.getPanel(), m);
		m.gridy = 3; 
		mainPanel.add(logPanel.getPanel(), m);


		// Revalidate and Repaint
		infoPanel.refresh();
		warnPanel.refresh();
		controlPanel.refresh();
		contentPane.revalidate();
		contentPane.repaint();

		// Add this as action listener to Control Panel buttons
		addListeners(controlPanel.getComp());

		// Set screen size and position
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow.setBounds(screenSize.width / 2, 0, screenSize.width / 2, screenSize.height); 
		mainWindow.setVisible(true);

		// Start Networking Thread
		startComm("192.168.0.100");

		// Render a png image based on content pane
		//printFrame(contentPane);
	}

	/**
	 * Export given JPanel to a .png
	 * These are saved by default in the 
	 * root folder, this will be changed later if 
	 * need be.
	 * @param panel JPanel to export
	 */
	private void printFrame(JPanel panel){
		int width = panel.getWidth();
		int height = panel.getHeight();
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.print(g);

		File file = new File("HalfFrame.png");
		try {
			ImageIO.write(bi, "png", file);
			System.out.println("Frame Image Saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create a new thread for rover communications
	 * @param ip String representing IP to point our roverComm too 
	 * @see RoverComm
	 */
	private void startComm(String ip){
		roverComm = new RoverComm(logPanel.getLog(), outQ);
		roverComm.setIP(ip);
		roverComm.execute();
	}

	/**
	 * Add ActionListeners to JButtons in component list
	 * @param components ArrayList list of components
	 * 			to add main GUI ActionListeners to
	 */
	private void addListeners(ArrayList<Component> components){
		// Add action listeners to JButtons only
		for(int i = 0; i < components.size(); i ++){
			Component comp = components.get(i);
			if(comp instanceof JButton){
				JButton button = (JButton) comp;
				button.addActionListener(this);
			}
		}
	}

	/**
	 * ActionEvent handler for the ContolPanel JButtons.
	 * @see ControlPanel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton button = (JButton) e.getSource();
			if(button.getText().equals("Shutdown")){
				roverComm.sendAtomicComm("Shutdown");
				logPanel.getLog().display("SHUTDOWN!");
			}
			else if(button.getText().equals("Connect/Reconnect")){
				System.out.println("Connect/Reconnect Pressed");
				if(roverComm != null){
					roverComm.cancel(true);  // Close the previous roverComm thread
				}
				// Start a new roverComm thread. This is done if we need
				// to reconnect with the rover or reset the connection
				if(roverComm.isCancelled() || roverComm.isDone()){
					startComm("192.168.0.100");
					logPanel.getLog().display("Reconnecting...");
				}
			}
		}

	}

}
