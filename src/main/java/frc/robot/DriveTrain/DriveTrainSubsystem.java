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
    public void setMotors(final double speed, final String side, boolean speedMod) 
    {
       if (RobotMap.driveTrainSafety)
       {
           if (side == "left")
           {
               leftMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, true));
               leftMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, true));
           }
           if (side == "right")
           {
               rightMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, true));
               rightMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, true));
           }
       }
       else
       {
           if (side == "left")
           {
            leftMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, false));
            leftMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, false));
           }
           if (side == "right")
           {
            rightMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, false));
            rightMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, false));
           }
       }

       /// DEBUG CODE ///
       if (RobotMap.driveDebug)
       {
           System.out.println("Side : " + side + " \t\t Speed : " + speed);
       }
    }

    /// This function returns a double based on the values of two safety variables ///
    public static double configSpeed(final double speed, final boolean speedMod, final boolean safetyCube)
    {
        final double returnVar;

        /// If cubic safety AND speed modifiers enabled... ///
        if ((safetyCube) && (speedMod))
        {
            /// Return speed**3 * speed modifier ///
            returnVar = ((Math.pow(speed, 3)) * RobotMap.driveSafetySpeedMod);
        }
        /// If cubic safety enabled but NOT speed modifiers... ///
        else if ((safetyCube) && (!speedMod))
        {
            /// Return speed**3 ///
            returnVar = Math.pow(speed, 3);
        }
        /// If speed modifiers enabled but NOT cubic safety... ///
        else if ((!safetyCube) && (speedMod))
        {
            /// Return speed * speed modifier ///
            returnVar = speed * (RobotMap.driveSafetySpeedMod);
        }
        /// If neither cubic safety or speed modifiers enabled... ///
        else if ((!safetyCube) && (!speedMod))
        {
            /// Return raw speed ///
            returnVar = speed;
        }
        /// IF NONE OF THE ABOVE ARE TRUE (somehow...) ///
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
                System.out.println("Error in configSpeed");
            }
            /// Return 0 ///
            return 0;
        }

        /// Final return statement ///
        return returnVar;
    }

    @Override
    protected void initDefaultCommand() 
    {

    }
}