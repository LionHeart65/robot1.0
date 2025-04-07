package org.firstinspires.ftc.teamcode.TeleOp;

import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.DriveBase;
import org.firstinspires.ftc.teamcode.Utils.Intake;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;

@TeleOp(name = "Red Teleop")
public class RedTeleop extends LinearOpMode {

    Robot robot;
    DriveBase drive;
    SparkFunOdo odo;
    Intake intake;

    DigitalChannel LED;
    private final double heading = 0;

    boolean reverseSwitch, sensSwitch, reset;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                update();

                if (intake.getDirection().equals("Outtake")) {
                    LED.setState(true);
                } else {
                    LED.setState(false);
                }
                //A: Reverses spin
                if (gamepad1.a || gamepad2.a) {
                    if (!reverseSwitch) {
                        intake.reverse();
                        reverseSwitch = true;
                    }

                } else {
                    reverseSwitch = false;
                }
                //Left Bumper: toggles sensitivity
                if (gamepad1.left_bumper) {
                    if (!sensSwitch) {
                        sensSwitch = true;
                        if (drive.sensitivity == 1) {
                            drive.setSensitivity(0.5);
                        } else {
                            drive.setSensitivity(1);
                        }
                    }
                } else {
                    sensSwitch = false;
                }
                //Back: reset field centric
                if (gamepad1.back || gamepad2.back) {
                    if (!reset) {
                        reset = true;
                        odo.reset();
                    }
                } else {
                    reset = false;
                }
                telemetry.update();
            }
        }
    }

    public void initialize() {
        robot = new Robot(hardwareMap, new Pose(0,0, heading));
        drive = new DriveBase(robot, gamepad1);
        odo = robot.odo;
        intake = new Intake(robot);

        this.LED = robot.LED;
    }

    public void update() {
        drive.moveAbs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        SparkFunOTOS.Pose2D pos = odo.getPos();
        telemetry.addData("A: reverse intake", "left bumper: sensitivity toggle, Back: Resets fieldcentric");

        telemetry.addData("X: ", pos.x);
        telemetry.addData("Y: ", pos.y);
        telemetry.addData("Heading: ", pos.h);
        telemetry.addData("Sensitivity: ", drive.sensitivity);
        telemetry.addData("Direction: ", intake.getDirection());
        intake.update();
    }
}
