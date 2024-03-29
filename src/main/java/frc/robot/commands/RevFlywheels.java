package frc.robot.commands;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.vision.Vision;

public class RevFlywheels extends Command {

  private Shooter shooter;
  private Vision vision;

  /** Creates a new RevFlywheelsWithInterpolation */
  public RevFlywheels(Shooter shooter, Vision vision) {

    this.shooter = shooter;
    this.vision = vision;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.interpolatedFlywheelVelocity(shooter.getAutomaticState(vision));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.disableFlywheels();
  }

}
