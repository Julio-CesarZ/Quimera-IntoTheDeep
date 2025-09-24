package org.firstinspires.ftc.teamcode.TestMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorTestWithoutControl", group = "Test")
public class motorTestWithoutControl extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor testMotor0 = hardwareMap.get(DcMotor.class, "0");
        DcMotor testMotor1 = hardwareMap.get(DcMotor.class, "1");
        DcMotor testMotor2 = hardwareMap.get(DcMotor.class, "2");
        DcMotor testMotor3 = hardwareMap.get(DcMotor.class, "3");

        waitForStart();

        while (opModeIsActive()) {

            testMotor0.setPower(0);
            testMotor1.setPower(0);
            testMotor2.setPower(0);
            testMotor3.setPower(0);
            sleep(2500);
            testMotor0.setPower(0.3);
            testMotor1.setPower(0.3);
            testMotor2.setPower(0.3);
            testMotor3.setPower(0.3);
            sleep(5000);
            testMotor0.setPower(0.5);
            testMotor1.setPower(0.5);
            testMotor2.setPower(0.5);
            testMotor3.setPower(0.5);
            sleep(5000);
            testMotor0.setPower(0.7);
            testMotor1.setPower(0.7);
            testMotor2.setPower(0.7);
            testMotor3.setPower(0.7);
            sleep(5000);
            testMotor0.setPower(1);
            testMotor1.setPower(1);
            testMotor2.setPower(1);
            testMotor3.setPower(1);
            sleep(5000);

        }
    }
}