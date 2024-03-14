// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.led.LED;
import frc.robot.subsystems.led.LED.LEDState;
import frc.robot.subsystems.amp.Amp;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.intake.Intake;

public class ScoreAmp extends Command {

  private Intake intake;
  private Amp amp;
  private Elevator elevator;

  /** Creates a new ScoreAmp */
  public ScoreAmp(Intake intake, Amp amp, Elevator elevator) {

    this.intake = intake;
    this.amp = amp;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, amp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     LED.getInstance().changeLedState(LEDState.AMP_SCORING);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!amp.ampSensorOut()) {
      intake.intakeToAmp();
      if(intake.intakeSensorOut()) {
        LED.getInstance().changeLedState(LEDState.NOTE_IN_FEED);
      } else {
        LED.getInstance().changeLedState(LEDState.INTAKING_AMP);
      }
    }
    if(amp.ampSensorOut() && elevator.isElevatorAmped()) {
      intake.disableIntake();
      amp.ampOuttakeOn();
      LED.getInstance().changeLedState(LEDState.SHOT_FIRED);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.disableIntake();
    LED.getInstance().changeLedState(LEDState.IDLE);
  }
  

}
