package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="viperTest")
@Disabled

public class viperTest extends LinearOpMode {

    private DcMotor viper;
    private DcMotor rightSup;
    private DcMotor leftSup;
    private Servo pulso;
    private Servo garra;

    double powerv = 1;

    int timeT = 100;

    boolean openGarra = true;

    boolean upPulso = true;

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

        pulso = hardwareMap.get(Servo.class, "pulso");
        garra = hardwareMap.get(Servo.class, "garra");

        pulso.setPosition(0.9);
        garra.setPosition(0.2);

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.x && !upPulso) {
                pulso.setPosition(0.9);
                sleep(100);
                upPulso = true;
            } else if (gamepad1.x && upPulso) {
                pulso.setPosition(0.65);
                sleep(100);
                upPulso = false;
            }

            if(gamepad1.a && openGarra) {
                garra.setPosition(0.8);
                sleep(100);
                openGarra = false;
            } else if(gamepad1.a && !openGarra) {
                garra.setPosition(0.2);
                sleep(100);
                openGarra = true;
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

            } else if (gamepad1.left_trigger > 0.3) {

                viper.setTargetPosition(viperCP + timeT);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(powerv);

            }

            if(gamepad1.right_bumper) {

                leftSup.setTargetPosition(leftCP + timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                rightSup.setTargetPosition(rightCP + timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                sleep(1);

            } else if (gamepad1.left_bumper) {

                rightSup.setTargetPosition(rightCP - timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                leftSup.setTargetPosition(leftCP - timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                sleep(1);

            }

            telemetry.addData("Starting at",  "%7d :%7d",
                    leftSup.getCurrentPosition(),
                    rightSup.getCurrentPosition());
            telemetry.update();

        }

    }

}
