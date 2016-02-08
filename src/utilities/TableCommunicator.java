package utilities;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class TableCommunicator implements ITableListener {
	
	Double number = new Double(0);
	
	public TableCommunicator() {
		initialize();
	}
	
	private void initialize() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(RobotMap.TABLE_IP);
		NetworkTable table = NetworkTable.getTable(RobotMap.TABLE_NAME);
		table.addTableListener(this);
	}

	@Override
	public void valueChanged(ITable source, String key, Object value, boolean isNew) {
		if(isNew && key.equals(RobotMap.TABLE_KEY)) {
			number = (Double) value;
		}
	}
	
	public double getLatestNumber() {
		return number.doubleValue();
	}

}
