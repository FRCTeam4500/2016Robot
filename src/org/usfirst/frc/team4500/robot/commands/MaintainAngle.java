package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MaintainAngle extends Command {

    /**
     * Drive with angle maintained through the gyro using joystick input
     */
	public MaintainAngle() {
        requires(Robot.drivetrain);
        auto = false;
    }
	
	private double speed;
	private double sec;
	private boolean auto;
	private Timer time;
	
	/**
	 * Drive with angle maintained through the gyro at the given speed for the given number of seconds
	 * @param speed
	 * @param sec
	 */
	public MaintainAngle(double speed, double sec) {
		requires(Robot.drivetrain);
		this.speed = speed;
		this.sec = sec;
		auto = true;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.initTankDriveStraight(Robot.drivetrain.getGyroAngle());
    	SmartDashboard.putBoolean("Maintaining Angle", true);
    	if(auto) time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(auto) {
    		Robot.drivetrain.tankDriveStraight(speed); 
    	} else {
    		Robot.drivetrain.tankDriveStraight(Robot.oi.getJoyY());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (auto && time.get() > sec) ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Maintaining Angle", false);
    	if(auto) time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
