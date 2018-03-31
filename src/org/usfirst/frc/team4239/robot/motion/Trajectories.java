package org.usfirst.frc.team4239.robot.motion;

import org.usfirst.frc.team4239.robot.tools.Logger;

import jaci.pathfinder.Waypoint;

public class Trajectories {
	public static final double kDefaultMaxVelocity = 8;
	public static final double kDefaultMaxAcceleration = 4;
	public static final double kDefaultMaxJerk = 40;
	
	public static void initialize() {
		Logger.log("Trajectory initialization started.");
		crossAutoLine = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 8.0);
		centerToLeftSwitch = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(9.0, 3.8, 0),
				new Waypoint(0, 0, 0)
		});
		
		centerToRightSwitch = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
			new Waypoint(8.5, -4.0, 0),
			new Waypoint(0, 0, 0)
		});
		driveForward1Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 1);
		driveForward2Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 2);
		driveForward3Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 3);
		driveForward12Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 12);
		driveForward15Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 15);
		driveForward18Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 18);
		driveForward20Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 20);
		driveBackward2Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -2);
		driveBackward3Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -3);
		rotateRight60Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 60);
		rotateLeft60Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -60);
		rotateRight90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 90);
		rotateLeft90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -90);
		rotateRight94Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 94);
		rotateLeft94Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -94);
		rotateRight100Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 100);
		rotateLeft100Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -100);
		Logger.log("Trajecotry initialization complete.");
	}
	
	public static TrajectoryResult crossAutoLine;
	
	public static TrajectoryResult centerToLeftSwitch;
	public static TrajectoryResult centerToRightSwitch;
	
	public static TrajectoryResult driveForward1Ft;
	public static TrajectoryResult driveForward2Ft;
	public static TrajectoryResult driveForward3Ft;
	public static TrajectoryResult driveForward12Ft;
	public static TrajectoryResult driveForward15Ft;
	public static TrajectoryResult driveForward18Ft;
	public static TrajectoryResult driveForward20Ft;
	
	public static TrajectoryResult driveBackward2Ft;
	public static TrajectoryResult driveBackward3Ft;
	
	public static TrajectoryResult rotateRight60Degrees;
	public static TrajectoryResult rotateLeft60Degrees;
	public static TrajectoryResult rotateRight90Degrees;
	public static TrajectoryResult rotateLeft90Degrees;
	public static TrajectoryResult rotateRight94Degrees;
	public static TrajectoryResult rotateLeft94Degrees;
	public static TrajectoryResult rotateRight100Degrees;
	public static TrajectoryResult rotateLeft100Degrees;
}
