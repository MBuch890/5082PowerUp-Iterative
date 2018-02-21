package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RobotBase {
	
	//VARIABLES
	
	final int WHEEL_DIAMETER_IN = 6;													//For calculating with encoders
	final int PULSE_PER_REVOLUTION = 256;									    		//For calculating with encoders

	//for PID control (later)
		public double kDriveP = 0, kTurnP = 0;
		public double kDriveI = 0, kTurnI = 0;
		public double kDriveD = 0, kTurnD = 0;
		public double driveIntegral = 0, turnIntegral = 0;
		public double previousDriveError = 0, previousTurnError = 0;
		
	double previousVal = 0;
	
	//For auto modes, making things more readable
	final int DEFAULT = 0;
	final int LSWITCH = 1;
	final int CSWITCH = 2;
	final int RSWITCH = 3;
	final int AUTOLINE = 4;
	
	Compressor compressor;
	
	Solenoid sClamPush;
	DoubleSolenoid sClamp;
	DoubleSolenoid sRamp, sTrans;
	Spark mTopLeft, mMidLeft, mBackLeft, mTopRight, mMidRight, mBackRight, mSpool;		//motors
	DifferentialDrive topCims, midCims, bottomCims;										//drive base w all drive motors
	
	//Encoder encoder;																	//measuring the distance driven
	//Gyro gyro;																			//measuring the angle turned
	Accelerometer accel;
	Timer timer;
	
	//whenever someone instantiates rb do all that automatically
	public RobotBase () {
		
		//instantiate encoder
		//encoder = new Encoder(0, 1);
		//gyro = new ADXRS450_Gyro();
		compressor = new Compressor(1);
		//accel = new ADXL362(Accelerometer.Range.k4G);
		
		timer = new Timer();
		
		//create instances of motors
		mTopLeft = new Spark(0);
		mMidLeft = new Spark(1);
		mBackRight = new Spark(9);
		mTopRight = new Spark(3);
		mBackLeft = new Spark(2);
		mMidRight = new Spark(4);
		
		mSpool = new Spark(8);
		
		sRamp = new DoubleSolenoid(0, 1);
		sTrans = new DoubleSolenoid(2, 3);
		sClamp = new DoubleSolenoid(4, 5);
		sClamPush = new Solenoid(7);
		
		//setting the multiplier used for getDistance();
		//encoder.setDistancePerPulse(PULSE_PER_REVOLUTION / (WHEEL_DIAMETER_IN * Math.PI));
		
		//setup drive base
		topCims = new DifferentialDrive(mTopLeft,mTopRight);
		midCims = new DifferentialDrive(mMidLeft, mMidRight);
		bottomCims = new DifferentialDrive(mBackLeft, mBackRight);
	}
	
	public void driveAuto (double power) {
		this.mTopLeft.set(-power);
		this.mTopRight.set(power);
		this.mMidLeft.set(-power);
		this.mMidRight.set(power);
		this.mBackLeft.set(-power);
		this.mBackRight.set(power);
	}
	
	public void setClamPush (boolean forward) {
		this.sClamPush.set(forward);
	}
	
	public void setTrans (boolean forward, boolean off) {
		if (!off) {
			if (forward)
				this.sTrans.set(DoubleSolenoid.Value.kForward);
			else
				this.sTrans.set(DoubleSolenoid.Value.kReverse);
		}
		else {
			this.sTrans.set(DoubleSolenoid.Value.kOff);
		}
	}
	
	public void setClamp (boolean forward, boolean off) {
		if (!off) {
			if (forward)
				this.sClamp.set(DoubleSolenoid.Value.kForward);
			else
				this.sClamp.set(DoubleSolenoid.Value.kReverse);
		}
		else {
			this.sClamp.set(DoubleSolenoid.Value.kOff);
		}
	}
	
	/*public double turnPID (double heading) {
		double error = heading - this.getAngle();
		this.turnIntegral += error * 0.02;
		double derivative = (error - previousDriveError) * 0.02;
		this.previousTurnError = error;
		return (this.kTurnP * error) + (this.kTurnI * this.turnIntegral) + (this.kTurnD * derivative);
	}
	
	public double drivePID (double dist) {
		double error = dist - this.encoder.getDistance();
		this.driveIntegral += error * 0.02;
		double derivative = (error - this.previousDriveError) * 0.02;
		this.previousDriveError = error;
		return (this.kDriveP * error) + (this.kDriveI * this.driveIntegral) + (this.kDriveD * derivative);
	}*/
	
	public void setRamp (boolean forward, boolean off) {
		if (off) {
			this.sRamp.set(DoubleSolenoid.Value.kOff);
		}
		else {
			if (forward)
				this.sRamp.set(DoubleSolenoid.Value.kForward);
			else
				this.sRamp.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void setSpool(double power) {
		this.mSpool.set(power);
	}
	
	/*public void resetEncoder() {
		this.encoder.reset();
	}
	
	public void resetGyro() {
		this.gyro.reset();
	}*/
	
	public void setClosedLoopControl() {
		this.compressor.setClosedLoopControl(true);
	}
	
	/*public double getAngle() {
		
		this.gyro.reset();
		double gyroData = this.gyro.getAngle();
		double accData = this.accel.getZ();
		
		double result = 0.98 * (previousVal + (gyroData * 0.05)) + (0.02 * accData);
		result = previousVal;
		
		return result;
	}*/
	
	public void finishAction() {
		/*kDriveP = 1;
		kDriveI = 1;
		kD = 1;*/
		this.driveAuto(0);
		//this.encoder.reset();
		//this.gyro.reset();
	}
	
	public void runSpool(double pwr) { mSpool.set(pwr); }
	
	public void setSafetyEnabled(boolean enabled) {topCims.setSafetyEnabled(enabled); midCims.setSafetyEnabled(enabled); bottomCims.setSafetyEnabled(enabled);}
	
	public void tankDrive (double left, double right) {topCims.tankDrive(left, right); midCims.tankDrive(left, right); bottomCims.tankDrive(left, right); }
	
	public void arcadeDrive(double forwardPwr, double turnPwr) {topCims.arcadeDrive(forwardPwr, turnPwr); midCims.arcadeDrive(forwardPwr, turnPwr); bottomCims.arcadeDrive(forwardPwr, turnPwr);}
}
