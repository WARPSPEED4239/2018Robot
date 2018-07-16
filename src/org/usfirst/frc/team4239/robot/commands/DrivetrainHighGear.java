package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainHighGear extends Command {

    public DrivetrainHighGear() {
        requires(Robot.drivetrainShifting);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrainShifting.drivetrainHighGear();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
