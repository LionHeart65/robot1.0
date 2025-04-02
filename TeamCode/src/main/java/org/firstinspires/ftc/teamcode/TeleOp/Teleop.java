package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

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
    Servo gate;

    double gateTarget = 0;
    double open = 0;
    double closed = 0.3;
    boolean reverseSwitch, sensSwitch, reset;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                update();

                //A: Reverses spin
                if (gamepad1.a) {
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


                if (gamepad1.back) {
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
        robot = new Robot(hardwareMap);
        drive = new DriveBase(robot, gamepad1);
        odo = robot.odo;
        intake = new Intake(robot);

        gate = hardwareMap.servo.get("servoGate");
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
