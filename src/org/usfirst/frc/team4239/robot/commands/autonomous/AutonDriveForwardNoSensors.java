package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDriveForwardNoSensors extends CommandGroup {

    public AutonDriveForwardNoSensors() {
        addSequential(new DrivetrainSetSpeed());
    }
}
