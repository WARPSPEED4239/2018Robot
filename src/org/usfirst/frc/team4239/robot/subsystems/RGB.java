package org.usfirst.frc.team4239.robot.subsystems;

import org.usfirst.frc.team4239.robot.RobotMap;
import org.usfirst.frc.team4239.robot.commands.RGBRed;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RGB extends Subsystem {

    private CANifier rgbCanifer = new CANifier(RobotMap.rgbCanifier);
    
    public void initDefaultCommand() {
        setDefaultCommand(new RGBRed());
    }
    
    public void rgbRed() {
    	rgbCanifer.setLEDOutput(0.6, LEDChannel.LEDChannelA);    //R
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);    //G
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelC);    //B
    }
    
    public void rgbBlack() {
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelC);
    }
    
    public void rgbWhite() {
    	rgbCanifer.setLEDOutput(0.3, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.3, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(0.3, LEDChannel.LEDChannelC);
    }
    
    public void rgbGreen() {
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.3, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelC);
    }
    
    public void rgbBlue() {
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(0.5, LEDChannel.LEDChannelC);
    }
    
    public void rgbPurple() {
    	rgbCanifer.setLEDOutput(1.0, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(1.0, LEDChannel.LEDChannelC);
    }
    
    public void rgbRedDim() {
    	rgbCanifer.setLEDOutput(0.1, LEDChannel.LEDChannelA);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);
    	rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelC);
    }
    
    public void rgbPurpleDim() {
    	rgbCanifer.setLEDOutput(0.1, LEDChannel.LEDChannelA);
		rgbCanifer.setLEDOutput(0.0, LEDChannel.LEDChannelB);
		rgbCanifer.setLEDOutput(0.1, LEDChannel.LEDChannelC);
    }
}