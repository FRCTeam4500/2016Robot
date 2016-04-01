package climbing;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Retract extends Command {

	private Timer time;
	private double seconds;
	private boolean useTime;

    public Retract() {
        requires(Robot.climber);
        useTime = false;
    }
    
    public Retract(double seconds) {
    	requires(Robot.climber);
    	this.seconds = seconds;
    	time = new Timer();
    	useTime = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.retract();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (useTime && time.get() > seconds) ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(useTime) time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
