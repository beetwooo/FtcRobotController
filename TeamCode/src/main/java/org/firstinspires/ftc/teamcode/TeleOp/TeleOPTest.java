package org.firstinspires.ftc.teamcode.TeleOp;

import com.bylazar.configurables.annotations.Configurable;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Configs.ShooterPID;
import org.firstinspires.ftc.teamcode.Mechanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

@Configurable
@TeleOp(name = "TeleOPTest", group = "2025-2026 Test OP")
public class TeleOPTest extends OpMode {
    double forward, strafe, rotate;
    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();

    Shooter Shooter = new Shooter();


    @Override
    public void init() {

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);
        Shooter.init(hardwareMap);

    }

    @Override
    public void loop(){

        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        MecanumDrive.MoveRobot(forward, strafe, rotate);

//================================================
        Pose2D robotPosition = MecanumDrive.getTraditionalPose();
        Shooter.adjustForBlueGoal(robotPosition);

        if (gamepad1.right_bumper) {
            Shooter.setPIDPower(ShooterPID.TARGET_VELOCITY);
        } else {
            Shooter.resetPID();
        }
//================================================

        if(gamepad1.right_bumper){
            Shooter.setPower(1.0);
        }else{
            Shooter.setPower(0.0);
        }

    }
}
