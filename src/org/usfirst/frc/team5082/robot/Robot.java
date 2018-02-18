package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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

/* TODO (X for needs done before season over, O for whenever)
 *    finish testing clamp 						X				(ready to go when bot is available)
 *    integrate gyro & accel					X				(code needs written)
 *    finish elevator testing					X				(blocked by mechanical)
 *    write out and test autos					X				(code needs written, blocked by mechanical to test)
 *    test new controls							X				(ready to go when bot is available)
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
	boolean justOpenedClamp, justPressedA;
	double time, timeRamp;
	
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
		rb.setSafetyEnabled(false);
		
		rb.compressor.setClosedLoopControl(true);
		
		justOpenedClamp = false;
		justPressedA = false;
		timeRamp = 0;
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
		
		//what is the way things are set up (like the randomized plates)
		//String orientation = (DS.getGameSpecificMessage()).substring(0, 1);
		
		//run auto only while the ds is on
		if(DS.isEnabled()) auton.autoPeriodic(autoChooser, "L");
		else {
			rb.arcadeDrive(0, 0);
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
			SmartDashboard.putNumber("Gyro Orientation: ", rb.gyro.getAngle());
			SmartDashboard.putNumber("Accel X value: ", rb.accel.getX());
			SmartDashboard.putNumber("Accel Y value: ", rb.accel.getY());
			SmartDashboard.putNumber("Accel Z value: ", rb.accel.getZ());
			
			if (joy.getRawAxis(5) > 0.5)
				System.out.println("Joy Raw Axis 5");
			if (joy.getRawAxis(6) > 0.5)
				System.out.println("Joy Raw Axis 6");
			
			//DRIVE
			rb.arcadeDrive(joy.getRawAxis(1), -joy.getRawAxis(4));
			
			//ELEVATOR
			if (joy.getRawButton(3)) {							
				rb.mSpool.set(1);
				System.out.println("Power on");
			}
			else if (joy.getRawButton(4)) {					
				rb.mSpool.set(-0.15);
				System.out.println("Power on");
			}
			else {
				rb.mSpool.set(0);
				System.out.println("Power off");
			}
			
			//RAMP - LB and RB
			if (joy.getRawButton(5) && joy.getRawButton(6) && timeRamp == 0) {
				timeRamp = System.currentTimeMillis();
			}
			else if (joy.getRawButton(5) && joy.getRawButton(6) && (System.currentTimeMillis() - timeRamp) >= 1000)
				rb.sRamp.set(DoubleSolenoid.Value.kForward);
			
			//CLAMP
			if (joy.getRawButton(1) && !justPressedA) {					
				rb.sClamp.set(DoubleSolenoid.Value.kForward);
				justOpenedClamp = true;
				//justPressedA = true;
				//time = System.currentTimeMillis();
			}
			else if (joy.getRawButton(2) /*&& (System.currentTimeMillis() - time) >= 200 && justPressedA*/) {						
				rb.sClamp.set(DoubleSolenoid.Value.kReverse);
				justOpenedClamp = false;
				justPressedA = false;
			}
			if (justOpenedClamp && (System.currentTimeMillis() - time) >= 100) {
				rb.sClamPush.set(true);
			}
			else {
				rb.sClamPush.set(false);
			}
		
		}
		else {
			rb.arcadeDrive(0, 0);
			rb.sClamp.set(DoubleSolenoid.Value.kOff);
			rb.mSpool.set(0);
			rb.sRamp.set(DoubleSolenoid.Value.kOff);
			rb.sClamPush.set(false);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}

