package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainArcadeDrive extends Command {

    public DrivetrainArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	XboxController controller = Robot.oi.xbox;
<<<<<<< HEAD
    	double move = controller.getTriggerAxis(Hand.kRight) - controller.getTriggerAxis(Hand.kLeft);
    	double rotate = - controller.getX(Hand.kLeft);
=======
    	double move = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
    	double rotate = -controller.getX(Hand.kLeft);
    	
    	System.out.println("move = " + move);
    	System.out.println("rotate = " + rotate);
    	
    	SmartDashboard.putNumber("Move", move);
    	SmartDashboard.putNumber("Rotate", rotate);
>>>>>>> darren
    	
    	Robot.drivetrain.arcadeDrive(move, rotate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}