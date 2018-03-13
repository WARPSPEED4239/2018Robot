package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeout;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOutWithTimeoutScale;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommandRight extends CommandGroup {

    public AutonCommandRight(TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
    	Logger.log("AutonCommandRight");
    	
        if (targetPriority == TargetPriority.Drive || targetPriority == TargetPriority.Unknown) {
            addSequential(new AutonCrossAutoLine());
            return;
        }
        
        boolean doSwitch = false;
        boolean doScale = false;
        
        if (switchPosition == SwitchPosition.Right && scalePosition == ScalePosition.Right) {
            doSwitch = (targetPriority == TargetPriority.Switch);
            doScale = (targetPriority == TargetPriority.Scale);
        }
        else if (switchPosition == SwitchPosition.Right) {
            doSwitch = true;
        }
        else if (scalePosition == ScalePosition.Right) {
            doScale = true;
        }
        
        if (doSwitch) {
        	addSequential(new DrivetrainFollowProfile(Trajectories.driveForward12Ft));
			addParallel(new LiftUpWithTimeout(1.5));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveForward1Ft));
			addSequential(new AutonIntakeOutWithTimeout(1.0));
        }
        
        else if (doScale) {
        	addSequential(new DrivetrainFollowProfile(Trajectories.driveForward25Ft));
			addSequential(new DrivetrainFollowProfile(Trajectories.rotateLeft90Degrees));
			addSequential(new DrivetrainFollowProfile(Trajectories.driveBackward2Ft));
			addSequential(new LiftUpWithTimeout(3.5));
			addSequential(new AutonIntakeOutWithTimeoutScale(1.0));
        }
        
        else {
            addSequential(new AutonCrossAutoLine());
        }
    }
}