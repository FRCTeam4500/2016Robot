
package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.ConnectToCoprocessor;
import org.usfirst.frc.team4500.robot.subsystems.Cannon;
import org.usfirst.frc.team4500.robot.subsystems.Climber;
import org.usfirst.frc.team4500.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4500.robot.subsystems.Loader;
import org.usfirst.frc.team4500.robot.subsystems.PneumaticsMain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utilities.VisionClient;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Instantiate subsystems here, i.e: public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static VisionClient visionClient;
	public static Drivetrain drivetrain;
	public static Cannon cannon;
	public static Climber climber;
	public static PneumaticsMain pneumatics;
	public static Loader loader;
	int n;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	n = 0;
    	//TODO We'll comment these out and only initialize them one by one as we test the robot.
    	
		visionClient = new VisionClient();
		drivetrain = new Drivetrain();
		pneumatics = new PneumaticsMain();
		cannon = new Cannon();
		loader = new Loader();
		//climber = new Climber();
		
		oi = new OI();
		
		//visionClient = new VisionClient();
		(new ConnectToCoprocessor()).start(); //TODO: Make sure that this command runs in parallel
		
		
        // instantiate the command used for the autonomous period
        //i.e. autonomousCommand = new ExampleCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	/*if(Robot.cannon.horizEncoder != null){
    		SmartDashboard.putNumber("HoizEncoder", Robot.cannon.horizEncoder.get());
    		SmartDashboard.putNumber("Vert Encoder", Robot.cannon.vertEncoder.getDistance());
    		SmartDashboard.putNumber("Counter", n);
    		n++;
    		SmartDashboard.putString("Working", "yes");
    	}else{
    		SmartDashboard.putString("Working", "No");
    	}*/
    	if(visionClient.socketInitialized()){
    		visionClient.getXAngle();
    		visionClient.getYAngle();
    	}
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
