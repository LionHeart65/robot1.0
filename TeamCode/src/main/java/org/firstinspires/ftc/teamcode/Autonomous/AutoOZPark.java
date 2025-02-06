package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils.Robot;

@Autonomous(name = "Auto Observation Zone Park1")
public class AutoOZPark extends LinearOpMode {


    /**
     * This function is executed when this Op Mode is selected.
     */

    // JUST WORK ALREADY
    // YAY you work
    Robot robot = new Robot(hardwareMap, telemetry);
    @Override
    public void runOpMode() {
        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                robot.tankDrive(0.5, 0.5);
                telemetry.addLine("wefewfew");
            }
        }
    }
}
