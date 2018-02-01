package org.usfirst.frc.team4239.robot.commands.multitasks;

import org.usfirst.frc.team4239.robot.commands.ClimberClimb;
import org.usfirst.frc.team4239.robot.commands.ClimberServoForward;
import org.usfirst.frc.team4239.robot.commands.LiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ClimberClimbMulti extends CommandGroup {
    public ClimberClimbMulti() {
    	addParallel(new LiftDown(), 3);
    	addParallel(new WaitCommand(1));
    	addSequential(new ClimberClimb(), 4.5);
    	addParallel(new WaitCommand(2));
    	addSequential(new ClimberServoForward());
    }
}