package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDownWithTimeout extends Command {
	private double mTimeout;

	public LiftDownWithTimeout(double timeout) {
		requires(Robot.lift);
		mTimeout = timeout;
	}

	protected void initialize() {
		setTimeout(mTimeout);
		Color[] colors = {Color.Red, Color.Black};
    	Robot.rgbController.setColors(colors, 0.5);
	}

	protected void execute() {
		Robot.lift.liftDown();
	}

	protected boolean isFinished() {
		return Robot.lift.getBottomSwitch() || isTimedOut();
	}

	protected void end() {
		Robot.lift.liftStop();
		Robot.rgbController.setColor(Color.Red);
	}

	protected void interrupted() {
	}
}