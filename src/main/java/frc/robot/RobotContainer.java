// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drivetrain.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.DrivetrainConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  //Subsystem Instantiation
  private final Drivetrain m_drivetrain = new Drivetrain();


  private final CommandXboxController m_xboxController =
          new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandPS5Controller m_ps5Controller =
          new CommandPS5Controller(OperatorConstants.kDriverControllerPort);
  private final CommandGenericHID m_flightStick =
          new CommandGenericHID(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureDefaultCommands();
    configureBindings();
  }

  private void configureDefaultCommands() {
    switch (DrivetrainConstants.CONTROLLER_MODE) {
      case PS5:
        m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, () -> m_ps5Controller.getLeftY(), () -> m_ps5Controller.getRightX(), () -> m_ps5Controller.getRightY()));
        break;
      case LOGITECH_FLIGHTSTICK:
        m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, () -> m_flightStick.getRawAxis(1), () -> m_flightStick.getRawAxis(0), () -> m_flightStick.getRawAxis(2)));
        break;
      case XBOX:
      default:
        m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, () -> m_xboxController.getLeftY(), () -> m_xboxController.getRightX(), () -> m_xboxController.getRightY()));
        break;
    }
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null; //TODO: Replace this with something else once we have AUTON.
  }
}
