package climbing;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lock extends Command {
	boolean dir;
	boolean toggle;

    /**
     * Moves the piston in the designated "locked" position.
     * If toggle desired, use boolean constructor.
     */
	public Lock() {
        requires(Robot.climber);
        toggle = false;
    }
    
    /**
     * Toggles the locking piston
     * @param dir true for lock, false for unlock
     */
	public Lock(boolean dir) {
		requires(Robot.climber);
    	this.dir = dir;
    	toggle = true;
    }
   

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!toggle || dir) {
    		Robot.climber.lock();
    	} else {
    		Robot.climber.unLock();
    	}
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
