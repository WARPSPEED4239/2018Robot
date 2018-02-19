package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.AutonIntakeOut;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.LiftUp;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Waypoint;

/**
 *
 */
public class AutonCenterSwitchPlace extends CommandGroup {

	public AutonCenterSwitchPlace() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				Waypoint[] leftPoints = new Waypoint[] { new Waypoint(10, 5.5, 0), new Waypoint(0, 0, 0) };

				TrajectoryResult leftResult = TrajectoryGenerator.getTrajectory(leftPoints);
				
				addParallel(new DrivetrainHighGear());
				addParallel(new DrivetrainFollowProfile(leftResult.leftTrajectory, leftResult.rightTrajectory));
				addParallel(new LiftUp(),1.5);
				addSequential(new AutonIntakeOut());
				
			} else {
				Waypoint[] rightPoints = new Waypoint[] { new Waypoint(10, -6.1666, 0), new Waypoint(0, 0, 0) };

				TrajectoryResult rightResult = TrajectoryGenerator.getTrajectory(rightPoints);
				
				addParallel(new DrivetrainHighGear());
				addParallel(new DrivetrainFollowProfile(rightResult.leftTrajectory, rightResult.rightTrajectory));
				addParallel(new LiftUp(),1.5);
				addSequential(new AutonIntakeOut());
			}
		}
	}
}
