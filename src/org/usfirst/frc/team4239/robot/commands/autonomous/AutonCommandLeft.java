package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeoutScale;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.IntakeInWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonCommandLeft extends CommandGroup {

	public AutonCommandLeft(TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommandLeft");

		if (targetPriority == TargetPriority.Drive) {
			addSequential(new AutonCrossAutoLine());
			return;
		}

		boolean doSwitch = false;
		boolean doOpSwitch = false;
		boolean doScale = false;
		boolean doOpScale = false;
		boolean doScaleAndSwitch = false;

		if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left) {
			doScaleAndSwitch = true;
		} 
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right) {
			doSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doScale = (targetPriority == TargetPriority.Scale);
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		}

		if (doSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addParallel(new LiftDownWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
		}

		else if (doOpSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward16Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward21Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight45Degrees));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new WaitCommand(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
		}

		else if (doOpScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward16Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward16Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScaleAndSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward21Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight45Degrees));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward5Ft));
			addSequential(new LiftDownWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
		}

		else {
			addSequential(new AutonCrossAutoLine());
		}
	}
}