package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeInWithTimeout extends Command {
	private double mTimeout;

	public IntakeInWithTimeout(double timeout) {
		requires(Robot.intake);
		mTimeout = timeout;
	}

	protected void initialize() {
		setTimeout(mTimeout);
		Color[] colors = {Color.PurpleDim, Color.Black};
    	Robot.rgbController.setColors(colors, 0.5);
	}

	protected void execute() {
		Robot.intake.intakeIn();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.intake.intakeStop();
		Robot.rgbController.setColor(Color.Red);
	}

	protected void interrupted() {
	}
}
