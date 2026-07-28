package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShooterV2 {
    public DcMotorEx ShooterLeft, ShooterRight;
    private Servo BackShooter;

    public void init(HardwareMap hwMap){
        ShooterLeft = hwMap.get(DcMotorEx.class, "SL");
        ShooterRight = hwMap.get(DcMotorEx.class, "SR");
        BackShooter = hwMap.get(Servo.class, "BS");

        ShooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ShooterLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ShooterLeft.setDirection(DcMotor.Direction.FORWARD);
        ShooterRight.setDirection(DcMotor.Direction.REVERSE);

        ShooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ShooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        double flywheel_p = 300;
        double flywheel_i = 0;
        double flywheel_d = 5;
        double flywheel_f = 0;

        com.qualcomm.robotcore.hardware.PIDFCoefficients flywheel_pidfCoeffiients
                = new com.qualcomm.robotcore.hardware
                .PIDFCoefficients(flywheel_p, flywheel_i, flywheel_d, flywheel_f);

        ShooterLeft.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, flywheel_pidfCoeffiients);


        BackShooter.setPosition(0);


    }

    public void setVelocity(double ShooterVelocity){
        ShooterLeft.setVelocity(ShooterVelocity);
        ShooterRight.setVelocity(ShooterVelocity);
    }

    public void setPosition(double BackPosition){
        BackShooter.setPosition(BackPosition);
    }





}
