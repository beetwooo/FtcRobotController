package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mecanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mecanisms.MecanumDrive;

@TeleOp(name = "TeleOPTest", group = "2025-2026 Test OP")
public class TeleOPTest extends OpMode {

    double forward, strafe, rotate;

    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();

    // TurretTracking TurretTracking = new TurretTracking();

    @Override
    public void init() {

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);

    }

    @Override
    public void loop(){

        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        MecanumDrive.MoveRobot(forward, strafe, rotate);

//================================================

        if (gamepad1.left_bumper) {
            ArtifactIntake.setPower(0.7, 0.3);
        } else {
            ArtifactIntake.setPower(0, 0);
        }
//================================================

    }
}
