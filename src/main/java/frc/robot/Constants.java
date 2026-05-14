// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  /**
   * Constants for the Drivetrain
   */
  public static class DrivetrainConstants {
    //ID Assignments
    public static final int kLeftMotorID = 21;
    public static final int kRightMotorID = 20;

    //Robot Profile Configurations
    public static final HardwareMode ROBOT_HARDWARE = HardwareMode.CTRE_TALONFX;
    public static final DriveChoices DRIVE_MODE = DriveChoices.ARCADE;
    public static final ControllerType CONTROLLER_MODE = ControllerType.LOGITECH_FLIGHTSTICK;
    /**
     * Available Hardware Configuration Profiles for the Drivetrain Motors
     */
    public enum HardwareMode {
      REV_SPARK,
      CTRE_TALONFX,
      PWM_BRUSHED //Please tell me it isn't this one 🥲
    }
    /**
     * Available Driver Control Methods for the Drivetrain
     */
    public enum DriveChoices {
      ARCADE,
      TANK
    }

    public enum ControllerType {
      XBOX,
      PS5,
      LOGITECH_FLIGHTSTICK
    }
  }
}
