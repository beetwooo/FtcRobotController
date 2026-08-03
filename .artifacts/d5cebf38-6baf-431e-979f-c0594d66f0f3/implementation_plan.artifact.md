# Implement Velocity Increment Logic in ShooterEquation

The user wants to increase the shooter motor velocity when `gamepad1.a` is pressed. The current code has syntax errors and logic that would cause the velocity to increase uncontrollably every frame.

## Proposed Changes

### [TeamCode](file:///home/betwo/StudioProjects/FtcRobotController/TeamCode)

#### [MODIFY] [ShooterEquation.java](file:///home/betwo/StudioProjects/FtcRobotController/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/manualCalibration/ShooterEquation.java)

- Add a `double targetVelocity` variable to track the desired speed.
- Add a `boolean lastA` variable to detect the moment the button is *first* pressed (rising edge), preventing the velocity from increasing every single frame while the button is held.
- Replace the invalid `setVelocity() += 0.1` syntax with logic that updates `targetVelocity` and then calls `setVelocity(targetVelocity)`.
- Note: If using `setVelocity`, an increment of `0.1` is likely too small (velocity is typically measured in hundreds or thousands of ticks per second). I will use a larger increment (e.g., 50 or 100) or let the user decide, but I'll show the correct structure.

## Verification Plan

### Automated Tests
- Run `./gradlew :TeamCode:assembleDebug` to ensure the syntax is correct.

### Manual Verification
- N/A (Requires hardware to test button response).
