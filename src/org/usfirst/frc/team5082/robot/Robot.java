package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Auton auton;
	RobotBase rb;
	Joystick joy;																		//joystick for 1 driver arcade drive

	//instances of the cool kids (we need them)
	Timer timer;
	DriverStation DS;
	
	SendableChooser<Integer> chooser = new SendableChooser<Integer>();					//communicates what auto got chose, pt 1
	int autoChooser;																	//communicates what auto got chose, pt 2

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//INSTANCE OF THE RB
		rb = new RobotBase();
		
		//INITIALIZING VARS
		timer = new Timer();
		joy = new Joystick(0);
		DS = DriverStation.getInstance();
		autoChooser = 0;
		
		//OTHER INIT STUFF		
		SmartDashboard.putData("Autonomous Mode Selector: ", chooser);					//creates a menu of autonomii
		chooser.addObject("Left Start, Switch", rb.LSWITCH);
		chooser.addObject("Center Start, Switch", rb.CSWITCH);
		chooser.addObject("Right Start, Switch", rb.RSWITCH);
		chooser.addObject("Auto Line Only", rb.AUTOLINE);
		chooser.addDefault("None", rb.DEFAULT);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	//This function is run once before autonomousPeriodic() begins
	@Override
	public void autonomousInit() {
							
		//INSTANCE OF THE AUTON & CHOOSE THE AUTON
		auton = new Auton();
		autoChooser = chooser.getSelected();
		
		//prep important sensors to go
		rb.encoder.reset();
		rb.gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		//what is the way things are set up (like the randomized plats)
		//String orientation = (DS.getGameSpecificMessage()).substring(0, 1);
		
		//run auto only while the ds is on
		if(DS.isEnabled()) auton.autoPeriodic(autoChooser, "L");
		else {
			rb.topCims.arcadeDrive(0, 0);
			rb.midCims.arcadeDrive(0, 0);
			rb.bottomCims.arcadeDrive(0, 0);
		}
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		//run tele only while the ds is on
		if (DS.isEnabled()) {
			//printing msgs to dash
			SmartDashboard.putNumber("Encoder Distance: ", rb.encoder.getDistance());
			SmartDashboard.putNumber("Match Timer: ", 135 - timer.get());
			SmartDashboard.putNumber("Your Orientation: ", rb.gyro.getAngle());
			
			//driving with same joys as last year (change if requested to)
			rb.topCims.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(4));
			rb.midCims.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(4));
			rb.bottomCims.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(4));
		}
		else {
			rb.topCims.arcadeDrive(0, 0);
			rb.midCims.arcadeDrive(0, 0);
			rb.bottomCims.arcadeDrive(0, 0);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
		//THIS IS FOR TESTING so nothing here is sacred
		SmartDashboard.putNumber("Encoder Counts", rb.encoder.get());
		
	}
}

