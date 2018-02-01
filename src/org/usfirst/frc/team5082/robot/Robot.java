package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	double kP = 1;
	double kI = 1;
	double kD = 1;
	
	Auton auton;
	RobotBase rb;
	Joystick joy;																		//joystick for 1 driver arcade drive

	DriverStation DS;
	
	SendableChooser<Integer> chooser = new SendableChooser<Integer>();					//communicates what auto got chose, pt 1
	
	double speed, rotation;																//to be fed into arcadeDrive()
	int autoChooser;																	//communicates what auto got chose, pt 2

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		rb = new RobotBase();
		
		//INITIALIZING VARS
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
	}

	//This function is run once before autonomousPeriodic() begins
	@Override
	public void autonomousInit() {
											
		auton = new Auton();
		autoChooser = chooser.getSelected();													//pick the auto to run
		
		//prep important sensors to go
		rb.timer.reset();
		rb.timer.start();
		rb.encoder.reset();
		rb.gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		String orientation = (DS.getGameSpecificMessage()).substring(0, 1);
		
		if(DS.isEnabled()) auton.autoPeriodic(autoChooser, orientation);
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		if (DS.isEnabled()) {
			SmartDashboard.putNumber("Encoder Distance: ", rb.encoder.getDistance());
			SmartDashboard.putNumber("Match Timer: ", 135 - rb.timer.get());
			SmartDashboard.putNumber("Your Orientation: ", rb.gyro.getAngle());
			
			rb.drive.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(4));
			
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
		rb.init();
		
		SmartDashboard.putNumber("Encoder Counts", rb.encoder.get());
		
		System.out.println("It has done the download m'lady");
		
	}
}

