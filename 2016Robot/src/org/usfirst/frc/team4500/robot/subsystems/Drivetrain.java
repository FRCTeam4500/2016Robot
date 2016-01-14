package org.usfirst.frc.team4500.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(double joyX, double joyY, double joyTwist, double gyro) {
    	//TODO: Make tank drive function
    }
    
    public void omniDrive(double joyX, double joyY, double joyTwist, double gyro) {
    	//TODO: Make omni drive function
    }
}

