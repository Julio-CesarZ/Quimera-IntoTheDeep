package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.config.Config;

@TeleOp(name = "ServoTest", group = "TeleOp")
public class servoTest extends LinearOpMode {


    private DcMotor viper;
    private DcMotor rightSup;
    private DcMotor leftSup;


    private Servo pulso;
    private Servo garra;

    private boolean upPulso = true;
    private boolean openGarra = false;

    private double pulsoP = 0.9;

    private final double powerViper = 1.0;
    private final int passoEncoder = 50;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-24, -64.5, Math.toRadians(90)));

        viper = hardwareMap.get(DcMotor.class, "viper");
        rightSup = hardwareMap.get(DcMotor.class, "rightSup");
        leftSup = hardwareMap.get(DcMotor.class, "leftSup");

        pulso = hardwareMap.get(Servo.class, "pulso");
        garra = hardwareMap.get(Servo.class, "garra");

        rightSup.setDirection(DcMotor.Direction.REVERSE);

        leftSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pulso.setPosition(pulsoP);
        garra.setPosition(0.5);

        double speedLimit = 0.75;

        waitForStart();

        while (opModeIsActive()) {

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(-gamepad1.left_stick_y * speedLimit, -gamepad1.left_stick_x * speedLimit),
                    -gamepad1.right_stick_x * speedLimit
            ));
            drive.updatePoseEstimate();

            Pose2d pose = drive.localizer.getPose();
            telemetry.addData("ServoPosition: ", pulsoP);
            telemetry.addData("garraP", openGarra);
            telemetry.addData("pulsoP: ", upPulso);
            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.addData("BEsquerdo: ", leftSup.getCurrentPosition());
            telemetry.addData("Bdireito: ", rightSup.getCurrentPosition());
            telemetry.addData("Viper: ", viper.getCurrentPosition());

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);


            if (gamepad1.x && pulsoP < 0.9) {
                pulsoP = pulsoP + 0.05;
                pulso.setPosition(pulsoP);
            }

            if (gamepad1.x && pulsoP > 0.3) {
                pulsoP = pulsoP - 0.05;
                pulso.setPosition(pulsoP);
            }


            if (gamepad2.a) {
                garra.setPosition(1);
                sleep(1000);
                garra.setPosition(0.5);
            }

            if (gamepad1.a) {
                if(openGarra) {
                    garra.setPosition(0.75);
                } else {
                    garra.setPosition(0.5);
                }
                openGarra = !openGarra;
                sleep(500);
            }

            int viperCP = viper.getCurrentPosition();
            int leftCP = leftSup.getCurrentPosition();
            int rightCP = rightSup.getCurrentPosition();

            if(gamepad1.b) {
                if(openGarra) {
                    garra.setPosition(0.25);
                } else {
                    garra.setPosition(0.5);
                }
                openGarra = !openGarra;
                sleep(500);
            }

            if(gamepad2.y) {
                encoder(leftSup, leftCP + passoEncoder, 0.3);
                encoder(rightSup, rightCP + passoEncoder, 0.3);
            }

            if(gamepad2.b) {
                encoder(viper, viperCP + passoEncoder, 0.3);
            }

            if (gamepad1.right_trigger > 0.3 && viperCP > -2600) {
                encoder(viper, viperCP - passoEncoder, powerViper);
            } else if (gamepad1.left_trigger > 0.3 && viperCP < -100) {
                encoder(viper, viperCP + passoEncoder, powerViper);
            }

            if (gamepad1.right_bumper && leftCP < -100 && rightCP < -100) {
                encoder(leftSup, leftCP + passoEncoder, powerViper);
                encoder(rightSup, rightCP + passoEncoder, powerViper);
            } else if (gamepad1.left_bumper && leftCP > -1400 && rightCP > -1400) {
                encoder(leftSup, leftCP - passoEncoder, powerViper);
                encoder(rightSup, rightCP - passoEncoder, powerViper);
            }

            telemetry.update();
        }
    }


    private void encoder(DcMotor motor, int novoAlvo, double power) {
        motor.setTargetPosition(novoAlvo);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }
}