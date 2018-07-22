package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBWhite extends Command {

    public RGBWhite() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rgbController.setColor(Color.White);
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
