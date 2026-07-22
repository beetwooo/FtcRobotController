package org.firstinspires.ftc.teamcode.Mecanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {

    private DcMotor FrontLeftMotor, FrontRightMotor, BackLeftMotor, BackRightMotor;
    private IMU IMU;

    public void init(HardwareMap hwMap){

        FrontLeftMotor = hwMap.get(DcMotor.class, "FL");
        FrontRightMotor = hwMap.get(DcMotor.class, "FR");
        BackLeftMotor = hwMap.get(DcMotor.class, "BL");
        BackRightMotor = hwMap.get(DcMotor.class, "BR");

        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        IMU = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        );

        IMU.initialize(new IMU.Parameters(RevOrientation));

    }

    public void drive(double forward, double strafe, double rotate){
        double FrontLeftPower, FrontRightPower, BackLeftPower, BackRightPower;
        double Denominator;

        Denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1);

        FrontLeftPower = (forward + strafe + rotate) / Denominator;
        FrontRightPower = (forward - strafe - rotate) / Denominator;
        BackLeftPower = (forward - strafe + rotate) / Denominator;
        BackRightPower = (forward + strafe - rotate) / Denominator;

        FrontLeftMotor.setPower(FrontLeftPower);
        BackLeftMotor.setPower(BackLeftPower);
        FrontRightMotor.setPower(FrontRightPower);
        BackRightMotor.setPower(BackRightPower);

    }

    public void DriveFieldRelative(double forward, double strafe, double rotate){

        double theta, r;
        double newForward, newStrafe;

        theta = Math.atan2(forward, strafe);
        r = Math.hypot(forward, strafe);

        theta = AngleUnit.normalizeRadians(
                theta - IMU.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)
        );

        newForward = r * Math.sin(theta);
        newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);

    }


}
