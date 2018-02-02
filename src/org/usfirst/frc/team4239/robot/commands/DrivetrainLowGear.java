package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainLowGear extends Command {

    public DrivetrainLowGear() {
<<<<<<< HEAD
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainShifting);
=======
        requires(Robot.drivetrain);
>>>>>>> darren
    }

    protected void initialize() {
<<<<<<< HEAD
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrainShifting.drivetrainLowGear();
=======
    	Robot.drivetrain.drivetrainLowGear();
>>>>>>> darren
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}