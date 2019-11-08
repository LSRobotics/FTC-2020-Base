package org.firstinspires.ftc.teamcode.lschs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoPilot {


    private static Util.Timer timer = new Util.Timer();

    public static boolean driveByTime(double x, double y, double strafing, double milliseconds) {

        LinearOpMode opMode = (LinearOpMode)Core.mainClass;

        if(!opMode.opModeIsActive()) return false;

        timer.start();

        Chassis.drive(strafing,y,x);

        while(timer.getElaspedTimeInMs() < milliseconds) {
            if(!opMode.opModeIsActive()) return false;
        }

        Chassis.stop();

        return true;
    }

    public static boolean runMotorByTime(Motor motor, double power, double milliseconds) {

        LinearOpMode opMode = (LinearOpMode)Core.mainClass;

        if(!opMode.opModeIsActive()) return false;

        timer.start();

        motor.move(power);

        while(timer.getElaspedTimeInMs() < milliseconds) {
            if(!opMode.opModeIsActive()) return false;
        }

        motor.stop();

        return true;
    }
}
