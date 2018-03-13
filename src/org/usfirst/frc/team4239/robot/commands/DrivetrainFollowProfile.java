package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.motion.MotionConvert;
import org.usfirst.frc.team4239.robot.motion.ProfilePoint;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;
import org.usfirst.frc.team4239.robot.tools.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainFollowProfile extends Command {

	private final double KA = 0.075;
	private final double KP = 5;
	
	private final double ACCEPTABLE_DISTANCE_ERROR = .125;
	private final double PROFILE_TIMEOUT = 0.25; 
	
	private TrajectoryResult mProfile;
	private WPI_TalonSRX mLeftController;
	private WPI_TalonSRX mRightController;

	private boolean mInputError;
	private boolean mProfileFinished;
	
	private double mLeftFinishDistance;
	private double mRightFinishDistance;
	
    public DrivetrainFollowProfile(TrajectoryResult profile) {
        requires(Robot.drivetrain);
        mProfile = profile;
    }

    protected void initialize() {
    	mInputError = false;
    	mProfileFinished = false;
    	
    	Robot.drivetrain.setIsAuto(true);
    	
    	if (mProfile == null) {
    		Logger.log("The trajectory is null. Have you given it enough time to compute?");
    		mInputError = true;
    		return;
    	}
    	
    	if (!mProfile.isValid()) {
    		Logger.log("The trajectory is invalid. Something is wrong.");
    		mInputError = true;
    		return;
    	}
    	
    	mLeftFinishDistance = mProfile.getLastLeftPoint().position;
    	mRightFinishDistance = mProfile.getLastRightPoint().position;
    	
    	mLeftController = Robot.drivetrain.getLeftController();
        mRightController = Robot.drivetrain.getRightController();  
    	
    	if (mLeftController == null) {
    		System.err.println("ERROR, mLeftController is Null");
    		mInputError = true;
    		return;
    	}
    	if (mRightController == null) {
    		System.err.println("ERROR, mRightController is Null");
    		mInputError = true;
    		return;
    	}
    	
    	Robot.drivetrain.resetSensors();
    }

    protected void execute() {
    	if (mInputError || mProfileFinished) {
    		System.err.println(String.format("Finished in execute. mInputError = %b, mProfileFinished = %b", mInputError, mProfileFinished));
    		return;
    	}
    	
    	double elapsedTime = timeSinceInitialized();
    	
    	ProfilePoint left = mProfile.getLeftAtTime(elapsedTime);
    	ProfilePoint right = mProfile.getRightAtTime(elapsedTime);
    	
    	double leftPositionError = left.position - Robot.drivetrain.getLeftDistance();
    	double rightPositionError = right.position - Robot.drivetrain.getRightDistance();
    	
    	double desiredLeftVelocity = left.velocity + 
    								 KA * left.acceleration +
    								 KP * leftPositionError;
    	
    	double desiredRightVelocity = right.velocity +
									  KA * right.acceleration +
									  KP * rightPositionError;
    	
    	// Don't allow thrashing if we haven't completed the profile
    	if (elapsedTime < mProfile.getRuntime()) {
    		if (Math.signum(desiredLeftVelocity) != Math.signum(mLeftFinishDistance)) {
    			desiredLeftVelocity = 0;
    		}
    		if (Math.signum(desiredRightVelocity) != Math.signum(mRightFinishDistance)) {
    			desiredRightVelocity = 0;
    		}
    	}
    	
    	mLeftController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredLeftVelocity));
    	mRightController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredRightVelocity));
  
    	boolean sensorInRange = Math.abs(leftPositionError) < ACCEPTABLE_DISTANCE_ERROR &&
								Math.abs(rightPositionError) < ACCEPTABLE_DISTANCE_ERROR;
    	
    	if ((elapsedTime > mProfile.getRuntime() && sensorInRange)  || elapsedTime > mProfile.getRuntime() + PROFILE_TIMEOUT) {
    		mProfileFinished = true;
    	}
    }

    protected boolean isFinished() {
        return mInputError || mProfileFinished;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
    
}