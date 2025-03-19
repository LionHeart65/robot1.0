package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.opencv.core.Mat;


public class SparkFunOdo {

    SparkFunOTOS myOtos;

    public SparkFunOdo(HardwareMap map, SparkFunOTOS.Pose2D offset, SparkFunOTOS.Pose2D startingPos) {
        myOtos = map.get(SparkFunOTOS.class, "sensor_otos");
        this.configureOtos(offset, startingPos);
    }

    public SparkFunOTOS.Pose2D getPos() {
        return myOtos.getPosition();
    }

    public void reset() {
        myOtos.resetTracking();
    }

    public void calibrate() {
        myOtos.calibrateImu();
    }

    private void configureOtos(SparkFunOTOS.Pose2D offset, SparkFunOTOS.Pose2D startingPos) {

        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);

        myOtos.setOffset(offset);

        //needs tuning
        myOtos.setLinearScalar(1.0409);
        myOtos.setAngularScalar(0.9606);

        myOtos.calibrateImu();
        myOtos.resetTracking();

        myOtos.setPosition(startingPos);

        // Get the hardware and firmware version
        SparkFunOTOS.Version hwVersion = new SparkFunOTOS.Version();
        SparkFunOTOS.Version fwVersion = new SparkFunOTOS.Version();
        myOtos.getVersionInfo(hwVersion, fwVersion);
    }
}
