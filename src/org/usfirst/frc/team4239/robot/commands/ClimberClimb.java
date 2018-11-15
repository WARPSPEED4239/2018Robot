package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberClimb extends Command {

    public ClimberClimb() {
    	requires(Robot.climber);
    }

    protected void initialize() {
    	Robot.rgbController.setColor(Color.Green);
    }

    protected void execute() {
    	Robot.climber.climberClimb();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.rgbController.setColor(Color.Red);
    	Robot.climber.climberStop();
    }

    protected void interrupted() {
    }
}