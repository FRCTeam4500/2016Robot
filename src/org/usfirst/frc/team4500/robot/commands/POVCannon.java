package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class POVCannon extends Command {

    public POVCannon() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Robot.oi.getPOV()) {
    	case 0:
    		Robot.cannon.moveVertically(.3);
    		break;
    	case 90:
    		Robot.cannon.moveHorizontally(.3);
    	case 180:
    		Robot.cannon.moveVertically(-.3);
    	case 270:
    		Robot.cannon.moveHorizontally(-.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.getPOV() == -1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cannon.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
