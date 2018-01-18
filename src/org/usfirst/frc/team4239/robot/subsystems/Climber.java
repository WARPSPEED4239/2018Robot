package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private Spark climberMotor;
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberStop());
    }
    
    public void ClimberClimb() {
    	climberMotor.set(1.0);    
    	}
    
    public void ClimberReverse() {
    	climberMotor.set(-1.0);
    }
    
    public void  ClimberStop () {
    	climberMotor.set(0);
    }
}

