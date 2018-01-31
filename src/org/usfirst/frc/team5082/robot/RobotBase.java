package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public abstract class RobotBase {
	
	//VARIABLES
	//0 is being used as a placeholder for port #s
	
	final int WHEEL_DIAMETER = 0;														//For calculating with encoders. Don't know which wheel will be encoded
	final int PULSE_PER_REVOLUTION = 360;									    		//For calculating with encoders

	//For auto modes, making things more readable
	final int DEFAULT = 0;
	final int LSWITCH = 1;
	final int CSWITCH = 2;
	final int RSWITCH = 3;
	final int AUTOLINE = 4;
	
	Spark mTopLeft, mMidLeft, mBackLeft, mTopRight, mMidRight, mBackRight;				//drive motors
	DifferentialDrive drive;															//drive base w all drive motors
	SpeedControllerGroup left, right;													//grouping motors by side
	
	Timer timer;																		//game timer	
	Encoder encoder;																	//measuring the distance driven
	Gyro gyro;																			//measuring the angle turned
	
	public void init() {
		
		timer = new Timer();
		encoder = new Encoder(0, 0, false);
		
		mTopLeft = new Spark(1);
		mMidLeft = new Spark(6);
		mBackLeft = new Spark(3);
		mTopRight = new Spark(5);
		mMidRight = new Spark(2);
		mBackRight = new Spark(4);
		
		left = new SpeedControllerGroup(mTopLeft, mMidLeft, mBackLeft);
		right = new SpeedControllerGroup(mTopRight, mMidRight, mBackRight);
		drive = new DifferentialDrive(left, right);
		
		encoder.setDistancePerPulse(Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION);	//set multiplier for getDistance()
		
		drive.setSafetyEnabled(true);													//presumably this is useful, but we're not sure yet

	}
}
