package frc.robot.DriveTrain;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;

public class DriveTrainCommand extends CommandBase
{
    public DriveTrainCommand() {
        /// MAKES THIS FILE REQUIRE driveSubsystem ///
        requires(driveSubsystem);
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
            System.out.println("Drive Train Command Init");
        }
    }

    /// MAKES A CONTROLLER CALLED "DRIVER" THAT IS EQUAL TO A CONTROLLER DEFINED IN OI FILE ///
    private static XboxController driver = OI.driverController;

    /// Null speed variables ///
    public double forwardSpeedLeft, forwardSpeedRight;
    public boolean speedModToggle = true;
    
    @Override
    protected void execute() 
    {
        /// SETS SPEED VARIABLES EQUAL TO STICK VALUES ///
        forwardSpeedLeft = driver.getRawAxis(OI.leftStickY);
        forwardSpeedRight = driver.getRawAxis(OI.rightStickY);

        checkBumpers();
        driveDriverCode();
    }

    /// Checks for bumpers, which manually disable safeties ///
    public void checkBumpers()
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
        }
    }


    /// Main driver code for motors ///
    public void driveDriverCode() 
    {
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
                System.out.println("Left Stick not above Deadzone");
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
                System.out.println("Right Stick not above Deadzone");
            }
        }
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
            System.out.println("DriveTrainCommand Driver Code");
        }
    }




    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }
    
}