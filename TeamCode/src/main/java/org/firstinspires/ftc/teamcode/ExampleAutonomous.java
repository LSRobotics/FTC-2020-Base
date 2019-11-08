package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.lschs.*;

@Autonomous(name="Example Autnomous", group = "Autonomous")

public class ExampleAutonomous extends LinearOpMode {


     //Declare shared objects HERE
     private Motor forebar;

    /**
     * Put your initialization code in here
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

        //Initialize forebar motor
        forebar = new Motor("forebar");

    }

    public void runOpMode() {

        //DON'T TOUCH THIS PART OF THE CODE ! =====================
        initialize();
        waitForStart();
        //=========================================================

        //Now put your autonomous instructions HERE

        //Exmaple 1: Move forward by 1 second At half power
        if(!AutoPilot.driveByTime(0,0.5,0,1000)) return;

        //Example 2: Run forebar motor by 1.5 seconds at FULL POWER
        if(!AutoPilot.runMotorByTime(forebar,1,1500)) return;

        /**
         * Format: if(! <An AutoPilot command>) return;
         *
         * Just follow this format and don't question about it unless you know what you are doing.
         */


    }
}