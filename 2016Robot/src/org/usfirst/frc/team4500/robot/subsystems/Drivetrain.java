package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import utilities.Vector;
import utilities.Wheel;

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
	
	private Wheel[] omniWheels;
	
	
	/**
	 * The RobotDrive object to be used for tank drive
	 */
	private RobotDrive tank;

    public Drivetrain() {
    	tank = new RobotDrive(lTank, rTank);
    	
    	lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	fOmni = new Talon(RobotMap.FMOTOR);
    	bOmni = new Talon(RobotMap.BMOTOR);
    	
    	lTank = lOmni;
    	rTank = rOmni;
    	
    	omniWheels = new Wheel[4];
    	
    	omniWheels[0] = new Wheel(RobotMap.lOmniPosition, RobotMap.lOmniDirection, RobotMap.lOmniRatio, lOmni);
    	omniWheels[1] = new Wheel(RobotMap.rOmniPosition, RobotMap.rOmniDirection, RobotMap.rOmniRatio, rOmni);
    	omniWheels[2] = new Wheel(RobotMap.fOmniPosition, RobotMap.fOmniDirection, RobotMap.fOmniRatio, fOmni);
    	omniWheels[3] = new Wheel(RobotMap.bOmniPosition, RobotMap.bOmniDirection, RobotMap.bOmniRatio, bOmni);
    	
    	
    	
    	
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
    	Vector linear = new Vector(joyX, joyY, 0); 					//The non rotational component of the motion
    	Vector rotation = new Vector(0, 0, joyTwist - gyro); 		//The rotational component of the motion
    	
    	double speeds[] = new double[4]; 							//Array to store each wheel
    	double maxSpeed = 0.0; 										//Stores the value of the maximum speed of any of the wheels during the current iteration
    	
    	for(int i = 0; i<omniWheels.length; i++){
    		speeds[i] = omniWheels[i].getSpeed(linear, rotation); 	//Gets the speed of each wheel relative to the robot's current position.
    																//i.e. the actual speed of the wheel 
    		if(Math.abs(speeds[i]) > maxSpeed){
    			maxSpeed = Math.abs(speeds[i]); 					//Sets the value of the max speed
    		}
    	}
    	
    	if(maxSpeed > 1){											//In case the max speed is too high,
    																//reduces all speeds using the max speed as a scale factor
    		for(int i = 0; i<omniWheels.length; i++){
    			speeds[i] /= maxSpeed;
    		}
    	}
    																//Sets the wheels to the proper speed
    	for(int i = 0; i<omniWheels.length; i++){
    		omniWheels[i].setSpeed(speeds[i]);
    	}
    	
    	
    }
}

