package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainCalculateTrackWidth extends Command {

	private int mTimesToSpin;
	private double mTrackWidth;

	public DrivetrainCalculateTrackWidth(int timesToSpin) {
		requires(Robot.drivetrain);
		mTimesToSpin = timesToSpin;
	}

	protected void initialize() {
		Robot.drivetrain.setIsAuto(false);
		Robot.drivetrain.resetSensors();
	}

	protected void execute() {
		XboxController controller = Robot.oi.xbox;
		double move = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
    	double rotate = -controller.getX(Hand.kLeft);
    	Robot.drivetrain.arcadeDrive(move, rotate);

		mTrackWidth = (Math.abs(Robot.drivetrain.getLeftDistance()) + Math.abs(Robot.drivetrain.getRightDistance()))
				/ (2 * Math.PI * mTimesToSpin);
		SmartDashboard.putNumber("TrackWidth", mTrackWidth);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
