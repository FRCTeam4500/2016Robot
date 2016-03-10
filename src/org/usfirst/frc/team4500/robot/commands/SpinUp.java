package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUp extends Command {

	double sec;
    Timer time;
	double speed;
	boolean useTime;
	
	/**
	 * Spins up the flywheel at the given speed
	 * @param speed speed to spin between -1 and 1
	 */
    public SpinUp(double speed) {
    	requires(Robot.cannon);
    	this.speed = speed;
    	useTime = false;
    }
    
    /**
     * Spins the flywheel at the given speed for the given amount of seconds
     * @param speed speed to spin between -1 and 1
     * @param sec time to spin in seconds
     */
    public SpinUp(double speed, double sec) {
    	requires(Robot.cannon);
    	time = new Timer();
    	this.sec = sec;
    	this.speed = speed;
    	useTime = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(useTime) time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.spinUp(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (useTime && time.get() > sec) ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(useTime) time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
