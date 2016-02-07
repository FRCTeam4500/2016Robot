package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import utilities.PIDHandler;

/**
 * The firing mechanism of the robot
 */
public class Cannon extends Subsystem {
	
    /**
     * The motor controllers for the horizontal and vertical aiming mechanisms
     */
	Talon horizMotor, vertMotor;
	/**
	 * The encoders on the horizontal and vertical aiming mechanisms
	 */
	Encoder horizEncoder, vertEncoder;
	/**
	 * PID controllers for the vertical and horizontal components of the cannon
	 */
	PIDController verticalPID, horizontalPID;
	/**
	 * The PIDHandler objects for the vertical and horizontal components of the cannon
	 */
	PIDHandler vertHandler, horizHandler;
	
	public Cannon() {
		horizMotor = new Talon(RobotMap.HORIZMOTOR);
		vertMotor = new Talon(RobotMap.VERTMOTOR);
		vertHandler = new PIDHandler();
		horizHandler = new PIDHandler();
		horizontalPID = new PIDController(RobotMap.horizCannonP, RobotMap.horizCannonI, RobotMap.horizCannonD, horizEncoder, horizHandler);
		verticalPID = new PIDController(RobotMap.vertCannonP, RobotMap.vertCannonI, RobotMap.vertCannonD, vertEncoder, vertHandler);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void aimHorizontal() {
    	
    }

    /**
     * Calculates where to aim the cannon vertically given an angle off the horizontal to the goal from the camera
     * Uses a projectile equation to find the angle to set to.
     * @param degrees The angle from the camera to the goal in degrees
     * @param velocity The velocity of the ball as it leaves the cannon in m/s
     * @param gravity The gravitational constant to use in m/s/s
     * @param height The height from the camera to the top of the goal in m, i.e. height of goal minus height of camera
     * @param heightAdjustment The amount to adjust upward from the center of the TAPE to the center of the GOAL, since
     * 			the tape only covers the base of the goal. This has the added benefit of allowing a distance-scaled 
     * 			adjustment for if the robot tends to shoot too low or too high
     */
    private double calculateVerticalAngle(double degrees, double velocity, double gravity, double height, double heightAdjustment) {
    	double angle = degrees*Math.PI/180; //Convert the degree measurement into radians
    	double xDistance = height/Math.tan(angle); //Use trig to find the distance from the robot to the goal horizontally
    	/*
    	 * Now that the height to the center of the TAPE has been used for calculating the x distance, the height to use
    	 * for the adjustment is added to the original height to get the height to the center of the actual GOAL, which is
    	 * higher than the center of the tape.
    	 */
    	height += heightAdjustment; 
    	//Use a projectile equation, solved for angle, to find the angle to set the cannon to
    	return (Math.pow(velocity, 2) 
    				- Math.sqrt(Math.pow(velocity, 4) - gravity*(gravity*Math.pow(xDistance, 2) + 2*height*Math.pow(velocity, 2))))
    				/(gravity*xDistance);
    }
    
    /**
     * Uses the constants in RobotMap to set the setpoint of the PID controller to the necessary angle to fire.
     * Must be given an angle from vision processing. 
     * TODO May need to make this set an offset from a calculated "0" point.
     * Use this function, and the iterate aimVertically() to move the cannon to the calculated setpoint
     * @param degrees The angle in degrees to use from vision
     */
    public void setVerticalAngle(double degrees) {
    	verticalPID.setSetpoint(calculateVerticalAngle(
    			degrees, RobotMap.VELOCITY, RobotMap.GRAVITY, (RobotMap.HEIGHT_OF_GOAL-RobotMap.HEIGHT_TO_CAMERA), RobotMap.HEIGHT_OFFSET));
    }
    
    
    
    /**
     * Sets the vertical motor to the output of the PID controller.
     * Iterate this function until verticalAimFinished() returns true.
     */
    public void aimVertically() {
    	vertMotor.set(vertHandler.getOutput());
    }
    
    /**
     * Returns true when the vertical aim is finished
     * @return the onTarget() function of the vertical PID loop
     */
    public boolean verticalAimFinished() {
    	return verticalPID.onTarget();
    }
}

