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
    }

    protected void execute() {
    	Robot.drivetrainShifting.drivetrainLowGear();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
