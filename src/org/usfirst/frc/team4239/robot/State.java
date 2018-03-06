package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

public class State {
	public static enum ControlMode {
		Standard, NoSensors, DoNothing
	}

	public static enum StartingPosition {
		Left, Center, Right, Unknown
	}

	public static enum TargetPriority {
		Switch, Scale, Drive, Unknown
	}

	public static enum ScalePosition {
		Left, Right, Unknown
	}

	public static enum SwitchPosition {
		Left, Right, Unknown
	}
	public static TrajectoryResult crossAutoLineTrajectory;
	
	public static TrajectoryResult leftSwitchTrajectory;
	public static TrajectoryResult leftScaleTrajectory;
	
	public static TrajectoryResult centerLeftSwitchFirstTrajectory;
	public static TrajectoryResult centerRightSwitchFirstTrajectory;
	public static TrajectoryResult centerLeftSwitchSecondTrajectory;
	public static TrajectoryResult centerRightSwitchSecondTrajectory;
	public static TrajectoryResult centerLeftSwitchThirdTrajectory;
	public static TrajectoryResult centerRightSwitchThirdTrajectory;
	public static TrajectoryResult centerLeftSwitchFourthTrajectory;
	public static TrajectoryResult centerRightSwitchFourthTrajectory;
	public static TrajectoryResult centerLeftSwitchFifthTrajectory;
	public static TrajectoryResult centerRightSwitchFifthTrajectory;
	
	public static TrajectoryResult rightSwitchTrajectory;
	public static TrajectoryResult rightScaleTrajectory;
}