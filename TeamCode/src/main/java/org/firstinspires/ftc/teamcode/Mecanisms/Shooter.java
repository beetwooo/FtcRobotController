package org.firstinspires.ftc.teamcode.Mecanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.ShooterPID;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Shooter {

    private static final double RED_GOAL_X = 144.0;
    private static final double RED_GOAL_Y = 144.0;
    private static final double BLUE_GOAL_X = 0.0;
    private static final double BLUE_GOAL_Y = 144.0;

    GoBildaPinpoint GobildaPinpoint = new GoBildaPinpoint();
    ShooterPID ShooterPID = new ShooterPID();

    private DcMotor LeftMotor;
    private DcMotor RightMotor;
    private Servo Adjuster;
    private int LastEncoderPosition;
    private long LastEncoderTime;

    public void init(HardwareMap hardwareMap) {
        LeftMotor = hardwareMap.get(DcMotor.class, "SL");
        RightMotor = hardwareMap.get(DcMotor.class, "SR");
        Adjuster = hardwareMap.get(Servo.class, "SA");

        LeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LastEncoderPosition = LeftMotor.getCurrentPosition();
        LastEncoderTime = System.nanoTime();
    }

    public void setPower(double power) {
        LeftMotor.setPower(power);
        RightMotor.setPower(power);
    }

    public void setPIDPower(double targetVelocity) {
        long now = System.nanoTime();
        double deltaTime = (now - LastEncoderTime) / 1_000_000_000.0;
        int currentPosition = LeftMotor.getCurrentPosition();
        double currentVelocity = deltaTime > 0.0
                ? (currentPosition - LastEncoderPosition) / deltaTime
                : 0.0;

        LastEncoderPosition = currentPosition;
        LastEncoderTime = now;

        double power = ShooterPID.calculate(targetVelocity, currentVelocity);
        setPower(Math.max(0.0, Math.min(1.0, power)));
    }

    public void resetPID() {
        ShooterPID.reset();
        LastEncoderPosition = LeftMotor.getCurrentPosition();
        LastEncoderTime = System.nanoTime();
        setPower(0.0);
    }


    public void setAngle(double position) {
        Adjuster.setPosition(Math.max(0.0, Math.min(1.0, position)));
    }
    //==========================RED=============================
    public double distanceToRed(Pose2D robotPose) {
        return distanceToGoal(robotPose, RED_GOAL_X, RED_GOAL_Y);
    }

    public void adjustForRedGoal(Pose2D robotPose) {
        setAngle(adjusterPosition(distanceToRed(robotPose)));
    }

    //==========================BLUE=============================

    public double distanceToBlue(Pose2D robotPose) {
        return distanceToGoal(robotPose, BLUE_GOAL_X, BLUE_GOAL_Y);
    }



    public void adjustForBlueGoal(Pose2D robotPose) {
        setAngle(adjusterPosition(distanceToBlue(robotPose)));
    }

    public double adjusterPosition(double distanceInches) {
        // *IMPORTANT* Continuous mapping ->>>>> 144 inches = 0.50 and 106.56 inches ≈ 0.37.
        return distanceInches / 288.0;
    }

    private double distanceToGoal(Pose2D robotPose, double goalX, double goalY) {
        double robotX = robotPose.getX(DistanceUnit.INCH);
        double robotY = robotPose.getY(DistanceUnit.INCH);
        return Math.hypot(goalX - robotX, goalY - robotY);
    }
}
