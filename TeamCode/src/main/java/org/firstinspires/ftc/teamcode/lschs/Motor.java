package org.firstinspires.ftc.teamcode.lschs;


import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by LBYPatrick on 11/9/2017.
 */

public class Motor {

    private     double  speedLimit = 1.0;
    private     double  motorSpeed = 0;
    private     DcMotor motor;
    private     boolean isReverse;


    public Motor(String motorID, boolean isForward) {

        motor = Core.getHwMap().dcMotor.get(motorID);

        isReverse = !isForward;

        motor.setDirection((isForward?DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE));
    }

    public void setReverse(boolean isReverse) {
        motor.setDirection(isReverse? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
    }

    public void flip() {
        isReverse = !isReverse;

        setReverse(isReverse);
    }

    public Motor(String motorID) {
        this(motorID, true);
    }

    private double getLimitedSpeed(double rawSpeed) {return rawSpeed * speedLimit;}

    public void moveWithButton(boolean up, boolean down) {

        if (up == down) motor.setPower(0);
        else { motor.setPower(getLimitedSpeed(up? 1 : -1)); }
    }

    public void stop() {
        motor.setPower(0);
    }

    public void move(double value) {
        motor.setPower(getLimitedSpeed(value));
    }

    public double getPower() { return motor.getPower(); }

    public double getSpeed() {
        return getPower();
    }

    public int getPosition() {
        return motor.getCurrentPosition();
    }

    public int getCurrentPosition() {
        return getPosition();
    }

    public void updateSpeedLimit(double speed) { speedLimit = speed; }
}
