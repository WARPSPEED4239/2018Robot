package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class AutonTest extends CommandGroup {
	
    public AutonTest() {
    
    	final double WHEEL_BASE_WIDTH = 2;
    	final double DELTA_TIME = 0.05;
    	final double MAX_VELOCITY = 5;
    	final double MAX_ACCELERATION = 2.0;
    	final double MAX_JERK = 30;
    	
    	Waypoint[] points = new Waypoint[] {
    		new Waypoint(5, 0, 0),
    		new Waypoint(0, 0, 0)
    	};
    	
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
        
        Trajectory leftTrajectory = modifier.getLeftTrajectory();
        Trajectory rightTrajectory = modifier.getRightTrajectory();
        
        addSequential(new DrivetrainFollowProfile(leftTrajectory, rightTrajectory));
    }
}
