package edu.mtu.input;

import java.awt.Toolkit;

import net.java.games.input.*;

public class ControllerTest {

	protected Component[] components;
	protected Controller gamepad;

	public static void main(String[] args){
		ControllerTest test = new ControllerTest();
		test.controllerTest();
	}

	void controllerTest(){
		//System.out.println("Hello World");

		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
		// Run through the list of available input devices and get the gamepad
		for(int i = 0; i < ca.length; i ++){
			if(ca[i].getType().equals(Controller.Type.GAMEPAD)){  // Snag the first instance of a gamepad
				gamepad = ca[i];
			}
		}
		// Print the name of the controller and its type
		if(gamepad != null){
			System.out.println(gamepad.getName() + ": " + gamepad.getType());
			components = gamepad.getComponents();
			System.out.println("COMPONENTS:");
			for(int i = 0; i < components.length; i ++){
				StringBuffer buffer = new StringBuffer();
				buffer.append("Component #" + i + ": ");  // Get the Component #
				buffer.append("\t" + components[i].getName());  // Get the component name
				// Check if the component is analog or relative
				// Relative relies on its past position for values
				// Analog is a current position
				// Analog absolute is a toggle (think your a,b,y,x button)
				if(components[i].isRelative()){
					buffer.append(" [Relative]");
				}
				else if(components[i].isAnalog()){
					buffer.append(" [Analog]");
				}
				else{
					buffer.append(" [Analog Absolute]");
				}
				System.out.println(buffer.toString());
			}
		}
		else{
			System.out.println("No gamepad connected");
		}

		while(true){
			gamepad.poll();
			Component[] comps = gamepad.getComponents();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < comps.length; i ++){
				if(i > 0){
					buffer.append(", ");
				}
				buffer.append(components[i].getName());
				buffer.append(": ");
				if(components[i].isAnalog()) {
					buffer.append(components[i].getPollData());
				} else {
					if(components[i].getPollData()==1.0f) {
						buffer.append("On");
					} else {
						buffer.append("Off");
					}
				}
			}
			System.out.println(buffer.toString());

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
