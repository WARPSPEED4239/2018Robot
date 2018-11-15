package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonIntakeOutWithTimeoutScale extends Command {

	private double mTimeout;

	public AutonIntakeOutWithTimeoutScale(double timeout) {
		requires(Robot.intake);
		mTimeout = timeout;
	}

	protected void initialize() {
		setTimeout(mTimeout);
		Robot.rgbController.setColor(Color.Purple);
	}

	protected void execute() {
		Robot.intake.autonIntakeOutScale();
		}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.rgbController.setColor(Color.Red);
		Robot.intake.intakeStop();
	}

	protected void interrupted() {
	}
}