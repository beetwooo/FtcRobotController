package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// import org.firstinspires.ftc.teamcode.AutoCalibration.TurretTracking;
import org.firstinspires.ftc.teamcode.Mecanisms.ArtifactIntake;
import org.firstinspires.ftc.teamcode.Mecanisms.MecanumDrive;

@TeleOp(name = "TeleOPTest-Linear", group = "2025-2026 Test OP")
public class LinearOpModeTest extends LinearOpMode {

    double forward, strafe, rotate;

    MecanumDrive MecanumDrive = new MecanumDrive();
    ArtifactIntake ArtifactIntake = new ArtifactIntake();

    // TurretTracking TurretTracking = new TurretTracking();

    public void runOpMode() throws InterruptedException{

        MecanumDrive.init(hardwareMap);
        ArtifactIntake.init(hardwareMap);


        while(opModeIsActive()){

            forward = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;

            MecanumDrive.MoveRobot(forward, strafe, rotate);

        }

    }



}
/*
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

        MecanumDrive.DriveFieldRelative(forward, strafe, rotate);

//================================================

        if (gamepad1.left_bumper) {
            ArtifactIntake.setPower(0.7, 0.3);
        } else {
            ArtifactIntake.setPower(0, 0);
        }
//================================================

    }
 */
