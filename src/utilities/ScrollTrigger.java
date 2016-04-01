package utilities;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ScrollTrigger extends Trigger {
    
    public boolean get() {
        return Robot.oi.getScroller();
    }
}
