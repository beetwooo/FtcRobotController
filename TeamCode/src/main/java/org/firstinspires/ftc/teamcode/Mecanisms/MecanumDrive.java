package org.firstinspires.ftc.teamcode.Mecanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class MecanumDrive {

    private DcMotor FrontLeftMotor, FrontRightMotor, BackLeftMotor, BackRightMotor;

    GoBildaPinpointDriver ODO;
    private IMU IMU;

    public void init(HardwareMap hwMap){

        ODO = hwMap.get(GoBildaPinpointDriver.class, "odo");

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

        //==============ODO VERSION==================

        //ODO.setOffsets(,);
        ODO.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        ODO.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);

        ODO.resetPosAndIMU();
        // Pose2D startingPosition = new Pose2D(DistanceUnit.MM. . . AngleUnit.RADIANS, );
        //ODO.setPosition(startingPosition);


        //==============IMU VERSION==================
        /*
        IMU = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        );

        IMU.initialize(new IMU.Parameters(RevOrientation));

         */

    }

    public void MoveRobot(double forward, double strafe, double rotate){

        double GlobalStrafe, GlobalForward;
        double CosAngle, SinAngle;
        double Heading;
        double[] NewWheelSpeed = new double[4];

        Pose2D Position = ODO.getPosition();
        Heading = Position.getHeading(AngleUnit.RADIANS);

        CosAngle = Math.cos((Math.PI / 2) - Heading);
        SinAngle = Math.sin((Math.PI / 2) - Heading);

        GlobalStrafe = -forward * SinAngle + strafe * CosAngle;
        GlobalForward = forward * CosAngle + strafe * SinAngle;

        NewWheelSpeed[0] = GlobalForward + GlobalForward + rotate;
        NewWheelSpeed[1] = GlobalForward - GlobalStrafe - rotate;
        NewWheelSpeed[2] = GlobalForward - GlobalStrafe + rotate;
        NewWheelSpeed[3] = GlobalForward + GlobalStrafe - rotate;

       FrontLeftMotor.setPower(NewWheelSpeed[0]);
       FrontRightMotor.setPower(NewWheelSpeed[1]);
       BackLeftMotor.setPower(NewWheelSpeed[2]);
       BackRightMotor.setPower(NewWheelSpeed[3]);



    }

    //==============IMU VERSION==================
    /*
    public void Drive(double forward, double strafe, double rotate){
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

        this.Drive(newForward, newStrafe, rotate);

    }

     */


}
