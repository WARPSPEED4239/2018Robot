package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.IntakeInWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandCenter extends CommandGroup {

	public AutonCommandCenter(AutoType autoType, TargetPriority targetPriority, PossibleCollision possibleCollision, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommandCenter");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToRightSwitch)); 
			return;
		}
		
		if (targetPriority == TargetPriority.DriveNoSensors) {
			addSequential(new AutonDriveForwardNoSensors());
			return;
		}
		
		if (targetPriority == TargetPriority.DoNothing) {
			return;
		}

		switch (switchPosition) {
		case Left:
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToLeftSwitch));
			addParallel(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addParallel(new LiftDownWithTimeout(0.75));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight87Degrees));
			addParallel(new IntakeInWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			break;
		case Right:
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToRightSwitch));
			addParallel(new AutonIntakeOutWithTimeout(0.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addParallel(new LiftDownWithTimeout(0.75));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addParallel(new IntakeInWithTimeout(3.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight87Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward3Ft));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			break;
		default:
			addSequential(new DrivetrainFollowProfile(Trajectories.centerToRightSwitch));
			break;
		}
	}
}