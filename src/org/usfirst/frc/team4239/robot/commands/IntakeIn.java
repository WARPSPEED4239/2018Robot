package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeIn extends Command {

    public IntakeIn() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Color[] colors = {Color.PurpleDim, Color.Black};
    	Robot.rgbController.setColors(colors, 0.5);
    }

    protected void execute() {
    	Robot.intake.intakeIn();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.rgbController.setColor(Color.Red);
    	Robot.intake.intakeStop();
    }

    protected void interrupted() {
    }
}