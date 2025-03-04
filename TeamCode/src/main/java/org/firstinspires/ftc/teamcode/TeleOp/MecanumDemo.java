package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;


@TeleOp(name = "mecanum bot demo", group = "Teleop")
public class MecanumDemo extends LinearOpMode {

    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;

    public void moveY(int power, int direction) {
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
    }

    public void moveX(int power, int direction) {
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
        backLeft.setPower(power * direction);
    }

    public void initialize() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Press Start When Ready","");
        telemetry.update();
    }
    public void runOpMode(){
        initialize();

        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
