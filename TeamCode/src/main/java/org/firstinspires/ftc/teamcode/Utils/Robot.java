package org.firstinspires.ftc.teamcode.Utils;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public final DcMotor frontLeft, backLeft, frontRight, backRight;
    public DcMotor intakeMotor;

    public final SparkFunOdo odo;

    public Robot(@NonNull HardwareMap hardwareMap) {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

//        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        odo = new SparkFunOdo(hardwareMap, new SparkFunOTOS.Pose2D(-6.49606,0,0), new SparkFunOTOS.Pose2D(0,0,0));


    }
}
