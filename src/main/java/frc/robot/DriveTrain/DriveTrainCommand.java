package frc.robot.DriveTrain;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;


public class DriveTrainCommand extends CommandBase
{
    public DriveTrainCommand() 
    {
        /// MAKES THIS FILE REQUIRE driveSubsystem ///
        requires(driveSubsystem);
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
           // System.out.println("Drive Train Command Init");
        }
    }

    /// MAKES A CONTROLLER CALLED "DRIVER" THAT IS EQUAL TO A CONTROLLER DEFINED IN OI FILE ///
    private static XboxController driver = OI.driverController;

    /// Null speed variables ///
    public double forwardSpeedLeft, forwardSpeedRight;
    public boolean speedModToggle = true;

    // Autonomous variable
    public boolean autoMode = false;
    
    @Override
    protected void execute()
    {

        
        /// SETS SPEED VARIABLES EQUAL TO STICK VALUES ///
        forwardSpeedLeft = driver.getRawAxis(OI.leftStickY);
        forwardSpeedRight = driver.getRawAxis(OI.rightStickY);

        checkBumpers();
        
        motorDriveCode();
    }

    /// Checks for bumpers, which manually disable safeties ///
    public void checkBumpers()
    {
        /// If both bumpers are pressed, don't let either safety disable /// 
        if ((driver.getRawButton(OI.leftTrigger)) && (driver.getRawButton(OI.leftBumper)))
            {
              //  System.out.println("insert debug thing here");
            }
        else
        {
            /// If left trigger pressed, disable speed modifiers ///
            if (driver.getRawButton(OI.leftTrigger))
                {
                    speedModToggle = false;
                }
            /// If left bumper pressed, disable cubic safety ///
            if (driver.getRawButton(OI.leftBumper))
                {
                    RobotMap.driveTrainSafety = !RobotMap.driveTrainSafety;
                }}
       }

      

    /// Main driver code for motors ///
    public void motorDriveCode() 
    {
        if (autoMode == true)
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left", speedModToggle);
            driveSubsystem.setMotors(forwardSpeedRight, "right", speedModToggle);

            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "left", speedModToggle);
                System.out.println("Autonomous code not working");
            }
        }

        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedLeft) > RobotMap.deadzone) 
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left", speedModToggle);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "left", speedModToggle);
                //System.out.println("Left Stick not above Deadzone");
            }
        }
        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedRight) > RobotMap.deadzone)
        {
            driveSubsystem.setMotors(forwardSpeedRight, "right", speedModToggle);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "right", speedModToggle);
               // System.out.println("Right Stick not above Deadzone");
            }
        }
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
          //  System.out.println("DriveTrainCommand Driver Code");
        }
        
    }


    @Override
    protected boolean isFinished() 
    {
        return false;
    }

    @Override
    protected void end() 
    {
        
    }
    
}