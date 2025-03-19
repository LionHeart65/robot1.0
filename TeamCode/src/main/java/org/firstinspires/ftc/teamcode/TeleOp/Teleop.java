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

    DcMotor intakeMotor;
    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                update();
                if (gamepad1.a) {
                    intake.reverse();
                }
                if (gamepad1.b) {
                    intake.stopStart();
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
        intakeMotor = robot.intakeMotor;
    }

    public void update() {
        drive.moveAbs();
        SparkFunOTOS.Pose2D pos = odo.getPos();
        telemetry.addData("X: ", pos.x);
        telemetry.addData("Y: ", pos.y);
        telemetry.addData("Heading: ", pos.h);
        intake.update();
    }
}
