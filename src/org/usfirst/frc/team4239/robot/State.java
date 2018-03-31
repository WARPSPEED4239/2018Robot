package org.usfirst.frc.team4239.robot;

public class State {
	public static enum AutoType {
		RobotAlignmentBased, TargetBased, NA
	}
	
	public static enum StartingPosition {
		Left, Center, Right
	}

	public static enum TargetPriority {
		Switch, Scale, Drive, DriveNoSensors, DoNothing, NA
	}

	public static enum ScalePosition {
		Left, Right, Unknown
	}

	public static enum SwitchPosition {
		Left, Right, Unknown
	}
	
	public static enum CodeExecutionState {
		Competition,
		Debug
	}
}