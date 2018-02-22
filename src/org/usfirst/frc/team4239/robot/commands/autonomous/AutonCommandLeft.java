package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.AutonIntakeOut;
import org.usfirst.frc.team4239.robot.commands.DrivetrainFollowProfile;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.LiftUpWithTimeout;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.motion.TrajectoryResult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

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
	       
	        Waypoint[] points;
			TrajectoryResult result;
	        
	        if (doSwitch) {
	        	points = new Waypoint[] { new Waypoint(13, 2.1666, Pathfinder.d2r(90)), new Waypoint(0, 0, 0) }; 	//change points
				result = TrajectoryGenerator.getTrajectory(points);
				
				addParallel(new DrivetrainHighGear());
				addParallel(new LiftUpWithTimeout(1.0));
				addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
				addSequential(new AutonIntakeOut(1.0));
				
	        }
	        else if (doScale) {
	        	points = new Waypoint[] { new Waypoint(24, 0, Pathfinder.d2r(90)), new Waypoint(0, 0, 0) }; 	//change points
				result = TrajectoryGenerator.getTrajectory(points);
				
				addParallel(new DrivetrainHighGear());
				addSequential(new DrivetrainFollowProfile(result.leftTrajectory, result.rightTrajectory));
				addSequential(new LiftUpWithTimeout(2.5));
				addSequential(new AutonIntakeOut(1.0));
	        }
	        else {
	            addSequential(new AutonCrossAutoLine());
	        }
	    }
	}