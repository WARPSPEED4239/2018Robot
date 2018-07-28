package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.IntakeInWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithDelay;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandCenter extends CommandGroup {

	public AutonCommandCenter(AutoType autoType, TargetPriority targetPriority, PossibleCollision possibleCollision, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommandCenter");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new DrivetrainFollowProfile(Trajectories.centerRightSwitch1)); 
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
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerLeftSwitch1));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addParallel(new LiftDownWithDelay(0.5, 1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerLeftSwitch2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward44In));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward44In));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerLeftSwitch3));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addParallel(new LiftDownWithDelay(0.5, 1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward7Ft));
			break;
		case Right:
			addParallel(new DrivetrainHighGear());
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerRightSwitch1));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addParallel(new LiftDownWithDelay(0.5, 1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerRightSwitch2));
			addParallel(new IntakeInWithTimeout(2.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward44In));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward44In));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.centerRightSwitch3));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			addParallel(new LiftDownWithDelay(0.5, 1.0));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward7Ft));
			break;
		default:
			addParallel(new DrivetrainHighGear());
			addSequential(new DrivetrainFollowProfile(Trajectories.centerRightSwitch1));
			break;
		}
	}
}