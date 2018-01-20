package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.LiftStop;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    private Spark lift = new Spark (RobotMap.liftMotor);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftStop());
    }
    
    public void liftStop() {
    	lift.set(0);
    }
    
    public void liftUp() {
    	lift.set(1.0);
    }
    
    public void liftDown() {
    	lift.set(-1.0);
    }
}

