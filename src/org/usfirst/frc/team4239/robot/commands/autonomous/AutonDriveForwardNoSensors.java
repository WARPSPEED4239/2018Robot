package org.usfirst.frc.team4239.robot.commands.autonomous;

import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.DrivetrainSetSpeed;
import org.usfirst.frc.team4239.robot.tools.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonDriveForwardNoSensors extends CommandGroup {

    public AutonDriveForwardNoSensors() {
    	Logger.log("AutonDriveForwardNoSensors");
    	addParallel(new DrivetrainHighGear());
    	//addSequential(new WaitCommand(12.0));
    	addSequential(new DrivetrainSetSpeed(-1.0, 0.0, 1));
    }
}