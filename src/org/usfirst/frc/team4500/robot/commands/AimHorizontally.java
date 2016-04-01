package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimHorizontally extends Command {

    double offset;
	
    public AimHorizontally() {
    	requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cannon.setHorizontalOffset(Robot.cannon.getVisionAngle());
    	//Robot.cannon.setHorizontalOffset(-40);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.aimHorizontally();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.horizontalAimFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
