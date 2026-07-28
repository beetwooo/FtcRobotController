package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configs.ShooterPID;
// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mechanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "RedTeleOp", group = "2025-2026 Test OP")
public class RedTeleOP extends OpMode {

    double forward, strafe, rotate;

    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();
    Shooter Shooter = new Shooter();

    // TurretTracking TurretTracking = new TurretTracking();

    @Override
    public void init() {

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);
        Shooter.init(hardwareMap);

    }

    @Override
    public void loop(){

        /*DEFAULT teleoptest
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

         */

        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = -(gamepad1.right_stick_x);

        MecanumDrive.SetAllianceRed(forward, strafe, rotate);
        Pose2D robotPosition = MecanumDrive.getTraditionalPose();
        Shooter.adjustForRedGoal(robotPosition);

        if (gamepad1.right_bumper) {
            Shooter.setPIDPower(ShooterPID.TARGET_VELOCITY);
        } else {
            Shooter.resetPID();
        }

//================================================

        if (gamepad1.left_bumper) {
            ArtifactIntake.setPower(0.7, 0.3);
        } else {
            ArtifactIntake.setPower(0, 0);
        }
//================================================

    }
}
