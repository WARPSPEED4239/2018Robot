package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.LiftStop;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {

    private Spark lift = new Spark (RobotMap.liftMotor);
    
    private DigitalInput liftTopSwitch = new DigitalInput(RobotMap.liftTopSwitch);
    private DigitalInput liftBottomSwitch = new DigitalInput(RobotMap.liftBottomSwitch);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftStop());
    }
    
    public void liftStop() {
    	updateSmartDashboard();
    	lift.set(0);
    }
    
    public void liftUp() {
    	updateSmartDashboard();
    	lift.set(1.0);
    }
    
    public void liftDown() {
    	updateSmartDashboard();
    	lift.set(1);
    }
    
    public boolean getTopSwitch() {
    	return !liftTopSwitch.get();
    }
    
    public boolean getBottomSwitch() {
    	return !liftBottomSwitch.get();
    }
    
    private void updateSmartDashboard() {
    	SmartDashboard.putBoolean("Top Limit Switch", getTopSwitch());
    	SmartDashboard.putBoolean("Bottom Limit Switch", getBottomSwitch());
    }
}