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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionClient extends Subsystem {
	
	
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
	
	
	private byte[] data = new byte[8];
	private int bytesRead = 0;
	private double latestX = -1;
	private double latestY = -1;
	private int angleToGet = 0;
	private boolean needNext = true;
	private boolean needsConnect = false;
	
	/**
	 * Gets the angle above the horizontal that the goal is.
	 * The format of the reading is 8 bytes for the double
	 */
	public void readData() {
		needsConnect = false;
		try{
			//byte[] data = new byte[8];
			//int bytesRead = 0;
			if (needNext) {
				coprocessorSock.getOutputStream().write(angleToGet);
				needNext = false;
			}
			if(bytesRead < 8) {
				int amountRead = socketReader.read(data, bytesRead, 8 - bytesRead);
				
				if(amountRead < 0){ //if the connection closes on us
					this.socketReader = null;
					this.coprocessorSock = null;
					this.isInitialized = false;
					//new ConnectToCoprocessor().start();
					needsConnect = true;
				} else if(bytesRead < 8){
					bytesRead += amountRead;
				} else {
					bytesRead = 0;
				}
			}
			if(bytesRead == 0) {
			//reverse the array
				for(int i = 0; i < 4; i++){
					byte tmp = data[i];
					data[i] = data[7-i];
					data[7-i] = tmp;
				}
				if (angleToGet == 0) {
					latestY = ByteBuffer.wrap(data).getDouble(); 
					angleToGet = 1;
					needNext = true;
					
				} else if (angleToGet == 1) {
					latestX = ByteBuffer.wrap(data).getDouble();
					angleToGet = 0;
					needNext = true;
				}
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean needsConnect() {
		return needsConnect;
	}
	
	public double getYAngle() {
		return latestY;
	}
	
	public double getXAngle() {
		return latestX;
	}
	
	/*public double getYAngle(){
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
		
	}*/
	
	/**
	 * Gets the angle horizontal horizontally relative to the camera to the goal.
	 * The format of the reading is 8 bytes then \n
	 */
	/*public double getXAngle(){
		try {
			coprocessorSock.getOutputStream().write(1); //thisi writes a byte, not a whole integer
														//Tell the coprocessor that we want an angle
			double d = readDouble();
			SmartDashboard.putNumber("xAngle", d);
			return d;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}*/

	public boolean socketInitialized() {
		// TODO Auto-generated method stub
		return ! (coprocessorSock == null);
	}


	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ConnectToCoprocessor());
	}

}
