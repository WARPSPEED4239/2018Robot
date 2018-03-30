package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.StartingPosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonCommand extends CommandGroup {

	public AutonCommand(StartingPosition startingPosition, TargetPriority targetPriority, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommand");
		Logger.log("StartingPosition: " + startingPosition.name());
		Logger.log("TargetPriority: " + targetPriority.name());
		Logger.log("SwitchPosition: " + switchPosition.name());
		Logger.log("Scale Position: " + scalePosition.name());
		
		switch (startingPosition) {
		case Left:
			addSequential(new AutonCommandLeft(targetPriority, switchPosition, scalePosition));
			break;
		case Center:
			addSequential(new AutonCommandCenter(targetPriority, switchPosition, scalePosition));
			break;
		case Right:
			addSequential(new AutonCommandRight(targetPriority, switchPosition, scalePosition));
			break;
		default:
			// if position is unknown, try to cross auto line
			addSequential(new AutonCrossAutoLine());
			break;
		}

	}

}
