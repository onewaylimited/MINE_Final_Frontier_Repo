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

/**
 * This is the SwingWorker class that will be our main connection to the rover.
 * It has a copy of the log in the GUI, along with the outQ which it uses to
 * send communications placed enqueue.
 * <p> Keep in mind that this will run in a never ending loop unless it is interrupted 
 * by an uncaught exception, is cancelled by the EDT, or some other mystical force
 * manages to stop this thread. 
 * <p>This class extends SwingWorker and as such runs in a different thread from
 * the EDT (Event Dispatch Thread). This can only send data via the publish method,
 * which processes information based on the process method.
 * <p> If you forgot how it works, look at this tutorial online: 
 * <n>https://docs.oracle.com/javase/tutorial/uiswing/concurrency/worker.html
 * @author Daniel Wagner
 *
 */
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
	private boolean connected = false;

	public RoverComm(Console log, ConcurrentLinkedQueue<String> outQ){
		this.log = log;
		this.outQ = outQ;
	}

	/**
	 * Setup and run the network connection
	 * This method is used by SwingWorker to startup the new thread
	 *  
	 */
	@Override
	protected Void doInBackground() throws Exception {
		System.out.println("Initializing Connection");
		publish("Initializing Connection");
		init();


		return null;
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
			connected = true;
			publish("Connection Established to " + serverIP.toString());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			//			e.printStackTrace();
			publish("Failed to connect to: " + serverIP.toString());
		}

		try{
			if(connected){  // Check that we are connected to the server before initializing loop
				loop();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This is the overridden version of init() so we can change ips on
	 * the fly if need be. 
	 * @param ip String denoting the IP we wish to set our socket to.
	 */
	public void init(String ip){
		setIP(ip);
		init();
	}

	/**
	 * This method is the communications loop that will run until we are interrupted.
	 * The basic loop follows this sequence:
	 * <p>1. We check to see if there is a communication to send in the outQ. If so send it to the
	 * output stream.
	 * <p>2. We check to see if there is any communications in the incoming buffer waiting
	 * to be read. We then read in this string and place it in the inQ.
	 * <p>3. Wait a small amount of time so the loop doesn't overtake itself.
	 */
	public void loop(){
		try{
		while(!atomic){
			if(outQ.peek() != null && out != null){
				String comm = outQ.peek();
				out.println(outQ.poll());
				publish("OUTGOING: " + comm);
				Thread.sleep(50);
			}
			else{
				//publish("OutQ empty or null!");
			}
			if(in.ready()){
				String temp = in.readLine();
				inQ.add(temp);
				System.out.println("New Addition to InQ: " + inQ.toString());
				publish("INCOMING: " + temp);
			}
			Thread.sleep(10);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Do nothing while GUI starts up
	 * @deprecated
	 * This doesn't do anything useful.
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
	 * @param comm String: Communication to add to the outgoing queue
	 * @deprecated
	 */
	public void sendComm(String comm){
		outQ.add(comm);
	}

	/**
	 * Called from the EDT to send an immediate communication to the 
	 * rover. This is only to be called in the cases of an emergency stop, 
	 * loss of connection, or some other error.
	 * @param comm String: Emergency command to send to the rover
	 */
	public void sendAtomicComm(String comm){
		atomic = true;
		if(out!= null){
			out.print(comm);
			publish("ATOMIC: " + comm);
		}
		atomic = false;
		//		loop();
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
		try {
			if(socket != null){
				socket.close();
				log.display("Connection Terminated");
				System.out.println("Connection Terminated");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.display("Network Thread Closed");
		System.out.println("Network Thread Closed");
	}

}
