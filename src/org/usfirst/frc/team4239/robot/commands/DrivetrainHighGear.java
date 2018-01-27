package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainHighGear extends Command {
	
    public DrivetrainHighGear() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.drivetrainHighGear();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
    
}