package frc.robot.commands.indexing;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.led.LED;
import frc.robot.subsystems.led.LED.LEDState;

public class HPAmpToFeed extends Command {
  
    /** Creates a new HPShooterFeed */
    public HPAmpToFeed() {
  
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements();
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
       LED.getInstance().changeLedState(LEDState.INTAKING_AMP);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
  
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    // @Override
    // public boolean isFinished() {
    //   if (intake.intakeSensorOut()) {
    //     return true;
    //   }
    //   return false;
    // }
  }
  