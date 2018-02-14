package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Waypoint;

/**
 *
 */
public class AutonTest extends CommandGroup {
	
    public AutonTest() {
    	
    	Waypoint[] points = new Waypoint[] {
    		new Waypoint(5, -3, 0),
    		new Waypoint(0, 0, 0)
    	};
    	
    	TrajectoryResult result = TrajectoryGenerator.getTrajectory(points);
    	
        addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
    }
}
