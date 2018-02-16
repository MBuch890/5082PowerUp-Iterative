package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RobotBase {
	
	//VARIABLES
	
	final int WHEEL_DIAMETER_IN = 6;													//For calculating with encoders
	final int PULSE_PER_REVOLUTION = 256;									    		//For calculating with encoders

	//For auto modes, making things more readable
	final int DEFAULT = 0;
	final int LSWITCH = 1;
	final int CSWITCH = 2;
	final int RSWITCH = 3;
	final int AUTOLINE = 4;
	
	Compressor compressor;
	
	DoubleSolenoid sClamp;
	Spark mTopLeft, mMidLeft, mBackLeft, mTopRight, mMidRight, mBackRight, mSpool;		//motors
	DifferentialDrive topCims, midCims, bottomCims;										//drive base w all drive motors
	
	Encoder encoder;																	//measuring the distance driven
	Gyro gyro;																			//measuring the angle turned
	
	//DoubleSolenoid ramp1 = new DoubleSolenoid(1,0);
	//DoubleSolenoid ramp2 = new DoubleSolenoid(3,2);
	
	//whenever someone instantiates rb do all that automatically
	public RobotBase () {
		
		//instantiate encoder
		encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		gyro = new ADXRS450_Gyro();
		compressor = new Compressor(1);
		
		//create instances of motors
		mTopLeft = new Spark(0);
		mMidLeft = new Spark(1);
		mBackRight = new Spark(9);
		mTopRight = new Spark(3);
		mBackLeft = new Spark(2);
		mMidRight = new Spark(4);
		
		mSpool = new Spark(8);
		
		sClamp = new DoubleSolenoid(4, 5);
		
		//setting the multiplier used for getDistance();
		encoder.setDistancePerPulse(PULSE_PER_REVOLUTION / (WHEEL_DIAMETER_IN * Math.PI));
		
		//setup drive base
		topCims = new DifferentialDrive(mTopLeft,mTopRight);
		midCims = new DifferentialDrive(mMidLeft, mMidRight);
		bottomCims = new DifferentialDrive(mBackLeft, mBackRight);
		
	}
	
	public void runSpool(double pwr) { mSpool.set(pwr); }
	
	public void setSafetyEnabled(boolean enabled) {topCims.setSafetyEnabled(enabled); midCims.setSafetyEnabled(enabled); bottomCims.setSafetyEnabled(enabled);}
	
	public void arcadeDrive(double forwardPwr, double turnPwr) {topCims.arcadeDrive(forwardPwr, turnPwr); midCims.arcadeDrive(forwardPwr, turnPwr); bottomCims.arcadeDrive(forwardPwr, turnPwr);}
}
