package org.firstinspires.ftc.teamcode.TestMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorTest", group = "Test")
@Disabled
public class motorTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor testMotor0 = hardwareMap.get(DcMotor.class, "0");
        DcMotor testMotor1 = hardwareMap.get(DcMotor.class, "1");
        DcMotor testMotor2 = hardwareMap.get(DcMotor.class, "2");
        DcMotor testMotor3 = hardwareMap.get(DcMotor.class, "3");

        waitForStart();

        while (opModeIsActive()) {

            testMotor0.setPower(gamepad1.left_stick_x);
            testMotor1.setPower(gamepad1.left_stick_x);
            testMotor2.setPower(gamepad1.left_stick_x);
            testMotor3.setPower(gamepad1.left_stick_x);

        }
    }
}