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
     * Drive motor ports
     */
	public static final int LMOTOR = 0, RMOTOR = 1, FMOTOR = 2, BMOTOR = 3;
	
	/**
	 * 	Turret motor ports
	 */
	public static final int HORIZMOTOR = 5, VERTMOTOR = 7, FIREMOTOR = 6, CLIMBMOTOR = 8, LOADMOTOR = 4;
	
	/**
	 * Analog sensor ports
	 */
	public static final int GYRO = 0, CLIMBPOTENT = 1; 
	
	/**
	 * Digital sensor ports
	 */
	public static final int HORIZENCODER1 = 0, HORIZENCODER2 = 1, VERTENCODER1 = 2, VERTENCODER2 = 3;
	//DRIVEENCODER = 2, STRAFENCODER = 3, 
	
	/**
	 * Limit switch on horizontal component of cannon
	 */
	public static final int SWITCH = 4;
	
	/**
	 * The deadzone on the joystick
	 */
	public static final double DEADZONE = .05;
	
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
	
	
	//TODO Find actual value
	/**
	 * The height from the ground to the camera in meters - DUMMY VALUE!
	 */
	public static final double HEIGHT_TO_CAMERA = 1;
	
	/**
	 * The maximum speed we can move the cannon while calibrating it, safely.
	 * XXX Dummy
	 */
	public static final double CALLIBRATE_SPEED = 0.2;
	
	/**
	 * The gear ratio of the lazy suzan : the gear on the motor turning the lazy suzan
	 */
	public static final double CANNON_RATIO = 13.5/2.6;
	
	/**
	 * The number of encoder pulses per degree of axle rotation on a PG motor
	 */
	public static final double PULSES_PER_DEGREE = 498.148/360;
	
	/**
	 * The ratio of the small sprocket attached to the motor : the large sprocket which moves the vertical component of the cannon
	 */
	public static final double VERTICAL_RATIO = .6/1.9;

	
	/**
	 * The height of the goal from the ground in meters
	 */
	public static final double HEIGHT_OF_GOAL = 2.1336;
	
	/**
	 * The height from the center of the TAPE to the center of the GOAL in m - DUMMY VALUE!
	 */
	public static final double HEIGHT_OFFSET = 0.25;
	
	/**
	 * The velocity of the ball as it leaves the cannon in m/s
	 */
	public static final double VELOCITY = 7;
	
	//TODO May need to adjust for practicality
	/**
	 * The gravitational constant in m/s^2
	 */
	public static final double GRAVITY = 9.81;
	
	
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
	
	/**
	 * The P, I and D values for controlling the horizontal motion of the cannon
	 */
	public static final double horizCannonP = -0.01, horizCannonI = 0, horizCannonD = 0;
	
	/**
	 * The P, I and D values for controlling the vertical motion of the cannon
	 */
	public static final double vertCannonP = .01, vertCannonI = .001, vertCannonD = 0;

	/**
	 * The IP address to use to communicate with the network table for vision.
	 * XXX Dummy
	 */
	public static final String TABLE_IP = "10.1.90.2";

	/**
	 * The name of the network table.
	 * XXX Dummy
	 */
	public static final String TABLE_NAME = "vision";

	/**
	 * The name of the angle value in the table
	 * XXX Dummy
	 */
	public static final String TABLE_KEY = "angle";
	
	/**
	 * Ports for solenoids
	 */
	public static final int DRIVESWITCHER1 = 0, DRIVESWICHER2 = 1; 
	
	public static final double CLIMBERTOP = 0, CLIMBERBOTTOM = 0;
	
	/**
	 * Motor for the climber
	 */
	public static final int CLIMBERMOTOR = 0;

	/**
	 * The max voltage output of the potentiometer for scaling down to a value between -1 and 1
	 * XXX Dummy
	 */
	public static final double MAX_POT_VOLTS = 5;
	
	/**
	 * The address (IP probably) and socket on which to connect the port
	 */
	//TODO: Find values for these
	
	public static final String COPROCESSOR_ADDRESS = "";
	public static final int COPROCESSOR_PORT = 1234;

	/**
	 * The angle after which the plexiglass will pop out
	 */
	public static final double VERTICAL_LIMIT = 80;

	/**
	 * The angle after which the wires will pop out
	 */
	public static final double HORIZONTAL_LIMIT = 40;

	public static final int pneumaticsModule = 0;

	public static final double HORIZ_TOLERANCE = 2;

	public static final double VERT_TOLERANCE = 2;

	public static final double ANGULAR_TOLERANCE = 2;

	public static final double LOAD_SPEED = .5;

	/**
	 * 180 minus the actual aimed angle where the encoder reads 0 degrees
	 */
	public static final double EFFECTIVE_HORIZONTAL = 150;

	//Dummys
	public static int tankDrive = 0;

	public static int omniDrive = 1;
	
	
	
}
