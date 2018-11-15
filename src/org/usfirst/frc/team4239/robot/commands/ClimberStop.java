package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberStop extends Command {

    public ClimberStop() {
        requires(Robot.climber);
    }

    protected void initialize() {
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void execute() {
    	Robot.climber.climberStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}