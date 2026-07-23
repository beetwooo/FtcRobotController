package org.firstinspires.ftc.teamcode.Mecanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    private DcMotor ShooterLeft, ShooterRight;
    private Servo ShooterAdjuster;

    public void init(HardwareMap hwMap){
        ShooterLeft = hwMap.get(DcMotor.class, "SL");
        ShooterRight = hwMap.get(DcMotor.class, "SR");
        ShooterAdjuster = hwMap.get(Servo.class, "SA");

        ShooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ShooterRight.setDirection(DcMotorSimple.Direction.REVERSE);
        ShooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ShooterLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ShooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ShooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ShooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }

}
