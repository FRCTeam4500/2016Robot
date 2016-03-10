package org.usfirst.frc.team4500.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Fire extends CommandGroup {
    
    /**
     * Fires the cannon over the course of 10 seconds
     */
	public  Fire() {
    	addParallel(new SpinUp(1, 10));
    	addSequential(new Wait(5));
    	addSequential(new Load(5));
    }
	
	public  Fire(double spinupTime) {
    	addParallel(new SpinUp(1, spinupTime));
    	addSequential(new Wait(spinupTime/2));
    	addSequential(new Load(spinupTime/2));
    }
}
