
package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.AimHorizontally;
import org.usfirst.frc.team4500.robot.commands.Load;
import org.usfirst.frc.team4500.robot.commands.LoadDown;
import org.usfirst.frc.team4500.robot.commands.LoadUp;
import org.usfirst.frc.team4500.robot.commands.MaintainAngle;
import org.usfirst.frc.team4500.robot.commands.MoveHorizontally;
import org.usfirst.frc.team4500.robot.commands.MoveLoader;
import org.usfirst.frc.team4500.robot.commands.MoveVertically;
import org.usfirst.frc.team4500.robot.commands.NewAimAndFire;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;
import org.usfirst.frc.team4500.robot.commands.ReadVision;
import org.usfirst.frc.team4500.robot.commands.ResetEncoders;
import org.usfirst.frc.team4500.robot.commands.ReverseLoad;
import org.usfirst.frc.team4500.robot.commands.SpinUp;
import org.usfirst.frc.team4500.robot.commands.StopLoad;
import org.usfirst.frc.team4500.robot.commands.TankDrive;
import org.usfirst.frc.team4500.robot.commands.VisionGroup;
import org.usfirst.frc.team4500.robot.subsystems.Loader;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import utilities.ScrollTrigger;

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

	Button shootForward, shootReverse, shootUp, shootDown;

	Button moveLoaderUp, moveLoaderDown;

	Button aimHoriz;

	Button aimTarget;

	Button visionAim;
	
	Button moveTurret;

	Trigger scrollTrigger;


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
		moveLoaderUp = new JoystickButton(driveStick, 4);
		moveLoaderUp.whenPressed(new LoadUp());

		moveLoaderDown = new JoystickButton(driveStick, 3);
		moveLoaderDown.whenPressed(new LoadDown());
		putAngle = new JoystickButton(driveStick, 7);
		putAngle.whenPressed(new ReadVision());

		moveLeft = new JoystickButton(shootStick, 4);
		moveRight = new JoystickButton(shootStick, 5);
		//moveUp = new JoystickButton(shootStick, 3);
		//moveDown = new JoystickButton(shootStick, 2);

		aimHoriz = new JoystickButton(shootStick, 3);
		//aimHoriz.whenPressed(new AimHorizontally());

		omni = new JoystickButton(driveStick,11);
		tank = new JoystickButton(driveStick,12);

		straight = new JoystickButton(driveStick, 9);
		straight.whenPressed(new MaintainAngle());


		//callibrate = new JoystickButton(stick, 12);

		moveLeft.whileHeld(new MoveHorizontally(-.3));
		moveRight.whileActive(new MoveHorizontally(.3));
		//moveUp.whileHeld(new MoveVertically(-.4));
		//moveDown.whileHeld(new MoveVertically(.3));
		//moveDown.whenReleased(new MoveVertically(0));
		//moveUp.whenReleased(new MoveVertically(0));
		moveRight.whenReleased(new MoveHorizontally(0));
		moveLeft.whenReleased(new MoveHorizontally(0));

		omni.whenPressed(new OmniDrive());
		tank.whenPressed(new TankDrive());


		/*spinUp = new JoystickButton(shootStick, 1);
		spinUp.whenPressed(new SpinUp(1));
		spinUp.whenReleased(new SpinUp(0));*/

		//callibrate.whenPressed(new CalibrateCannon());

		load = new JoystickButton(shootStick, 1);
		load.whenPressed(new Load());
		load.whenReleased(new StopLoad());

		reverseLoad = new JoystickButton(driveStick, 3);
		reverseLoad.whenPressed(new ReverseLoad());
		reverseLoad.whenReleased(new StopLoad());

		loadDown = new JoystickButton(driveStick, 4);
		loadDown.whenPressed(new LoadDown());
		loadDown.whenReleased(new StopLoad());

		loadUp = new JoystickButton(driveStick, 6);
		loadUp.whenPressed(new LoadUp());
		loadUp.whenReleased(new StopLoad());

		shootForward = new JoystickButton(shootStick, 6);
		shootForward.whenPressed(new Load());
		shootForward.whenReleased(new StopLoad());

		shootReverse = new JoystickButton(shootStick, 7);
		shootReverse.whenPressed(new ReverseLoad());
		shootReverse.whenReleased(new StopLoad());

		shootDown = new JoystickButton(shootStick, 11);
		shootDown.whenPressed(new LoadDown());
		shootDown.whenReleased(new StopLoad());

		shootUp = new JoystickButton(shootStick, 10);
		shootUp.whenPressed(new LoadUp());
		shootUp.whenReleased(new StopLoad());

		resetEncoders = new JoystickButton(driveStick, 7);
		resetEncoders.whenPressed(new ResetEncoders());

		//aimTarget = new JoystickButton(shootStick, 12);
		//aimTarget.whenPressed(new AimAtTarget());

		visionAim = new JoystickButton(shootStick, 8);
		
		moveTurret = new JoystickButton(driveStick, 8);
		moveTurret.whenPressed(new VisionGroup());

		//cameraAim = new JoystickButton(shootStick, 2);
		//cameraAim.whenPressed(new AimVertically(Robot.visionClient.getYAngle()));

		//cameraAim = new JoystickButton(stick, 1);
		//cameraAim.whenPressed(new AimHorizontally());

	}

	public void initTrigger() {
		scrollTrigger = new ScrollTrigger();
		scrollTrigger.whenActive(new SpinUp(1.0));
		scrollTrigger.whenInactive(new SpinUp(0));
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

	public boolean getScroller() {
		return shootStick.getZ() < 0;
	}


}

