package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBBlue extends Command {

    public RGBBlue() {
        requires(Robot.rgb);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rgb.rgbBlue();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
