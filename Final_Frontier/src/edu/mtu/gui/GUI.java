package edu.mtu.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import edu.mtu.network.RoverComm;

/**
 * DESC: 	Initialize the GUI at startup
 * NOTES: 	You should only need to call this at startup, since we are creating and populating
 * 			the GUI for the operator. This class does not handle GUI events, only the creation 
 * 			of all the objects required for the GUI.
 * 
 * @author Dan Wagner
 * LAST UPDATED: 11/21/2106
 *
 */
public class GUI{

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
	 * Constructor for MainWindow 
	 * 
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
	 * Initialize the GUI
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

		// Set screen size and position
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow.setBounds(screenSize.width / 2, 0, screenSize.width / 2, screenSize.height); 

		mainWindow.setVisible(true);

		startComm("192.168.0.100");

		// Render a png image based on content pane
		//printFrame(contentPane);
	}

	/**
	 * Export given JPanel to a .png
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
	 * @param ip
	 */
	private void startComm(String ip){
		roverComm = new RoverComm(logPanel.getLog());
		roverComm.setIP(ip);
		roverComm.execute();
	}

}
