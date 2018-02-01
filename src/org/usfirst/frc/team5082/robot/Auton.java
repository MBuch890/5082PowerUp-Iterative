package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.Timer;

public class Auton {
	
	private Timer autoTimer;
	private RobotBase rb;
	
	private boolean didDrove = false, didTurn = false, didDump = false;
	private int actioNum = 0;
	
	public Auton () {
		autoTimer = new Timer();
	}
	
	public void autoPeriodic(int orientation, String plateOrient) {
		
		if (orientation == rb.DEFAULT) {
		
		} else if (orientation == rb.LSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (!didDrove && actioNum == 0 && rb.encoder.getDistance() < 12) {
					rb.drive.arcadeDrive(0.75, 0); //replace the 0 with a gyro based value later
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1 && rb.gyro.getAngle() < 90) {
					//TODO gyro based turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					actioNum++;
				}
				
				if (didTurn && !didDump && actioNum == 2 /*condition based on ??*/) {
					//TODO dump cube in based on ??
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				if (!didDrove && actioNum == 0 /*&& condition*/) {
					//drive
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
					//turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
					//drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
					//turn again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
					//drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
					//dump cube
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		
		} else if (orientation == rb.CSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (!didDrove && actioNum == 0 /*&& condition*/) {
					//TODO drive
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
					//TODO turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
					//TODO drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
					//TODO turn again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
					//TODO drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
					//TODO dump cube
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				if (!didDrove && actioNum == 0 /*&& condition*/) {
					//drive
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
					//turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
					//drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
					//turn again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
					//drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
					//dump cube
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		
		} else if (orientation == rb.RSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (!didDrove && actioNum == 0 /*&& condition*/) {
					//TODO drive
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1/*&& condition*/) {
					//TODO turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 2/*&& condition*/) {
					//TODO drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					didTurn = false;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 3 /*&& condition*/) {
					//TODO turn again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					didDrove = false;
					actioNum++;
				}
				
				if (didTurn && !didDrove && actioNum == 4 /*&& condition*/) {
					//TODO drive again
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if(didDrove && !didDump && actioNum == 5 /*&& condition*/) {
					//TODO dump cube
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				if (!didDrove && actioNum == 0/*&& condition*/) {
					//TODO drive
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
					actioNum++;
				}
				
				if (didDrove && !didTurn && actioNum == 1 /*&& condition*/) {
					//TODO turn
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didTurn = true;
					actioNum++;
				}
				
				if (didTurn && !didDump && actioNum == 2/*&& condition*/) {
					//TODO dump cube in
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDump = true;
					actioNum++;
				}
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		
		} else if (orientation == rb.AUTOLINE) {
		}
		
	}

}
