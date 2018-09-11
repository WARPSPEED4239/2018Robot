package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBPurpleFlashing extends Command {

    public RGBPurpleFlashing() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Color[] colors = {Color.PurpleDim, Color.Black};
    	Robot.rgbController.setColors(colors, 0.5);
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
