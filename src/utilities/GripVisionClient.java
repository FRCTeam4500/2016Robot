package utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GripVisionClient {

	NetworkTable table;
	double WIDTH = 0.5080;
	double HEIGHT = 0.3048;
	double FOV = 0.820305;
	
	double[] contour = new double[3];
	
	public GripVisionClient() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
		for (int i = 0; i < contour.length; i++ ) {
			contour[i] = 0;
		}
	}
	
	public void getValues() {
		double[] defaultValue = new double[0];
		double[] centerX = table.getNumberArray("centerX", defaultValue);
		double[] centerY = table.getNumberArray("centerY", defaultValue);
		double[] area = table.getNumberArray("area",defaultValue);
		if (area.length == 0) {
			contour[0] = -10000000;
			contour[1] = -10000000;
			contour[2] = -10000000;
		}
		for (int i = 0; i < contour.length; i++) {
			if (contour[2] < area[i]) {
				contour[0] = centerX[i];
				contour[1] = centerY[i];
				contour[2] = area[i];
			}

		}
		
	}
	
	public double getAngleX() {
		double x = 0;
		if (contour[0] != -10000000)
			x = Math.atan(contour[0]/WIDTH * Math.tan(FOV/2));
		
		return x;
	}
	
	public double getAngleY() {
		double y = 0;
		if (contour[0] != -10000000)
			y = Math.atan(contour[1]/HEIGHT * Math.tan(FOV/2));
		
		return y;
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
