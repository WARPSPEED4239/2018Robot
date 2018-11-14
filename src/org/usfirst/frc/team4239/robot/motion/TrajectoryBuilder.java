package org.usfirst.frc.team4239.robot.motion;

import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import org.usfirst.frc.team4239.robot.tools.Logger;

public class TrajectoryBuilder {
	
	public static final double DELTA_TIME = 0.05;
	public static final double WHEEL_BASE_WIDTH = 2.75;
	public static TrajectoryResult getStraightLineTrajectory(String name, double maxVelocity, double maxAcceleration, double maxJerk, double targetDistance) {
		Waypoint[] points = new Waypoint[] {
			new Waypoint(Math.abs(targetDistance), 0, 0),
			new Waypoint(0, 0, 0)
		};
		
		Trajectory trajectory = getTrajectory(name, maxVelocity, maxAcceleration, maxJerk, points);
		boolean invert = targetDistance < 0;
		
		return new TrajectoryResult(trajectory, trajectory, invert, invert);
	}
	
	public static TrajectoryResult getRotationTrajectory(String name, double maxVelocity, double maxAcceleration, double maxJerk, double targetAngle) {
		double targetDistance = Math.PI * WHEEL_BASE_WIDTH * targetAngle / 360;
		
		Waypoint[] points = new Waypoint[] {
			new Waypoint(Math.abs(targetDistance), 0, 0),
			new Waypoint(0, 0, 0)
		};
		
		Trajectory trajectory = getTrajectory(name, maxVelocity, maxAcceleration, maxJerk, points);
		
		return new TrajectoryResult(trajectory, trajectory, targetAngle < 0, targetAngle > 0);
	}
	
	public static TrajectoryResult getSplineTrajectory(String name, double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
        return getSplineTrajectory(name, false, maxVelocity, maxAcceleration, maxJerk, points);	
	}
	
	public static TrajectoryResult getSplineTrajectory(String name, boolean inverted, double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
		Trajectory trajectory = getTrajectory(name, maxVelocity, maxAcceleration, maxJerk, points);
		TankModifier modifier = new TankModifier(trajectory).modify(WHEEL_BASE_WIDTH);
        return new TrajectoryResult(modifier.getLeftTrajectory(), modifier.getRightTrajectory(), inverted, inverted);
	}
	
	private static Trajectory getTrajectory(String name, double maxVelocity, double maxAcceleration, double maxJerk, Waypoint[] points) {
		String logMessage = "";

		String trajectoryDirString = "/home/lvuser/trajectories";
		File trajectoryDir = new File(trajectoryDirString);

		boolean trajectoryDirExists = trajectoryDir.exists();

		if (!trajectoryDirExists) {
			try {
				logMessage = "Creating Directory: " + trajectoryDir.getAbsolutePath();
				Logger.log(logMessage);
				trajectoryDirExists = trajectoryDir.mkdir();
			}
			catch (Exception e)
			{
				logMessage = "Failed to Create Directory. Exception:\n" + e.getMessage();
				Logger.log(logMessage);
			}
		}

		File file = new File(trajectoryDirString + "/" + name + ".traj");
		if (file.isFile()) {
			if (file.canRead()) {
				logMessage = "Reading from file: " + file.getAbsolutePath();
				Logger.log(logMessage); 
				return Pathfinder.readFromFile(file);
			}
			else {
				logMessage = "Cannot read from file: " + file.getAbsolutePath();
				Logger.log(logMessage);
			}
		}

		boolean fileCreated = false;

		try {
			logMessage = "Creating file: " + file.getAbsolutePath();
			Logger.log(logMessage);
			fileCreated = file.createNewFile();
		} 
		catch (Exception e) {
			logMessage = "Failed to create file " + file.getAbsolutePath() + "\nException: " + e.getMessage();
			Logger.log(logMessage);
		}

		Trajectory.Config config = new Trajectory.Config(
			Trajectory.FitMethod.HERMITE_CUBIC, 
			Trajectory.Config.SAMPLES_HIGH,
			DELTA_TIME, 
			maxVelocity, 
			maxAcceleration, 
			maxJerk
		);
		Trajectory trajectory = Pathfinder.generate(points, config);
		
		if (fileCreated) {
			Pathfinder.writeToFile(file, trajectory);
		}

	    return trajectory;
	}
}
