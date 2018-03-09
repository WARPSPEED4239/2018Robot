package org.usfirst.frc.team4239.robot.motion;

import org.usfirst.frc.team4239.robot.tools.Logger;

import jaci.pathfinder.Waypoint;

public class Trajectories {
	private static final double kDefaultMaxVelocity = 8;
	private static final double kDefaultMaxAcceleration = 4;
	private static final double kDefaultMaxJerk = 40;
	
	public static void initialize() {
		Logger.log("Trajectory initialization started.");
		crossAutoLine = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 8.0);
		centerToLeftSwitch = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(8.33, 8.0, 0),
				new Waypoint(0, 0, 0)
		});
		centerToRightSwitch = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
			new Waypoint(8.33, -4.0, 0),
			new Waypoint(0, 0, 0)
		});
		driveForward1Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 1);
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
	
	public static TrajectoryResult driveForward1Ft;
	public static TrajectoryResult driveForward12Ft;
	public static TrajectoryResult driveForward25Ft;
	
	public static TrajectoryResult driveBackward3Ft;
	
	public static TrajectoryResult rotateRight90Degrees;
	public static TrajectoryResult rotateLeft90Degrees;
}
