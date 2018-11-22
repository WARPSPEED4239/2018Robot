package org.usfirst.frc.team4239.robot.motion;

import org.usfirst.frc.team4239.robot.tools.Logger;

import jaci.pathfinder.Waypoint;

public class Trajectories {
	public static final double kDefaultMaxVelocity = 8;
	public static final double kDefaultMaxAcceleration = 4.5;
	public static final double kDefaultMaxJerk = 40;
	
	public static void initialize() {
		Logger.log("Trajectory initialization started.");
		centerToLeftSwitch = TrajectoryBuilder.getSplineTrajectory("centerToLeftSwitch", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(9.0, 4.5, 0),
				new Waypoint(0, 0, 0)
		});
		
		centerToRightSwitch = TrajectoryBuilder.getSplineTrajectory("centerToRightSwitch", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
			new Waypoint(8.5, -3.5, 0),
			new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline = TrajectoryBuilder.getSplineTrajectory("leftScaleSpline", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(22.0, -3.0, 0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline = TrajectoryBuilder.getSplineTrajectory("rightScaleSpline", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(22.0, 3.0, 0),
				new Waypoint(0, 0, 0)
		});
		
		driveForward1Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward1Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 1);
		driveForward2Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward2Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 2);
		driveForward3Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward3Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 3);
		driveForward8Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward8Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 8);
		driveForward12Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward12Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 12);
		driveForward15Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward15Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 15);
		driveForward18Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward18Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 18);
		driveForward21Ft = TrajectoryBuilder.getStraightLineTrajectory("driveForward21Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 20);
		
		driveBackward2Ft = TrajectoryBuilder.getStraightLineTrajectory("driveBackward2Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -2);
		driveBackward3Ft = TrajectoryBuilder.getStraightLineTrajectory("driveBackward3Ft", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -3);
		
		rotateRight60Degrees = TrajectoryBuilder.getRotationTrajectory("rotateRight60Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 60);
		rotateLeft60Degrees = TrajectoryBuilder.getRotationTrajectory("rotateLeft60Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -60);
		rotateRight87Degrees = TrajectoryBuilder.getRotationTrajectory("rotateRight87Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 87);
		rotateLeft90Degrees = TrajectoryBuilder.getRotationTrajectory("rotateLeft90Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -90);
		rotateRight94Degrees = TrajectoryBuilder.getRotationTrajectory("rotateRight94Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 94);
		rotateLeft94Degrees = TrajectoryBuilder.getRotationTrajectory("rotateLeft94Degrees",kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -94);
		rotateRight100Degrees = TrajectoryBuilder.getRotationTrajectory("rotateRight100Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 100);
		rotateLeft100Degrees = TrajectoryBuilder.getRotationTrajectory("rotateLeft100Degrees", kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -100);
		Logger.log("Trajecotry initialization complete.");
	}
	
	public static TrajectoryResult centerToLeftSwitch;
	public static TrajectoryResult centerToRightSwitch;
	public static TrajectoryResult leftScaleSpline;
	public static TrajectoryResult rightScaleSpline;
	
	public static TrajectoryResult driveForward1Ft;
	public static TrajectoryResult driveForward2Ft;
	public static TrajectoryResult driveForward3Ft;
	public static TrajectoryResult driveForward8Ft;
	public static TrajectoryResult driveForward12Ft;
	public static TrajectoryResult driveForward15Ft;
	public static TrajectoryResult driveForward18Ft;
	public static TrajectoryResult driveForward21Ft;
	
	public static TrajectoryResult driveBackward2Ft;
	public static TrajectoryResult driveBackward3Ft;
	
	public static TrajectoryResult rotateRight60Degrees;
	public static TrajectoryResult rotateLeft60Degrees;
	public static TrajectoryResult rotateRight87Degrees;
	public static TrajectoryResult rotateLeft90Degrees;
	public static TrajectoryResult rotateRight94Degrees;
	public static TrajectoryResult rotateLeft94Degrees;
	public static TrajectoryResult rotateRight100Degrees;
	public static TrajectoryResult rotateLeft100Degrees;
}
