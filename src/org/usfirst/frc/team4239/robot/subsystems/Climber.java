package org.usfirst.frc.team4239.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	climberMotor.set(1.0);    
    }
}

