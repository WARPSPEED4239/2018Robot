package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
    WPI_TalonSRX drivetrainMotorLeftOne = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftOne);
    WPI_TalonSRX drivetrainMotorRightFour = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFour);
    
    WPI_TalonSRX drivetrainMotorLeftTwo = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftTwo);
    WPI_TalonSRX drivetrainMotorRightFive = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFive);
    WPI_TalonSRX drivetrainMotorLeftThree = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftThree);
    WPI_TalonSRX drivetrainMotorRightSix = new WPI_TalonSRX(RobotMap.drivetrainMotorRightSix);
    
    
    DifferentialDrive drive = new DifferentialDrive(drivetrainMotorLeftOne, drivetrainMotorRightFour);
	
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DrivetrainArcadeDrive());
    }
    
    public void initialize() {
    	
    	drivetrainMotorLeftTwo.follow(drivetrainMotorLeftOne);
    	drivetrainMotorLeftThree.follow(drivetrainMotorLeftOne);
    	drivetrainMotorRightFive.follow(drivetrainMotorRightFour);
    	drivetrainMotorRightSix.follow(drivetrainMotorRightFour);
    	

        final int PEAK_CURRENT_LIMIT = 45;
        final int CONTINUOUS_CURRENT_LIMIT = 35;
        
        drivetrainMotorLeftOne.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT, 0);
        drivetrainMotorLeftOne.configPeakCurrentLimit(PEAK_CURRENT_LIMIT, 0);
        drivetrainMotorLeftOne.configPeakCurrentDuration(100, 0);
        drivetrainMotorLeftOne.enableCurrentLimit(true);
        
        drivetrainMotorRightFour.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT, 0);
        drivetrainMotorRightFour.configPeakCurrentLimit(PEAK_CURRENT_LIMIT, 0);
        drivetrainMotorRightFour.configPeakCurrentDuration(100, 0);
        drivetrainMotorRightFour.enableCurrentLimit(true);
    
        
        drivetrainMotorLeftOne.configOpenloopRamp(2, 0);
    	drivetrainMotorRightFour.configOpenloopRamp(2, 0);
    	
    	drivetrainMotorLeftTwo.configOpenloopRamp(0, 0);
    	drivetrainMotorLeftThree.configOpenloopRamp(0, 0);
    	drivetrainMotorRightFive.configOpenloopRamp(0, 0);
    	drivetrainMotorRightSix.configOpenloopRamp(0, 0);
    }
    
    public void arcadeDrive(double move, double rotate) {
    	drive.arcadeDrive(move, rotate);
    }
}