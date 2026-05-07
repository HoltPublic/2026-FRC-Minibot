// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//Riley Here, I'll also use this class to show documentation

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;


public class Drivetrain extends SubsystemBase {
  private MotorController m_leftMotor; 
  private MotorController m_rightMotor; //All 3 of these are good variable names.
  private DifferentialDrive m_diffDrive; //They should be short, yet descriptive
  public Drivetrain() {
    switch (DrivetrainConstants.ROBOT_HARDWARE) {
      case REV_SPARK:
        m_leftMotor = new SparkMax(DrivetrainConstants.kLeftMotorID, MotorType.kBrushless);
        m_rightMotor = new SparkMax(DrivetrainConstants.kRightMotorID, MotorType.kBrushless);
      case CTRE_TALONFX:
        //Add the configs for this
      case PWM_BRUSHED:
        //Add configurations for this as well
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
