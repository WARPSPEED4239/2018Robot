package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainHighGear extends Command {
	
    public DrivetrainHighGear() {
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
    	Robot.drivetrainShifting.drivetrainHighGear();
=======
    	Robot.drivetrain.drivetrainHighGear();
>>>>>>> darren
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
    
}