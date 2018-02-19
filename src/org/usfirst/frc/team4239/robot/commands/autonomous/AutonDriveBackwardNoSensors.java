package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.DrivetrainSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDriveBackwardNoSensors extends CommandGroup {

    public AutonDriveBackwardNoSensors() {
        addParallel(new DrivetrainHighGear());
    	addParallel(new DrivetrainSetSpeed(-1.0, 0.0, 4));
    }
}