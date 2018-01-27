package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainArcadeDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	
	private final int PEAK_CURRENT_LIMIT = 45;
	private final int CONTINUOUS_CURRENT_LIMIT = 35;
	private final int PEAK_CURRENT_DURATION_MILLIS = 100;
	
	private final int RAMP_RATE = 2;
	
    private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftOne);
    private WPI_TalonSRX leftSlave1 = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftTwo);
    private WPI_TalonSRX leftSlave2 = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftThree);
    
    private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFour);
    private WPI_TalonSRX rightSlave1 = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFive);
    private WPI_TalonSRX rightSlave2 = new WPI_TalonSRX(RobotMap.drivetrainMotorRightSix);
    
    private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
    private DoubleSolenoid drivetrainSolenoid = new DoubleSolenoid(RobotMap.drivetrainSolenoidHighGear, RobotMap.drivetrainSolenoidLowGear);
    
    public Drivetrain() {
    	leftSlave1.follow(leftMaster);
    	leftSlave2.follow(leftMaster);
    	rightSlave1.follow(rightMaster);
    	rightSlave2.follow(rightMaster);
        
        setCurrentLimit(leftMaster);
        setCurrentLimit(rightMaster);

        leftMaster.configOpenloopRamp(RAMP_RATE, 0);
    	rightMaster.configOpenloopRamp(RAMP_RATE, 0);
    	
    	leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	leftMaster.setSensorPhase(false);
    	
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	rightMaster.setSensorPhase(false);
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Position", leftMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Left Velocity", leftMaster.getSelectedSensorVelocity(0));
        
        SmartDashboard.putNumber("Right Position", leftMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Velocity", leftMaster.getSelectedSensorVelocity(0));
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainArcadeDrive());
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
    
    private void setCurrentLimit(WPI_TalonSRX controller) {
    	controller.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT, 0);
    	controller.configPeakCurrentLimit(PEAK_CURRENT_LIMIT, 0);
    	controller.configPeakCurrentDuration(PEAK_CURRENT_DURATION_MILLIS, 0);
    	controller.enableCurrentLimit(true);
    }
    
}