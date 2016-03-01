package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUp extends Command {

    //double sec;
    //Timer time;
	double speed;
	
	/**
	 * Spins up the flywheel for the given number of seconds
	 * @param sec time to spin in seconds
	 */
    public SpinUp(double speed) {
		//time = new Timer();
		//this.sec = sec;
    	requires(Robot.cannon);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.spinUp(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //(time.get() > sec);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
