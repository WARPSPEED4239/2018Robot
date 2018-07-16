package org.usfirst.frc.team4239.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RGBRedFlashingFast extends CommandGroup {

	private int counter = 0;

	public RGBRedFlashingFast() {
		while (counter < 999) {
			addSequential(new RGBBlack(), 0.05);
			addSequential(new RGBRed(), 0.05);
			counter = counter + 1;
		}
	}
}