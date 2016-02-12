package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Climber extends PIDSubsystem {

	private static final double kp = 0, ki = 0, kd = 0;
	private Talon climberTalon;
    // Initialize your subsystem here
    public Climber() {
        // Use these to get going:
    	super("Climber", kp, ki, kd);  
    	climberTalon = new Talon(RobotMap.CLIMBERMOTOR);
        enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	climberTalon.set(output);
    }
    
    public void setTop()
    {
    	setSetpoint(RobotMap.CLIMBERTOP);  
    }
    
    public void setBottom()
    {
    	setSetpoint(RobotMap.CLIMBERBOTTOM);  
    }
}
