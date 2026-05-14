// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

/**
 * Default Teleoperating Driving Command for the minibot
 * <p></p>
 * Evaluates {@link frc.robot.Constants.DrivetrainConstants#DRIVE_MODE} to cleanly map the joysticks to
 * use a standard layout
 * 
 */
public class DriveCommand extends Command {
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_leftYAxis;
  private final DoubleSupplier m_rightXAxis;
  private final DoubleSupplier m_rightYAxis;

  /**
   * Creates a new DriveCommand instance.
   * @param drivetrain The target subsystem to manipulate.
   * @param leftYAxis Primary Vertical Input (Arcade Speed / Left Tank)
   * @param rightXAxis Primary Horizontal Input (Arcade Turn)
   * @param rightYAxis Secondary Vertical Input (Right Tank)
   */
  public DriveCommand(
          Drivetrain drivetrain,
          DoubleSupplier leftYAxis,
          DoubleSupplier rightXAxis,
          DoubleSupplier rightYAxis
  ) {
    m_drivetrain = drivetrain;
    m_leftYAxis = leftYAxis;
    m_rightXAxis = rightXAxis;
    m_rightYAxis = rightYAxis;

    addRequirements(m_drivetrain);
  }

  @Override
  public void execute() {
    double leftY = -m_leftYAxis.getAsDouble();
    double rightX = m_rightXAxis.getAsDouble();
    double rightY = -m_rightYAxis.getAsDouble();

    switch (Constants.DrivetrainConstants.DRIVE_MODE){
      case TANK:
        m_drivetrain.tankDrive(leftY, rightY);
        break;
      case ARCADE:
      default:
        m_drivetrain.arcadeDrive(leftY, rightX);
        break;
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }
}
