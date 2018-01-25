package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.IntakePivotDown;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakePivot extends Subsystem {

	 private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(RobotMap.intakePivotSolenoidUp, RobotMap.intakePivotSolenoidDown);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakePivotDown());
    }
    public void intakePivotUp () {
    	intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void intakePivotDown() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

