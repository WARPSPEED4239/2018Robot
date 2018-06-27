package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainShifting extends Subsystem {

   private DoubleSolenoid drivetrainShifting = new DoubleSolenoid(RobotMap.drivetrainSolenoidHighGear, RobotMap.drivetrainSolenoidLowGear);

    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainHighGear());
    }
    
    public void drivetrainHighGear() {
    	drivetrainShifting.set(Value.kForward);
    }
    
    public void drivetrainLowGear() {
    	drivetrainShifting.set(Value.kReverse);
    }
}

