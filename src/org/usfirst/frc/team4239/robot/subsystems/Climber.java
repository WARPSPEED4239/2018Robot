package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	private Talon climber = new Talon (RobotMap.climberMotor);
	
    public void initDefaultCommand() {
        setDefaultCommand(new ClimberStop());
    }    
    
    public void climberStop() {
    	climber.set(0);
    }
    
    public void climberClimb() {
    	climber.set(-1.0);
    }
    
    public void climberReverse() {
    	climber.set(1.0);
    }
}