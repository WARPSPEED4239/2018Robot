package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftStop extends Command {

    public LiftStop() {
        requires(Robot.lift);
    }

    protected void initialize() {
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void execute() {
    	Robot.lift.liftStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}