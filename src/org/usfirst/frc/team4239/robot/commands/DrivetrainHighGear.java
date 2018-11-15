package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainHighGear extends Command {

    public DrivetrainHighGear() {
        requires(Robot.drivetrainShifting);
    }

    protected void initialize() {
    	Robot.drivetrainShifting.drivetrainLowGear();
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void interrupted() {
    }
}
