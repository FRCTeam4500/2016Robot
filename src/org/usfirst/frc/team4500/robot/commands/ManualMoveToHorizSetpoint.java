package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualMoveToHorizSetpoint extends Command {

    double setpoint;
	
	public ManualMoveToHorizSetpoint(double setpoint) {
        requires(Robot.cannon);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cannon.safelySetHorizSetpoint(Robot.cannon.toHorizontalPulses(setpoint));
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
