/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.State.AutoType;
import org.usfirst.frc.team4239.robot.State.PossibleCollision;
import org.usfirst.frc.team4239.robot.State.ScalePosition;
import org.usfirst.frc.team4239.robot.State.StartingPosition;
import org.usfirst.frc.team4239.robot.State.SwitchPosition;
import org.usfirst.frc.team4239.robot.State.TargetPriority;
import org.usfirst.frc.team4239.robot.commands.autonomous.AutonCommand;
import org.usfirst.frc.team4239.robot.motion.Trajectories;
import org.usfirst.frc.team4239.robot.subsystems.Climber;
import org.usfirst.frc.team4239.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4239.robot.subsystems.DrivetrainShifting;
import org.usfirst.frc.team4239.robot.subsystems.Intake;
import org.usfirst.frc.team4239.robot.subsystems.Lift;
import org.usfirst.frc.team4239.robot.tools.FMSInterface;
import org.usfirst.frc.team4239.robot.tools.RGBController;
import org.usfirst.frc.team4239.robot.tools.RGBController.Color;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static Climber climber;
	public static Drivetrain drivetrain;
	public static DrivetrainShifting drivetrainShifting;
	public static Intake intake;
	public static Lift lift;
	public static RGBController rgbController;
	public static OI oi;

	private Command m_autonomousCommand;
	private SendableChooser<StartingPosition> positionChooser = new SendableChooser<>();
	private SendableChooser<AutoType> typeChooser = new SendableChooser<>();
	private SendableChooser<TargetPriority> priorityChooser = new SendableChooser<>();
	private SendableChooser<PossibleCollision> collisionChooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		climber = new Climber();
		drivetrain = new Drivetrain();
		drivetrainShifting = new DrivetrainShifting();
		intake = new Intake();
		lift = new Lift();
		rgbController = new RGBController(new CANifier(RobotMap.rgbCanifier));
		oi = new OI();
		
		Color[] colors = {Color.RedDim, Color.Black};
		rgbController.setColors(colors, 1.5);
		
		
		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
		cam0.setFPS(10);

		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
		cam1.setResolution(320, 240);
		cam1.setFPS(10);
		
    	typeChooser.addDefault("N/A", AutoType.NA);
		typeChooser.addObject("Task Based", AutoType.TargetBased); 				  	  //Use when there is no chance of running into another team during autos
		typeChooser.addObject("Robot Alignment Based", AutoType.RobotAlignmentBased); //Use when another team can handle the scale, switch, and both to minimize running into each other
		SmartDashboard.putData("Auto Type", typeChooser);

		positionChooser.addObject("Left", StartingPosition.Left);
        positionChooser.addDefault("Center", StartingPosition.Center);
        positionChooser.addObject("Right", StartingPosition.Right);
        SmartDashboard.putData("Starting Position", positionChooser);
		
        priorityChooser.addDefault("N/A", TargetPriority.NA);
        priorityChooser.addObject("Switch", TargetPriority.Switch);
        priorityChooser.addObject("Scale", TargetPriority.Scale);
        priorityChooser.addObject("Drive", TargetPriority.Drive);
        priorityChooser.addObject("Drive No Sensors", TargetPriority.DriveNoSensors);
        priorityChooser.addObject("Do Nothing", TargetPriority.DoNothing);
        SmartDashboard.putData("Target Priority", priorityChooser);
        
        collisionChooser.addDefault("No", PossibleCollision.No);
        collisionChooser.addObject("Yes", PossibleCollision.Yes);
        SmartDashboard.putData("Possible Collision", collisionChooser);               //Only used in robot alignment auto type
        
        Trajectories.initialize();

        rgbController.setColor(Color.GreenDim);
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println(String.valueOf(System.currentTimeMillis()) + ">> " + "Auto Init Called");
		
		drivetrain.setIsAuto(true);
		
		StartingPosition startingPosition = positionChooser.getSelected();
		AutoType autoType = typeChooser.getSelected();
		TargetPriority targetPriority = priorityChooser.getSelected();
		PossibleCollision possibleCollision = collisionChooser.getSelected();
		
		String gameData = FMSInterface.getGameData();

		SwitchPosition switchPosition = SwitchPosition.Unknown;
		ScalePosition scalePosition = ScalePosition.Unknown;

		if (gameData != null && gameData.length() >= 3) {
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
		}
		
		m_autonomousCommand = new AutonCommand(autoType, startingPosition, targetPriority, possibleCollision,switchPosition, scalePosition);
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		Robot.rgbController.setColor(Color.Red);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}