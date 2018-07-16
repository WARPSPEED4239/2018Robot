package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBPurple extends Command {

    public RGBPurple() {
        requires(Robot.rgb);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rgb.rgbPurple();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
