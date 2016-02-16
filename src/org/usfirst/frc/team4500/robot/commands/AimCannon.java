package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimCannon extends CommandGroup {
    
    public  AimCannon(double verticalAngle, double horizontalOffset) {
    	requires(Robot.cannon);
    	addParallel(new AimHorizontally(horizontalOffset));
    	addSequential(new AimVertically(verticalAngle));
    }
}
