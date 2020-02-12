package frc.robot.Spinsor;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SpinnerMotorSubsystem extends Subsystem
{

    // Motor Definitions
    public CANSparkMax spinnerMotor = new CANSparkMax(RobotMap.spinnerMotorCan, MotorType.kBrushless);
    
    // This function sets motor speeds, will set to static speed when bumper is pressed
    // There is a debug at the end  

    public void setMotors()
    { 
        System.out.println("INTO setmotors");
        spinnerMotor.set(1);
           
       /// DEBUG CODE ///
       if (RobotMap.driveDebug)
       {
           System.out.println("Spinner Speed : current speed not variable");
       }
    }

    // This function returns a double based on the values of two safety variables 
    public static double configSpeed(final double speed, final double speedMod)
    {
        final double returnVar;
        
        returnVar = speed * speedMod;
        
        /// DEBUG CODE ///
        if (RobotMap.driveDebug)
        {
            System.out.println("Error in configSpeed");
            /// Return 0 ///
            return 0;
        }
            
           
        return returnVar;
    }

    @Override
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new SpecialFunctions());
    }


}