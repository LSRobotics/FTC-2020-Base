package org.firstinspires.ftc.teamcode.lschs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Core {

    public static OpMode mainClass;

    public static void initialize(OpMode opMode) {
        mainClass = opMode;
    }

    public static HardwareMap getHwMap() {
        return mainClass.hardwareMap;
    }

    public static Telemetry getTelemetry() {
        return mainClass.telemetry;
    }

}
