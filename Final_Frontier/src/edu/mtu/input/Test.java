package edu.mtu.input;

import javax.swing.JFrame;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Test extends JFrame{

	public static void main(String[] args){
		JInputJoystick gamepad = new JInputJoystick(Controller.Type.GAMEPAD);
		float datax = 0;
		float datay = 0;
		
		// Check if controller is connected
		System.out.println("Controller: " + gamepad.getControllerName() + " is connected: " + gamepad.isControllerConnected());
		
		while(gamepad.pollController()){
			StringBuffer buffer = new StringBuffer();
			datax = gamepad.getXAxisValue();
			datay = gamepad.getYAxisValue();
			buffer.append("X axis: " + datax + " ");
			buffer.append("Y axis: " + datay);
			System.out.println(buffer.toString());
			
			try{
				Thread.sleep(200);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
