/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4239.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int 
		drivetrainMotorLeftOne = 1,
		drivetrainMotorLeftTwo = 2,
		drivetrainMotorLeftThree = 3,
		drivetrainMotorRightFour = 4,
		drivetrainMotorRightFive = 5,
		drivetrainMotorRightSix = 6,
		
		rgbCanifier = 7;
	
	
	public static int 
		climberMotor = 0,
		intakeMotorLeft = 1,
		intakeMotorRight = 2,
		liftMotor = 3;
	
	
	public static int
		drivetrainSolenoidLowGear = 0,
		drivetrainSolenoidHighGear = 1,
		intakePivotSolenoidDown = 2,
		intakePivotSolenoidUp = 3;	
}