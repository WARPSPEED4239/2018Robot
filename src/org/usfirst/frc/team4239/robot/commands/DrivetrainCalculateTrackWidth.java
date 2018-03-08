package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainCalculateTrackWidth extends Command {

	private int mTimesToSpin;
	private double mTrackWidth;
	
    public DrivetrainCalculateTrackWidth(int timesToSpin) {
        requires(Robot.drivetrain);
        mTimesToSpin = timesToSpin;
    }

    protected void initialize() {
    	Robot.drivetrain.setIsAuto(true);
    	Robot.drivetrain.resetSensors();
    }

    protected void execute() {
    	double rotate = Robot.oi.getController().getX(Hand.kLeft);
    	Robot.drivetrain.getLeftController().set(ControlMode.Velocity, 5 * rotate);
    	Robot.drivetrain.getRightController().set(ControlMode.Velocity, -5 * rotate);
    	
    	mTrackWidth = (Math.abs(Robot.drivetrain.getLeftDistance()) + Math.abs(Robot.drivetrain.getRightDistance())) / (2 * Math.PI * mTimesToSpin);
    	SmartDashboard.putNumber("TrackWidth", mTrackWidth);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
