package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOut;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class AutonCommandRight extends CommandGroup {

    public AutonCommandRight(TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
    	System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "AutonCommandRight");
    	
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
       
        Waypoint[] points;
		TrajectoryResult result;
        
        if (doSwitch) {
        	points = new Waypoint[] { new Waypoint(13, -2.1666, Pathfinder.d2r(-90)), new Waypoint(0, 0, 0) }; 	//change points
			result = TrajectoryGenerator.getTrajectory(points);
		
			double delay = result.runtime - 2;
			if (delay < 0) {
			    delay = 0;
			}
			
			addParallel(new DrivetrainHighGear());
			//addParallel(new LiftUpWithDelay(delay, 2));
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addSequential(new AutonIntakeOut(1));
			
        }
        else if (doScale) {
        	points = new Waypoint[] { new Waypoint(24, 0, Pathfinder.d2r(-90)), new Waypoint(0, 0, 0) }; 	//change points
			result = TrajectoryGenerator.getTrajectory(points);
        
			double delay = result.runtime - 4;
			if (delay < 0) {
			    delay = 0;
			}
			
			addParallel(new DrivetrainHighGear());
			//addParallel(new LiftUpWithDelay(delay, 4));
			addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
			addSequential(new AutonIntakeOut(1));
        }
        else {
            addSequential(new AutonCrossAutoLine());
        }
    }
}