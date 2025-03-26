package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Gate Test")
public class servoGateTest extends OpMode {
    Servo gate;
    public void init() {
        gate = hardwareMap.servo.get("servoGate");
    }
    public void loop() {
        gate.setPosition(0.44);
        //position 0.44 is closed (does not allow objects through)
        if (gamepad1.a) {
            gate.setPosition(0);
            //position 0 is open (allows objects through)
        }
        telemetry.addData("servoPos", gate.getPosition());
    }
}
