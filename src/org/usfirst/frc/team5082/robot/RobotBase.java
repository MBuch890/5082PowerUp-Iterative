package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RobotBase {
	
	//VARIABLES
	
	final int WHEEL_DIAMETER_IN = 6;													//For calculating with encoders
	final int PULSE_PER_REVOLUTION = 256;									    		//For calculating with encoders

	//for PID control (later)
		public double kP = 1;
		public double kI = 1;
		public double kD = 1;
	
	//For auto modes, making things more readable
	final int DEFAULT = 0;
	final int LSWITCH = 1;
	final int CSWITCH = 2;
	final int RSWITCH = 3;
	final int AUTOLINE = 4;
	
	Compressor compressor;
	
	Solenoid sClamPush;
	DoubleSolenoid sClamp;
	DoubleSolenoid sRamp;
	Spark mTopLeft, mMidLeft, mBackLeft, mTopRight, mMidRight, mBackRight, mSpool;		//motors
	DifferentialDrive topCims, midCims, bottomCims;										//drive base w all drive motors
	
	Encoder encoder;																	//measuring the distance driven
	Gyro gyro;																			//measuring the angle turned
	Accelerometer accel;
	
	//whenever someone instantiates rb do all that automatically
	public RobotBase () {
		
		//instantiate encoder
		encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		gyro = new ADXRS450_Gyro();
		compressor = new Compressor(1);
		accel = new ADXL362(Accelerometer.Range.k4G);
		
		//create instances of motors
		mTopLeft = new Spark(0);
		mMidLeft = new Spark(1);
		mBackRight = new Spark(9);
		mTopRight = new Spark(3);
		mBackLeft = new Spark(2);
		mMidRight = new Spark(4);
		
		mSpool = new Spark(8);
		
		sRamp = new DoubleSolenoid(0, 1);
		sClamp = new DoubleSolenoid(4, 5);
		sClamPush = new Solenoid(7);
		
		//setting the multiplier used for getDistance();
		encoder.setDistancePerPulse(PULSE_PER_REVOLUTION / (WHEEL_DIAMETER_IN * Math.PI));
		
		//setup drive base
		topCims = new DifferentialDrive(mTopLeft,mTopRight);
		midCims = new DifferentialDrive(mMidLeft, mMidRight);
		bottomCims = new DifferentialDrive(mBackLeft, mBackRight);
	}
	
	public void finishAction() {
		kP = 1;
		kI = 1;
		kD = 1;
		this.arcadeDrive(0,0);
		this.encoder.reset();
		this.gyro.reset();
	}
	
	public void runSpool(double pwr) { mSpool.set(pwr); }
	
	public void setSafetyEnabled(boolean enabled) {topCims.setSafetyEnabled(enabled); midCims.setSafetyEnabled(enabled); bottomCims.setSafetyEnabled(enabled);}
	
	public void arcadeDrive(double forwardPwr, double turnPwr) {topCims.arcadeDrive(forwardPwr, turnPwr); midCims.arcadeDrive(forwardPwr, turnPwr); bottomCims.arcadeDrive(forwardPwr, turnPwr);}
}
