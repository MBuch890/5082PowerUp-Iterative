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
	int autoChooser, actioNum = 0;																	//communicates what auto got chose, pt 2
	boolean aIsReleased, clamp, justOpenedClamp, yIsReleased, trans, didDrove = false, didTurn = false;
	double time, timeRamp, forPower, turnPower, startTime, currentTime;
	
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
		
		rb.setClosedLoopControl();
		rb.setRamp(false, false);
		
		clamp = false;
		aIsReleased = true;
		trans = false;
		yIsReleased = true;
		timeRamp = 0;
	}

	//This function is run once before autonomousPeriodic() begins
	@Override
	public void autonomousInit() {
		autoChooser = chooser.getSelected();
		startTime = System.currentTimeMillis();
		rb.setClamp(false, true);
		rb.setRamp(true, false);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		//what is the way things are set up (like the randomized plates)
		//String orientation = (DS.getGameSpecificMessage()).substring(0, 1);
		//orientation = "L";
		
		//run auto only while the ds is on
		if(DS.isEnabled()) {//IF YOU JUST RUN THE 'DEFAULT' {
				currentTime = System.currentTimeMillis();
				
				if (Math.abs(currentTime - startTime) > 5000 && Math.abs(currentTime - startTime) < 5500) {
					rb.setTrans(false, false);
					rb.setClamp(true, false);
				}
				//Drive forward 2 secs
				if (Math.abs(currentTime - startTime) > 6000 && Math.abs(currentTime - startTime) < 8000) {
					rb.driveAuto(0.75); 
					System.out.println("Driving");				//THIS GOES 6 FT front to back
				}
				//Setup for next action
				else {
					rb.finishAction();
					didDrove = true;
					actioNum++;
					System.out.println("Drive complete");
				}
			//RUNNING LEFT SIDE STARTING POSITION
			/*else if (autoChooser == rb.LSWITCH) {
				//If our alliance color on our switch is on the LEFT
				if (orientation.equalsIgnoreCase("L") || true) {
					
					SmartDashboard.putString("Running: ", "Left starting, Left side switch");
					currentTime = System.currentTimeMillis();
					
					//Drive forward 2 secs (UNTESTED)
					if (!didDrove && actioNum == 0 && (currentTime - startTime) > 2000) {
						rb.driveAuto(0.75); 
					}
					//Setup for next action
					else {
						rb.finishAction();
						didDrove = true;
						actioNum++;
						System.out.println("Drive complete");
					}
					
					//Turn 90 degrees (UNTESTED)
					if (didDrove && !didTurn && actioNum == 1 /*&& rb.getAngle() < 90) {
						/*turnPower = rb.turnPID(90);
						if (turnPower > 1)
							turnPower = 1;
						rb.arcadeDrive(0, turnPower);*/
					/*}
					//Setup for next action
					else {
						rb.finishAction();
						didTurn = true;
						actioNum++;
						System.out.println("Turn complete");
					}
				}*/}
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
			//SmartDashboard.putNumber("Encoder Distance: ", rb.encoder.getDistance());
			SmartDashboard.putNumber("Match Timer: ", 135 - timer.get());
			//SmartDashboard.putNumber("Gyro Orientation: ", rb.getAngle());
			//SmartDashboard.putNumber("Accel X value: ", rb.accel.getX());
			//SmartDashboard.putNumber("Accel Y value: ", rb.accel.getY());
			//SmartDashboard.putNumber("Accel Z value: ", rb.accel.getZ());
			
			//DRIVE
			forPower = joy.getRawAxis(1);											//joystick 1
			turnPower = -joy.getRawAxis(4);											//joystick 2
			
			rb.arcadeDrive(forPower, turnPower);
			
			//ELEVATOR
			if (joy.getRawAxis(3) < -0.2 || joy.getRawButton(2)) {					//left trigger or B button					
				rb.setSpool(-1);
				System.out.println("Power on");
			}
			else if (joy.getRawButton(3))
				rb.setSpool(0.5);
			else {
				rb.setSpool(0);
				System.out.println("Power off");
			}
			
			//RAMP
			if (joy.getRawButton(5) && joy.getRawButton(6) && timeRamp == 0) {		//LB and RB
				timeRamp = System.currentTimeMillis();
			}
			else if ((joy.getRawButton(5) && joy.getRawButton(6) && (System.currentTimeMillis() - timeRamp) >= 1000))
				rb.setRamp(false, false);											//LB and RB for 1+ secs
			else if (joy.getRawButton(8)){
				rb.setRamp(true, false);
			}
			
			//TRANSMISSION SHIFT
			if (joy.getRawButton(4)) {
				if (yIsReleased) {
					yIsReleased = false;
					if (!trans)
						trans = true;
					else
						trans = false;
				}
			}
			else {
				yIsReleased = true;
			}
			
			if (trans)
				rb.setTrans(true, false);
			else
				rb.setTrans(false, false);
			
			//CLAMP
			if (joy.getRawButton(1)) {												//A button toggle
				if (aIsReleased) {
					aIsReleased = false;
					if (!clamp)
						clamp = true;
					else if (clamp)
						clamp = false;
				}
			}
			else {
				aIsReleased = true;
			}
			
			if (clamp) {
				rb.setClamp(true, false);
				time = System.currentTimeMillis();
				justOpenedClamp = true;
			}
			else {
				rb.setClamp(false, false);
			}
			
			if(justOpenedClamp && (System.currentTimeMillis() - time) >= 100) {
				rb.setClamPush(true);
			}
			else {
				rb.setClamPush(false);
			}
			
			//CLAMP
			/*if (joy.getRawButton(1) && !justPressedA) {					
				rb.sClamp.set(DoubleSolenoid.Value.kForward);
				justOpenedClamp = true;
				//justPressedA = true;
				//time = System.currentTimeMillis();
			}
			else if (joy.getRawButton(2) /*&& (System.currentTimeMillis() - time) >= 200 && justPressedA*//*) {						
				rb.sClamp.set(DoubleSolenoid.Value.kReverse);
				justOpenedClamp = false;
				justPressedA = false;
			}
			if (justOpenedClamp && (System.currentTimeMillis() - time) >= 100) {
				rb.sClamPush.set(true);
			}
			else {
				rb.sClamPush.set(false);
			}*/
			
			if (joy.getRawButton(7))												//back button
				rb.setClamPush(true);
			else
				rb.setClamPush(false);
		
		}
		else {
			rb.arcadeDrive(0, 0);
			rb.setClamp(false, true);
			rb.setSpool(0);
			rb.setRamp(false, true);
			rb.setClamPush(false);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}

