package org.usfirst.frc.team4239.robot.tools;

import edu.wpi.first.wpilibj.DriverStation;

public class FMSInterface {

	public static String getGameData() {
		Logger.log("Requesting FMS Game Data");
		String gameData = "";
		int retry = 0;

		while (gameData.length() < 3 && retry < 50) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			retry++;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.log("FMS Game Data = '" + gameData + "'");
		return gameData;
	}

}
