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


    public void moveAbs(double x, double y, double heading){
        double angle = odo.getPos().h;
        double x_rotated = x * Math.cos(angle) - y * Math.sin(angle);
        double y_rotated = x * Math.sin(angle) + y * Math.cos(angle);
        mecanumDrive(1.4 * x_rotated, y_rotated, heading);
    }

    private void mecanumDrive(double x, double y, double heading){
        //moves relative to robot starting position
        frontLeft.setPower(-(-x + y - heading));
        backLeft.setPower((-x - y + heading));
        frontRight.setPower((-x - y - heading));
        backRight.setPower(-(-x + y + heading));
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
