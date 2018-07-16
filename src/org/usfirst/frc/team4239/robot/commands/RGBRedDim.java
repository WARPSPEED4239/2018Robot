package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBRedDim extends Command {

    public RGBRedDim() {
        requires(Robot.rgb);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rgb.rgbRedDim();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
