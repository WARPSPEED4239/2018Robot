package org.usfirst.frc.team4239.robot.motion;

import org.usfirst.frc.team4239.robot.tools.Logger;

import jaci.pathfinder.Waypoint;

public class Trajectories {
	public static final double kDefaultMaxVelocity = 8;			//16
	public static final double kDefaultMaxAcceleration = 4;		//8
	public static final double kDefaultMaxJerk = 40;			//2000
	
	public static void initialize() {
		Logger.log("Trajectory initialization started.");
		centerLeftSwitch1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(9.0, 3.8, 0.0),
				new Waypoint(0, 0, 0)
		});
		
		centerLeftSwitch2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(-7.0, -1.0, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		centerLeftSwitch3 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(7.0, 1.0, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		
		
		centerRightSwitch1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
			new Waypoint(8.5, -4.0, 0.0),
			new Waypoint(0, 0, 0)
		});
		
		centerRightSwitch2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(-7.83, 0.5, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		centerRightSwitch3 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint[] {
				new Waypoint(7.83, -0.5, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		
		
		leftSwitchSpline = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(10.66, -3.5, -95.0),
				new Waypoint(0, 0, 0)
		});
		
		leftBackSwitchSpline = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(13.91, -13.5, -180.0),
				new Waypoint(16.66, -11.5, -90.0),
				new Waypoint(16.66, -3.0, -90.0),
				new Waypoint(13.66, 0.0, 0.0),
				new Waypoint(0, 0, 0)
		});
		
		
		
		leftScaleSpline1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(21.66, -1.5, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-3.0, 2.0, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline3 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-4.5, -3.5, -30.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline4 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(4.5, 3.5, 30.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline5 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(3.0, -2.0, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline6 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-4.5, -6.0, -30.0),
				new Waypoint(0, 0, 0)
		});
		
		leftScaleSpline7 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(4.5, 6.0, 30.0),
				new Waypoint(0, 0, 0)
		});
		
		
		leftBackScaleSpline1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(20.56, -17.25, 15.0),
				new Waypoint(19.16, -17.25, 0.0),
				new Waypoint(16.16, -14.25, -90.0),
				new Waypoint(16.16, -3.0, -90.0),
				new Waypoint(13.16, 0.0, 0.0),
				new Waypoint(0, 0, 0)
		});

		leftBackScaleSpline2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-2.5, -3.0, 75.0),
				new Waypoint(0, 0, 0)
		});
		
		
		
		rightSwitchSpline = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(10.66, 3.5, 95.0),
				new Waypoint(0, 0, 0)
		});
		
		rightBackSwitchSpline = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(13.91, 13.5, 180.0),
				new Waypoint(16.66, 11.5, 90.0),
				new Waypoint(16.66, 3.0, 90.0),
				new Waypoint(13.66, 0.0, 0.0),
				new Waypoint(0, 0, 0)
		});
		
		
		rightScaleSpline1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(21.66, 1.5, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-3.0, -2.0, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline3 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-4.5, 3.5, 45.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline4 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(4.5, -3.5, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline5 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(3.0, 2.0, -45.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline6 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-4.5, 6.0, 30.0),
				new Waypoint(0, 0, 0)
		});
		
		rightScaleSpline7 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(4.5, -6.0, -30.0),
				new Waypoint(0, 0, 0)
		});
		
		
		
		rightBackScaleSpline1 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(20.56, 17.25, -15.0),
				new Waypoint(19.16, 17.25, 0.0),
				new Waypoint(16.16, 14.25, 90.0),
				new Waypoint(16.16, 3.0, 90.0),
				new Waypoint(13.16, 0.0, 0.0),
				new Waypoint(0, 0, 0)
		});

		rightBackScaleSpline2 = TrajectoryBuilder.getSplineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, new Waypoint [] {
				new Waypoint(-2.5, 3.0, -75.0),
				new Waypoint(0, 0, 0)
		});
		
		driveForward18In = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 1.5);
		driveForward44In = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 5.5);
		driveForward8Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 8.0);
		
		//driveForward3Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 3);
		//driveForward8Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 8);
		//driveForward12Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 12);
		//driveForward15Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 15);
		//driveForward18Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 18);
		//driveForward20Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 20);
		
		driveBackward18In = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -1.5);
		driveBackward44In = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -5.5);
		driveBackward7Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -7.0);
		
		//driveBackward2Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -2);
		//driveBackward3Ft = TrajectoryBuilder.getStraightLineTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -3);
		
		//rotateRight60Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 60);
		//rotateLeft60Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -60);
		//rotateRight90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 90);
		//rotateLeft90Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -90);
		//rotateRight94Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 94);
		//rotateLeft94Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -94);
		//rotateRight100Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, 100);
		//rotateLeft100Degrees = TrajectoryBuilder.getRotationTrajectory(kDefaultMaxVelocity, kDefaultMaxAcceleration, kDefaultMaxJerk, -100);
		Logger.log("Trajecotry initialization complete.");
	}
	
	public static TrajectoryResult centerLeftSwitch1;
	public static TrajectoryResult centerLeftSwitch2;
	public static TrajectoryResult centerLeftSwitch3;
	
	public static TrajectoryResult centerRightSwitch1;
	public static TrajectoryResult centerRightSwitch2;
	public static TrajectoryResult centerRightSwitch3;
	
	public static TrajectoryResult leftSwitchSpline;
	public static TrajectoryResult leftBackSwitchSpline;
	
	public static TrajectoryResult leftScaleSpline1;
	public static TrajectoryResult leftScaleSpline2;
	public static TrajectoryResult leftScaleSpline3;
	public static TrajectoryResult leftScaleSpline4;
	public static TrajectoryResult leftScaleSpline5;
	public static TrajectoryResult leftScaleSpline6;
	public static TrajectoryResult leftScaleSpline7;
	
	public static TrajectoryResult leftBackScaleSpline1;
	public static TrajectoryResult leftBackScaleSpline2;
	
	public static TrajectoryResult rightSwitchSpline;
	public static TrajectoryResult rightBackSwitchSpline;
	
	public static TrajectoryResult rightScaleSpline1;
	public static TrajectoryResult rightScaleSpline2;
	public static TrajectoryResult rightScaleSpline3;
	public static TrajectoryResult rightScaleSpline4;
	public static TrajectoryResult rightScaleSpline5;
	public static TrajectoryResult rightScaleSpline6;
	public static TrajectoryResult rightScaleSpline7;
	
	public static TrajectoryResult rightBackScaleSpline1;
	public static TrajectoryResult rightBackScaleSpline2;
	
	public static TrajectoryResult driveForward18In;
	public static TrajectoryResult driveForward44In;
	public static TrajectoryResult driveForward8Ft;
	
	//public static TrajectoryResult driveForward3Ft;
	//public static TrajectoryResult driveForward12Ft;
	//public static TrajectoryResult driveForward15Ft;
	//public static TrajectoryResult driveForward18Ft;
	//public static TrajectoryResult driveForward20Ft;
	
	public static TrajectoryResult driveBackward18In;
	public static TrajectoryResult driveBackward44In;
	public static TrajectoryResult driveBackward7Ft;
	
	
	//public static TrajectoryResult driveBackward2Ft;
	//public static TrajectoryResult driveBackward3Ft;
	
	//public static TrajectoryResult rotateRight60Degrees;
	//public static TrajectoryResult rotateLeft60Degrees;
	//public static TrajectoryResult rotateRight90Degrees;
	//public static TrajectoryResult rotateLeft90Degrees;
	//public static TrajectoryResult rotateRight94Degrees;
	//public static TrajectoryResult rotateLeft94Degrees;
	//public static TrajectoryResult rotateRight100Degrees;
	//public static TrajectoryResult rotateLeft100Degrees;
}