package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
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

public class AutonCommandRight extends CommandGroup {

	public AutonCommandRight(AutoType autoType, TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommandRight");

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
		boolean doOpScale = false;
		boolean doScaleAndSwitch = false;

		if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right) {
			doScaleAndSwitch = true;
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased) {
			doSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.TargetBased) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.RobotAlignmentBased) {
			doSwitch = true;
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased) {
			doScale = true;
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left && autoType == AutoType.RobotAlignmentBased) {
			doOpScale = true; //CHANGE THIS IF OP SCALE IS GOING TO RUN INTO THERE AUTO TO: addSequential(new AutonCrossAutoLine());
		}
			
		if (doSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addParallel(new LiftDownWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
		}

		else if (doOpSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward15Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
		}

		else if (doOpScale) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight100Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
		}

		else if (doScaleAndSwitch) {
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft94Degrees));
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