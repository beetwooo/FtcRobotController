package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShooterV2 {
    private DcMotorEx ShooterLeft, ShooterRight;
    private Servo BackShooter;

    public void init(HardwareMap hwMap){
        ShooterLeft = hwMap.get(DcMotorEx.class, "SL");
        ShooterRight = hwMap.get(DcMotorEx.class, "SR");
        BackShooter = hwMap.get(Servo.class, "BS");

        ShooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ShooterLeft.setDirection(DcMotor.Direction.FORWARD);
        ShooterRight.setDirection(DcMotor.Direction.REVERSE);

        ShooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BackShooter.setPosition(0);


    }

    public void setPower(double ShooterPower, double BackPosition){

        ShooterLeft.setPower(ShooterPower);
        ShooterRight.setPower(ShooterPower);
        BackShooter.setPosition(BackPosition);

    }



}
