package org.firstinspires.ftc.teamcode.DECODE.TeleOpDECODE;

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

@TeleOp (name = "LauncherTeleOp", group = "TeleOp")
public class launcherTeleOp extends LinearOpMode {
    private Servo s0;
    private DcMotor upright;
    private DcMotor upleft;
    private MecanumDrive drive;
    private DcMotor coreC;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        drive = new MecanumDrive(hardwareMap, new Pose2d(-24,-64.5, Math.toRadians(90)));

        s0 = hardwareMap.get(Servo.class, "s0");
        upright = hardwareMap.get(DcMotor.class, "upright");
        upleft = hardwareMap.get(DcMotor.class, "upleft");
        coreC = hardwareMap.get(DcMotor.class, "coreC");

        upright.setDirection(DcMotor.Direction.REVERSE);

        boolean s0L = false;
        boolean upL = false;
        boolean coreL = false;
        boolean retornoL = false;

        String retorno = "";
        String core = "";
        String ups = "";
        String servo = "";

        double currentPowerBase = 0.5;

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Retorno: ", retorno);
            telemetry.addData("Core: ", core);
            telemetry.addData("Launcher: ", ups);
            telemetry.addData("Servo: ", servo);

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(-gamepad1.left_stick_y * currentPowerBase, -gamepad1.left_stick_x * currentPowerBase),
                    -gamepad1.right_stick_x * currentPowerBase
            ));
            drive.updatePoseEstimate();

            s0L = gamepad1.right_trigger > 0.3;

            if(s0L) {
                s0.setPosition(1);
                servo = "Ligado";
            } else {
                s0.setPosition(0.5);
                servo = "Desligado";
            }

            coreL = gamepad1.left_trigger > 0.3;

            if(coreL) {
                coreC.setPower(1);
                core = "Ligado";
            } else {
                coreC.setPower(0);
                core = "Desligado";
            }

            upL = gamepad1.a;

            if(upL) {
                upleft.setPower(-1);
                upright.setPower(-1);
                ups = "Ligado";
            } else {
                upright.setPower(0);
                upleft.setPower(0);
                ups = "Desligado";
            }

            retornoL = gamepad1.b && !coreL && !s0L && !upL;

            if(retornoL) {
                upleft.setPower(1);
                upright.setPower(1);
                s0.setPosition(0);
                coreC.setPower(-1);
                retorno = "Ativado";
            } else if(!retornoL && !coreL && !s0L && !upL) {
                upright.setPower(0);
                upleft.setPower(0);
                s0.setPosition(0.5);
                coreC.setPower(0);
                retorno = "Desativado";
            }

            telemetry.update();

        }


    }





}




