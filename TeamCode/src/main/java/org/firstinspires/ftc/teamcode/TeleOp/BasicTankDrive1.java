package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.Robot;

@TeleOp(name = "BasicTankDrive (Blocks to Java)1")
public class BasicTankDrive1 extends LinearOpMode{

    Robot robot = new Robot(hardwareMap, telemetry);

    /**
     * This function is executed when this Op Mode is selected.
     */
    @Override
    public void runOpMode() {

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Put loop blocks here
                robot.tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
                telemetry.addLine("wefewfew");

            }
        }
    }
}
