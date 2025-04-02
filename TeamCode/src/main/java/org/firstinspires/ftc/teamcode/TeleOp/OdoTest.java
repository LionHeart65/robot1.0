package org.firstinspires.ftc.teamcode.TeleOp;

import com.pedropathing.follower.Follower;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.DriveBase;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@TeleOp(name = "Odo Teleop")
public class OdoTest extends LinearOpMode {


    public SparkFunOdo odo1;
    public SparkFunOdo odo2;

    public Follower pedro;

    private final Pose loadingZone = new Pose(26.919, 24.024, 5*Math.PI/4);
    private final Pose destination = new Pose(118.963,119.686,Math.PI/4);

    private Path path = new Path(new BezierLine(new Point(loadingZone), new Point(destination)));

    public Robot r;

    public DriveBase drive;
    @Override
    public void runOpMode() {

        odo1 = new SparkFunOdo(hardwareMap, new SparkFunOTOS.Pose2D(-6.49606,0,0), new SparkFunOTOS.Pose2D(0,0,0));
        odo2 = new SparkFunOdo(hardwareMap, new SparkFunOTOS.Pose2D(-6.49606,0,0), new SparkFunOTOS.Pose2D(0,0,0));
        Constants.setConstants(FConstants.class, LConstants.class);

        pedro = new Follower(hardwareMap);

        r = new Robot(hardwareMap, new Pose(0,0,0));

        drive = new DriveBase(r, gamepad1);
        telemetry.addData("Odo1: ", odo1.getPos().x);
        telemetry.addData("Odo2: ", odo2.getPos().x);
        telemetry.update();
        sleep(3000);
        waitForStart();
        pedro.followPath(path);
        telemetry.addData("path", path);
        while (opModeIsActive()) {


            if (gamepad1.x) {
                pedro.update();
            } else {
                drive.moveAbs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            }
        }

    }



}
