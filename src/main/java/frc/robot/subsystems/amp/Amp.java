package frc.robot.subsystems.amp;

import com.ctre.phoenix6.hardware.TalonFX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.TunableNumber;

public class Amp extends SubsystemBase {
   private TunableNumber ampScoreSpeed = new TunableNumber("Amp/Score Speed");
   private TunableNumber ampReverseSpeed = new TunableNumber("Amp/Reverse Speed");
   private TunableNumber ampSensorThreshold = new TunableNumber("Sensors/Amp Sensor Threshold");

  private final TalonFX ampMotor;
  public final TimeOfFlight amp_sensor;


  public Amp() {
    ampMotor = new TalonFX(Constants.IDs.amp);
    ampScoreSpeed.setDefault(AmpConstants.scoreSpeed);
    ampReverseSpeed.setDefault(AmpConstants.reverseSpeed);
    /*Sensor Shtuff */
    amp_sensor = new TimeOfFlight(Constants.IDs.ampsensor);
    amp_sensor.setRangingMode(TimeOfFlight.RangingMode.Short, 0.001);
    amp_sensor.setRangeOfInterest(6, 6, 14, 14);
    ampSensorThreshold.setDefault(AmpConstants.sensorThreshold);

    setDefaultCommand(
        runOnce(
                () -> {
                  ampMotor.set(0);
                })
            .andThen(run(() -> {}))
            .withName("Amp Idle"));
  }

  public void ampOuttakeOn() {
    ampMotor.set(ampScoreSpeed.get());
  }

  public void ampIntakeOn() {
    ampMotor.set(-ampReverseSpeed.get());
  }

  public void disableAmp() {
    ampMotor.set(0);
  }

  public boolean ampSensorOut() {
    return (amp_sensor.getRange() < ampSensorThreshold.get());
  }

  public double ampSensor() {
    return amp_sensor.getRange();
  }

  public void periodic() {
    SmartDashboard.putNumber("Amp/Reported Power: ", ampMotor.get());
  }
}
