package org.firstinspires.ftc.teamcode.TestMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "portTest", group = "Test")
public class portsTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor testMotor0 = hardwareMap.get(DcMotor.class, "0");
        DcMotor testMotor1 = hardwareMap.get(DcMotor.class, "1");
        DcMotor testMotor2 = hardwareMap.get(DcMotor.class, "2");
        DcMotor testMotor3 = hardwareMap.get(DcMotor.class, "3");

        boolean v1 = false;
        boolean v2 = false;
        boolean v3 = false;
        boolean v4 = false;

        float X = 0;

        String vOn = "";

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.a) {
                v1 = true;
                v2 = false;
                v3 = false;
                v4 = false;
                vOn = "Motor 0";
            }
            if(gamepad1.x) {
                v1 = false;
                v2 = true;
                v3 = false;
                v4 = false;
                vOn = "Motor 1";
            }
            if(gamepad1.y) {
                v1 = false;
                v2 = false;
                v3 = true;
                v4 = false;
                vOn = "Motor 2";
            }
            if(gamepad1.b) {
                v1 = false;
                v2 = false;
                v3 = false;
                v4 = true;
                vOn = "Motor 3";
            }

            X = gamepad1.left_stick_x;

            if(v1) {
                testMotor0.setPower(X);
            } else if(v2) {
                testMotor1.setPower(X);
            } else if(v3) {
                testMotor2.setPower(X);
            } else if(v4) {
                testMotor3.setPower(X);
            }

            telemetry.addData("Motor On: ", vOn);
            telemetry.update();

        }
    }
}