package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.AimVertically;
import org.usfirst.frc.team4500.robot.commands.CalibrateCannon;
import org.usfirst.frc.team4500.robot.commands.CannonDoNothing;
import org.usfirst.frc.team4500.robot.commands.Load;
import org.usfirst.frc.team4500.robot.commands.ManualMoveToHorizSetpoint;
import org.usfirst.frc.team4500.robot.commands.ManualMoveToVertSetpoint;
import org.usfirst.frc.team4500.robot.commands.MoveHorizontally;
import org.usfirst.frc.team4500.robot.commands.MoveVertically;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;
import org.usfirst.frc.team4500.robot.commands.ResetEncoders;
import org.usfirst.frc.team4500.robot.commands.SpinUp;
import org.usfirst.frc.team4500.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick;
	
	//Buttons are instantiated like this:
	Button setpointB, moveLeft, moveRight, 
	moveUp, moveDown, spinUp, callibrate, 
	load, resetEncoders, cameraAim, omni, tank,
	loadUp, loadDown;
	
	
	
	
	/**
	 * Initializes the joystick and the coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public OI() {
		stick = new Joystick(0); 
		//Buttons can be made to activate commands like this:
		
		//setpointB = new JoystickButton(stick, 11);
		//setpointB.whenPressed(new ManualMoveToVertSetpoint(30));
		
		moveLeft = new JoystickButton(stick, 19);
		moveRight = new JoystickButton(stick, 14);
		moveUp = new JoystickButton(stick, 12);
		moveDown = new JoystickButton(stick, 16);
		
		//callibrate = new JoystickButton(stick, 12);

		moveLeft.whileHeld(new MoveHorizontally(-.5));
		moveRight.whileActive(new MoveHorizontally(.5));
		moveUp.whileHeld(new MoveVertically(-.4));
		moveDown.whileHeld(new MoveVertically(.3));
		moveDown.whenReleased(new MoveVertically(0));
		moveUp.whenReleased(new MoveVertically(0));
		moveRight.whenReleased(new MoveHorizontally(0));
		moveLeft.whenReleased(new MoveHorizontally(0));

		
		spinUp = new JoystickButton(stick, 1);
		spinUp.whenPressed(new SpinUp(1));
		spinUp.whenReleased(new CannonDoNothing());
		
		//callibrate.whenPressed(new CalibrateCannon());
		
		load = new JoystickButton(stick, 2);
		load.whileHeld(new Load());
		
		loadUp = new JoystickButton(stick, 5);
		loadDown = new JoystickButton(stick, 3);
		
		resetEncoders = new JoystickButton(stick, 7);
		resetEncoders.whenPressed(new ResetEncoders());
		
		cameraAim = new JoystickButton(stick, 9);
		//cameraAim.whenPressed(new AimVertically(Robot.visionClient.getYAngle()));
		
		//cameraAim = new JoystickButton(stick, 1);
		cameraAim.whenPressed(new AimVertically(45));//Robot.visionClient.getYAngle()*180/3.14 + 45));
		
		omni = new JoystickButton(stick, 4);
		omni.whenPressed(new OmniDrive());
		
		tank = new JoystickButton(stick, 6);
		tank.whenPressed(new TankDrive());
		
		
		
		
		
	}
	
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		return (Math.abs(stick.getX()) < RobotMap.DEADZONE) ? 0 : stick.getX();
	}
	
	/**
	 * The value of the y axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return y value from joystick (-1 to 1)
	 */
	public double getJoyY() {
		return (Math.abs(stick.getY()) < RobotMap.DEADZONE) ? 0 : stick.getY();
	}
	
	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return twist value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		return (Math.abs(stick.getTwist()) < RobotMap.DEADZONE) ? 0 : stick.getTwist();
	}
	
	
}

