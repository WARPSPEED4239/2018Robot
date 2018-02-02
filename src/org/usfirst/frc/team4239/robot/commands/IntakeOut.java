package org.usfirst.frc.team4239.robot.commands;

import org.usfirst.frc.team4239.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOut extends Command {

	private boolean isPreset;
	private double presetSpeed;
	
    public IntakeOut() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}