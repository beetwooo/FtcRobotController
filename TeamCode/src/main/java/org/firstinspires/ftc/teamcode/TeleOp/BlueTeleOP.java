package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mecanism.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mecanism.MecanumDrive;

@TeleOp
public class BlueTeleOP extends OpMode {

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

        /*DEFAULT teleoptest
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

         */

        forward = gamepad1.left_stick_x;
        strafe = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;

        MecanumDrive.DriveFieldRelative(forward, strafe, rotate);

//================================================

        if (gamepad1.left_bumper) {
            ArtifactIntake.setPower(0.7, 0.3);
        } else {
            ArtifactIntake.setPower(0, 0);
        }
//================================================

    }
}
