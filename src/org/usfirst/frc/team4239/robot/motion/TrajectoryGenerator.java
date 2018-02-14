package org.usfirst.frc.team4239.robot.motion;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class TrajectoryGenerator {
	public static TrajectoryResult getTrajectory (Waypoint[] points) {
		TrajectoryResult result = new TrajectoryResult();
	
		final double WHEEL_BASE_WIDTH = 2;
    	final double DELTA_TIME = 0.05;
    	final double MAX_VELOCITY = 5;
    	final double MAX_ACCELERATION = 2.0;
    	final double MAX_JERK = 30;
    	
        Trajectory.Config config = new Trajectory.Config(
                Trajectory.FitMethod.HERMITE_CUBIC, 
                Trajectory.Config.SAMPLES_HIGH,
                DELTA_TIME, 
                MAX_VELOCITY, 
                MAX_ACCELERATION, 
                MAX_JERK
        );
        Trajectory trajectory = Pathfinder.generate(points, config);
        
        TankModifier modifier = new TankModifier(trajectory).modify(WHEEL_BASE_WIDTH);
        
        result.leftTrajectory = modifier.getLeftTrajectory();
        result.rightTrajectory = modifier.getRightTrajectory();
		
		return result;
	}
}