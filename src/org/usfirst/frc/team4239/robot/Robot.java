/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.commands.autonomous.AutonDriveBackwardNoSensors;
import org.usfirst.frc.team4239.robot.commands.autonomous.AutonDriveForwardNoSensors;
import org.usfirst.frc.team4239.robot.subsystems.Climber;
import org.usfirst.frc.team4239.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4239.robot.subsystems.DrivetrainShifting;
import org.usfirst.frc.team4239.robot.subsystems.Intake;
import org.usfirst.frc.team4239.robot.subsystems.IntakePivot;
import org.usfirst.frc.team4239.robot.subsystems.Lift;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Climber climber;
	public static Drivetrain drivetrain;
	public static DrivetrainShifting drivetrainShifting;
	public static Intake intake;
	public static IntakePivot intakePivot;
	public static Lift lift;
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		climber = new Climber();
		drivetrain = new Drivetrain();
		drivetrainShifting = new DrivetrainShifting();
		intake = new Intake();
		intakePivot = new IntakePivot();
		lift = new Lift();
		oi = new OI();
		
		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
		cam0.setFPS(10);
		
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
		cam1.setResolution(320, 240);
		cam1.setFPS(10);
		
		
		chooser.addDefault("Drive Forward No Sensors", new AutonDriveForwardNoSensors());
		chooser.addObject("Drive Backwardr No Sensors", new AutonDriveBackwardNoSensors());
		SmartDashboard.putData("Auto mode", chooser);
		
		drivetrain.initialize();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}