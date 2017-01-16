package org.usfirst.frc.team4500.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utilities.VisionClientFinal;

/**
 *
 */
public class NewAimAndFire extends Command {
	
	double speed = 0;
	double visionX = 0;
	double fov = 75.0;
	double width = 320;
	boolean firstRun = false;

    public NewAimAndFire(double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cannon);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(firstRun == false) {
    		visionX = RobotMap.visionX;
    		firstRun = true;
    	}
    	double deg = Robot.cannon.horizEncoder.getDistance();
    	double dist_to_move = ((visionX - (width/2)) * (fov/width));
    	double dist_left = Math.abs(dist_to_move) - deg;
    	SmartDashboard.putNumber("vX", visionX);
    	SmartDashboard.putNumber("dist_to_move", dist_to_move);
    	SmartDashboard.putNumber("deg", deg);
    	SmartDashboard.putNumber("Dist left", dist_left);
    	if(dist_to_move > 0) {
    		Robot.cannon.moveHorizontally(speed);
    	} else if(dist_to_move < 0) {
    		Robot.cannon.moveHorizontally(-1 * speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double deg = Robot.cannon.horizEncoder.getDistance();
    	double dist_to_move = ( ((visionX - (width/2)) * (fov/width)) ) - 6;
    	if(Math.abs(dist_to_move) - Math.abs(deg) <= 0.5) {
    		firstRun = false;
    		Robot.cannon.moveHorizontally(0);
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Finished", "Yes");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	firstRun = false;
    }
}
