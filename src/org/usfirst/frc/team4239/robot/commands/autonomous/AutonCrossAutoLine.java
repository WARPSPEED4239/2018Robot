package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCrossAutoLine extends CommandGroup {

	public AutonCrossAutoLine() {
		Logger.log("AutonCrossAutoLine");
		addSequential(new DrivetrainFollowProfile(Trajectories.crossAutoLine));
	}
	
}
