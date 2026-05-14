// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//I'll also use this class to show documentation

package frc.robot.subsystems;

import java.util.function.DoubleConsumer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;


public class Drivetrain extends SubsystemBase {
  private DoubleConsumer m_leftMotorSpeed;
  private DoubleConsumer m_rightMotorSpeed;

  private DifferentialDrive m_diffDrive; //They should be short, yet descriptive
  
  
  @SuppressWarnings("removal")
  /**
   * Configures the Drivetrain to use a specific set of motors, and sets that up for the Differential Drive
   */
  public Drivetrain() {
    /*This is basically just a big switch statement to configure the motors based on Motor Type */
    switch (DrivetrainConstants.ROBOT_HARDWARE) {
      case REV_SPARK:
        final SparkMax leftSpark = new SparkMax(DrivetrainConstants.kLeftMotorID, MotorType.kBrushless);
        final SparkMax rightSpark = new SparkMax(DrivetrainConstants.kRightMotorID, MotorType.kBrushless);
      
        rightSpark.configure(
          new SparkMaxConfig().inverted(true),
          ResetMode.kResetSafeParameters,
          PersistMode.kPersistParameters
        );

        m_leftMotorSpeed = (double speed) -> leftSpark.set(speed);
        m_rightMotorSpeed = (double speed) -> rightSpark.set(speed);
        break;

      case CTRE_TALONFX:
        final TalonFX leftTalon = new TalonFX(DrivetrainConstants.kLeftMotorID);
        final TalonFX rightTalon = new TalonFX(DrivetrainConstants.kRightMotorID);

        final DutyCycleOut leftRequest = new DutyCycleOut(0);
        final DutyCycleOut rightRequest = new DutyCycleOut(0);

        leftTalon.getConfigurator().apply(new TalonFXConfiguration());
        var rightConfig = new TalonFXConfiguration();
        rightConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        rightTalon.getConfigurator().apply(rightConfig);

        m_leftMotorSpeed = (double speed) -> leftTalon.setControl(leftRequest.withOutput(speed));
        m_rightMotorSpeed = (double speed) -> rightTalon.setControl(rightRequest.withOutput(speed));
        break;
      case PWM_BRUSHED:
        final PWMSparkMax leftPWM = new PWMSparkMax(DrivetrainConstants.kLeftMotorID);
        final PWMSparkMax rightPWM = new PWMSparkMax(DrivetrainConstants.kRightMotorID);

        rightPWM.setInverted(true);

        m_leftMotorSpeed = (double speed) -> leftPWM.set(speed);
        m_rightMotorSpeed = (double speed) -> rightPWM.set(speed);
        break;
      default:
        throw new IllegalStateException("Unexpected Hardware Type: " + DrivetrainConstants.ROBOT_HARDWARE);
    }
    
    m_diffDrive = new DifferentialDrive(m_leftMotorSpeed, m_rightMotorSpeed);
  }

  /**
   * Arcade Style Drive Control Mapping.
   * @param forwardSpeed The forward Speed demand, from -1.0 to 1.0
   * @param turnSpeed Rotational steering speed demand, from -1.0 to 1.0
   */
  public void arcadeDrive(double forwardSpeed, double turnSpeed) {
    m_diffDrive.arcadeDrive(forwardSpeed, turnSpeed);
  }

  /**
   * Drives the Robot using Tank DriveInput
   * @param leftSpeed
   * @param rightSpeed
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_diffDrive.tankDrive(leftSpeed, rightSpeed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
