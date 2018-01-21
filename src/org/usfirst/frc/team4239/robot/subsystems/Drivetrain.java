package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private DoubleSolenoid drivetrainSolenoid = new DoubleSolenoid(RobotMap.drivetrainSolenoidHighGear, RobotMap.drivetrainSolenoidLowGear);
	
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
    
    }
    
    public void arcadeDrive(double move, double rotate) {
    	drive.arcadeDrive(move, rotate);
    }
    
    public void drivetrainHighGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void drivetrainLowGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}