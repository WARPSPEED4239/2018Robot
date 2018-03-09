package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandCenter extends CommandGroup {

	public AutonCommandCenter(TargetPriority targetPriority, SwitchPosition switchPosition,	ScalePosition scalePosition) {
		Logger.log("AutonCommandCenter");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new AutonCrossAutoLine());
			return;
		}
		
		switch (switchPosition) {
		case Left:
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToLeftSwitch));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			
			/* Backup - Drive Straight, Rotate, etc...
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward5Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward7Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			*/
			break;
		case Right:		
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToRightSwitch));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			break;
		default:
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToRightSwitch));
			break;
		}
	}
	
}
