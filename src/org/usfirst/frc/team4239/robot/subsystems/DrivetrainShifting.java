package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainShifting extends Subsystem {

	private DoubleSolenoid drivetrainSolenoid = new DoubleSolenoid(RobotMap.drivetrainSolenoidHighGear, RobotMap.drivetrainSolenoidLowGear);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DrivetrainHighGear());
    }
    
    public void drivetrainHighGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void drivetrainLowGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

