package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandLeft extends CommandGroup {

	  public AutonCommandLeft(TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
		  	Logger.log("AutonCommandLeft");
		  
		    if (targetPriority == TargetPriority.Drive || targetPriority == TargetPriority.Unknown) {
	            addSequential(new AutonCrossAutoLine());
	            return;
	        }
	        
	        boolean doSwitch = false;
	        boolean doScale = false;
	        
	        if (switchPosition == SwitchPosition.Left && scalePosition == ScalePosition.Left) {
	            doSwitch = (targetPriority == TargetPriority.Switch);
	            doScale = (targetPriority == TargetPriority.Scale);
	        }
	        else if (switchPosition == SwitchPosition.Left) {
	            doSwitch = true;
	        }
	        else if (scalePosition == ScalePosition.Left) {
	            doScale = true;
	        }
	        
	        if (doSwitch) {
				addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
				addParallel(new LiftUpWithTimeout(1.5));
				addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
				addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
				addSequential(new AutonIntakeOutWithTimeout(1.0));
	        }
	        /*
	        else if (doScale) {
	        	addSequential(new DrivetrainFollowProfile(Trajectories.driveForward25Ft));
				addParallel(new LiftUpWithTimeout(3.5));
				addSequential(new DrivetrainFollowProfile(Trajectories.rotateRight90Degrees));
				addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
				addSequential(new AutonIntakeOutWithTimeout(1.0));
				addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward3Ft));
	        }
	        */
	        else {
	            addSequential(new AutonCrossAutoLine());
	        }
	    }
	}