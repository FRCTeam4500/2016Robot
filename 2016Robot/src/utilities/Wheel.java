package utilities;

import edu.wpi.first.wpilibj.Talon;

public class Wheel {
	private Vector position;			//Where is the wheel in relation to the center of the robot
	private Vector direction;			//What direction does the wheel poing
	private double speed_ratio;			//How fast does the wheel go (gear ratio ...)
	private Talon motor_controller;
	
	public Wheel(Vector position, Vector direction, double speed_ratio, Talon motor_controller) {
		this.position = position;
		this.direction = direction;
		this.speed_ratio = speed_ratio;
		this.motor_controller = motor_controller;
	}
	
	public double getSpeed(Vector velocity, Vector rotation){
		/**
		 * Gets the speed of the wheel based on the linear and rotational movements
		 */
		Vector wheel_velocity = velocity.add(rotation.cross(position));
		return direction.dot(wheel_velocity) * speed_ratio;
		
	}
	
	public void setSpeed(double speed){
		/**
		 * Set the talon value (Between -1.0 and 1.0) for teh wheel
		 */
		motor_controller.set(speed);
	}
	
	
	

}
