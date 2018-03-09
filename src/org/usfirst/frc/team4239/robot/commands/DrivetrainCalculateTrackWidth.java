package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.motion.MotionConvert;

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
        if (Math.abs(rotate) < 0.2) {
            rotate = 0;
        }
        else {
            double sign = Math.signum(rotate);
            rotate = sign * (Math.abs(rotate) - .2);
        }
        double velocity = 6.25 * rotate;
        double nativeVelocity = MotionConvert.velocityToUnits(velocity);
        
        SmartDashboard.putNumber("TrackWidthRotate", rotate);
        SmartDashboard.putNumber("TrackWidthVelocity", velocity);
        SmartDashboard.putNumber("TrackWidthNativeVelocity", nativeVelocity);
        
        System.out.println("rotate = " + rotate + ", " + "velocity = " + velocity + ", nativeVelocity = " + nativeVelocity);
        
        Robot.drivetrain.getLeftController().set(ControlMode.Velocity, nativeVelocity);
        Robot.drivetrain.getRightController().set(ControlMode.Velocity, -nativeVelocity);
        mTrackWidth = (Math.abs(Robot.drivetrain.getLeftDistance()) + Math.abs(Robot.drivetrain.getRightDistance()))
                / (2 * Math.PI * mTimesToSpin);
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
