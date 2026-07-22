package org.firstinspires.ftc.teamcode.Mecanism;

import android.hardware.HardwareBuffer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArtifactIntake {

    private DcMotor FrontEaterMotor, BackEaterMotor;

    public void init(HardwareMap hwMap){

        double FrontEaterPower, BackEaterPower;

        FrontEaterMotor = hwMap.get(DcMotor.class, "FE");
        BackEaterMotor = hwMap.get(DcMotor.class, "BE");

        FrontEaterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackEaterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FrontEaterMotor.setPower(0);
        BackEaterMotor.setPower(0);

    }

    public void setPower(double frontPower, double backPower){
        FrontEaterMotor.setPower(frontPower);
        BackEaterMotor.setPower(backPower);
    }
}
