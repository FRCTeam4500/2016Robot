package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.ManualMoveToHorizSetpoint;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick;
	
	//Buttons are instantiated like this:
	//Button b1 = new JoystickButton(stick, 0);
	
	/**
	 * Initializes the joystick and the coprocessor socket;
	 * The coprocesser socket may be a null socket - be warned.
	 */
	public OI() {
		stick = new Joystick(0);
		//Buttons can be made to activate commands like this:
		//b1.whenPressed(new ManualMoveToHorizSetpoint(30));
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

