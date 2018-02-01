package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RobotBase {
	
	//VARIABLES
	//0 is being used as a placeholder for port #s
	
	final int WHEEL_DIAMETER_IN = 6;													//For calculating with encoders
	final int PULSE_PER_REVOLUTION = 256;									    		//For calculating with encoders

	//For auto modes, making things more readable
	final int DEFAULT = 0;
	final int LSWITCH = 1;
	final int CSWITCH = 2;
	final int RSWITCH = 3;
	final int AUTOLINE = 4;
	
	Spark mTopLeft, mMidLeft, mBackLeft, mTopRight, mMidRight, mBackRight;				//drive motors
	SpeedControllerGroup left, right;
	DifferentialDrive drive;															//drive base w all drive motors
	
	Encoder encoder;																	//measuring the distance driven
	Gyro gyro;																			//measuring the angle turned
	
	public RobotBase () {
		
		encoder = new Encoder(0, 1);
		
		mTopLeft = new Spark(1);
		mMidLeft = new Spark(6);
		mBackLeft = new Spark(3);
		mTopRight = new Spark(5);
		mMidRight = new Spark(2);
		mBackRight = new Spark(4);
		
		//setting the multiplier used for getDistance();
		encoder.setDistancePerPulse(PULSE_PER_REVOLUTION / (WHEEL_DIAMETER_IN * Math.PI));
		
		drive = new DifferentialDrive(left, right);
		
		drive.setSafetyEnabled(true);
		
	}
}
