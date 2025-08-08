package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="leftSupTest")

public class leftSupTest extends LinearOpMode {

    private DcMotor leftSup;

    double powerv = 1;

    int timeT = 1000;

    @Override
    public void runOpMode() {

        leftSup = hardwareMap.get(DcMotor.class, "leftSup");

        leftSup.setDirection(DcMotor.Direction.REVERSE);

        leftSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            //int leftCP = leftSup.getCurrentPosition();

            //leftSup.setTargetPosition(leftCP);
            //leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //leftSup.setPower(powerv);

            if(gamepad1.right_bumper) {

                leftSup.setTargetPosition(timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                timeT = timeT + 1000;

                sleep(500);

            } else if (gamepad1.left_bumper) {

                leftSup.setTargetPosition(timeT);
                leftSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftSup.setPower(powerv);

                timeT = timeT - 1000;

                sleep(500);

            }

            telemetry.addData("Starting at", timeT);
            telemetry.update();

        }

    }

}
