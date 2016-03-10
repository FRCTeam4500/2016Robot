package org.usfirst.frc.team4500.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimAndFire extends CommandGroup {
    
    public  AimAndFire() {
    	addSequential(new AimHorizontally());
    	addSequential(new Fire(4));
    }
}
