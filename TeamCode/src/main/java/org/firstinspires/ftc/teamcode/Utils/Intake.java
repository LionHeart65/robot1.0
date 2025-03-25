package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    private final DcMotor motor;

    private boolean running;

    private int direction = 1;
    public Intake(Robot r) {
        motor = r.intakeMotor;
    }

    public void update() {
        if (running) {
            motor.setPower(direction);
        }
    }
    public void reverse() {
        direction *= -1;
    }

    public void stopStart() {
        running = !running;
    }


}
