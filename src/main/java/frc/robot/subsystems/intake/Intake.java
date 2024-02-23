package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.TalonFX;
import com.playingwithfusion.TimeOfFlight;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final TalonFX intake = new TalonFX(17);
  private final TalonFX frontFeed = new TalonFX(13);
  private final TalonFX backFeed = new TalonFX(14);
  public final TimeOfFlight intake_sensor = new TimeOfFlight(1);

  public Intake() {
    setDefaultCommand(
        runOnce(
                () -> {
                  intake.set(0);
                  frontFeed.set(0);
                  backFeed.set(0);
                })
            .andThen(run(() -> {}))
            .withName("Intake Idle"));
  }

  public void intakeToShooter() {
    intake.set(1);
    frontFeed.set(-0.95);
    backFeed.set(0.95);
  }

  public void intakeToAmp() {
    intake.set(1);
    frontFeed.set(-0.95);
    backFeed.set(-0.95);
  }

  public void outakeFromShooter() {
    intake.set(-1);
    frontFeed.set(0.95);
    backFeed.set(-0.95);
  }

  public void outakeFromAmp() {
    intake.set(-1);
    frontFeed.set(0.95);
    backFeed.set(0.95);
  }

  public void disableIntake() {
    intake.set(0);
    frontFeed.set(0);
    backFeed.set(0);
  }

  public void intakeOn() {
    intake.set(1);
  }

  public boolean intakeSensorOut() {
    return (intake_sensor.getRange() < 300);
  }

  public boolean invIntakeSensorOut() {
    return !(intake_sensor.getRange() < 300);
  }
}
