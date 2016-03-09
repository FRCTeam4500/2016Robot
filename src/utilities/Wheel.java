package utilities;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Wheel {
	private Vector position;			//Where is the wheel in relation to the center of the robot
	private Vector direction;			//What direction does the wheel going
	private double speed_ratio;			//How fast does the wheel go (gear ratio ...)
	private SpeedController motor_controller;
	
	/*
	 * Class to store values relating to each omni wheel's postion,
	 *  and methods for controlling their speeds for the purpose of gyroscopic driving
	 */
	public Wheel(Vector position, Vector direction, double speed_ratio, SpeedController motor_controller) {
		this.position = position;
		this.direction = direction;
		this.speed_ratio = speed_ratio;
		this.motor_controller = motor_controller;
	}
	
	/**
	 * Gets the speed of the wheel based on the linear and rotational movements
	 */
	public double getSpeed(Vector velocity, Vector rotation, double theta){
		Vector wheel_velocity = velocity.add(rotation.cross(position));
		wheel_velocity.rotateAboutZAxis(theta);
		return direction.dot(wheel_velocity) * speed_ratio;
		/*Vector wheelVec = new Vector(direction.getX()*magnitude, direction.getY()*magnitude, direction.getZ());
		wheelVec.rotateAboutZAxis(theta);
		return wheelVec.projectOnto(position);*/
	}
	
	/**
	 * Set the talon value (Between -1.0 and 1.0) for the wheel
	 */
	public void setSpeed(double speed){
		motor_controller.set(speed);
	}	
	

}
