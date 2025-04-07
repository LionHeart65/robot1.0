package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    private final DcMotor motor;


    private double direction = -0.7;
    public Intake(Robot r) {
        motor = r.intakeMotor;
    }

    public void update() {
            motor.setPower(direction);

    }
    public void reverse() {
        direction *= -1;
    }


    public String getDirection() {
        if (direction < 0) {
            return "Intake";
        } else {
            return "Outtake";
        }
    }


}
