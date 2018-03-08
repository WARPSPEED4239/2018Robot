package org.usfirst.frc.team4239.robot.motion;

import java.util.ArrayList;
import java.util.List;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class TrajectoryResult {
	
	private ProfilePoint[] mLeftProfile;
	private ProfilePoint[] mRightProfile;
	
	private boolean mIsValid = false;
	private int mLength;
	private double mRuntime;
	
	public TrajectoryResult(Trajectory leftTrajectory, Trajectory rightTrajectory, boolean invertLeft, boolean invertRight) {
		if (leftTrajectory == null || leftTrajectory.segments == null || leftTrajectory.segments.length <= 0) {
			return;
		}
		if (rightTrajectory == null || rightTrajectory.segments == null || rightTrajectory.segments.length <= 0) {
			return;
		}
		if (leftTrajectory.segments.length != rightTrajectory.segments.length) {
			return;
		}
		if (leftTrajectory.segments[0].dt != rightTrajectory.segments[0].dt) {
			return;
		}
		
		mIsValid = true;
		mLength = leftTrajectory.segments.length;
		mRuntime = leftTrajectory.segments[0].dt * mLength;
		mLeftProfile = convertTrajectoryToProfile(leftTrajectory, invertLeft);
		mRightProfile = convertTrajectoryToProfile(rightTrajectory, invertRight);
	}
	
	public ProfilePoint[] getLeftProfile() {
		return mLeftProfile;
	}
	
	public ProfilePoint getLeftAtTime(double time) {
		return getLeftAtIndex(getIndex(time));
	}
	
	public ProfilePoint getRightAtTime(double time) {
		return getRightAtIndex(getIndex(time));
	}
	
	public ProfilePoint[] getRightProfile() {
		return mRightProfile;
	}
	
	public ProfilePoint getLeftAtIndex(int index) {
		return mLeftProfile[index];
	}
	
	public ProfilePoint getRightAtIndex(int index) {
		return mRightProfile[index];
	}
	
	public int getLength() {
		return mLength;
	}
	
	public double getRuntime() {
		return mRuntime;
	}
	
	public boolean isValid() {
		return mIsValid;
	}
	
	private ProfilePoint[] convertTrajectoryToProfile(Trajectory trajectory, boolean isInverted) {
		List<ProfilePoint> profile = new ArrayList<>();
		
		double pos, vel, acc, jrk;
		
		for (Segment s : trajectory.segments) {
			pos = isInverted ? -s.position : s.position;
			vel = isInverted ? -s.velocity : s.velocity;
			acc = isInverted ? -s.acceleration : s.acceleration;
			jrk = isInverted ? -s.jerk : s.jerk;
			profile.add(new ProfilePoint(pos, vel, acc, jrk));
		}
		
		return profile.toArray(new ProfilePoint[profile.size()]);
	}
	
	private int getIndex(double time) {
		int index = (int) (time / TrajectoryBuilder.DELTA_TIME);
		if (index >= mLength) {
			index = mLength - 1;
		}
		return index;
	}
	
}
