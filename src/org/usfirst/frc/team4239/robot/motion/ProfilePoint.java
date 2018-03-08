package org.usfirst.frc.team4239.robot.motion;

public class ProfilePoint {

	public ProfilePoint(double pos, double vel, double acc) {
		position = pos;
		velocity = vel;
		acceleration = acc;
		jerk = 0;
	}
	
	public ProfilePoint(double pos, double vel, double acc, double jrk) {
		position = pos;
		velocity = vel;
		acceleration = acc;
		jerk = jrk;
	}
	
	public double position;
	public double velocity;
	public double acceleration;
	public double jerk;
}
