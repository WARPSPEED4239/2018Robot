package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainLowGear extends Command {

    public DrivetrainLowGear() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.drivetrainLowGear();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}