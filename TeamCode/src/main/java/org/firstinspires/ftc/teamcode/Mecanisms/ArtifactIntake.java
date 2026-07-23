package org.firstinspires.ftc.teamcode.Mecanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArtifactIntake {

    private DcMotor FrontEaterMotor, BackEaterMotor;
    private Servo ArtifactLid;

    public void init(HardwareMap hwMap){

        double FrontEaterPower, BackEaterPower;

        FrontEaterMotor = hwMap.get(DcMotor.class, "FE");
        BackEaterMotor = hwMap.get(DcMotor.class, "BE");
        ArtifactLid = hwMap.get(Servo.class, "AL");

        FrontEaterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackEaterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FrontEaterMotor.setPower(0);
        BackEaterMotor.setPower(0);

        BackEaterMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void setPower(double frontPower, double backPower){
        FrontEaterMotor.setPower(frontPower);
        BackEaterMotor.setPower(backPower);
    }

    public void setPosition(double ArtifactLidPos){
    }
}
