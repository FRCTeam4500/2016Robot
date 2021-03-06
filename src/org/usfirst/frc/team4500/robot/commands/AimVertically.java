package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is intentionally NOT 
 */
public class AimVertically extends Command {

    double degrees = 0;
	
	/**
	 * DON'T USE THIS ALONE. DOES NOT REQUIRE THE SUBSYSTEM. USE AIMCANNON
	 * Aims the vertical component of the cannon to the specified angle in degrees.
	 * @param degrees The angle to set the vertical component of cannon to in degrees.
	 */
    public AimVertically(double degrees) {
        //requires(Robot.cannon); //We don't want this if we want both axes to aim simultaneously
        this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cannon.setVerticalAngle(degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.aimVertically();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.verticalAimFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
