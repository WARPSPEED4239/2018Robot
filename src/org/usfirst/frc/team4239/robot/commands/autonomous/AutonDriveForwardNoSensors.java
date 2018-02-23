package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.DrivetrainSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDriveForwardNoSensors extends CommandGroup {

    public AutonDriveForwardNoSensors() {
    	addParallel(new DrivetrainHighGear());
        addParallel(new DrivetrainSetSpeed(0.75, 0.0, 4));
    }
}