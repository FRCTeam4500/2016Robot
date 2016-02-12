package org.usfirst.frc.team4500.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick;
	Socket coprocessorSock;
	BufferedReader socketReader;
	
	/**
	 * Initializes the joystick and the coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public OI() {
		
		stick = new Joystick(0);
		initializeSocket();
		
		
	}
	
	/**
	 * Initializes coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public boolean initializeSocket(){
		try {
			coprocessorSock = new Socket(RobotMap.COPROCESSOR_ADDRESS, RobotMap.COPROCESSOR_PORT);
			socketReader = new BufferedReader(new InputStreamReader(coprocessorSock.getInputStream()));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			coprocessorSock = null;
			socketReader = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			coprocessorSock = null;
			socketReader = null;
			return false;
		}
		
		return true;
	}
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		return (stick.getX() < RobotMap.DEADZONE) ? 0 : stick.getX();
	}
	
	/**
	 * The value of the y axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return y value from joystick (-1 to 1)
	 */
	public double getJoyY() {
		return (stick.getY() < RobotMap.DEADZONE) ? 0 : stick.getY();
	}
	
	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return twist value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		return (stick.getTwist() < RobotMap.DEADZONE) ? 0 : stick.getTwist();
	}
	
	public double getGoalAngle(){
		/**
		 * Gets the angle above the horizontal that the goal is.
		 * The format of the reading is 8 bytes then \n
		 */
		
		try {
			coprocessorSock.getOutputStream().write(0); //Tell the coprocessor that we want an angle
			String angleInString = socketReader.readLine();
			
			byte[] angleInBytes = angleInString.substring(0, angleInString.length() - 1).getBytes(); //skip the newline character
			
			return ByteBuffer.wrap(angleInBytes).getDouble();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}

	public boolean socketInitialized() {
		// TODO Auto-generated method stub
		return !coprocessorSock.equals(null);
	}
}

