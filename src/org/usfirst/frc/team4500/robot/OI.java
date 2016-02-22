package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.AimVertically;
import org.usfirst.frc.team4500.robot.commands.CalibrateCannon;
import org.usfirst.frc.team4500.robot.commands.Load;
import org.usfirst.frc.team4500.robot.commands.ManualMoveToHorizSetpoint;
import org.usfirst.frc.team4500.robot.commands.ManualMoveToVertSetpoint;
import org.usfirst.frc.team4500.robot.commands.MoveHorizontally;
import org.usfirst.frc.team4500.robot.commands.MoveVertically;
import org.usfirst.frc.team4500.robot.commands.ResetEncoders;
import org.usfirst.frc.team4500.robot.commands.SpinUp;
import org.usfirst.frc.team4500.robot.commands.TankDrive;

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
	Button setpointB, moveLeft, moveRight, moveUp, moveDown, spinUp, callibrate, load, resetEncoders, cameraAim;
	
	/**
	 * Initializes the joystick and the coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public OI() {
		stick = new Joystick(0); 
		//Buttons can be made to activate commands like this:
		
		setpointB = new JoystickButton(stick, 11);
		setpointB.whenPressed(new ManualMoveToVertSetpoint(30));
		
		moveLeft = new JoystickButton(stick, 3);
		moveRight = new JoystickButton(stick, 4);
		moveUp = new JoystickButton(stick, 5);
		moveDown = new JoystickButton(stick, 6);
		
		//callibrate = new JoystickButton(stick, 12);
		resetEncoders = new JoystickButton(stick, 7);
		moveLeft.whileHeld(new MoveHorizontally(-.5));
		moveRight.whileActive(new MoveHorizontally(.5));
		moveUp.whileHeld(new MoveVertically(-.4));
		moveDown.whileHeld(new MoveVertically(.3));
		moveDown.whenReleased(new MoveVertically(0));
		moveUp.whenReleased(new MoveVertically(0));
		moveRight.whenReleased(new MoveHorizontally(0));
		moveLeft.whenReleased(new MoveHorizontally(0));
		resetEncoders.whenPressed(new ResetEncoders());
		
		spinUp = new JoystickButton(stick, 2);
		spinUp.whenPressed(new SpinUp(1));
		
		//callibrate.whenPressed(new CalibrateCannon());
		
		load = new JoystickButton(stick, 8);
		load.whileHeld(new Load());
		
		//cameraAim = new JoystickButton(stick, 9);
		//cameraAim.whenPressed(new AimVertically(Robot.visionClient.getYAngle()));
		
		
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

