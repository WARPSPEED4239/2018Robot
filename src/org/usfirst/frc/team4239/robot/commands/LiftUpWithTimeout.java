package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

public class LiftUpWithTimeout extends Command {
	private double mTimeout;

	public LiftUpWithTimeout(double timeout) {
		requires(Robot.lift);
		mTimeout = timeout;
	}

	protected void initialize() {
		setTimeout(mTimeout);
		Robot.rgbController.setColor(Color.White);
	}

	protected void execute() {
		Robot.lift.liftUp();
	}

	protected boolean isFinished() {
		return Robot.lift.getTopSwitch() || isTimedOut();
	}

	protected void end() {
		Robot.lift.liftStop();
		Robot.rgbController.setColor(Color.Red);
	}

	protected void interrupted() {
	}
}