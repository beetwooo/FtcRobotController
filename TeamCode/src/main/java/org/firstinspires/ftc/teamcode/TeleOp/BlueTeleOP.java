package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mechanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Mechanisms.ShooterV2;

@TeleOp(name = "BlueTeleOP", group = "2025-2026 Test OP")
public class BlueTeleOP extends OpMode {

    double forward, strafe, rotate;

    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();

    Shooter Shooter = new Shooter();
    ShooterV2 ShooterV2 = new ShooterV2();

    @Override
    public void init() {

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);
        Shooter.init(hardwareMap);
        ShooterV2.init(hardwareMap);

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

        if(gamepad1.right_trigger_pressed){
            ShooterV2.setPower(0.5, 0.5);
        }else if(gamepad1.left_trigger_pressed){
            ShooterV2.setPower(0.2, 1);
        }
        else{
            ShooterV2.setPower(0, 0.4);
        }

//============================================================================================

        // ==================== 텔레메트리 추가 부분 ====================
        Pose2D position = MecanumDrive.getTraditionalPose();
        if (position != null) {
            telemetry.addData("Heading (Degrees)", position.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Heading (Radians)", position.getHeading(AngleUnit.RADIANS));
        }
        telemetry.update();

        // ============================================================
    }
}