package climbing;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private SpeedController climberMotor;
	private int MOTOR_PORT = 5, SOL_PORT_1 = 4, SOL_PORT_2 = 5;
	private double DEPLOY_SPEED = 1;
	private double CLIMB_SPEED = 1;
	private DoubleSolenoid locker;
	
	public Climber() {
		climberMotor = new Talon(MOTOR_PORT);
		locker = new DoubleSolenoid(SOL_PORT_1, SOL_PORT_2);
	}
	
	public void deployClimber() {
		climberMotor.set(DEPLOY_SPEED);
	}
	
	public void climb() {
		climberMotor.set(-CLIMB_SPEED);
	}
	
	public void retract() {
		climberMotor.set(-DEPLOY_SPEED);
	}
	
	public void doNothing() {
		climberMotor.set(0);
	}
	
	public void lock() {
		locker.set(Value.kForward);
	}
	
	public void unLock() {
		locker.set(Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
