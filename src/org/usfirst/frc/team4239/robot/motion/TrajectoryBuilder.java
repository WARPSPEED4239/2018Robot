package org.usfirst.frc.team4239.robot.motion;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class TrajectoryBuilder {
	
	public static final double DELTA_TIME = 0.05;
	public static final double WHEEL_BASE_WIDTH = 2.75;
	public static TrajectoryResult getStraightLineTrajectory(double maxVelocity, double maxAcceleration, double maxJerk, double targetDistance) {
		Waypoint[] points = new Waypoint[] {
			new Waypoint(Math.abs(targetDistance), 0, 0),
			new Waypoint(0, 0, 0)
		};
		
		Trajectory trajectory = getTrajectory(maxVelocity, maxAcceleration, maxJerk, points);
		boolean invert = targetDistance < 0;
		
		return new TrajectoryResult(trajectory, trajectory, invert, invert);
	}
	
	public static TrajectoryResult getRotationTrajectory(double maxVelocity, double maxAcceleration, double maxJerk, double targetAngle) {
		double targetDistance = Math.PI * WHEEL_BASE_WIDTH * targetAngle / 360;
		
		Waypoint[] points = new Waypoint[] {
			new Waypoint(Math.abs(targetDistance), 0, 0),
			new Waypoint(0, 0, 0)
		};
		
		Trajectory trajectory = getTrajectory(maxVelocity, maxAcceleration, maxJerk, points);
		
		return new TrajectoryResult(trajectory, trajectory, targetAngle < 0, targetAngle > 0);
	}
	
	public static TrajectoryResult getSplineTrajectory(double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
        return getSplineTrajectory(false, maxVelocity, maxAcceleration, maxJerk, points);	
	}
	
	public static TrajectoryResult getSplineTrajectory(boolean inverted, double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
		Trajectory trajectory = getTrajectory(maxVelocity, maxAcceleration, maxJerk, points);
		TankModifier modifier = new TankModifier(trajectory).modify(WHEEL_BASE_WIDTH);
        return new TrajectoryResult(modifier.getLeftTrajectory(), modifier.getRightTrajectory(), inverted, inverted);
	}
	
	private static Trajectory getTrajectory(double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
		Trajectory.Config config = new Trajectory.Config(
				Trajectory.FitMethod.HERMITE_CUBIC, 
				Trajectory.Config.SAMPLES_HIGH,
				DELTA_TIME, 
				maxVelocity, 
				maxAcceleration, 
				maxJerk
		);
		return Pathfinder.generate(points, config);
	}
	
}
