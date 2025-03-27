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
    boolean stopStartSwitch, reverseSwitch, sensSwitch, dDown, dUp;

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

                //B: turns intake on/off
                if (gamepad1.b) {
                    if (!stopStartSwitch) {
                        stopStartSwitch = true;
                        intake.stopStart();
                    }
                } else {
                    stopStartSwitch = false;
                }
                if (gamepad1.dpad_down && closed > 0.05 && !dDown) {
                    closed -= 0.05;
                    dDown = true;
                }
                if (!gamepad1.dpad_down) {
                    dDown = false;
                }
                telemetry.addData("d-up/dn", dUp+","+dDown);
                if (gamepad1.dpad_up && closed < 0.35 && !dUp) {
                    closed += 0.05;
                    dUp = true;
                }
                if (!gamepad1.dpad_up) {
                    dUp = false;
                }

                if (gamepad1.x) {
                    gateTarget = (open);
                }
                if (gamepad1.y) {
                    gateTarget = (closed);
                }

                gate.setPosition(gateTarget);
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
        telemetry.addData("D-Pad Up/Dn - change servo closed pos.", "X: open gate, Y: close gate");
        telemetry.addData("B: start/stop intake, A: reverse intake", "left bumper: sensitivity toggle");

        telemetry.addData("X: ", pos.x);
        telemetry.addData("Y: ", pos.y);
        telemetry.addData("Heading: ", pos.h);
        telemetry.addData("Sensitivity: ", drive.sensitivity);
        telemetry.addData("Gate Pos", gate.getPosition());
        telemetry.addData("Closed Position", closed);
        intake.update();
    }
}
