package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.math.MathFunctions;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.subConstants.PositionConst;
import org.firstinspires.ftc.teamcode.subConstants.ShooterConst;

public class Shooter {
    public DcMotorEx ShooterLeft, ShooterRight;
    public Servo BackShooter;
    private Servo FrontTurretServo, BackTurretServo;
    GoBildaPinpoint GobildaPinpoint = new GoBildaPinpoint();
    ShooterConst ShooterConst = new ShooterConst();
    PositionConst PositionConst = new PositionConst();

    double positionX;
    double positionY;

    public void init(HardwareMap hwMap){
        GobildaPinpoint.init(hwMap);

        ShooterLeft = hwMap.get(DcMotorEx.class, "SL");
        ShooterRight = hwMap.get(DcMotorEx.class, "SR");
        BackShooter = hwMap.get(Servo.class, "BS");

        FrontTurretServo = hwMap.get(Servo.class, "FT");
        BackTurretServo = hwMap.get(Servo.class, "BT");

        ShooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ShooterLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ShooterLeft.setDirection(DcMotor.Direction.FORWARD);
        ShooterRight.setDirection(DcMotor.Direction.REVERSE);

        ShooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ShooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        com.qualcomm.robotcore.hardware.PIDFCoefficients flywheel_pidfCoeffiients
                = new com.qualcomm.robotcore.hardware
                .PIDFCoefficients(ShooterConst.flywheel_P, ShooterConst.flywheel_I, ShooterConst.flywheel_D, ShooterConst.flywheel_F);

        ShooterLeft.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, flywheel_pidfCoeffiients);
        BackShooter.setPosition(0);

    }

    public void updatePosition(){
        GobildaPinpoint.update();

        positionX = GobildaPinpoint.ODO.getPosX(DistanceUnit.INCH);
        positionY = GobildaPinpoint.ODO.getPosY(DistanceUnit.INCH);

    }
    public double RedDistance(){
        updatePosition();
        return (Math.hypot(Math.abs(PositionConst.GoalRedX - positionX),
                Math.abs(PositionConst.GoalRedY - positionY)));
    }

    public double BlueDistance(){
        updatePosition();
        return (Math.hypot(Math.abs(PositionConst.GoalBlueX - positionX),
                Math.abs(PositionConst.GoalBlueY - positionY)));
    }

    public double positionX(){
        return GobildaPinpoint.ODO.getPosX(DistanceUnit.INCH);
    }

    public double positionY(){
        return GobildaPinpoint.ODO.getPosY(DistanceUnit.INCH);
    }


/*
    public void setFlywheelVelocity(){
        ShooterLeft.setVelocity(AdjustFlywheelVelocity());
        ShooterRight.setVelocity(AdjustFlywheelVelocity());
    }

    public void setHoodAngle(){
        BackShooter.setPosition(AdjustHoodAngle());
    }

    public static double AdjustFlywheelVelocity(double GoalDistance){
        return MathFunctions.clamp();
    }

    public static double AdjustHoodAngle(double GoalDistance){
        return MathFunctions.clamp();

    }

 */



}
