package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RGBGreen extends Command {

    public RGBGreen() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	//Robot.rgbController.setColor(Color.Green);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	//Robot.rgbController.setColor(Color.Red);
    }

    protected void interrupted() {
    }
}
