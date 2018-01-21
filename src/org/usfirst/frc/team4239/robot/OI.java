/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

import org.usfirst.frc.team4239.robot.commands.ClimberClimb;
import org.usfirst.frc.team4239.robot.commands.DrivetrainHighGear;
import org.usfirst.frc.team4239.robot.commands.DrivetrainLowGear;
import org.usfirst.frc.team4239.robot.commands.IntakeIn;
import org.usfirst.frc.team4239.robot.commands.IntakeOut;
import org.usfirst.frc.team4239.robot.commands.IntakeUp;
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
		xbutton1,
		xbutton3;
	
	public JoystickButton
		jbutton1,
		jbutton2,
		jbutton3,
		jbutton8,
		jbutton9,
		jbutton10;
	
	public OI () {
	xbutton1 = new JoystickButton(xbox, 1);
	xbutton3 = new JoystickButton(xbox, 3);
	
	jbutton1 = new JoystickButton(joystick, 1);
	jbutton2 = new JoystickButton(joystick, 2);
	jbutton3 = new JoystickButton(joystick, 3);
	jbutton8 = new JoystickButton(joystick, 8);
	jbutton9 = new JoystickButton(joystick, 9);
	jbutton10 = new JoystickButton(joystick, 10);
	
	
	xbutton1.whenPressed(new DrivetrainLowGear());
	xbutton3.whenPressed(new DrivetrainHighGear());
	
	jbutton1.whileHeld(new IntakeOut());
	jbutton2.whileHeld(new IntakeIn());
	jbutton3.toggleWhenPressed(new IntakeUp());
	jbutton8.whileHeld(new ClimberClimb());
	jbutton9.whileHeld(new LiftDown());
	jbutton10.whileHeld(new LiftUp());
	}
}