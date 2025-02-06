package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utils.Robot;

@Autonomous(name = "Auto Observation Zone Park1")
public class AutoOneNetPark extends LinearOpMode {

    Robot robot = new Robot(hardwareMap, telemetry);

    /**
     * This function is executed when this Op Mode is selected.
     */
    // JUST WORK ALREADY
    @Override
    public void runOpMode() {

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                robot.tankDrive(0.5, 0.5);
                sleep(5000);
                robot.tankDrive(-0.5, -0.5);
                telemetry.addLine("wefewfew");

            }
        }
    }
}
