package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.State.CodeExecutionState;
import org.usfirst.frc.team4239.robot.motion.MotionConvert;
import org.usfirst.frc.team4239.robot.motion.ProfilePoint;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;
import org.usfirst.frc.team4239.robot.tools.Logger;
import org.usfirst.frc.team4239.robot.tools.Plot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainFollowProfile extends Command {

	private final double KA = 0.075;
	private final double KP = 5;
	
	private final double ACCEPTABLE_DISTANCE_ERROR = .125;
	private final double PROFILE_TIMEOUT = 0.25; 
	
	private TrajectoryResult mResult;
	private WPI_TalonSRX mLeftController;
	private WPI_TalonSRX mRightController;

	private boolean mInputError;
	private boolean mProfileFinished;
	
    public DrivetrainFollowProfile(TrajectoryResult result) {
        requires(Robot.drivetrain);
        mResult = result;
    }

    protected void initialize() {
    	mInputError = false;
    	mProfileFinished = false;
    	
    	Robot.drivetrain.setIsAuto(true);
    	
    	if (mResult == null || !mResult.isValid()) {
    		System.err.println("ERROR, profile in not valid");
    		mInputError = true;
    		return;
    	}
    	
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
    	
    	ProfilePoint left = mResult.getLeftAtTime(elapsedTime);
    	ProfilePoint right = mResult.getRightAtTime(elapsedTime);
    	
    	double leftPositionError = left.position - Robot.drivetrain.getLeftDistance();
    	double rightPositionError = right.position - Robot.drivetrain.getRightDistance();
    	
    	double desiredLeftVelocity = left.velocity + 
    								 KA * left.acceleration +
    								 KP * leftPositionError;
    	
    	double desiredRightVelocity = right.velocity +
									  KA * right.acceleration +
									  KP * rightPositionError;
    	
    	mLeftController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredLeftVelocity));
    	mRightController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredRightVelocity));
    
    	boolean sensorInRange = Math.abs(leftPositionError) < ACCEPTABLE_DISTANCE_ERROR &&
    							Math.abs(rightPositionError) < ACCEPTABLE_DISTANCE_ERROR;
    	
    	if ((elapsedTime > mResult.getRuntime() && sensorInRange)  || elapsedTime > mResult.getRuntime() + PROFILE_TIMEOUT) {
    		mProfileFinished = true;
    	}
    	
    	if (RobotMap.executionState == CodeExecutionState.Debug) {
    		updatePlots(elapsedTime, left, right);
    	}
    }

    protected boolean isFinished() {
        return mInputError || mProfileFinished;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	if (RobotMap.executionState == CodeExecutionState.Debug) {
    		publishPlots();
    	}
    }

    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
    
    private Plot leftPositionErrorPlot = new Plot("Time(s)", "Left Position Error(ft)");
    private Plot leftVelocityErrorPlot = new Plot("Time(s)", "Left Velocity Error(ft/s)");
    
    private Plot rightPositionErrorPlot = new Plot("Time(s)", "Right Position Error(ft)");
    private Plot rightVelocityErrorPlot = new Plot("Time(s)", "Right Velocity Error(ft/s)");
    
    
    private void updatePlots(double elapsedTime, ProfilePoint left, ProfilePoint right) {
    	double leftPositionError = Robot.drivetrain.getLeftDistance() - left.position;
    	double leftVelocityError = Robot.drivetrain.getLeftVelocity() - left.velocity;
    	double rightPositionError = Robot.drivetrain.getRightDistance() - right.position;
    	double rightVelocityError = Robot.drivetrain.getRightVelocity() - left.velocity;
    	leftPositionErrorPlot.addDataPoint(elapsedTime, leftPositionError);
    	leftVelocityErrorPlot.addDataPoint(elapsedTime, leftVelocityError);
    	rightPositionErrorPlot.addDataPoint(elapsedTime, rightPositionError);
    	rightVelocityErrorPlot.addDataPoint(elapsedTime, rightVelocityError);
    }
    
    private void publishPlots() {
		Logger.log(leftPositionErrorPlot.toString(false));
		Logger.log(leftVelocityErrorPlot.toString(false));
		Logger.log(rightPositionErrorPlot.toString(false));
		Logger.log(rightVelocityErrorPlot.toString(false));
    }
    
}