package frc.robot.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends Subsystem
{

    /// TEST COMMIT TEXT ///

    /// Motor Definitions ///
        /// Left Side ///
    public VictorSPX leftMotorMaster = new VictorSPX(RobotMap.leftMotorMaster);
    public VictorSPX leftMotorSlave = new VictorSPX(RobotMap.leftMotorSlave);
        /// Right Side ///
    public VictorSPX rightMotorMaster = new VictorSPX(RobotMap.rightMotorMaster);
    public VictorSPX rightMotorSlave = new VictorSPX(RobotMap.rightMotorSlave);

    /// This function sets motor speeds ///
        /// It contains nested conditionals; one that checks for the safety toggle, and one that checks which side is being set ///
        /// There is a debug at the end /// 
    public void setMotors(double speed, String side) 
    {
       if (RobotMap.driveTrainSafety)
       {
           if (side == "left")
           {
               leftMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed));
               leftMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed));
           }
           if (side == "right")
           {
               rightMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed));
               rightMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed));
           }
       }
       else
       {
           if (side == "left")
           {
            leftMotorSlave.set(ControlMode.PercentOutput, speed);
            leftMotorMaster.set(ControlMode.PercentOutput, speed);
           }
           if (side == "right")
           {
            rightMotorSlave.set(ControlMode.PercentOutput, speed);
            rightMotorMaster.set(ControlMode.PercentOutput, speed);
           }
       }

       /// DEBUG CODE ///
       if (RobotMap.driveDebug)
       {
           System.out.println("Side : " + side + " \t\t Speed : " + speed);
       }
    }

    /// This function returns the cube of an inputted double ///
    public static double configSpeed(double speed)
    {
       return (Math.pow(speed, 3));
    }

    @Override
    protected void initDefaultCommand() {
        /// This sets the default command file for this subsystem to be DriveTrainCommand ///
        
            setDefaultCommand(new DriveTrainCommand());
    }
}