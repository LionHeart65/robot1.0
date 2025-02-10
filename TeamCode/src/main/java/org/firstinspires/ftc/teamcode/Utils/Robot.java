package org.firstinspires.ftc.teamcode.Utils;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public final DcMotor frontLeft;
    public final DcMotor backLeft;
    public final DcMotor frontRight;
    public final DcMotor backRight;
    private final Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry tele) {
        telemetry = tele;
        if (hardwareMap == null) {
            telemetry.addLine("oi");
        }
        telemetry.addLine("Starting");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        telemetry.addLine("Frontleft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        telemetry.addLine("backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        telemetry.addLine("frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        telemetry.addLine("backRight");
    }

    public void setMotors(double frontLeft, double backLeft, double frontRight, double backRight) {
        this.frontLeft.setPower(frontLeft);
        this.backLeft.setPower(backLeft);
        this.frontRight.setPower(frontRight);
        this.backRight.setPower(backRight);
        telemetry.addData("Front Left Power:", this.frontLeft.getPower());
        telemetry.addData("Back Left Power", this.backLeft.getPower());
        telemetry.addData("Front Right Power", this.frontRight.getPower());
        telemetry.addData("Back Right Power", this.backRight.getPower());
        telemetry.update();
    }

    public void tankDrive(double power, double turn) {
//        rightPower *= -1;
        telemetry.addData("Power: ", power);
        telemetry.addData("Turn: ", turn);


        this.setMotors(power, power, -power, -power);
        this.setMotors(turn, turn, -turn, -turn);


    }
}
