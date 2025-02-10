package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utils.Robot;

@Autonomous(name = "Auto One Net Observation Zone Park")
public class AutoOneNetPark extends LinearOpMode {


    /**
     * This function is executed when this Op Mode is selected.
     */
    // JUST WORK ALREADY
    @Override
    public void runOpMode() {
        telemetry.addLine("Ccreating robot");
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        if (opModeIsActive()) {

                robot.tankDrive(0.5, 0.5);
                sleep(2000);
                robot.tankDrive(-0.5, -0.5);
                sleep(10000000);
                telemetry.addLine("wefewfew");
        }
    }
}
