package org.usfirst.frc.team4500.robot;

import utilities.Vector;

/**
 * Use the RobotMap for storing the port numbers of all of our motors and
 * sensors as well as any other constants which may be subject to change.
 * 
 * Never hard code port numbers! Reference the variables here so they may be
 * changed on the fly while we build the robot.
 */
public class RobotMap {
	/**
	 * Drive motor ports - dummy values!
	 */
	public static final int LMOTOR = 0, RMOTOR = 1, FMOTOR = 2, BMOTOR = 3;

	/**
	 * Turret motor ports - dummy values!
	 */
	public static final int ROTATEMOTOR = 4, AIMMOTOR = 5, FIREMOTOR = 6;

	/**
	 * Climb motor port - dummy values!
	 */
	public static final int CLIMBMOTOR = 7;

	/**
	 * Analog drive encoders - dummy values!
	 */
	public static final int DRIVELENCODER = 0, DRIVERENCODER = 1,
			DRIVEFENCODER = 2, DRIVEBENCODER = 3;

	/**
	 * Analog turret encoders - dummy values!
	 */
	public static final int ROTATEENCODER = 4, AIMENCODER = 5;

	/**
	 * Analog climb potentiometer - dummy values!
	 */
	public static final int CLIMBPOTENTIOMETER = 6;

	/**
	 * Analog sensor ports - dummy values!
	 */
	public static final int GYRO = 7;

	/**
	 * Digital turret limit switches - dummy values!
	 */
	public static final int ROTATESWITCHL = 0, ROTATESWITCHR = 1;

	/**
	 * The position of each wheel with relation to the center of the robot (in
	 * inches but that doesn't matter)
	 */
	public static final Vector lOmniPosition = new Vector(-11.75, -10.592, 0),
			rOmniPosition = new Vector(-11.75, 10.592, 0),
			fOmniPosition = new Vector(0, 12.915, 0),
			bOmniPosition = new Vector(0, -12.915, 0);

	/**
	 * The direction of each wheel in relation the robot's coordinate system -
	 * may need to negativise some values
	 */
	public static final Vector lOmniDirection = new Vector(0, 1, 0),
			rOmniDirection = new Vector(0, 1, 0), fOmniDirection = new Vector(
					1, 0, 0), bOmniDirection = new Vector(-1, 0, 0);

	/**
	 * These values relate how different motors need to move according to their
	 * gear ratio. Set at dummy values now
	 */
	public static final double lOmniRatio = 1, rOmniRatio = 1, fOmniRatio = 1,
			bOmniRatio = 1;

}
