package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDownWithTimeout extends Command {
	private double mTimeout;

	public LiftDownWithTimeout(double timeout) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		mTimeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(mTimeout);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.liftDown();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.lift.getBottomSwitch() || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.liftStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}