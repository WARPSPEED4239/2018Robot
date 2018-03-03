/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.State.ControlMode;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.StartingPosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.autonomous.AutonCommand;
import org.usfirst.frc.team4239.robot.motion.TrajectoryGenerator;
import org.usfirst.frc.team4239.robot.subsystems.Climber;
import org.usfirst.frc.team4239.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4239.robot.subsystems.Intake;
import org.usfirst.frc.team4239.robot.subsystems.Lift;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

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
	public static Intake intake;
	public static Lift lift;
	public static OI oi;

	Command m_autonomousCommand;
	private SendableChooser<ControlMode> controlModeChooser = new SendableChooser<>();
	private SendableChooser<StartingPosition> positionChooser = new SendableChooser<>();
	private SendableChooser<TargetPriority> priorityChooser = new SendableChooser<>();
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		climber = new Climber();
		drivetrain = new Drivetrain();
		intake = new Intake();
		lift = new Lift();
		oi = new OI();

		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
		cam0.setFPS(10);

		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
		cam1.setResolution(320, 240);
		cam1.setFPS(10);

		controlModeChooser.addDefault("Standard", ControlMode.Standard);
        controlModeChooser.addObject("No Sensors", ControlMode.NoSensors);
        controlModeChooser.addObject("Do Nothing", ControlMode.DoNothing);
        SmartDashboard.putData("Control Mode", controlModeChooser);
        
        positionChooser.addDefault("Unknown", StartingPosition.Unknown);
        positionChooser.addObject("Left", StartingPosition.Left);
        positionChooser.addObject("Center", StartingPosition.Center);
        positionChooser.addObject("Right", StartingPosition.Right);
        SmartDashboard.putData("Starting Position", positionChooser);
        
        priorityChooser.addDefault("Unknown", TargetPriority.Unknown);
        priorityChooser.addObject("Switch", TargetPriority.Switch);
        priorityChooser.addObject("Scale", TargetPriority.Scale);
        SmartDashboard.putData("Target Priority", priorityChooser); 
        
        State.crossAutoLineTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(8.0, 0, 0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
        
        
        
    	State.leftSwitchTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(6.75, -9.1 , Pathfinder.d2r(-90)),  //Test? probs won't work :(
				new Waypoint(0.0, 0.0, 0.0)
		});
    	
    	
		
		State.leftScaleTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(14.55, 1.0, Pathfinder.d2r(-90)),  //Test? probs won't work :(
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		
		
		State.centerLeftSwitchFirstTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(4.0, 10.5 , 0.0), //DONT TOUCH
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		/*State.centerLeftSwitchSecondTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(-4.0, -6.5, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		State.centerLeftSwitchThirdTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(3.0, 0, 0), //TEST AND EDIT
				new Waypoint(0, 0, 0)
		});
		
		State.centerLeftSwitchFourthTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(-2.0, 0, 0), //TEST AND EDIT
				new Waypoint(0, 0, 0)
		});
		
		State.centerLeftSwitchFifthTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(4.0, 7.0, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		*/
		
		State.centerRightSwitchFirstTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(8.0, -6.75, 0.0), //DONT TOUCH
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		/*State.centerRightSwitchSecondTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(-4.0, 6.5, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		State.centerRightSwitchThirdTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(3.0, 0.0, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		State.centerRightSwitchFourthTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(-2.0, 0.0, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		State.centerRightSwitchFifthTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(4.0, -6.5, 0.0), //TEST AND EDIT
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		*/
		
		State.rightSwitchTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(6.75, 8.2, Pathfinder.d2r(90)), //Test? probs won't work :(
				new Waypoint(0.0, 0.0, 0.0)
		});
		
		State.rightScaleTrajectory = TrajectoryGenerator.getTrajectory(new Waypoint[] {
				new Waypoint(14.55, -1.0, Pathfinder.d2r(90)), //Test? probs won't work :(
				new Waypoint(0.0, 0.0, 0.0)
		});
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Auto Init Called");
		
		drivetrain.setIsAuto(true);
		
		ControlMode controlMode = controlModeChooser.getSelected();
		StartingPosition startingPosition = positionChooser.getSelected();
		TargetPriority targetPriority = priorityChooser.getSelected();
		
		String gameData = getGameData();
		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "gameData = " +gameData);

		SwitchPosition switchPosition = SwitchPosition.Unknown;
		ScalePosition scalePosition = ScalePosition.Unknown;

		if (gameData != null && gameData.length() >= 3) {
			switch (gameData.charAt(1)) {
			case 'L':
				scalePosition = ScalePosition.Left;
				break;
			case 'R':
				scalePosition = ScalePosition.Right;
				break;
			default:
				scalePosition = ScalePosition.Unknown;
				break;
			}

			switch (gameData.charAt(0)) {
			case 'L':
				switchPosition = SwitchPosition.Left;
				break;
			case 'R':
				switchPosition = SwitchPosition.Right;
				break;
			default:
				switchPosition = SwitchPosition.Unknown;
				break;

			}
		}
		m_autonomousCommand = new AutonCommand(controlMode, startingPosition, targetPriority, switchPosition, scalePosition);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
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

	public String getGameData() {
		String gameData = null;
		int retry = 0;

		while ((gameData == null || gameData.length() < 3) && retry < 50) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			retry++;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gameData;
	}
}