package org.firstinspires.ftc.teamcode.TestMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Drawing;

@TeleOp (name = "Launcher", group = "GTuning")
public class launcherTest extends LinearOpMode {
    private Servo s1;
    private Servo s2;
    private Servo s3;
    private Servo s4;
    private DcMotor Upright;
    private DcMotor Upleft;
    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        drive = new MecanumDrive(hardwareMap, new Pose2d(-24,-64.5, Math.toRadians(90)));

        s1 = hardwareMap.get(Servo.class, "s1");
        s2 = hardwareMap.get(Servo.class, "s2");
        s3 = hardwareMap.get(Servo.class, "s3");
        s4 = hardwareMap.get(Servo.class, "s4");
        Upright = hardwareMap.get(DcMotor.class, "Upright");
        Upleft = hardwareMap.get(DcMotor.class, "Upleft");


        s2.setDirection(Servo.Direction.REVERSE);
        s3.setDirection(Servo.Direction.REVERSE);
        Upleft.setDirection(DcMotor.Direction.REVERSE);

        double launchPower = 1;
        double currentPowerBase = 1 ;

        boolean ativado = false;
        boolean intake = true;
        boolean control2 = false;



        String mode = "";

        waitForStart();

        while (opModeIsActive()) {

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(-gamepad1.left_stick_y * currentPowerBase, -gamepad1.left_stick_x * currentPowerBase),
                    -gamepad1.right_stick_x * currentPowerBase
            ));
            drive.updatePoseEstimate();

            telemetry.addData("Servo: ", mode);
            telemetry.addData("Poder: ", launchPower);
            telemetry.addData("control 2: ", control2);


            if(gamepad1.left_trigger > 0.3 && launchPower > 0) {
                launchPower = launchPower - 0.05;
                sleep(100);
            } else if (gamepad1.right_trigger > 0.3 && launchPower < 1) {
                launchPower = launchPower + 0.05;
                sleep(100);
            }

            if(gamepad1.b && !control2) {
                control2 = true;
                sleep(150);
            } else if(gamepad1.b && control2) {
                control2 = false;
                sleep(150);
            }

            if(!control2) {
                if(gamepad1.y) {
                    s1.setPosition(0);
                    s2.setPosition(0);
                    s3.setPosition(0);
                    s4.setPosition(0);
                    mode = "Moving";
                } else {
                    s1.setPosition(0.5);
                    s2.setPosition(0.5);
                    s3.setPosition(0.5);
                    s4.setPosition(1);
                    mode = "Stopped";
                }

            } else {
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
                        s3.setPosition(0.5);
                        s4.setPosition(0.5);
                        mode = "stopped";
                    } else if (gamepad1.x) {
                        s1.setPosition(1);
                        s2.setPosition(1);
                        s3.setPosition(1);
                        s4.setPosition(2);
                        mode = "forward";
                    } else if (gamepad1.b) {
                        s1.setPosition(0);
                        s2.setPosition(0);
                        s3.setPosition(0);
                        s4.setPosition(0);
                        mode = "back";
                    }
                }
            }

            if(!control2) {
                if(gamepad1.right_bumper) {
                    Upright.setPower(1 * launchPower);
                    Upleft.setPower(1 * launchPower);
                } else {
                    Upright.setPower(0);
                    Upleft.setPower(0);
                }
            } else {
                if(gamepad1.right_bumper && !ativado) {
                    ativado = true;
                    sleep(150);
                } else if (gamepad1.right_bumper && ativado) {
                    ativado = false;
                    sleep(150);
                }

                if(ativado) {
                    Upright.setPower(1 * launchPower);
                    Upleft.setPower(1 * launchPower);
                } if(!ativado) {
                    Upright.setPower(0);
                    Upleft.setPower(0);
                }
            }

            if(!control2) {
                if(gamepad1.x) {
                    s3.setPosition(0);
                    s4.setPosition(0);
                } else {
                    s3.setPosition(0.5);
                    s4.setPosition(0.5);
                }
            }

            telemetry.update();

        }


    }





}




