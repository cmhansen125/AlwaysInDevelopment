package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class RobotMap
{
    /// Drive Train Safety Toggles ///
    public static boolean driveTrainSafety = true;
    public static double driveSafetySpeedMod = 0.7;

    /// Debug Toggles ///
	public static final boolean debug = false;
	public static final boolean driveDebug = true;

    /// Drive Train Deadzone Value ///
    public static double deadzone = 0.25;

    /// Control Mode (Required for VICTORSPX.set() functions) ///
    public ControlMode controlMode = ControlMode.PercentOutput;
    
    /// Motor Ports (CAN BUS) ///
    public static int leftMotorMaster = 0; //Left Side
    public static int leftMotorSlave = 3;
    public static int rightMotorMaster = 1; //Right Side
    public static int rightMotorSlave = 4; //Right Side
}