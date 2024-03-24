// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexing;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.intake.Intake;

public class BackfeedToScore extends Command {

  private Intake intake;
  private Shooter shooter;

  /** Creates a new GroundIntakeToShooter */
  public BackfeedToScore(Intake intake, Shooter shooter) {

    this.intake = intake;
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.lowerToIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (shooter.shooterSensorOut()) {
      intake.outakeFromShooter();
      } else {
        intake.disableIntake();
      }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.disableIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (!shooter.shooterSensorOut()) {
      return true;
    }
    return false;
  }
}