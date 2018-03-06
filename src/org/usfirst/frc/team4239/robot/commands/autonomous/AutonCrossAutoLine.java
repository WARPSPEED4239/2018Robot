package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonCrossAutoLine extends CommandGroup {

	public AutonCrossAutoLine() {

		TrajectoryResult result = State.crossAutoLineTrajectory;
		//addSequential(new WaitCommand (12.0));
		addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
	}
}
