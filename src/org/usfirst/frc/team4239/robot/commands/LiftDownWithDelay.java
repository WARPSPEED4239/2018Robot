package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LiftDownWithDelay extends CommandGroup {

    public LiftDownWithDelay(double seconds, double liftTime) {
    	if (seconds <= 0) {
    		seconds = 0;
    	}
    	
    	addSequential(new WaitCommand(seconds));
    	addSequential(new LiftDown(), liftTime);            //edit timeout?
    }
}