package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

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
    	Color[] colors = {Color.Red, Color.Black};
    	Robot.rgbController.setColors(colors, 0.2);
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
