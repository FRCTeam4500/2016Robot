package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsMain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Compressor compress = new Compressor(RobotMap.pneumaticsModule);
	
	public PneumaticsMain() {
		startCompressor();
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCompressor() {
    	compress.start();
    }
}

