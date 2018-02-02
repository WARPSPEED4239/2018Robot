package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.DrivetrainArcadeDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	
<<<<<<< HEAD
    WPI_TalonSRX drivetrainMotorLeftOne = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftOne);
    WPI_TalonSRX drivetrainMotorRightFour = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFour);
    
    WPI_TalonSRX drivetrainMotorLeftTwo = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftTwo);
    WPI_TalonSRX drivetrainMotorRightFive = new WPI_TalonSRX(RobotMap.drivetrainMotorRightFive);
    WPI_TalonSRX drivetrainMotorLeftThree = new WPI_TalonSRX(RobotMap.drivetrainMotorLeftThree);
    WPI_TalonSRX drivetrainMotorRightSix = new WPI_TalonSRX(RobotMap.drivetrainMotorRightSix);
    
    
    DifferentialDrive drive = new DifferentialDrive(drivetrainMotorLeftOne, drivetrainMotorRightFour);
=======
	private final int PEAK_CURRENT_LIMIT = 45;
	private final int CONTINUOUS_CURRENT_LIMIT = 35;
	private final int PEAK_CURRENT_DURATION_MILLIS = 100;
	
	private final int RAMP_RATE = 1;
>>>>>>> darren
	
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
    	leftMaster.setSensorPhase(true);
    	
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	rightMaster.setSensorPhase(false);
    	
    	//drive.setSafetyEnabled(false);
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Position", leftMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Left Velocity", leftMaster.getSelectedSensorVelocity(0));
        
        SmartDashboard.putNumber("Right Position", rightMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Velocity", rightMaster.getSelectedSensorVelocity(0));
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainArcadeDrive());
    }
    
    public void arcadeDrive(double move, double rotate) {
    	drive.arcadeDrive(move, rotate);
    }
<<<<<<< HEAD
=======
    
    public void drivetrainHighGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void drivetrainLowGear () {
    	drivetrainSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void autoInit () {
    	
    	leftMaster.setSelectedSensorPosition(0, 0, 10);
    	
    	int kPIDLoopIdx = 0;
    	int kTimeoutMs = 10;
    	int kSlotIdx = 0;
    	
    	  /* first choose the sensor */
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        leftMaster.setSensorPhase(true);
        leftMaster.setInverted(false);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
        /* set the peak and nominal outputs */
        leftMaster.configNominalOutputForward(0, kTimeoutMs);
        leftMaster.configNominalOutputReverse(0, kTimeoutMs);
        leftMaster.configPeakOutputForward(1, kTimeoutMs);
        leftMaster.configPeakOutputReverse(-1, kTimeoutMs);
        /* set closed loop gains in slot0 - see documentation */
        leftMaster.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
        leftMaster.config_kF(0, 0.2, kTimeoutMs);
        leftMaster.config_kP(0, 0.2, kTimeoutMs);
        leftMaster.config_kI(0, 0, kTimeoutMs);
        leftMaster.config_kD(0, 0, kTimeoutMs);
        /* set acceleration and vcruise velocity - see documentation */
        leftMaster.configMotionCruiseVelocity(15000, kTimeoutMs);
        leftMaster.configMotionAcceleration(6000, kTimeoutMs);
        /* zero the sensor */
        leftMaster.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
    }
    
    public void autoPeriodic () {
    	double targetPosition = 4096 * 10;
    	
    	leftMaster.set(ControlMode.MotionMagic, targetPosition);
    	
    }
    
    public void stop () {
    	leftMaster.stopMotor();
    	rightMaster.stopMotor();
    }
    
    private void setCurrentLimit(WPI_TalonSRX controller) {
    	controller.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT, 0);
    	controller.configPeakCurrentLimit(PEAK_CURRENT_LIMIT, 0);
    	controller.configPeakCurrentDuration(PEAK_CURRENT_DURATION_MILLIS, 0);
    	controller.enableCurrentLimit(true);
    }
    
>>>>>>> darren
}