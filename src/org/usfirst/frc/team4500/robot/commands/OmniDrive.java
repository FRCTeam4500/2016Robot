package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OmniDrive extends Command {

    public OmniDrive() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.switchDrivetrain(Drivetrain.driveType.OMNI);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.tankDrive(Robot.oi.getJoyY(), -Robot.oi.getJoyTwist());
    	//Robot.drivetrain.setStraife(Robot.oi.getJoyX());
    	//TODO temp uses tankdrive
    	//Gives the omni drive functions the square of the joystick axes, making for smoother control.
    	//Robot.drivetrain.omniDrive(Math.pow(Robot.oi.getJoyX(), 2), Math.pow(Robot.oi.getJoyY(), 2), -Math.pow(Robot.oi.getJoyTwist(), 2), Robot.drivetrain.getGyroAngle());
    	Robot.drivetrain.omniDrive(Robot.oi.getJoyX(), Robot.oi.getJoyY(), Robot.oi.getJoyTwist()/10, 0); //TODO:Fix gyro
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
