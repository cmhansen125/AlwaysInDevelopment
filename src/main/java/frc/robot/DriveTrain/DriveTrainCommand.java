package frc.robot.DriveTrain;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.DriveTrain.DriveTrainSubsystem;
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

    /// INITIALIZES DriveTrainSubsystem AS driveSubsystem ///
    public DriveTrainSubsystem driveSubsystem = new DriveTrainSubsystem(); 

    public double forwardSpeedLeft, forwardSpeedRight;
    
    @Override
    protected void execute() {
        /// SETS SPEED VARIABLES EQUAL TO STICK VALUES ///
        forwardSpeedLeft = driver.getRawAxis(OI.leftStickY);
        forwardSpeedRight = driver.getRawAxis(OI.rightStickY);
            super.execute();
    }

    public void driveDriverCode() 
    {
        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedLeft) > RobotMap.deadzone) 
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left");
        }
        else
        {
            if (RobotMap.driveDebug)
            {
                System.out.println("Left Stick not above Deadzone");
            }
        }
        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedRight) > RobotMap.deadzone)
        {
            driveSubsystem.setMotors(forwardSpeedRight, "right");
        }
        else
        {
            if (RobotMap.driveDebug)
            {
                System.out.println("Right Stick not above Deadzone");
            }
        }
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
            System.out.println("Drive Train Command Driver Code");
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