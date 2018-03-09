package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.motion.TrajectoryBuilder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivetrainCalculateTrackWidth extends CommandGroup {

    public DrivetrainCalculateTrackWidth(double targetAngle) {
    	double maxVelocity = Trajectories.kDefaultMaxVelocity;
    	double maxAcceleration = Trajectories.kDefaultMaxAcceleration;
    	double maxJerk = Trajectories.kDefaultMaxJerk;
    	addSequential(new DrivetrainFollowProfile(TrajectoryBuilder.getRotationTrajectory(maxVelocity, maxAcceleration, maxJerk, targetAngle)));
    }
    
}
