package org.usfirst.frc.team4239.robot;

public class State {
	public static enum StartingPosition {
		Left, Center, Right, Unknown
	}

	public static enum TargetPriority {
		Switch, Scale, Drive, DriveNoSensors, DoNothing, Unknown
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