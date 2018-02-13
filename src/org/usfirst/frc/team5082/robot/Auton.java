package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auton {
	
	//create instance of the rb
	private RobotBase rb;
	
	//make the vars you need to jump thru ifs
	private boolean didDrove = false, didTurn = false, didDump = false;
	private int actioNum = 0;
	
	//whenever auton is instantiated auto reset encoder
	public Auton () {
		rb = new RobotBase();
		//rb.encoder.reset();
	}
	
	//for use in Robot.autoPeriodic() to make it less ugly/put it someplace else
	public void autoPeriodic(int orientation, String plateOrient) {
		
		//IF YOU JUST RUN THE 'DEFAULT'
		if (orientation == rb.DEFAULT) {
			//...nothing will happen
		} 
		//RUNNING LEFT SIDE STARTING POSITION
		else if (orientation == rb.LSWITCH) {
			//If our alliance color on our switch is on the LEFT
			if (plateOrient.equalsIgnoreCase("L") && true) {
				
				SmartDashboard.putString("Running: ", "Left starting, Left side switch");
				
				//Drive forward 1 foot (UNTESTED)
				/*if (!didDrove && actioNum == 0 && rb.encoder.getDistance() < 48) {
					rb.arcadeDrive(0.75, 0); //replace the 0 with a gyro based value later, add PID
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
					System.out.println("Drive complete");
				}*/
				
				//Turn 90 degrees (UNTESTED)
				if (didDrove && !didTurn && actioNum == 1 && rb.gyro.getAngle() < 90) {
					//TODO gyro based turn with PID
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					actioNum++;
					System.out.println("Turn complete");
				}
				
				//Dump cube in switch (TODO)
				if (didTurn && !didDump && actioNum == 2 /*condition based on ??*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			//If our alliance color on our switch is on the RIGHT
			else if (plateOrient.equalsIgnoreCase("R")) {
				
				SmartDashboard.putString("Running: ", "Left starting, Right side switch");
				
				//Drive forward X ft (TODO)
				if (!didDrove && actioNum == 0 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Dump cube in switch (TODO)
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
		//RUNNING CENTER STARTING POSITION
		} else if (orientation == rb.CSWITCH) {
			//If our alliance color on our switch is on the LEFT
			if (plateOrient.equalsIgnoreCase("L")) {
				
				SmartDashboard.putString("Running: ", "Center starting, Left side switch");
				
				//Drive X ft (TODO)
				if (!didDrove && actioNum == 0 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				// Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Dump cube (TODO)
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			//If our alliance color for our switch is on the RIGHT
			else if (plateOrient.equalsIgnoreCase("R")) {
				
				SmartDashboard.putString("Running: ", "Center starting, Right side switch");
				
				//Drive X ft (TODO)
				if (!didDrove && actioNum == 0 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Turn X degrees
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Dump the cube
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
		//RUNNING RIGHT SIDE STARTING POSITION
		} else if (orientation == rb.RSWITCH) {
			//If our alliance color for our switch is on the LEFT
			if (plateOrient.equalsIgnoreCase("L")) {
				
				SmartDashboard.putString("Running: ", "Right starting, Left side switch");
				
				//Drive X ft (TODO)
				if (!didDrove && actioNum == 0 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
					//rb.encoder.reset();
				}
				
				//Drive X ft (TODO)
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Dump cube
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			//If our alliance color for our switch is on the RIGHT
			else if (plateOrient.equalsIgnoreCase("R")) {
				
				SmartDashboard.putString("Running: ", "Right starting, Right side switch");
				
				//Drive X ft (TODO)
				if (!didDrove && actioNum == 0/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				//Turn X degrees (TODO)
				if (didDrove && !didTurn && actioNum == 1 /*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didTurn = true;
					actioNum++;
				}
				
				//Dump cube (TODO)
				if (didTurn && !didDump && actioNum == 2/*&& condition*/) {
				}
				//Setup for next action
				else {
					rb.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
		//STARTING POSITION NULL, WE ONLY DO AUTO LINE
		} else if (orientation == rb.AUTOLINE) {
			
			SmartDashboard.putString("Running: ", "Auto Line Only");
			
			if (rb.encoder.getDistance() < 120) {
				rb.arcadeDrive(0.75, 0);
			}
			
		}
		
	}

}
