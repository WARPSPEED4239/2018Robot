package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RGBPrupleFlashingSlow extends CommandGroup {

	private int counter = 0;

	public RGBPrupleFlashingSlow() {
		while (counter < 999) {
			addSequential(new RGBBlack(), 0.5);
			addSequential(new RGBPurpleDim(), 0.5);
			counter = counter + 1;
		}
	}
}