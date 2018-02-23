package org.usfirst.frc.team4239.robot;

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
}