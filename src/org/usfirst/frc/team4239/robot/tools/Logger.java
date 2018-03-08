package org.usfirst.frc.team4239.robot.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.State.CodeExecutionState;

public class Logger {
	
	private static final boolean kDisable = false;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS");

	public static void log(Object obj) {
		if (kDisable) {
			return;
		}
		
		Date date = new Date();
		String time = dateFormat.format(date);
		String msg = obj.toString();
		
		System.out.println(time + " WARPLOG>> " + msg);
	}
	
	public static void vLog(Object obj) {
		if (RobotMap.executionState != CodeExecutionState.Debug) {
			return;
		}
		log(obj);
	}
	
}
