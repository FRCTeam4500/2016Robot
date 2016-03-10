
package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.AimHorizontally;
import org.usfirst.frc.team4500.robot.commands.Load;
import org.usfirst.frc.team4500.robot.commands.LoadDown;
import org.usfirst.frc.team4500.robot.commands.LoadUp;
import org.usfirst.frc.team4500.robot.commands.MaintainAngle;
import org.usfirst.frc.team4500.robot.commands.MoveHorizontally;
import org.usfirst.frc.team4500.robot.commands.MoveVertically;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;
import org.usfirst.frc.team4500.robot.commands.ReadVision;
import org.usfirst.frc.team4500.robot.commands.ResetEncoders;
import org.usfirst.frc.team4500.robot.commands.ReverseLoad;
import org.usfirst.frc.team4500.robot.commands.SpinUp;
import org.usfirst.frc.team4500.robot.commands.StopLoad;
import org.usfirst.frc.team4500.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick driveStick;
	Joystick shootStick;
	

	Button setpointB, moveLeft, moveRight, 
	moveUp, moveDown, callibrate, 
	load, loadDown, resetEncoders, cameraAim, omni, tank;
	Button straight;
	
	Button spinUp;
	
	Button putAngle;
	
	Button reverseLoad;
	
	Button loadUp;
	/**
	 * Initializes the joystick and the coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public OI() {
		driveStick = new Joystick(0);
		shootStick = new Joystick(1);
		//Buttons can be made to activate commands like this:
		
		//setpointB = new JoystickButton(stick, 11);
		//setpointB.whenPressed(new ManualMoveToVertSetpoint(30));
		
		putAngle = new JoystickButton(driveStick, 7);
		putAngle.whenPressed(new ReadVision());
		
		moveLeft = new JoystickButton(shootStick, 4);
		moveRight = new JoystickButton(shootStick, 5);
		moveUp = new JoystickButton(shootStick, 3);
		moveDown = new JoystickButton(shootStick, 2);
		
		omni = new JoystickButton(driveStick,11);
		tank = new JoystickButton(driveStick,12);
		
		straight = new JoystickButton(driveStick, 9);
		straight.whenPressed(new MaintainAngle());
		
		
		//callibrate = new JoystickButton(stick, 12);

		moveLeft.whileHeld(new MoveHorizontally(-.5));
		moveRight.whileActive(new MoveHorizontally(.5));
		moveUp.whileHeld(new MoveVertically(-.4));
		moveDown.whileHeld(new MoveVertically(.3));
		moveDown.whenReleased(new MoveVertically(0));
		moveUp.whenReleased(new MoveVertically(0));
		moveRight.whenReleased(new MoveHorizontally(0)
		);
		moveLeft.whenReleased(new MoveHorizontally(0));
		
		omni.whenPressed(new OmniDrive());
		tank.whenPressed(new TankDrive());

		
		spinUp = new JoystickButton(shootStick, 1);
		spinUp.whenPressed(new SpinUp(1));
		spinUp.whenReleased(new SpinUp(0));
		
		//callibrate.whenPressed(new CalibrateCannon());
		
		load = new JoystickButton(shootStick, 6);
		load.whenPressed(new Load());
		load.whenReleased(new StopLoad());
		
		reverseLoad = new JoystickButton(shootStick, 7);
		reverseLoad.whenPressed(new ReverseLoad());
		reverseLoad.whenReleased(new StopLoad());
		
		loadDown = new JoystickButton(shootStick, 11);
		loadDown.whenPressed(new LoadDown());
		loadDown.whenReleased(new StopLoad());
		
		loadUp = new JoystickButton(shootStick, 12);
		loadUp.whenPressed(new LoadUp());
		loadUp.whenReleased(new StopLoad());
		
		resetEncoders = new JoystickButton(driveStick, 7);
		resetEncoders.whenPressed(new ResetEncoders());
		
		
		
		cameraAim = new JoystickButton(shootStick, 2);
		//cameraAim.whenPressed(new AimVertically(Robot.visionClient.getYAngle()));
		
		//cameraAim = new JoystickButton(stick, 1);
		cameraAim.whenPressed(new AimHorizontally());
		
	}
	
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : driveStick.getX();
	}
	
	/**
	 * The value of the y axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return y value from joystick (-1 to 1)
	 */
	public double getJoyY() {
		return (Math.abs(driveStick.getY()) < RobotMap.DEADZONE) ? 0 : driveStick.getY();
	}
	
	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return twist value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		return ((Math.abs(driveStick.getTwist()) < RobotMap.DEADZONE) ? 0 : driveStick.getTwist());
	}


	public int getPOV() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

