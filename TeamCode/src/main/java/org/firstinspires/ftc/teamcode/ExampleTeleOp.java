package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lschs.*;
import org.firstinspires.ftc.teamcode.lschs.Controller.Key;

@TeleOp(name = "Example TeleOp", group = "Linear OpMode")

public class ExampleTeleOp extends LinearOpMode {

    private Controller xbox1;
    private boolean isLowSpeedMode = false;
    private ElapsedTime runtime = new ElapsedTime();

    /**
     * Put your initialization code HERE
     */
    public void initialize() {

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
    public void runOpMode() {

        //DON'T MODIFY THIS! ==============
        initialize();
        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
        //=================================

            //Your code zone START -----------------------------------------

            //Update key data for the first controller
            xbox1.fetchData();

            //Robot Drive
            Chassis.drive(xbox1.getValue(Key.J_LEFT_X), //Left Joystick's horizontal movement controls left/right strafing
                    -xbox1.getValue(Key.J_LEFT_Y), //Left joystick's vertical movement controls forward/back
                    xbox1.getValue(Key.J_RIGHT_X)); //Right joystick's horizontal movement controls robot rotation

            //Low speed mode by toggling "Right bumper"
            if (xbox1.isKeyToggled(Key.RB)) {

                isLowSpeedMode = !isLowSpeedMode;

                Chassis.updateSpeedLimit(isLowSpeedMode ? 0.7 : 1.0); //70% under low speed mode
            }

            //Print information on the Driver Station phone (When needed)
            Util.print("Elapsed time: " + runtime.toString());

            Util.print("Robot Drive input: "
                    + (xbox1.getValue(Key.J_LEFT_X)) + ", "
                    + (-xbox1.getValue(Key.J_LEFT_Y)) + ", "
                    + (xbox1.getValue(Key.J_RIGHT_X)));

            //Your code zone END ------------------------------------------

        }
    }
}
