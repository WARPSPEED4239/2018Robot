package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.motion.MotionConvert;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class DrivetrainFollowProfile extends Command {

	private final double KA = 0;
	private final double KP = 0;
	private final double KG = 0;
	
	private final double ACCEPTABLE_DISTANCE_ERROR = .125;
	private final double ACCEPTABLE_ANGLE_ERROR = 5;
	private final double PROFILE_TIMEOUT = 2; 
	
	private Trajectory mLeftTrajectory;
	private Trajectory mRightTrajectory;
	private WPI_TalonSRX mLeftController;
	private WPI_TalonSRX mRightController;

	private boolean mInputError;
	private boolean mProfileFinished;
	
	private int mTrajectorySize;
	
	private double mTrajectoryDeltaTime;
	private double mTrajectoryExpectedTime;
	
    public DrivetrainFollowProfile(Trajectory leftTrajectory, Trajectory rightTrajectory) {
        requires(Robot.drivetrain);
        
        mLeftTrajectory = leftTrajectory;
        mRightTrajectory = rightTrajectory;
        
        mLeftController = Robot.drivetrain.getLeftController();
        mRightController = Robot.drivetrain.getRightController();   	
    }

    protected void initialize() {
    	mInputError = false;
    	mProfileFinished = false;
    	
    	Robot.drivetrain.setIsAuto(true);
    	
    	if (mLeftTrajectory == null) {
    		System.err.println("ERROR, mLeftTrajectory is Null");
    		mInputError = true;
    		return;
    	}
    	if (mRightTrajectory == null) {
    		System.err.println("ERROR, mRightTrajectory is Null");
    		mInputError = true;
    		return;
    	}
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
    	
    	int leftTrajectorySize = mLeftTrajectory.segments.length;
    	int rightTrajectorySize = mRightTrajectory.segments.length;
    	
    	if (leftTrajectorySize != rightTrajectorySize) {
    		System.err.println("ERROR, leftSize != to rightSize");
    		mInputError = true;
    		return; 
    	}
    	
    	mTrajectorySize = leftTrajectorySize;
    	
    	if (mTrajectorySize <= 0) {
    		System.err.println("ERROR, mTrajectorySize <= 0");
    		mInputError = true;
    		return;
    	}
    	
    	double leftDeltaTime = mLeftTrajectory.segments[0].dt;
    	double rightDeltaTime = mRightTrajectory.segments[0].dt;
    	
    	if (leftDeltaTime != rightDeltaTime) {
    		System.err.println("ERROR, leftDeltaTime != rightDeltaTime");
    		mInputError = true;
    		return;
    	}
    	
    	mTrajectoryDeltaTime = leftDeltaTime;
    	mTrajectoryExpectedTime = mTrajectoryDeltaTime * mTrajectorySize;
    	
    	Robot.drivetrain.resetSensors();
    }

    protected void execute() {
    	if (mInputError || mProfileFinished) {
    		System.err.println("ERROR, Shit hit the fan");
    		return;
    	}
    	
    	double elapsedTime = timeSinceInitialized();
    	
    	int index = (int) (elapsedTime / mTrajectoryDeltaTime);
    	
    	if (index >= mTrajectorySize) {
    		index = mTrajectorySize - 1;
    	}
    	
    	Segment leftSegment = mLeftTrajectory.segments[index];
    	Segment rightSegment = mRightTrajectory.segments[index];
    	
    	double leftPositionError = leftSegment.position - Robot.drivetrain.getLeftDistance();
    	double rightPositionError = rightSegment.position - Robot.drivetrain.getRightDistance();
    	double angleError = Math.toDegrees(leftSegment.heading - Math.PI) - Robot.drivetrain.getGyroAngle();
    	
    	double desiredLeftVelocity = leftSegment.velocity + 
    								 KG * angleError + 
    								 KA * leftSegment.acceleration +
    								 KP * leftPositionError;
    	
    	double desiredRightVelocity = rightSegment.velocity - 
				 					  KG * angleError + 
									  KA * rightSegment.acceleration +
									  KP * rightPositionError;
    	
    	mLeftController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredLeftVelocity));
    	mRightController.set(ControlMode.Velocity, MotionConvert.velocityToUnits(desiredRightVelocity));
    
    	boolean sensorInRange = Math.abs(leftPositionError) < ACCEPTABLE_DISTANCE_ERROR &&
    							Math.abs(rightPositionError) < ACCEPTABLE_DISTANCE_ERROR &&
    							Math.abs(angleError) < ACCEPTABLE_ANGLE_ERROR;
    	
    	if ((elapsedTime > mTrajectoryExpectedTime && sensorInRange)  || elapsedTime > mTrajectoryExpectedTime + PROFILE_TIMEOUT) {
    		mProfileFinished = true;
    	}
    }

    protected boolean isFinished() {
        if (mInputError || mProfileFinished) {
            System.err.println("Finished in isFinished.");
        }
        return mInputError || mProfileFinished;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    }
}