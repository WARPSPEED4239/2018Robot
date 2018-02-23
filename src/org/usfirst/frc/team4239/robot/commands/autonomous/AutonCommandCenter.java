package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOut;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Waypoint;

public class AutonCommandCenter extends CommandGroup {

	public AutonCommandCenter(TargetPriority targetPriority, SwitchPosition switchPosition,	ScalePosition scalePosition) {
		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "AutonCommandCenter");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new AutonCrossAutoLine());
			return;
		}

		Waypoint[] points;
		TrajectoryResult result;

		switch (switchPosition) {
		case Left:
			points = new Waypoint[] { new Waypoint(5.5, 7, 0), new Waypoint(0, 0, 0) };
			result = TrajectoryGenerator.getTrajectory(points);

			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Trajectory Found");
			
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addSequential(new AutonIntakeOut(1.0));
			break;
		case Right:
			points = new Waypoint[] { new Waypoint(5.5, -6.5, 0), new Waypoint(0, 0, 0) };
			result = TrajectoryGenerator.getTrajectory(points);
			
			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Trajectory Found");
			
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addSequential(new AutonIntakeOut(1.0));
			break;
		default:
			addSequential(new AutonCrossAutoLine());
			break;
		}
	}
}
