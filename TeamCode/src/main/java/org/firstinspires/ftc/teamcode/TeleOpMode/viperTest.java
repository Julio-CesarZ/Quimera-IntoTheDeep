package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="viperTest")

public class viperTest extends LinearOpMode {

    private DcMotor viper;
    private DcMotor rightSup;
    private DcMotor leftSup;
    private Servo pulso;

    double powerv = 1;

    int timeT = 50;

    double pulsoP = 0.5;

    double garraP = 0.5;

    @Override
    public void runOpMode() {

        viper = hardwareMap.get(DcMotor.class, "viper");
        rightSup = hardwareMap.get(DcMotor.class, "rightSup");
        leftSup = hardwareMap.get(DcMotor.class, "leftSup");

        rightSup.setDirection(DcMotor.Direction.REVERSE);

        leftSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.y) {
                pulso.setPosition(90);
                sleep(10);
            } else if (gamepad1.b) {
                pulso.setPosition(90);
                sleep(10);
            }

            if(gamepad1.x) {
                timeT = timeT + 10;
                sleep(100);
            } else if(gamepad1.a) {
                timeT = timeT - 10;
                sleep(100);
            }

            int leftCP = leftSup.getCurrentPosition();
            int rightCP = rightSup.getCurrentPosition();
            int viperCP = viper.getCurrentPosition();

            leftSup.setTargetPosition(leftCP);
            leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftSup.setPower(powerv);

            rightSup.setTargetPosition(rightCP);
            rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightSup.setPower(powerv);

            viper.setTargetPosition(viperCP);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(powerv);

            if(gamepad1.right_trigger > 0.3) {

                viper.setTargetPosition(viperCP - timeT);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(powerv);

                sleep(10);

            } else if (gamepad1.left_trigger > 0.3) {

                viper.setTargetPosition(viperCP + timeT);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(powerv);

                sleep(10);

            }

            if(gamepad1.right_bumper) {

                leftSup.setTargetPosition(leftCP + timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                rightSup.setTargetPosition(rightCP + timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                sleep(10);

            } else if (gamepad1.left_bumper) {

                leftSup.setTargetPosition(leftCP - timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                rightSup.setTargetPosition(rightCP - timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                sleep(10);

            }

            telemetry.addData("DelayT: ", timeT);
            telemetry.addData("ViperP: ", powerv);
            telemetry.addData("Starting at",  "%7d :%7d",
                    leftSup.getCurrentPosition(),
                    rightSup.getCurrentPosition());
            telemetry.update();

        }

    }

}
