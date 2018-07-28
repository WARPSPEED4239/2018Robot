package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LiftUpWithDelay extends CommandGroup {

    public LiftUpWithDelay(double seconds, double liftTime) {
    	if (seconds <= 0) {
    		seconds = 0;
    	}
    	
    	addSequential(new WaitCommand(seconds));
    	addSequential(new LiftUp(), liftTime);            //edit timeout?
    }
}