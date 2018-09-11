package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOut extends Command {

	private boolean isPreset;
	private double presetSpeed;
	
    public IntakeOut() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.rgbController.setColor(Color.Purple);
    }

    protected void execute() {
    	double speed = 0.0;
    	
    	if (isPreset) {
    		speed = presetSpeed;
    	}
    	else {
    		double speedFromJoystick = Robot.oi.getJoystick().getThrottle();  // 1 to -1
    		speedFromJoystick *= -1;                                          // -1 to 1
    		speedFromJoystick += 1;                                           // 0 to 2
    		speedFromJoystick *= 0.3;                                         // 0 to 0.6
    		speedFromJoystick += 0.8;                                         // 0.4 to 1
    		speedFromJoystick -= 0.40;
    		speed = speedFromJoystick;
    
    	Robot.intake.intakeOut(speed);
    	
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.intakeStop();
    	Robot.rgbController.setColor(Color.Red);
    }

    protected void interrupted() {
    }
}