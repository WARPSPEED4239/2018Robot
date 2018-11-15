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
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.setIsAuto(false);
    }

    protected void execute() {
    	XboxController controller = Robot.oi.xbox;

    	double move = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
    	double rotate = -controller.getX(Hand.kLeft);
    	

    	//System.out.println("move = " + move);
    	//System.out.println("rotate = " + rotate);
    	
    	//SmartDashboard.putNumber("Move", move);
    	//SmartDashboard.putNumber("Rotate", rotate);
    	
    	Robot.drivetrain.arcadeDrive(move, rotate);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}