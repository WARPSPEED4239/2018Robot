package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUp extends Command {
	public LiftUp() {
		requires(Robot.lift);
	}

	protected void initialize() {
		Robot.rgbController.setColor(Color.White);
	}

	protected void execute() {
		Robot.lift.liftUp();
	}

	protected boolean isFinished() {
		return Robot.lift.getTopSwitch();
	}

	protected void end() {
		Robot.lift.liftStop();
		Robot.rgbController.setColor(Color.Red);
	}

	protected void interrupted() {
	}
}