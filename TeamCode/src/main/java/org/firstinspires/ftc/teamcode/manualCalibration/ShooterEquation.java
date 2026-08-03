package org.firstinspires.ftc.teamcode.manualCalibration;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.GoBildaPinpoint;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

@TeleOp
public class ShooterEquation extends OpMode {

    double targetVelocityPlusLarge = 0;
    double targetVelocityMinusLarge = 0;
    double targetVelocityPlusMinor = 0;
    double targetVelocityMinusMinor = 0;

    Shooter Shooter = new Shooter();
    MecanumDrive MecanumDrive = new MecanumDrive();

    @Override
    public void init(){
        Shooter.init(hardwareMap);
        MecanumDrive.init(hardwareMap);

        targetVelocityPlusLarge += 10;
        targetVelocityMinusLarge -= 10;

        targetVelocityPlusMinor += 1;
        targetVelocityMinusMinor -= 1;
    }

    
    @Override
    public void loop(){



        if(gamepad1.a){
            Shooter.ShooterLeft.setVelocity(targetVelocityPlusMinor);
            Shooter.ShooterRight.setVelocity(targetVelocityPlusMinor);
        }else{
            Shooter.ShooterLeft.setVelocity(targetVelocityMinusMinor);
            Shooter.ShooterRight.setVelocity(targetVelocityMinusMinor);
        }

        if(gamepad1.b){
            Shooter.ShooterLeft.setVelocity(targetVelocityPlusLarge);
            Shooter.ShooterRight.setVelocity(targetVelocityPlusLarge);
        }else{
            Shooter.ShooterLeft.setVelocity(targetVelocityMinusLarge);
            Shooter.ShooterRight.setVelocity(targetVelocityMinusLarge);
        }


        telemetry.addData("Motor Velocity: ", Shooter.ShooterLeft.getVelocity());
        telemetry.addData("BackHood Angle: ", Shooter.BackShooter.getPosition());
        telemetry.addData("Robot Distance to Goal Red: ", Shooter.RedDistance());
        telemetry.addData("Robot Distance to Goal Blue: ", Shooter.BlueDistance());
        telemetry.addLine("---------디버그--------");
        telemetry.addData("Robot Position X:", Shooter.positionX());
        telemetry.addData("Robot Position Y:", Shooter.positionY());
       //https://www.desmos.com/calculator


    }

}
