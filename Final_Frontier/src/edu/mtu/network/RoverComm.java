package edu.mtu.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.SwingWorker;

import edu.mtu.gui.Console;

public class RoverComm extends SwingWorker<Void, String>{
	
	private InetAddress serverIP;
	private int port = 23;
	private boolean atomic = false;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Console log;
	private Queue<String> outQ;  // Outgoing communications q
	private Queue<String> inQ = new ConcurrentLinkedQueue<String>();
	
	public RoverComm(Console log){
		this.log = log;
	}
	
	/**
	 * Initializes a new server connection
	 * This must be called whenever we change IPs 
	 * so that the correct 
	 */
	public void init(){
		try {
			socket = new Socket(serverIP, port);
			System.out.println("Connection Established to " + serverIP.toString());
			publish("Connection Established to " + serverIP.toString());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(String ip){
		setIP(ip);
		init();
	}
	
	public void loop() throws Exception{
		while(!atomic){
			String temp = in.readLine();
			if(temp != null){
				log.display(temp);
			}
		}
	}
	
	/**
	 * Do nothing while GUI starts up
	 */
	public void standby(){
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Set the IP address for the server
	 * @param ip String representing the IP address 
	 * EX: "192.168.0.3"
	 */
	public void setIP(String ip){
		try{
			serverIP = InetAddress.getByName(ip);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a communication to the queue
	 * @param comm
	 */
	public void sendComm(String comm){
		outQ.add(comm);
	}
	
	public void sendAtomicComm(String comm){
		atomic = true;
		out.print(comm);
		atomic = false;
//		loop();
	}

	@Override
	protected Void doInBackground() throws Exception {
		System.out.println("Initializing Connection");
		publish("Initializing Connection");
		init();
		
		
		return null;
	}
	
	@Override
	protected void process(List<String> strings){
		String comm = strings.get(strings.size()-1);
		if(!comm.equals("")){
			log.display(comm);
		}
	}
	
	/**
	 * Inform the EDT that the connection is terminated
	 */
	@Override
	protected void done(){
		log.display("Network Thread Closed");
		try {
			socket.close();
			log.display("Connection Terminated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}