package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandLeft extends CommandGroup {

	  public AutonCommandLeft(TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
		  System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "AutonCommandLeft");
		  
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
	       
			TrajectoryResult result;
	        
	        if (doSwitch) {
				result = State.leftSwitchTrajectory;
				
				addParallel(new LiftUpWithTimeout(1.75));
				addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
				addSequential(new AutonIntakeOutWithTimeout(1.0));
				
	        }
	        else if (doScale) {
				result = State.leftScaleTrajectory;
				
				addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
				addSequential(new LiftUpWithTimeout(1.75));
				addSequential(new AutonIntakeOutWithTimeout(1.0));
	        }
	        else {
	            addSequential(new AutonCrossAutoLine());
	        }
	    }
	}