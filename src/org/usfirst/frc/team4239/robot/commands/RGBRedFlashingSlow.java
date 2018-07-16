package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RGBRedFlashingSlow extends CommandGroup {

	private int counter = 0;

	public RGBRedFlashingSlow() {
		while (counter < 999) {
			addSequential(new RGBBlack(), 0.5);
			addSequential(new RGBRedDim(), 0.5);
			counter = counter + 1;
		}
	}
}