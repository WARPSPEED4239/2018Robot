package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeStop extends Command {

    public IntakeStop() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void execute() {
    	Robot.intake.intakeStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}