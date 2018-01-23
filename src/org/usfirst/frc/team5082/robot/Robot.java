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
	
	//0 is being used as a placeholder for port #s
	
	Spark mFrontLeft, mMidLeft, mBackLeft, mFrontRight, mMidRight, mBackRight;
	DifferentialDrive drive;
	SpeedControllerGroup left, right;
	Joystick joy;
	Timer timer;
	
	SendableChooser<Integer> chooser = new SendableChooser<Integer>();
	
	double speed, rotation;
	int auto;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		timer = new Timer();
		joy = new Joystick(0);
		mFrontLeft = new Spark(0);
		mMidLeft = new Spark(0);
		mBackLeft = new Spark(0);
		mFrontRight = new Spark(0);
		mMidRight = new Spark(0);
		mBackRight = new Spark(0);
		
		left = new SpeedControllerGroup(mFrontLeft, mMidLeft, mBackLeft);
		right = new SpeedControllerGroup(mFrontRight, mMidRight, mBackRight);
		drive = new DifferentialDrive(left, right);
		
		auto = 4;
		
		drive.setSafetyEnabled(true);
		
		SmartDashboard.putData("Autonomous Mode Selector: ", chooser);
    	chooser.addDefault("Left", 1);
    	chooser.addObject("Center", 2);
    	chooser.addObject("Right", 3);
    	chooser.addObject("Auto Line Only", 4);
    	chooser.addObject("None", 5);
	}

	//This function is run once before autonomousPeriodic() begins
	@Override
	public void autonomousInit() {
		auto = chooser.getSelected();
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		speed = joy.getRawAxis(1);
		rotation = joy.getRawAxis(4);
		
		drive.arcadeDrive(speed, rotation);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

