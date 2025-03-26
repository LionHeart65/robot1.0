package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.DriveBase;
import org.firstinspires.ftc.teamcode.Utils.Intake;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;

@TeleOp(name = "Teleop2", group = "TeleOp")
public class Teleop2 extends OpMode{
    DcMotor frontLeft, frontRight, backLeft, backRight;
    SparkFunOdo sensor;

    double gateTarget = 0;
    boolean stopStartSwitch, reverseSwitch, sensSwitch;

    Robot robot;
    DriveBase drive;
    Intake intake;
    Servo gate;

    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        sensor = new SparkFunOdo(hardwareMap, new SparkFunOTOS.Pose2D(165,0,0), new SparkFunOTOS.Pose2D(0,0,0));
    }
    public void loop() {
        double rotate = gamepad1.right_stick_x;
        double realRobotHeading = sensor.getPos().h;
        double yStick = -gamepad1.left_stick_y;
        double stickHeading = Math.atan2(yStick, gamepad1.left_stick_x);
        double stickHeadingDegree = Math.toDegrees(stickHeading);
        telemetry.addData("heading", stickHeadingDegree - 90);
        telemetry.addData("robo heading", realRobotHeading);
        double sendRobotHeading = stickHeadingDegree - 45;
        sendRobotHeading -= realRobotHeading;
        double sendRobotHeadingRads = Math.toRadians(sendRobotHeading);
        double speed = Math.hypot(yStick, gamepad1.left_stick_x);
        double rightFrontPower = Math.sin(sendRobotHeadingRads) * speed;
        double leftBackPower = Math.sin(sendRobotHeadingRads) * speed;
        double leftFrontPower = Math.cos(sendRobotHeadingRads) * speed;
        double rightBackPower = Math.cos(sendRobotHeadingRads) * speed;

        frontLeft.setPower(leftFrontPower + rotate);
        backLeft.setPower(leftBackPower + rotate);
        frontRight.setPower(rightFrontPower - rotate);
        backRight.setPower(rightBackPower - rotate);
    }
    public void initialize() {
        robot = new Robot(hardwareMap);
        drive = new DriveBase(robot, gamepad1);
    SparkFunOTOS myOtos;
        intake = new Intake(robot);
        gate = hardwareMap.servo.get("servoGate");
    }
}
