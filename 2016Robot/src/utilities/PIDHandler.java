package utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class PIDHandler implements PIDOutput {
	
	private double output;
	
	/**
	 * A class made for the purpose of simply being able to get the raw output of a PID controller instead of
	 * immediately using the output
	 */
	public PIDHandler() {
		output = 0.0;
	}

	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
	
	public double getOutput() {
		return output;
	}
}
