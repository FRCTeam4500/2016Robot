package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Loader extends Subsystem {
    
    Victor loader;
    
    public Loader() {
    	loader = new Victor(RobotMap.LOADMOTOR);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void load() {
    	loader.set(RobotMap.LOAD_SPEED);
    }
    
    public void stop() {
    	loader.set(0);
    }
}

