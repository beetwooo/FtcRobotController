package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mechanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Mechanisms.ShooterV2;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;


@TeleOp(name = "BlueTeleOP", group = "2025-2026 Test OP")
public class BlueTeleOP extends OpMode {

    double forward, strafe, rotate;

    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();


    ShooterV2 ShooterV2 = new ShooterV2();

    @Override
    public void init() {

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);
        ShooterV2.init(hardwareMap);

/*
        follower = Constants.createFollower(hardwareMap);

        //follower.setStartingPose(startPose);
        follower.setStartingPose(new Pose(72, 72, Math.toRadians(90)));

 */

    }

    @Override
    public void loop(){

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        MecanumDrive.MoveRobot(y, x, rx);

        /*
        Pose2D robotPosition = MecanumDrive.getTraditionalPose();
        Shooter.adjustForBlueGoal(robotPosition);

        if (gamepad1.right_bumper) {
            Shooter.setPIDPower(ShooterPID.TARGET_VELOCITY);
        } else {
            Shooter.resetPID();
        }

         */

        //=============ARTIFACT INTAKE================
        if (gamepad1.left_bumper) {
            ArtifactIntake.setPower(0.7, 0.3, 0.5);
        } else if(gamepad1.right_bumper)  {
            ArtifactIntake.setPower(0.9, 0.9, 0.0);
        } else {
            ArtifactIntake.setPower(0, 0, 0.5);
        }

        if(gamepad1.x){
            ShooterV2.setVelocity(2000);
            ShooterV2.setPosition(0.5);
        } else{
            ShooterV2.setVelocity(0);
            ShooterV2.setPosition(0.4);
        }

//============================================================================================

        // ==================== 텔레메트리 추가 부분 ====================
        Pose2D position = MecanumDrive.getTraditionalPose();
        if (position != null) {
            telemetry.addData("Heading (Degrees)", position.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Heading (Radians)", position.getHeading(AngleUnit.RADIANS));
        }
        telemetry.addData("curVelo", ShooterV2.ShooterLeft.getVelocity());

        /*
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getHeading()));

         */
        telemetry.update();

        // ============================================================
    }
}