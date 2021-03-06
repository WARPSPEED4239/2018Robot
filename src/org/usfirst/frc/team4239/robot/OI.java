/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.commands.ClimberClimb;
import org.usfirst.frc.team4239.robot.commands.ClimberReverse;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.DrivetrainLowGear;
import org.usfirst.frc.team4239.robot.commands.IntakeIn;
import org.usfirst.frc.team4239.robot.commands.IntakeOut;
import org.usfirst.frc.team4239.robot.commands.LiftDown;
import org.usfirst.frc.team4239.robot.commands.LiftUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController xbox = new XboxController(0);
	public Joystick joystick = new Joystick(1);
	
	public JoystickButton
		xButtonA,
		xButtonB,
		xButtonX,
		xButtonY;

  public JoystickButton
		jButton1,
		jButton2,
		jbutton7,
		jButton8,
		jButton9,
		jButton10;
	
	public OI () {
		xButtonA = new JoystickButton(xbox, 1);
		xButtonB = new JoystickButton(xbox, 2);
		xButtonX = new JoystickButton(xbox, 3);
		xButtonY = new JoystickButton(xbox, 4);
	
		jButton1 = new JoystickButton(joystick, 1);
		jButton2 = new JoystickButton(joystick, 2);
		jbutton7 = new JoystickButton(joystick, 7);
		jButton8 = new JoystickButton(joystick, 8);
		jButton9 = new JoystickButton(joystick, 9);
		jButton10 = new JoystickButton(joystick, 10);
	
		xButtonB.whenPressed(new DrivetrainHighGear());
		xButtonA.whenPressed(new DrivetrainLowGear());
		
		/*xButtonX.whenPressed(new DrivetrainArcadeDrive());
		xButtonA.whenPressed(new DrivetrainCalculateTrackWidth(-90));
		xButtonB.whenPressed(new DrivetrainCalculateTrackWidth(90));*/
	
		jButton1.whileHeld(new IntakeOut());
		jButton2.whileHeld(new IntakeIn());
		jButton8.whileHeld(new ClimberClimb());
		jbutton7.whileHeld(new ClimberReverse());
		jButton9.whileHeld(new LiftDown());
		jButton10.whileHeld(new LiftUp());
	}

	public XboxController getController() {
		return xbox;
	}

	public Joystick getJoystick() {
		return joystick;
	}
}