package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.ConnectToCoprocessor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionClient {
	
	
	Socket coprocessorSock;
	InputStream socketReader;
	boolean isInitialized;
	
	public VisionClient() {
		/*while(!this.socketInitialized()){
			initializeSocket();
			SmartDashboard.putString("InitS", "NO");
		}*/
		//SmartDashboard.putString("InitS", "yes");
	}
	
	
	/**
	 * Initializes coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public boolean initializeSocket(){
		isInitialized = false;
		try {
			coprocessorSock = new Socket(RobotMap.COPROCESSOR_ADDRESS, RobotMap.COPROCESSOR_PORT);
			socketReader = coprocessorSock.getInputStream();
			isInitialized = true;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			coprocessorSock = null;
			socketReader = null;
			SmartDashboard.putString("Error", "UnknownHost");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			coprocessorSock = null;
			socketReader = null;
			SmartDashboard.putString("Error", "IOException");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Gets the angle above the horizontal that the goal is.
	 * The format of the reading is 8 bytes for the double
	 */
	
	private double readDouble(){
		try{
			byte[] data = new byte[8];
			int bytesRead = 0;
			
			while(bytesRead < 8){
				int amountRead = socketReader.read(data, bytesRead, 8 - bytesRead);
				
				
				
				if(amountRead < 0){ //if the connection closes on us
					this.socketReader = null;
					this.coprocessorSock = null;
					this.isInitialized = false;
					new ConnectToCoprocessor().start();
					
				}else{
					bytesRead += amountRead;
				}
			}
			//reverse the array
			for(int i = 0; i < 4; i++){
				byte tmp = data[i];
				data[i] = data[7-i];
				data[7-i] = tmp;
			}
			return ByteBuffer.wrap(data).getDouble(); 
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public double getYAngle(){
		try {
			coprocessorSock.getOutputStream().write(0); //Tell the coprocessor that we want an angle
			double d = readDouble();
			SmartDashboard.putNumber("yAngle", d);
			return d;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
			initializeSocket();
		}
		
		return -1;
		
	}
	
	/**
	 * Gets the angle horizontal horizontally relative to the camera to the goal.
	 * The format of the reading is 8 bytes then \n
	 */
	public double getXAngle(){
		try {
			coprocessorSock.getOutputStream().write(1); //thisi writes a byte, not a whole integer
														//Tell the coprocessor that we want an angle
			double d = readDouble();
			SmartDashboard.putNumber("xAngle", d);

			//return (d == 0/0) ? 0 : d;
			return d;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}

	public boolean socketInitialized() {
		// TODO Auto-generated method stub
		return ! (coprocessorSock == null);
	}

}
