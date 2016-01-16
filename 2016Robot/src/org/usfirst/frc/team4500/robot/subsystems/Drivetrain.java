package org.usfirst.frc.team4500.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	/**
	 * Motor controllers for the left, right, front, and back omni wheels.
	 *  Use the setter functions to set speeds in case we need multiple controllers for the same side.
	 */
	private Talon lOmni, rOmni, fOmni, bOmni;
	
	/**
	 * Motor controllers for the left and right tank treads
	 */
	private Talon lTank, rTank;
	
	/**
	 * The RobotDrive object to be used for tank drive
	 */
	private RobotDrive tank;

    public Drivetrain() {
    	tank = new RobotDrive(lTank, rTank);
    	
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	/**
	 * Sets the speed for the left side omni wheels
	 * @param speed -1 to 1
	 */
	private void setLOmni(double speed) {
		lOmni.set(speed);
	}
	
	/**
	 * Sets the speed for the right side omni wheels
	 * @param speed -1 to 1
	 */
	private void setROmni(double speed) {
		rOmni.set(speed);
	}
	
	/**
	 * Sets the speed for the front side omni wheels
	 * @param speed -1 to 1
	 */
	private void setFOmni(double speed) {
		fOmni.set(speed);
	}
	
	/**
	 * Sets the speed for the back side omni wheels
	 * @param speed -1 to 1
	 */
	private void setBOmni(double speed) {
		bOmni.set(speed);
	}
	
    /**
     * Tank drive given a single joystick and optional gyro
     * @param joyX x-axis of joystick
     * @param joyY y-axis of joystick
     * @param joyTwist twist of joystick
     * @param gyro optional gyro reading - set to 0 for no gyro
     */
	public void tankDrive(double joyX, double joyY, double joyTwist, double gyro) {
    	//TODO Make tank drive function
    }
    
    /**
     * Six wheel omni drive given joystick input and a gyroscope reading
     * @param joyX x-axis of joystick input
     * @param joyY y-axis of joystick input
     * @param joyTwist twist of joystick input
     * @param gyro gyroscope reading (relative)
     */
    public void omniDrive(double joyX, double joyY, double joyTwist, double gyro) {
    	//TODO Make omni drive function
    }
}

