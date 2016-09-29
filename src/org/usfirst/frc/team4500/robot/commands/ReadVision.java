package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ReadVision extends Command {

    public ReadVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if(Robot.visionClient.socketInitialized()){
    		double xAngle = Robot.visionClient.getXAngle();
    		double yAngle = Robot.visionClient.getYAngle();

    		double aimAngle = Robot.cannon.getVerticalAngle(yAngle*180/3.14 + 45);

    		SmartDashboard.putNumber("x angle", xAngle*180/3.14 + 45);
    		SmartDashboard.putNumber("y angle", yAngle*180/3.14 + 45);
    		SmartDashboard.putNumber("aim angle", aimAngle);
		SmartDashboard.putString("InitS", "Yes");
    	}else{
    		Robot.visionClient.initializeSocket();
    		SmartDashboard.putString("InitS", "No");
    	}*/


    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
