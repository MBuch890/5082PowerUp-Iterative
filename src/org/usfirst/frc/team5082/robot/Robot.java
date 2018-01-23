package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//VARIABLES
	//0 is being used as a placeholder for port #s
	
	Spark mFrontLeft, mMidLeft, mBackLeft, mFrontRight, mMidRight, mBackRight;		//drive motors
	DifferentialDrive drive;															//drive base w all drive motors
	SpeedControllerGroup left, right;												//grouping motors by side
	//Joystick joy;																	//joystick for 1 driver arcade drive
	Timer timer;																		//game timer
	DriverStation.Alliance alliance;													//what alliance are we? in enum
	boolean isRed;																	//what alliance are we? in bool
	
	SendableChooser<Integer> chooser = new SendableChooser<Integer>();				//communicates what auto got chose, pt 1
	
	double speed, rotation;															//to be fed into arcadeDrive()
	int auto;																		//communicates what auto got chose, pt 2

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//INITIALIZING VARS
		timer = new Timer();
		//joy = new Joystick(0);
		mFrontLeft = new Spark(0);
		mMidLeft = new Spark(0);
		mBackLeft = new Spark(0);
		mFrontRight = new Spark(0);
		mMidRight = new Spark(0);
		mBackRight = new Spark(0);
		
		color = DriverStation.getInstance().getAlliance;
		
		left = new SpeedControllerGroup(mFrontLeft, mMidLeft, mBackLeft);
		right = new SpeedControllerGroup(mFrontRight, mMidRight, mBackRight);
		drive = new DifferentialDrive(left, right);
		
		auto = 0;
		
		//OTHER INIT STUFF
		
		drive.setSafetyEnabled(true);													//presumably this is useful, but we're not sure yet
		
		SmartDashboard.putData("Autonomous Mode Selector: ", chooser);					//creates a menu of autonomii
		chooser.addObject("Left", 1);
		chooser.addObject("Center", 2);
		chooser.addObject("Right", 3);
		chooser.addObject("Auto Line Only", 4);
		chooser.addDefault("None", 0);
	}

	//This function is run once before autonomousPeriodic() begins
	@Override
	public void autonomousInit() {
		
		//What alliance r we?
		if(alliance == DriverStation.Alliance.kBlue))
			isRed = false;
		else
			isRed = true;
																					
		auto = chooser.getSelected();													//pick an auto to run
		
		//timer gets prepared to do nothing
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		//do different things depending on the auto that was chosen to run
		switch (auto) {
		
			case 0: //default autonomous or no autonomous FINISHED
				break;
				
			case 1: //left starting position
				if(isRed) {
					
				}
				else {
					
				}
				break;
			
			case 2: //center starting position
				if(isRed) {
					
				}
				else {
					
				}
				break;
			
			case 3: //right starting position
				if (isRed) {
					
				}
				else {
					
				}
				break;
				
			case 4: //just drive forward
				break;
				
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//Currently dead cause we dk what controller is gotta get used
		//speed = joy.getRawAxis(1);
		//rotation = joy.getRawAxis(4);
		//drive.arcadeDrive(speed, rotation);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

