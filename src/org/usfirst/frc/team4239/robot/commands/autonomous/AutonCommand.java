package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
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

	public AutonCommand(AutoType autoType,StartingPosition startingPosition, TargetPriority targetPriority, PossibleCollision possibleCollision, SwitchPosition switchPosition, ScalePosition scalePosition) {
		Logger.log("AutonCommand");
		Logger.log("AutoType: " + autoType.name());
		Logger.log("StartingPosition: " + startingPosition.name());
		Logger.log("TargetPriority: " + targetPriority.name());
		Logger.log("SwitchPosition: " + switchPosition.name());
		Logger.log("Scale Position: " + scalePosition.name());
		
		switch (startingPosition) {
		case Left:
			addSequential(new AutonCommandLeft(autoType, targetPriority, possibleCollision, switchPosition, scalePosition));
			break;
		case Center:
			addSequential(new AutonCommandCenter(autoType, targetPriority, possibleCollision,  switchPosition, scalePosition));
			break;
		case Right:
			addSequential(new AutonCommandRight(autoType, targetPriority, possibleCollision, switchPosition, scalePosition));
			break;
		default:
			// If the position of the robot is not set on the sendable chooser, drive to auto line
			addSequential(new AutonCrossAutoLine());
			break;
		}

	}

}
