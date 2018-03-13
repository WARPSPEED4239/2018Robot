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
		driveBackward2Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -2);
		driveForward1Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 1);
		driveForward5Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 5);
		driveForward7Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 7);
		driveForward12Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 12);
		driveForward25Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 25);
		driveBackward3Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -3);
		rotateRight90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 90);
		rotateLeft90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -90);
		Logger.log("Trajecotry initialization complete.");
	}
	
	public static TrajectoryResult crossAutoLine;
	
	public static TrajectoryResult centerToLeftSwitch;
	public static TrajectoryResult centerToRightSwitch;
	
	public static TrajectoryResult driveBackward2Ft;
	public static TrajectoryResult driveForward1Ft;
	public static TrajectoryResult driveForward5Ft;
	public static TrajectoryResult driveForward7Ft;
	public static TrajectoryResult driveForward12Ft;
	public static TrajectoryResult driveForward25Ft;
	
	public static TrajectoryResult driveBackward3Ft;
	
	public static TrajectoryResult rotateRight90Degrees;
	public static TrajectoryResult rotateLeft90Degrees;
}
