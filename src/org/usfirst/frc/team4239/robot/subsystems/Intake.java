package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private Spark intakeLeft = new Spark (RobotMap.intakeMotorLeft);
    private Spark intakeRight = new Spark (RobotMap.intakeMotorRight);
    
    private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(RobotMap.intakeSolenoidUp, RobotMap.intakeSolenoidDown);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeStop());
    }
    
    public void intakeStop() {
    	intakeLeft.set(0);
    	intakeRight.set(0);
    }
    
    public void intakeIn() {
    	intakeLeft.set(1.0);
    	intakeRight.set(1.0);
    }
    
    public void intakeOut() {
    	intakeLeft.set(-1.0);
    	intakeRight.set(-1.0);
    }
    
    public void intakeUp () {
    	intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void intakeDown() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}