package org.usfirst.frc.team5082.robot;

import edu.wpi.first.wpilibj.Timer;

public class Auton {
	
	private Timer autoTimer;
	private RobotBase rb;
	
	private boolean didDrove = false;
	
	public Auton () {
		autoTimer = new Timer();
	}
	
	public void autoPeriodic(int orientation, String plateOrient) {
		
		if (orientation == rb.DEFAULT) {
		} else if (orientation == rb.LSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (autoTimer.get() < 2 && didDrove == false) {
					rb.drive.arcadeDrive(1, 0);
				}
				else {
					rb.drive.arcadeDrive(0, 0);
					didDrove = true;
				}
				
				if (autoTimer.get() < 2 && didDrove == true) {
					rb.drive.arcadeDrive(0, .5);
				}
				//TODO more & better
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				//TODO: drive around to other side and dump a cube
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		} else if (orientation == rb.CSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (autoTimer.get() < 2) {
					rb.drive.arcadeDrive(1, 0);
				}
				else {
					rb.drive.arcadeDrive(0, 0);
				}
				
				//TODO more & better
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				//TODO
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		} else if (orientation == rb.RSWITCH) {
			if (plateOrient.equalsIgnoreCase("L")) {
				
				if (autoTimer.get() < 2) {
					rb.drive.arcadeDrive(1, 0);
				}
				else {
					rb.drive.arcadeDrive(0, 0);
				}
				
				//TODO more & better
			}
			else if (plateOrient.equalsIgnoreCase("R")) {
				//TODO
			}
			else {
				System.out.println("someone done diddly messed up my girl");
			}
		} else if (orientation == rb.AUTOLINE) {
		}
		
	}

}
