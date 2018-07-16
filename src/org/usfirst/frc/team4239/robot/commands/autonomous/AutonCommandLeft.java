package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
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

	public AutonCommandLeft(AutoType autoType, TargetPriority targetPriority, PossibleCollision possibleCollision, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommandLeft");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new AutonCrossAutoLine());
			return;
		}

		if (targetPriority == TargetPriority.DriveNoSensors) {
			addSequential(new AutonDriveForwardNoSensors());
			return;
		}
		
		if (targetPriority == TargetPriority.DoNothing) {
			return;
		}

		boolean doSwitch = false;
		boolean doOpSwitch = false;
		boolean doScale = false;
		boolean doScaleSpline = false;
		boolean doOpScale = false;
		boolean doScaleAndSwitch = false;

		if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left) {
			doScaleAndSwitch = true;
		} 
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.TargetBased) {
			doSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased && possibleCollision == PossibleCollision.No) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doScaleSpline = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased && possibleCollision == PossibleCollision.Yes) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.TargetBased) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased) {
			doSwitch = true;
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.RobotAlignmentBased) {
			doScaleSpline = true;
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased && possibleCollision == PossibleCollision.No) {
			doOpScale = true;
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased && possibleCollision == PossibleCollision.Yes) {
			addSequential(new AutonCrossAutoLine());
		}

		if (doSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addParallel(new LiftDownWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
		}

		else if (doOpSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward15Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
		}
		
		else if (doScaleSpline) {
			addParallel(new DrivetrainFollowProfile(Trajectories.leftScaleSpline));
			addSequential(new WaitCommand(3.0));
			addSequential(new LiftUpWithTimeout(3.5));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
		}


		else if (doOpScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft100Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScaleAndSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight94Degrees));
			addParallel(new IntakeInWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
		}

		else {
			addSequential(new AutonCrossAutoLine());
		}
	}
}