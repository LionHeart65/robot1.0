package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.Robot;

@TeleOp(name = "BasicTankDrive")
public class BasicTankDrive1 extends LinearOpMode{


    /**
     * This function is executed when this Op Mode is selected.
     */
    @Override
    public void runOpMode() {

        Robot robot = new Robot(hardwareMap, telemetry);


        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Put loop blocks here
                telemetry.addData("Left stick", gamepad1.right_stick_y);
                telemetry.addData("Right stick", gamepad1.left_stick_y);

                robot.tankDrive(gamepad1.right_stick_y, gamepad1.left_stick_y);
                telemetry.addLine("wefewfew");

            }
        }
    }
}
