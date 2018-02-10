package org.usfirst.frc.team4239.robot.motion;

public class MotionConvert {
    private static final double WHEEL_DIAMETER = 0.5;
    private static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    private static final double ENCODER_TO_OUTPUT = (12 / 36) * (24 / 60);
    private static final double UNITS_PER_ROTATION = 4096;
    
    public static double unitsToEncoderRotations(double units) {
        return units / UNITS_PER_ROTATION;
    }
    
    public static double encoderRotationsToWheelRotations(double encoderRotations) {
        return encoderRotations * ENCODER_TO_OUTPUT;
    }
    
    public static double wheelRotationsToDistance(double wheelRotations) {
        return wheelRotations * WHEEL_CIRCUMFERENCE;
    }
    
    public static double unitsToDistance(double units) {
        return wheelRotationsToDistance(encoderRotationsToWheelRotations(unitsToEncoderRotations(units)));
    }
    
    public static double distanceToWheelRotations(double distance) {
        return distance / WHEEL_CIRCUMFERENCE;
    }
    
    public static double wheelRotationsToEncoderRotations(double wheelRotations) {
        return wheelRotations / ENCODER_TO_OUTPUT;
    }
    
    public static double encoderRotationsToUnits(double encoderRotations) {
        return encoderRotations * UNITS_PER_ROTATION;
    }
    
    public static double distanceToUnits(double distance) {
        return encoderRotationsToUnits(wheelRotationsToEncoderRotations(distanceToWheelRotations(distance)));
    }
    
    /*
     * Input: Feet Per Second
     * Output: Units Per 100ms
     */
    public static double velocityToUnits(double velocity) {
        double unitsPerSecond = distanceToUnits(velocity);
        return unitsPerSecond / 10;
    }
    
    /*
     * Input: Units Per 100ms
     * Output: Feet Per Second
     */
    public static double unitsToVelocity(double units) {
        double feetPerHundredMillis = unitsToDistance(units);
        return feetPerHundredMillis * 10;
    }
    
}
