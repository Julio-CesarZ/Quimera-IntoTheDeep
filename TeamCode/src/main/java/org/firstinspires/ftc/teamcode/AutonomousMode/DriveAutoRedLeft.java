package org.firstinspires.ftc.teamcode.AutonomousMode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "RedLeft", group = "Autonomous")
@Disabled
public class DriveAutoRedLeft extends LinearOpMode {

    public class moverGarra {
        private Servo garra;
        private Servo pulso;

        public moverGarra(HardwareMap hardwareMap) {
            garra = hardwareMap.get(Servo.class, "garra");
            pulso = hardwareMap.get(Servo.class, "pulso");
        }

        public class AbrirGarra implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                garra.setPosition(0.85);
                sleep(800);
                return false;
            }
        }
        public Action abrirGarra() {
            return new AbrirGarra();
        }

        public class FecharGarra implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                garra.setPosition(0);
                sleep(800);
                return false;
            }
        }
        public Action fecharGarra() {
            return new FecharGarra();
        }

        public class InitialGarra implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                garra.setPosition(0);
                return false;
            }
        }
        public Action initialGarra() {
            return new InitialGarra();
        }

        public class DescerPulso implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pulso.setPosition(0.9);
                sleep(1000);
                return false;
            }
        }

        public Action descerPulso() {
            return new DescerPulso();
        }

        public class DescerPulsoSlow implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pulso.setPosition(0.9);
                sleep(500);
                return false;
            }
        }

        public Action descerPulsoSlow() {
            return new DescerPulsoSlow();
        }

        public class SubirPulso implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pulso.setPosition(0.6);
                sleep(500);
                return false;
            }
        }

        public Action subirPulso() {
            return new SubirPulso();
        }


        public class RetrairPulso implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pulso.setPosition(0.43);
                sleep(500);
                return false;
            }
        }

        public Action retrairPulso() {
            return new RetrairPulso();
        }
    }

    public class moverArm {
        private DcMotor leftSup, rightSup;

        public moverArm(HardwareMap hardwareMap) {
            leftSup = hardwareMap.get(DcMotor.class, "leftSup");
            rightSup = hardwareMap.get(DcMotor.class, "rightSup");
            rightSup.setDirection(DcMotor.Direction.REVERSE);
            rightSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftSup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftSup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public class LevantarArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, -1300, 0.7);
                encoder(rightSup, -1350, 0.7);
                sleep(1000);
                return false;
            }
        }

        public Action levantarArm() {
            return new LevantarArm();
        }

        public class LevantarArmSlow implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, -1400, 0.35);
                encoder(rightSup, -1450, 0.35);
                sleep(1000);
                return false;
            }
        }

        public Action levantarArmSlow() {
            return new LevantarArmSlow();
        }

        public class AbaixarArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, 0, 0.7);
                encoder(rightSup, 0, 0.7);
                sleep(500);
                return false;
            }
        }

        public Action abaixarArm() {
            return new AbaixarArm();
        }

        public class SetArmPosition implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, -800, 0.5);
                encoder(rightSup, -800, 0.5);
                sleep(200);
                return false;
            }
        }
        public Action setArmPosition() {
            return new SetArmPosition();
        }

        public class InitialArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, -200, 0.1);
                encoder(rightSup, -200, 0.1);
                return false;
            }
        }
        public Action initialArm() {
            return new InitialArm();
        }

        public class ZeroArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(leftSup, 0, 0.1);
                encoder(rightSup, 0, 0.1);
                return false;
            }
        }
        public Action zeroArm() {
            return new ZeroArm();
        }
    }

    public class moverViper {
        private DcMotor viper;

        public moverViper(HardwareMap hardwareMap) {
            viper = hardwareMap.get(DcMotorEx.class, "viper");
            viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public class EsticarViper implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(viper, -2600, 7);
                sleep(1000);
                return false;
            }
        }
        public Action esticarViper() {
            return new EsticarViper();
        }

        public class RetrairViper implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(viper, -200, 6.5);
                sleep(1000);
                return false;
            }
        }
        public Action retrairViper() {
            return new RetrairViper();
        }

        public class InitialViper implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(viper, 0, 0.1);
                return false;
            }
        }
        public Action initialViper() {
            return new InitialViper();
        }

        public class ZeroViper implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(viper, 0, 0.1);
                return false;
            }
        }
        public Action zeroViper() {
            return new ZeroViper();
        }

        public class SetViperPosition implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                encoder(viper, -500, 5);
                sleep(500);
                return false;
            }
        }
        public Action SetViperPosition() {
            return new SetViperPosition();
        }
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-14.5, -62.25, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        moverGarra garra = new moverGarra(hardwareMap);
        moverArm arm = new moverArm(hardwareMap);
        moverViper viper = new moverViper(hardwareMap);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineToLinearHeading(new Pose2d(-56, -51, Math.toRadians(50)), Math.toRadians(180));
        TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                .strafeToLinearHeading(new Vector2d(-56, -40), Math.toRadians(95));
        TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
                .strafeToLinearHeading(new Vector2d(-46, -56), Math.toRadians(50));
        Action trajectoryActionCloseOut = tab3.endTrajectory().fresh()
                .splineToSplineHeading(new Pose2d(-30, -6, Math.toRadians(180)), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-12, 5), Math.toRadians(0))
                .build();

        // Start Actions
        Actions.runBlocking(garra.retrairPulso());
        Actions.runBlocking(viper.initialViper());
        Actions.runBlocking(arm.initialArm());
        Actions.runBlocking(garra.initialGarra());

        waitForStart();

        if (isStopRequested()) return;

        Action action1 = tab1.build();
        Action action2 = tab2.build();
        Action action3 = tab3.build();

        Actions.runBlocking(
                new SequentialAction(
                        action1,
                        garra.descerPulso(),
                        //arm.levantarArmSlow(),
                        arm.levantarArm(),
                        viper.esticarViper(),
                        garra.subirPulso(),
                        garra.abrirGarra(),
                        garra.descerPulso(),
                        viper.retrairViper(),
                        arm.abaixarArm(),
                        garra.subirPulso(),
                        action2,
                        garra.descerPulso(),
                        viper.SetViperPosition(),
                        garra.fecharGarra(),
                        viper.retrairViper(),
                        arm.setArmPosition(),
                        action3,
                        arm.levantarArm(),
                        viper.esticarViper(),
                        garra.subirPulso(),
                        garra.abrirGarra(),
                        garra.descerPulso(),
                        viper.retrairViper(),
                        arm.abaixarArm(),
                        garra.retrairPulso(),
                        arm.zeroArm(),
                        viper.zeroViper(),
                        trajectoryActionCloseOut
                )
        );
    }

    private void encoder(DcMotor motor, int novoAlvo, double power) {
        motor.setTargetPosition(novoAlvo);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

}
