package org.firstinspires.ftc.teamcode.Configs;

public class ShooterPID {
    public static double P = 0.0005;
    public static double I = 0.00001;
    public static double D = 0.00001;
    public static double F = 0.0005;
    public static double TARGET_VELOCITY = 1000.0;

    private double integral;
    private double lastError;
    private long lastTime;

    public double calculate(double targetVelocity, double currentVelocity) {
        long now = System.nanoTime();
        double deltaTime = lastTime == 0 ? 0.0 : (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        double error = targetVelocity - currentVelocity;
        if (deltaTime > 0.0) {
            integral += error * deltaTime;
        }

        double derivative = deltaTime > 0.0 ? (error - lastError) / deltaTime : 0.0;
        lastError = error;

        return P * error + I * integral + D * derivative + F * targetVelocity;
    }

    public void reset() {
        integral = 0.0;
        lastError = 0.0;
        lastTime = 0L;
    }
}
