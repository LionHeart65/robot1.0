package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils.DriveBase;
import org.firstinspires.ftc.teamcode.Utils.Intake;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {

    Robot robot;
    DriveBase drive;
    SparkFunOdo odo;
    Intake intake;


    boolean stopStart, reverse, sensSwitch;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                update();
                if (gamepad1.a) {
                    if (!reverse) {
                        intake.reverse();
                        reverse = true;
                    }

                } else {
                    reverse = false;
                }

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
                if (gamepad1.b) {
                    if (!stopStart) {
                        stopStart = true;
                        intake.stopStart();
                        telemetry.addData("B: ", intake.running);
                    }
                } else {
                    stopStart = false;
                }


                telemetry.addData("All: ", intake.running);
                telemetry.update();
            }
        }
    }

    public void initialize() {
        robot = new Robot(hardwareMap);
        drive = new DriveBase(robot, gamepad1);
        odo = robot.odo;
        intake = new Intake(robot);
    }

    public void update() {
//        drive.moveAbs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        drive.mecanumDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        SparkFunOTOS.Pose2D pos = odo.getPos();
        telemetry.addData("Odo: ", odo);
        telemetry.addData("X: ", pos.x);
        telemetry.addData("Y: ", pos.y);
        telemetry.addData("Heading: ", pos.h);
        telemetry.addData("Sensitivty: ", drive.sensitivity);
        intake.update();
    }
}
