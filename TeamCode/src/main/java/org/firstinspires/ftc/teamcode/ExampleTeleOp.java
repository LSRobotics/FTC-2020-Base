package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lschs.*;
import org.firstinspires.ftc.teamcode.lschs.Controller.Key;

@TeleOp(name = "Example TeleOp", group = "Iterative OpMode")

public class ExampleTeleOp extends OpMode {

    Controller xbox1;
    public boolean isLowSpeedMode = false;


    @Override
    public void init() {

        //WARNING: DO NOT MODIFY THIS PART OF THE CODE! ===========================

        Core.initialize(this);

        //Now do whatever you want ================================================


        //Initialize Chassis
        Chassis.initialize("frontLeft",
                          "frontRight",
                           "backLeft",
                          "backRight"); //Pass motors
        Chassis.setWheelMode(Chassis.WheelMode.HOLONOMIC); //Set drive mode to Holonomic mode (For mecanum/Omni wheels)

        //Pass controller to my framework for supporting ADVANCED FEATURES.
        xbox1 = new Controller(gamepad1);

    }

    @Override
    public void loop() {

        //Update key data for the first controller
        xbox1.fetchData();

        //Robot Drive
        Chassis.drive(xbox1.getValue(Key.J_LEFT_X), //Left Joystick's horizontal movement controls left/right strafing
                -xbox1.getValue(Key.J_LEFT_Y), //Left joystick's vertical movement controls forward/back
                xbox1.getValue(Key.J_RIGHT_X)); //Right joystick's horizontal movement controls robot rotation

        //Low speed mode by toggling "Right bumper"
        if(xbox1.isKeyToggled(Key.RB)) {

            isLowSpeedMode = !isLowSpeedMode;

            Chassis.updateSpeedLimit(isLowSpeedMode? 0.7 : 1.0); //70% under low speed mode
        }

    }
}
