package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.tests.StealTheMoon.EDITH.Edith;

public class DriveBase {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Gamepad gpad;
    private SparkFunOdo odo;

    private Edith edith = new Edith();


    public DriveBase(Robot r, Gamepad gpad) {
        frontLeft = r.frontLeft;
        frontRight = r.frontRight;
        backLeft = r.backLeft;
        backRight = r.backRight;

        odo = r.odo;
        this.gpad = gpad;
    }

    public void moveAbs() {
        double rotate = gpad.right_stick_x;
        double realRobotHeading = odo.getPos().h;
        double yStick = -gpad.left_stick_y;
        double stickHeading = Math.atan2(yStick, gpad.left_stick_x);
        double stickHeadingDegree = Math.toDegrees(stickHeading);
        double sendRobotHeading = stickHeadingDegree - 45;
        sendRobotHeading -= realRobotHeading;
        double sendRobotHeadingRads = Math.toRadians(sendRobotHeading);
        double speed = Math.hypot(yStick, gpad.left_stick_x);
        double rightFrontPower = Math.sin(sendRobotHeadingRads) * speed;
        double leftBackPower = Math.sin(sendRobotHeadingRads) * speed;
        double leftFrontPower = Math.cos(sendRobotHeadingRads) * speed;
        double rightBackPower = Math.cos(sendRobotHeadingRads) * speed;

        this.setMotors(leftFrontPower + rotate, leftBackPower + rotate, rightFrontPower - rotate, rightBackPower - rotate);
    }


    public void mecanumDrive() {
        double y_stick = -gpad.right_stick_y;

        double heading = Math.atan2(y_stick, gpad.right_stick_x);
        double speed = Math.hypot(gpad.right_stick_x, y_stick);

        double robot_heading = heading - Math.PI/4;

        double rightFrontPower = Math.sin(robot_heading) * speed;
        double leftBackPower = Math.sin(robot_heading) * speed;

        double leftFrontPower = Math.cos(robot_heading) * speed;
        double rightBackPower = Math.cos(robot_heading) * speed;

        this.setMotors(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
        if (gpad.left_stick_x != 0) {
            this.turn(gpad.left_stick_x);
        }
    }

    private void setMotors(double frontLeft, double backLeft, double frontRight, double backRight) {
        this.frontLeft.setPower(frontLeft);
        this.backLeft.setPower(backLeft);
        this.frontRight.setPower(frontRight);
        this.backRight.setPower(backRight);
    }

    private void turn(double turn) {
        this.setMotors(turn, turn, -turn, -turn);
    }


}
