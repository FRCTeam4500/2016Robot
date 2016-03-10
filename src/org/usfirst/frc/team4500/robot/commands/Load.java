package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Load extends Command {
	
	private double sec;
	private Timer time;
	private boolean useTime;

    public Load() {
        requires(Robot.loader);
    }
    
    public Load(double sec) {
    	requires(Robot.loader);
    	time = new Timer();
    	this.sec = sec;
    	useTime = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(useTime) time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.loader.load();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (useTime && time.get() > sec) ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (useTime) time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
