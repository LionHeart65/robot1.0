package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;

@TeleOp(name = "BasicTankDrive")
public class BasicTankDrive1 extends LinearOpMode{


    /**
     * This function is executed when this Op Mode is selected.
     */
    @Override
    public void runOpMode() {

        Robot robot = new Robot(hardwareMap, telemetry);
        SparkFunOdo odo = new SparkFunOdo(hardwareMap, new SparkFunOTOS.Pose2D(0, 0, 0), new SparkFunOTOS.Pose2D(0, 0, 0));

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                SparkFunOTOS.Pose2D pos = odo.getPos();
                // Put loop blocks here
                telemetry.addData("Left stick", gamepad1.right_stick_y);
                telemetry.addData("Right stick", gamepad1.left_stick_y);

                robot.tankDrive(gamepad1.right_stick_y, gamepad1.right_stick_x);
                telemetry.addData("X: ", pos.x);
                telemetry.addData("Y: ", pos.y);
                telemetry.addData("Heading: ", pos.h);

                telemetry.update();
            }
        }
    }
}
