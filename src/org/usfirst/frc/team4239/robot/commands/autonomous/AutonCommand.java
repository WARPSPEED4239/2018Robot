package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.State.ControlMode;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.StartingPosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonCommand extends CommandGroup {

	public AutonCommand(ControlMode controlMode, StartingPosition startingPosition, TargetPriority targetPriority,
			SwitchPosition switchPosition, ScalePosition scalePosition) {

		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "AutonCommand");
		
		if (controlMode == ControlMode.DoNothing) {
			return;
		}

		if (controlMode == ControlMode.NoSensors) {
			addSequential(new AutonDriveForwardNoSensors());
			return;
		}

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
