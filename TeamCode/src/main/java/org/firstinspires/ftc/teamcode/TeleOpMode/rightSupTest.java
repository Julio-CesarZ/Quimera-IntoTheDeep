package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="rightSupTest")

public class rightSupTest extends LinearOpMode {

    private DcMotor rightSup;

    double powerv = 1;

    int timeT = 1000;

    @Override
    public void runOpMode() {

        rightSup = hardwareMap.get(DcMotor.class, "rightSup");

        rightSup.setDirection(DcMotor.Direction.REVERSE);

        rightSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            //int rightCP = rightSup.getCurrentPosition();

            //rightSup.setTargetPosition(rightCP);
            //rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //rightSup.setPower(powerv);

            if(gamepad1.right_bumper) {

                rightSup.setTargetPosition(timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                timeT = timeT + 1000;

                sleep(500);

            } else if (gamepad1.left_bumper) {

                rightSup.setTargetPosition(timeT);
                rightSup.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSup.setPower(powerv);

                timeT = timeT - 1000;

                sleep(500);

            }

            telemetry.addData("Starting at", timeT);
            telemetry.update();

        }

    }

}
