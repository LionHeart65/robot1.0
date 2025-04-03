package org.firstinspires.ftc.teamcode.TeleOp;


import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous(name = "Pedro Test")
public class PedroTest extends LinearOpMode {

    private Follower pedro;

    PathChain line1, line2, line3;


    @Override
    public void runOpMode() {

        buildPaths();

        waitForStart();



        pedro.followPath(line1);

        while (pedro.isBusy() && opModeIsActive()) {
            pedro.update();
        }

    }




    public void buildPaths() {
        Constants.setConstants(FConstants.class, LConstants.class);
        pedro = new Follower(hardwareMap);
        pedro.setStartingPose(new Pose(9,33,-Math.PI/2));

        PathBuilder builder = new PathBuilder();

        line1 = builder
                .addPath(
                        new BezierCurve(
                                new Point(9, 33.286, Point.CARTESIAN),
                                new Point(1.013, 142.553, Point.CARTESIAN),
                                new Point(120.265, 118.239, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(-Math.PI/2, 0)
                .build();

    }
}
