package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.IntakeInWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftDownWithTimeout;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandCenter extends CommandGroup {

	public AutonCommandCenter(TargetPriority targetPriority, SwitchPosition switchPosition,	ScalePosition scalePosition) {
		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "AutonCommandCenter");
		
		if (targetPriority == TargetPriority.Drive) {
			addSequential(new AutonCrossAutoLine());
			return;
		}

		TrajectoryResult firstResult;
		TrajectoryResult secondResult;
		TrajectoryResult thirdResult;
		TrajectoryResult fourthResult;
		TrajectoryResult fifthResult;
		
		switch (switchPosition) {
		case Left:
			firstResult = State.centerLeftSwitchFirstTrajectory;
			secondResult = State.centerLeftSwitchSecondTrajectory;
			thirdResult = State.centerLeftSwitchThirdTrajectory;
			fourthResult = State.centerLeftSwitchFourthTrajectory;
			fifthResult = State.centerLeftSwitchFifthTrajectory;
			
			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Left Trajectory Found");
			
			addParallel(new LiftUpWithTimeout(1.75));
			addSequential(new DrivetrainFollowProfile(firstResult.leftTrajectory, firstResult.rightTrajectory));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			//addParallel(new LiftDownWithTimeout(0.75));
			//addSequential(new DrivetrainFollowProfile(secondResult.leftTrajectory, secondResult.rightTrajectory));
			//addParallel(new IntakeInWithTimeout(2.5));
			//addSequential(new DrivetrainFollowProfile(thirdResult.leftTrajectory, thirdResult.rightTrajectory));
			//addSequential(new DrivetrainFollowProfile(fourthResult.leftTrajectory, fourthResult.rightTrajectory));
			//addParallel(new LiftUpWithTimeout(1.0));
			//addSequential(new DrivetrainFollowProfile(fifthResult.leftTrajectory, fifthResult.rightTrajectory));
			//addSequential(new AutonIntakeOutWithTimeout(0.5));
			break;
		case Right:
			firstResult = State.centerRightSwitchFirstTrajectory;
			secondResult = State.centerRightSwitchSecondTrajectory;
			thirdResult = State.centerRightSwitchThirdTrajectory;
			fourthResult = State.centerRightSwitchFourthTrajectory;
			fifthResult = State.centerRightSwitchFifthTrajectory;
			
			System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Right Trajectory Found");
			
			addParallel(new LiftUpWithTimeout(1.75));
			addSequential(new DrivetrainFollowProfile(firstResult.leftTrajectory, firstResult.rightTrajectory));
			addSequential(new AutonIntakeOutWithTimeout(0.5));
			//addParallel(new LiftDownWithTimeout(0.75));
			//addSequential(new DrivetrainFollowProfile(secondResult.leftTrajectory, secondResult.rightTrajectory));
			//addParallel(new IntakeInWithTimeout(2.5));
			//addSequential(new DrivetrainFollowProfile(thirdResult.leftTrajectory, thirdResult.rightTrajectory));
			//addSequential(new DrivetrainFollowProfile(fourthResult.leftTrajectory, fourthResult.rightTrajectory));
			//addParallel(new LiftUpWithTimeout(1.0));
			//addSequential(new DrivetrainFollowProfile(fifthResult.leftTrajectory, fifthResult.rightTrajectory));
			//addSequential(new AutonIntakeOutWithTimeout(0.5));
			break;
		default:
			addSequential(new AutonCrossAutoLine());
			break;
		}
	}
}
