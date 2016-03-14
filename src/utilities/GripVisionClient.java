package utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GripVisionClient {

	NetworkTable table;
	
	public GripVisionClient() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	public double getHorizontalAngle() {
		double[] defaultValue = new double[0];
		double[] centerX = table.getNumberArray("centerX", defaultValue);
		return centerX[0];
	}
	
	public double getVerticalAngle() {
		double[] defaultValue = new double[0];
		double[] centerY = table.getNumberArray("centerY", defaultValue);
		return centerY[0];
	}
	
}
