package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainLowGear extends Command {

    public DrivetrainLowGear() {
        requires(Robot.drivetrainShifting);
    }

    protected void initialize() {
    	Robot.drivetrainShifting.drivetrainLowGear();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
