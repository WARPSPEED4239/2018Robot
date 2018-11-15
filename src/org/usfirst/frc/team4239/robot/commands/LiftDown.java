package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDown extends Command {

    public LiftDown() {
        requires(Robot.lift);
    }

    protected void initialize() {
    	Color[] colors = {Color.WhiteDim, Color.Black};
    	Robot.rgbController.setColors(colors, 0.5);
    }

    protected void execute() {
    	Robot.lift.liftDown();
    }

    protected boolean isFinished() {
        return Robot.lift.getBottomSwitch();
    }

    protected void end() {
    	Robot.rgbController.setColor(Color.Red);
    	Robot.lift.liftStop();
    }

    protected void interrupted() {
    }
}