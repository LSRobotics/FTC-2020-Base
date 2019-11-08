package org.firstinspires.ftc.teamcode.lschs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by LBYPatrick on 2017/11/3.
 */

final public class Chassis {

    /**
     * The positions of the wheels.
     */
    public enum Wheels{
        FRONT_LEFT,
        FRONT_RIGHT,
        REAR_LEFT,
        REAR_RIGHT,
    }

    public enum WheelMode {
        TANK,
        HOLONOMIC
    }

    private static Motor FL, FR, BL, BR;
    private static WheelMode wheelMode = WheelMode.HOLONOMIC;



    public static void initialize(String frontLeftWheel,
                                  String frontRightWheel,
                                  String backLeftWheel,
                                  String backRightWheel) {

        FL = new Motor(frontLeftWheel);
        FR = new Motor(frontRightWheel,true);
        BL = new Motor(backLeftWheel);
        BR = new Motor(backRightWheel,true);
    }

    public static void setWheelMode(WheelMode mode) {
        wheelMode = mode;
    }

    /**
     * Drives the robot.
     * @param sideMove
     * @param forwardBack
     * @param rotation
     */
    public static void drive(double sideMove, double forwardBack, double rotation) {
        switch(wheelMode) {
            case TANK:
                tankDrive(forwardBack,rotation);
                break;

            case HOLONOMIC:
                mecanumDrive(sideMove,forwardBack,rotation);
                break;
            default: break;
        }
    }

    /**
     * Tank Drive
     * @param forwardBack
     * @param rotation
     */
    public static void tankDrive(double forwardBack, double rotation) {


        double left = Range.clip(forwardBack + rotation, -1.0, 1.0),
               right = Range.clip(forwardBack - rotation, -1.0, 1.0);


        FL.move(left);
        BL.move(left);

        FR.move(right);
        BR.move(right);

    }

    //From http://ftckey.com/programming/advanced-programming/
    public static void omniDrive(double sideMove, double forwardBack, double rotation) {

        FR.move(getLimited(forwardBack+sideMove-rotation));
        FL.move(getLimited(forwardBack-sideMove+rotation));
        BR.move(getLimited(forwardBack-sideMove-rotation));
        BL.move(getLimited(forwardBack+sideMove+rotation));
    }

    private static double getLimited(double value) {
        if(value < -1) return -1;
        else if(value > 1) return 1;
        else return value;
    }

    /**
     * Mecanum Drive
     * @param sideMove
     * @param forwardBack
     * @param rotation
     */
    public static void mecanumDrive(double sideMove, double forwardBack, double rotation) {

        //A little Math from https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/6361-mecanum-wheels-drive-code-example
        final double r = Math.hypot(sideMove, forwardBack);
        final double robotAngle = Math.atan2(forwardBack, sideMove) - Math.PI / 4;

        FL.move(r * Math.cos(robotAngle) + rotation);
        FR.move(r * Math.sin(robotAngle) - rotation);
        BL.move(r * Math.sin(robotAngle) + rotation);
        BR.move(r * Math.cos(robotAngle) - rotation);

    }

    /**
     * Updates speed limit.
     * @param speed needs to be greater than 0, otherwise the robot would move backwards or stop moving if it is 0
     */
    public static void updateSpeedLimit(double speed) {

        FL.updateSpeedLimit(speed);
        FR.updateSpeedLimit(speed);
        BL.updateSpeedLimit(speed);
        BR.updateSpeedLimit(speed);
    }

    /**
     * Gets motor encoders' readings.
     * @param position the position of the Wheel
     * @return the motor encoder reading of the specified wheel.
     */
    public static double getEncoderInfo(Wheels position) {
        switch (position) {
            case FRONT_LEFT  : return FR.getCurrentPosition();
            case FRONT_RIGHT : return FL.getCurrentPosition();
            case REAR_LEFT   : return BL.getCurrentPosition();
            case REAR_RIGHT  : return BR.getCurrentPosition();
            default          : return 0; // Actually won't happen because the enum has already limited the actual parameter
        }
    }

    /**
     * Gets motors' speeds.
     * @param position the position of the Wheel
     * @return the motor power reading of the specified wheel.
     */
    public static double getSpeed(Wheels position) {
        switch (position) {
            case FRONT_LEFT  : return FR.getPower();
            case FRONT_RIGHT : return FL.getPower();
            case REAR_LEFT   : return BL.getPower();
            case REAR_RIGHT  : return BR.getPower();
            default          : return 0; // Won't happen because of the enum in parameter
        }
    }
}