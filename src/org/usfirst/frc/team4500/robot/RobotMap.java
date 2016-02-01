package org.usfirst.frc.team4500.robot;

import utilities.Vector;

/**
 * Use the RobotMap for storing the port numbers of all of our motors
 *  and sensors as well as any other constants which may be subject to change.
 * 
 * Never hard code port numbers! Reference the variables here so they may be
 *  changed on the fly while we build the robot.
 */
public class RobotMap {
    /**
     * Drive motor ports - dummy values!
     */
	public static final int LMOTOR = 0, RMOTOR = 1, FMOTOR = 2, BMOTOR = 3;
	
	/**
	 * 	Turret motor ports - dummy values!
	 */
	public static final int HORIZMOTOR = 4, VERTMOTOR = 5;
	
	/**
	 * Analog sensor ports - dummy values!
	 */
	public static final int GYRO = 0; 
	
	/**
	 * Digital sensor ports - dummy values!
	 */
	public static final int HORIZENCODER = 0, VERTENCODER = 1;
	
	/**
	 * The position of each wheel with relation to the center of the robot (in inches but that doesn't matter)
	 */
	public static final Vector lOmniPosition = new Vector(-11.75, -10.592, 0),
							   rOmniPosition = new Vector(-11.75, 10.592, 0), 
							   fOmniPosition = new Vector(0, 12.915, 0), 
							   bOmniPosition = new Vector(0, -12.915, 0);
	
	/**
	 * The direction of each wheel in relation the robot's coordinate system - may need to negativise some values
	 */
	public static final Vector  lOmniDirection = new Vector(0, 1, 0),
								rOmniDirection = new Vector(0, 1, 0),
								fOmniDirection = new Vector(1, 0, 0),
								bOmniDirection = new Vector(-1, 0, 0);
	
	/**
	 * These values relate how different motors need to move according to their gear ratio. Set at dummy values now
	 */
	public static final double  lOmniRatio = 1, 
								rOmniRatio = 1, 
								fOmniRatio = 1, 
								bOmniRatio = 1;
	
	
	//TODO We need to tune all of these PID values once we have the actual robot. It'll be a process.
	/**
	 * The P, I, and D values for controlling with the encoder on the tank drive
	 */
	public static final double forwardTankP = 0, forwardTankI = 0, forwardTankD = 0;
	
	/**
	 * The P, I, and D values for controlling with the encoder on the omni drive
	 */
	public static final double strafeOmniP = 0, strafeOmniI = 0, strafeOmniD = 0;
	
	/**
	 * The P, I, and D values for controlling with the gyro on the tank drive
	 */
	public static final double tankGyroP = 0, tankGyroI = 0, tankGyroD = 0;
	
	/**
	 * The P, I and D values for controlling with the gyro on the omni drive
	 */
	public static final double omniGyroP = 0, omniGyroI = 0, omniGyroD = 0;
	
	
	
}
