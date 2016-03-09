package org.usfirst.frc.team4500.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothing extends CommandGroup {
    
    public  DoNothing() {
      
    	addSequential(new Wait(15000));
    
    	
    }
}
