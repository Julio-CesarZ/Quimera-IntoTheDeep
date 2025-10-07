package org.firstinspires.ftc.teamcode.TestMode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name = "servoTest", group = "Test")
public class servoTest extends LinearOpMode {

    private Servo s1;
    private Servo s2;

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-24, -64.5, Math.toRadians(90)));

        s1 = hardwareMap.get(Servo.class, "s1");
        s2 = hardwareMap.get(Servo.class, "s2");

        s2.setDirection(Servo.Direction.REVERSE);

        String mode = "";

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Servos: ", mode);

            if(gamepad1.a) {
                s1.setPosition(0.5);
                s2.setPosition(0.5);
                mode = "stopped";
            } else if(gamepad1.x) {
                s1.setPosition(1);
                s2.setPosition(1);
                mode = "forward";
            } else if(gamepad1.b) {
                s1.setPosition(0);
                s2.setPosition(0);
                mode = "back";
            }

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(-gamepad1.left_stick_y * 0.7, -gamepad1.left_stick_x * 0.7),
                    -gamepad1.right_stick_x * 0.7
            ));
            drive.updatePoseEstimate();

            telemetry.update();
        }
    }
}