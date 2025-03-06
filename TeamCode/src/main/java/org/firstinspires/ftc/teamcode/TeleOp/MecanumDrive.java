package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Mecanum Drive")
public class MecanumDrive extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

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

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                double y_stick = -gamepad1.right_stick_y;

                double heading = Math.atan2(y_stick, gamepad1.right_stick_x);
                double speed = Math.hypot(gamepad1.right_stick_x, y_stick);

                double robot_heading = heading - Math.PI/4;

                double rightFrontPower = Math.sin(robot_heading) * speed;
                double leftBackPower = Math.sin(robot_heading) * speed;

                double leftFrontPower = Math.cos(robot_heading) * speed;
                double rightBackPower = Math.cos(robot_heading) * speed;

                this.setMotors(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
                if (gamepad1.left_stick_x != 0) {
                    this.turn(gamepad1.left_stick_x);
                }
                telemetry.update();

            }
        }
    }

    private void turn(double turn) {
        this.setMotors(turn, turn, -turn, -turn);
    }
    private void setMotors(double frontLeft, double backLeft, double frontRight, double backRight) {
        this.frontLeft.setPower(frontLeft);
        telemetry.addData("Front left power", this.frontLeft.getPower() + "");
        this.backLeft.setPower(backLeft);
        telemetry.addData("Back left power", this.backLeft.getPower() + "");
        this.frontRight.setPower(frontRight);
        telemetry.addData("Front right power", this.frontRight.getPower() + "");
        this.backRight.setPower(backRight);
        telemetry.addData("Back right power", this.backRight.getPower() + "");
    }
}
