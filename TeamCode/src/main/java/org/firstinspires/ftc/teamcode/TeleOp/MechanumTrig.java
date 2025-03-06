package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "MechanumTrig", group = "TeleOp")
public class MechanumTrig extends OpMode{
    DcMotor frontLeft, frontRight, backLeft, backRight;
    SparkFunOTOS sensor;
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        sensor = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        sensor.setAngularUnit(AngleUnit.DEGREES);
        sensor.calibrateImu();
    }
    public void loop() {
        double rotate = gamepad1.right_stick_x;
        double realRobotHeading = sensor.getPosition().h;
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
}
