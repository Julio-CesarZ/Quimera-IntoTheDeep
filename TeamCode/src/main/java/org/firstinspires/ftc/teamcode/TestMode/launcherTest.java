package org.firstinspires.ftc.teamcode.TestMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "launcherTest", group = "Test")
public class launcherTest extends LinearOpMode {

    double launchPower = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor testMotor0 = hardwareMap.get(DcMotor.class, "frontleft");
        DcMotor testMotor1 = hardwareMap.get(DcMotor.class, "backleft");

        Servo s1 = hardwareMap.get(Servo.class, "s1");
        Servo s2 = hardwareMap.get(Servo.class, "s2");
        Servo s3 = hardwareMap.get(Servo.class, "s3");

        testMotor0.setDirection(DcMotorSimple.Direction.REVERSE);
        s2.setDirection(Servo.Direction.REVERSE);

        String mode = "";

        boolean ativado = false;
        boolean intake = true;

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Servos: ", mode);
            telemetry.addData("LaunchP: ", launchPower);
            telemetry.addData("lance: ", ativado);
            telemetry.addData("intake: ", intake);

            if(gamepad1.left_trigger > 0.3 && launchPower > 0) {
                launchPower = launchPower - 0.05;
                sleep(100);
            } else if (gamepad1.right_trigger > 0.3 && launchPower < 1) {
                launchPower = launchPower + 0.05;
                sleep(100);
            }

            if(gamepad1.left_bumper && intake) {
                intake = false;
                sleep(150);
            } else if (gamepad1.left_bumper && !intake) {
                intake = true;
                sleep(150);
            }

            if(intake) {
                if (gamepad1.a) {
                    s1.setPosition(0.5);
                    s2.setPosition(0.5);
                    mode = "stopped";
                } else if (gamepad1.x) {
                    s1.setPosition(1);
                    s2.setPosition(1);
                    mode = "forward";
                } else if (gamepad1.b) {
                    s1.setPosition(0);
                    s2.setPosition(0);
                    mode = "back";
                }
            } else {
                if (gamepad1.a) {
                    s3.setPosition(0.5);
                } else if (gamepad1.x) {
                    s1.setPosition(1);
                } else if (gamepad1.b) {
                    s1.setPosition(0);
                }
            }

            if(gamepad1.y && !ativado) {
                ativado = true;
                sleep(150);
            } else if (gamepad1.y && ativado) {
                ativado = false;
                sleep(150);
            }

            if(ativado) {
                testMotor0.setPower(1 * launchPower);
                testMotor1.setPower(1 * launchPower);
            } if(!ativado) {
                testMotor0.setPower(0);
                testMotor1.setPower(0);
            }

            telemetry.update();

        }
    }
}