package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeoutScale;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.IntakeInWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithDelay;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithDelay;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
		boolean doOpScale = false;
		boolean doScaleAndSwitch = false;

		if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased) {
			doScaleAndSwitch = (targetPriority == TargetPriority.Switch);
			doScale = (targetPriority == TargetPriority.Scale);
		} 
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.TargetBased) {
			doSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.TargetBased) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.TargetBased) {
			doOpSwitch = (targetPriority == TargetPriority.Switch);
			doOpScale = (targetPriority == TargetPriority.Scale);
		}
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left && autoType == AutoType.RobotAlignmentBased) {
			doScale = (targetPriority == TargetPriority.Scale);
			doScaleAndSwitch = (targetPriority == TargetPriority.Switch);
		} 
		
		else if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased) {
			doSwitch = true;
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Left && autoType == AutoType.RobotAlignmentBased) {
			doScale = true;
		} 
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased && possibleCollision == PossibleCollision.No) {
			doOpScale = (targetPriority == TargetPriority.Scale);
			doOpSwitch = (targetPriority == TargetPriority.Switch);
		}
		
		else if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right && autoType == AutoType.RobotAlignmentBased && possibleCollision == PossibleCollision.Yes) {
			addSequential(new AutonCrossAutoLine());
		}

		if (doSwitch) {
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftSwitchSpline));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward18In));
			addSequential(new LiftDownWithTimeout(1.0));
			
			/*addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addParallel(new LiftDownWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));*/
		}

		else if (doOpSwitch) {
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithDelay(2.5, 1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftBackSwitchSpline));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addParallel(new LiftDownWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward18In));
			addParallel(new IntakeInWithTimeout(1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18In));
			addSequential(new LiftUpWithTimeout(1.5));
			addParallel(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18In));
			addParallel(new LiftDownWithDelay(0.5, 1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward7Ft));
			
			/*addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward15Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutScale(1.0));*/
		}

		else if (doScale) {
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithDelay(0.5, 3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline1));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline3));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline4));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline5));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline6));
			addParallel(new LiftDownWithDelay(0.5, 3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline7));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline5));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline2));
			
			/*addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));*/
		}

		else if (doOpScale) {
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithDelay(1.0, 3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftBackScaleSpline1));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftBackScaleSpline2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rightScaleSpline3));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rightScaleSpline4));
			addSequential(new DrivetrainFollowProfile(Trajectories.rightScaleSpline5));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rightScaleSpline2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.rightScaleSpline6));
			
			
			/*addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft100Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutScale(1.0));*/
		}

		else if (doScaleAndSwitch) {
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithDelay(0.5, 3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline1));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
			addParallel(new LiftDownWithDelay(0.5, 3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline3));
			addParallel(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward18In));
			addSequential(new DrivetrainFollowProfile(Trajectories.leftScaleSpline4));
			
			//1: Scale
			//2: Scale
			//3: Switch
			
			//or
			
			//1: Scale
			//2: Switch
			//3: Scale?
			
			/*addSequential(new DrivetrainFollowProfile(Trajectories.driveForward20Ft));
			addParallel(new LiftUpWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight60Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward2Ft));
			addSequential(new WaitCommand(0.75));
			addSequential(new AutonIntakeOutScale(1.0));
			addParallel(new LiftDownWithTimeout(3.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight94Degrees));
			addParallel(new IntakeInWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOut(0.5));*/
		}

		else {
			addSequential(new AutonCrossAutoLine());
		}
	}
}