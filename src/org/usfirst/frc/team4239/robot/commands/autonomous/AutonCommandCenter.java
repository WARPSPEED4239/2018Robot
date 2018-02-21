package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOut;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.LiftUp;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
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
			points = new Waypoint[] { new Waypoint(3, 7.5, 0), new Waypoint(0, 0, 0) };
			result = TrajectoryGenerator.getTrajectory(points);

			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Trajectory Found");
			
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addParallel(new DrivetrainHighGear());
			addParallel(new WaitCommand(2));
			//addSequential(new LiftUp(), 2);
			addSequential(new AutonIntakeOut());
			break;
		case Right:
			points = new Waypoint[] { new Waypoint(10, -5.7, 0), new Waypoint(0, 0, 0) };
			result = TrajectoryGenerator.getTrajectory(points);
			
			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Trajectory Found");
			
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addParallel(new DrivetrainHighGear());
			addParallel(new WaitCommand(2));
			//addSequential(new LiftUp(), 2);
			addSequential(new AutonIntakeOut());
			break;
		default:
			addSequential(new AutonCrossAutoLine());
			break;
		}
	}
}
