package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.usfirst.frc.team4500.robot.RobotMap;

public class VisionServer {
	
	
	Socket coprocessorSock;
	BufferedReader socketReader;
	
	public VisionServer() {
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
	 * Gets the angle above the horizontal that the goal is.
	 * The format of the reading is 8 bytes then \n
	 */
	public double getYAngle(){
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
	
	/**
	 * Gets the angle horizontal horizontally relative to the camera to the goal.
	 * The format of the reading is 8 bytes then \n
	 */
	public double getXAngle(){
		try {
			coprocessorSock.getOutputStream().write(1); //Tell the coprocessor that we want an angle
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
