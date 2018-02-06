package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private Spark intakeLeft = new Spark (RobotMap.intakeMotorLeft);
    private Spark intakeRight = new Spark (RobotMap.intakeMotorRight);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeStop());
    }
    
    public void intakeStop() {
    	intakeLeft.set(0.0);
    	intakeRight.set(0.0);
    }
    
    public void intakeIn() {
    	intakeLeft.set(-0.4);
    	intakeRight.set(-0.4);
    }
    
    public void intakeOut(double speed) {
    	intakeLeft.set(speed);
    	intakeRight.set(speed);
    }
}