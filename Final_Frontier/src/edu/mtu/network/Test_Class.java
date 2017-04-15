package edu.mtu.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Simple test class to get started with TCP communication to Arduino Mega 2560
 * @author Dan
 *
 */
public class Test_Class {
	
	public static void main(String args[]) throws Exception{
		
		InetAddress serverIP = InetAddress.getByName("192.168.0.100");
		int port = 23;
		
		Socket socket = new Socket(serverIP, port);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput;
		while((userInput = stdIn.readLine()) != null){
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
	}
}
