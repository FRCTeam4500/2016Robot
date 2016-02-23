package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import utilities.PIDHandler;
import utilities.Vector;
import utilities.Wheel;

/**
 * Subsystem containing all objects and functions relevant to the drivetrain of the robot
 */
public class Drivetrain extends Subsystem {
	/**
	 * Motor controllers for the left, right, front, and back omni wheels.
	 *  Use the setter functions to set speeds in case we need multiple controllers for the same side.
	 */
	private Victor lOmni, rOmni;
	private Jaguar fOmni, bOmni;
	
	/**
	 * The robot's gyroscope
	 */
	private ADXRS450_Gyro gyro;
	
	/**
	 * The right and front encoder (on the front omni wheel) and the right encoder (on the right tank tread)
	 */
	private Encoder rEncoder, fEncoder;
	//TODO Initialize these if we get them
	
	Victor lTank;
	/**
	 * Motor controllers for the left and right tank treads
	 */
	private Victor rTank;
	
	/**
	 * Array containing each omni wheel
	 */
	private Wheel[] omniWheels;
	
	/**
	 * PID controllers to contain different P, I, and D values for the encoder and gyro systems
	 *  of each drivetrain.
	 */
	private PIDController linearTankPID, linearOmniPID, angularTankPID, angularOmniPID;
	
	/**
	 * PIDHandler instances for the tank drive so that we can get the raw PID output
	 */
	private PIDHandler linearTankHandler, angularTankHandler;
	
	/**
	 * PIDHandler instances for omni drive strafing so that we can get the raw PID outpout
	 */
	private PIDHandler linearOmniHandler, angularOmniHandler;
	
	/**
	 * RobotDrive object for the tank treads
	 */
	private RobotDrive tankDrivetrain;
	
	/**
	 * RobotDrive object containing the front and back omni wheels for the purpose of straight strafing
	 */
	private RobotDrive strafeDrive;
	
	/**
	 * Solenoids for switching wheels
	 */
	private DoubleSolenoid wheelSwitch;

    public Drivetrain() {
    	lOmni = new Victor(RobotMap.LMOTOR);
    	rOmni = new Victor(RobotMap.RMOTOR);
    	fOmni = new Jaguar(RobotMap.FMOTOR);
    	bOmni = new Jaguar(RobotMap.BMOTOR);
    	lTank = lOmni;
    	rTank = rOmni;
    	gyro = new ADXRS450_Gyro();
    	tankDrivetrain = new RobotDrive(lTank, rTank);
    	strafeDrive = new RobotDrive(fOmni, bOmni);
    	omniWheels = new Wheel[4];
    	omniWheels[0] = new Wheel(RobotMap.lOmniPosition, RobotMap.lOmniDirection, RobotMap.lOmniRatio, lOmni);
    	omniWheels[1] = new Wheel(RobotMap.rOmniPosition, RobotMap.rOmniDirection, RobotMap.rOmniRatio, rOmni);
    	omniWheels[2] = new Wheel(RobotMap.fOmniPosition, RobotMap.fOmniDirection, RobotMap.fOmniRatio, fOmni);
    	omniWheels[3] = new Wheel(RobotMap.bOmniPosition, RobotMap.bOmniDirection, RobotMap.bOmniRatio, bOmni);
    	linearTankHandler = new PIDHandler();
    	angularTankHandler = new PIDHandler();
    	linearOmniHandler = new PIDHandler();
    	angularOmniHandler = new PIDHandler();
    	//linearTankPID = new PIDController(RobotMap.forwardTankP, RobotMap.forwardTankI, RobotMap.forwardTankD, rEncoder, linearTankHandler);
    	angularTankPID = new PIDController(RobotMap.tankGyroP, RobotMap.tankGyroI, RobotMap.tankGyroD, gyro, angularTankHandler);
    	//linearOmniPID = new PIDController(RobotMap.strafeOmniP, RobotMap.strafeOmniI, RobotMap.strafeOmniD, fEncoder, linearOmniHandler);
    	angularOmniPID = new PIDController(RobotMap.omniGyroP, RobotMap.omniGyroI, RobotMap.omniGyroD, gyro, angularOmniHandler);
    	//TODO Enable PID controllers - not sure if here or in the initPID... functions
    	wheelSwitch = new DoubleSolenoid(RobotMap.DRIVESWITCHER1, RobotMap.DRIVESWICHER2);
    }
    
    private void initPID() {
    	angularTankPID.setAbsoluteTolerance(RobotMap.ANGULAR_TOLERANCE);
    	angularOmniPID.setAbsoluteTolerance(RobotMap.ANGULAR_TOLERANCE);
    	angularOmniPID.setContinuous(false);
    	angularTankPID.setContinuous(false);
    }
	
	/**
	 * Makes the robot tank drive when no other command is assigned
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
	
	/**
	 * The reading of the gyro
	 * @return The angle given by the gyro in degrees
	 */
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
    /**
     * Tank drive given a single joystick, arcade style
     * @param joyY y-axis of joystick
     * @param joyTwist twist of joystick
     */
	public void tankDrive(double joyY, double joyTwist) {
    	tankDrivetrain.arcadeDrive(joyY, joyTwist, true);
    }
	
	
	/**
	 * Sets the proper setpoints for the PID loops before calling strafeStraight()
	 * @param distanceSetpoint the setpoint for the encoder
	 * @param angleToMaintain the setpoint for the gyro
	 */
	public void initStrafeStraight(double distanceSetpoint, double angleToMaintain) {
		linearOmniPID.setSetpoint(distanceSetpoint);
		angularOmniPID.setSetpoint(angleToMaintain);
	}
	
	/**
	 * Makes the robot strafe straight using PID control
	 */
	public void strafeStraight() {
		strafeDrive.arcadeDrive(linearOmniHandler.getOutput(), angularOmniHandler.getOutput());
	}
	
	/**
	 * Returns true when the robot has reached its destination after omni driving straight
	 */
	public boolean strafeStraightFinished() {
		return linearOmniPID.onTarget();
	}
	
	/**
	 * Sets the proper setpoints for the PID loops before calling tankDriveStraight()
	 * @param distanceSetpoint setpoint for the encoder
	 * @param angleToMaintain setpoint for the gyro
	 */
	public void initTankDriveStraight(double distanceSetpoint, double angleToMaintain) {
		linearTankPID.setSetpoint(distanceSetpoint); //Give the distance setpoint to the linear tank controller
		angularTankPID.setSetpoint(angleToMaintain); //Set the angular tank controller to maintain the angle
	}
	
	/**
	 * Makes the robot drive straight using PID control.
	 *  Call initializeTankDriveStraight() first, and then iterate this function until
	 *  tandDriveStraightFinished() returns true
	 */
	public void tankDriveStraight() {
		tankDrivetrain.arcadeDrive(linearTankHandler.getOutput(), angularTankHandler.getOutput());
	}
	
	/**
	 * Returns true when the robot has reached its destination after tank driving straight
	 */
	public boolean tankDriveStraightFinished() {
		return linearTankPID.onTarget();
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
    	
    	double speeds[] = new double[4]; 							//Array to store each wheel speed
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
    
    /**
     * The types of drivetrain (OMNI and TANK)
     */
    public enum driveType {
    	OMNI, TANK; //Defines an enumeration for both types of drivetrain
    }
    
    /**
     * Switches the drivetrain by using the pneumatics
     */
    public void switchDrivetrain(driveType drivetrain) {
    	if (drivetrain == driveType.OMNI) {
    		wheelSwitch.set(Value.kReverse);
    	} else {
    		wheelSwitch.set(Value.kForward);
    	}
    }

}

