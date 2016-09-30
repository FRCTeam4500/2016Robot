package org.usfirst.frc.team4500.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimCannon extends Command {

	int x;

    public AimCannon() {
        requires(Robot.cannon);
        try {
			x = Robot.visionClient.getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println(x);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
