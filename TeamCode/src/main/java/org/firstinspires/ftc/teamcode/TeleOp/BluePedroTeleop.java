package org.firstinspires.ftc.teamcode.TeleOp;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.DriveBase;
import org.firstinspires.ftc.teamcode.Utils.Intake;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.SparkFunOdo;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@TeleOp(name = "Blue Pedro Teleop")
public class BluePedroTeleop extends LinearOpMode {

    Robot robot;
    DriveBase drive;
    SparkFunOdo odo;
    Intake intake;


    private final Pose loadingZone = new Pose(26.919, 24.024, 5*Math.PI/4);
    private final Pose destination = new Pose(118.963,119.686,Math.PI/4);
    private final Pose destinationPass = new Pose(27.063,116.647,0);
    private final Pose loadingZonePass = new Pose(128.804, 30.971, 0);

    private final Pose startPose = new Pose(135,33,Math.PI/2);

    private Follower follower;

    private Gamepad.RumbleEffect sos;
    private String actionState = "Outtake";

    public static PathBuilder builder = new PathBuilder();

    public static PathChain paths = builder
            .addPath(
                    // Line 1
                    new BezierLine(
                            new Point(136.764, 32.563, Point.CARTESIAN),
                            new Point(136.040, 113.319, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
            .build();


    boolean reverseSwitch, sensSwitch, reset, autoMove, stateSwitch;
    int pathState;


    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                update();

                if (autoMove && (Math.abs(gamepad1.left_stick_x) > 0 || Math.abs(gamepad1.right_stick_x) > 0 || Math.abs(gamepad1.left_stick_y) > 0)) {
                    autoMove = false;
                }

                if (!autoMove) {
                    drive.moveAbs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
                }


                //GPad 2 A: Reverses spin
                if (gamepad2.a) {
                    if (!reverseSwitch) {
                        intake.reverse();
                        reverseSwitch = true;
                    }

                } else {
                    reverseSwitch = false;
                }

                //GPad 2 Back: reset field centric
                if (gamepad2.back) {
                    if (!reset) {
                        reset = true;
//                        odo.reset();
                    }
                } else {
                    reset = false;
                }

                //GPad 2 X: switches action state
                if (gamepad2.x) {
                    if (!stateSwitch) {
                        stateSwitch = true;
                        switchActionState();
                    }
                } else {
                    stateSwitch = false;
                }


                //Gpad1 Left Bumper: toggles sensitivity
                if (gamepad1.left_bumper) {
                    if (!sensSwitch) {
                        sensSwitch = true;
                        if (drive.sensitivity == 1) {
                            drive.setSensitivity(0.5);
                        } else {
                            drive.setSensitivity(1);
                        }
                    }
                } else {
                    sensSwitch = false;
                }

                //Gpad 1 X: destination move
                if (gamepad1.x) {
                    if (actionState.equals("Outtake")) {
                        setAutoMove(destination, destinationPass);
                    } else if (actionState.equals("Intake")) {
                        setAutoMove(loadingZone, destinationPass);
                    } else {
                        gamepad1.runRumbleEffect(sos);
                    }
                }

                //Gpad 1 Y: Loading move
                if (gamepad1.y) {
                    if (actionState.equals("Outtake")) {
                        setAutoMove(destination, loadingZonePass);
                    } else if (actionState.equals("Intake")) {
                        setAutoMove(loadingZone, loadingZonePass);
                    } else {
                        gamepad1.runRumbleEffect(sos);
                    }
                }



                //Gpad 1 B: No pass move
                if (gamepad1.b) {
                    if (actionState.equals("Outtake")) {
                        setAutoMove(destination);
                    } else if (actionState.equals("Intake")) {
                        setAutoMove(loadingZone);
                    } else {
                        gamepad1.runRumbleEffect(sos);
                    }
                }

                if (gamepad1.a) {
                    follower.followPath(paths, false);
                    autoMove = true;
                }
                if (autoMove) {
                    if (!follower.isBusy()) {
                        autoMove = false;
                        switchActionState();
                    }
                    telemetry.addLine("AutoMoving");
                    follower.update();
                }

                telemetry.update();
            }
        }
    }

    private void switchActionState() {
        if (actionState.equals("Outtake")) {
            actionState = "Intake";
        } else if (actionState.equals("Intake")) {
            actionState = "Outtake";
        }
    }


    private void setAutoMove(Pose endPos) {
        Path path;
        SparkFunOTOS.Pose2D robotPos = odo.getPos();

        path = new Path(new BezierLine(new Point(new Pose(robotPos.x, robotPos.y, robotPos.h)), new Point(endPos)));
        path.setLinearHeadingInterpolation(odo.getPos().h, endPos.getHeading());


        follower.followPath(path);
        autoMove = true;
    }

    private void setAutoMove(Pose endPos, Pose passPoint) {
        Path path;
        SparkFunOTOS.Pose2D robotPos = odo.getPos();

        path = new Path(new BezierCurve(new Point(new Pose(robotPos.x, robotPos.y, robotPos.h)), new Point(passPoint), new Point(endPos)));
        path.setLinearHeadingInterpolation(odo.getPos().h, endPos.getHeading());

        follower.followPath(path);
        autoMove = true;
    }
    public void initialize() {
        robot = new Robot(hardwareMap, startPose);
        drive = new DriveBase(robot, gamepad1);
        odo = robot.odo;
        intake = new Intake(robot);

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(robot.startPos);
        follower.setStartingPose(startPose);

        sos = new Gamepad.RumbleEffect.Builder()
                .addStep(1, 1, 100).addStep(1, 1, 100).addStep(1, 1, 100)
                .addStep(1,1,300).addStep(1,1,300).addStep(1,1,300)
                .addStep(1, 1, 100).addStep(1, 1, 100).addStep(1, 1, 100).build();
    }

    public void update() {
        SparkFunOTOS.Pose2D pos = odo.getPos();
        telemetry.addLine("Gamepad 2: A: reverse intake, Back: Resets fieldcentric, X: Switches actionState");

        telemetry.addLine("Gamepad 1: Left Bumper: sensitivity toggle, X: Destination Move, Y: Loading Move, B: Move without control point");

        telemetry.addData("X: ", pos.x);
        telemetry.addData("Y: ", pos.y);
        telemetry.addData("Heading: ", pos.h);
        telemetry.addData("Sensitivity: ", drive.sensitivity);
        telemetry.addData("Direction: ", intake.getDirection());
        telemetry.addData("Action State: ", actionState);
        telemetry.addData("Current Path", follower.getCurrentPath());
        intake.update();
    }

}
