package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBPurpleDim extends Command {

    public RGBPurpleDim() {
        requires(Robot.rgb);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rgb.rgbPurpleDim();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
