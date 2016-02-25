package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Loader extends Subsystem {
    
    private Talon loader;
    private DoubleSolenoid grabSol;
    
    public Loader() {
    	loader = new Talon(RobotMap.LOADMOTOR);
    	grabSol = new DoubleSolenoid(RobotMap.LOAD_SOLENOID_1, RobotMap.LOAD_SOLENOID_2);
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
    
    public void loadDown() {
    	grabSol.set(Value.kForward);
    }
    
    public void loadUp() {
    	grabSol.set(Value.kReverse);
    }
    
    public void load(loadDirection dir) {
    	if (dir == loadDirection.UP) {
    		loadUp();
    	} else {
    		loadDown();
    	}
    }
    
    public enum loadDirection {
    	UP, DOWN;
    }
}

