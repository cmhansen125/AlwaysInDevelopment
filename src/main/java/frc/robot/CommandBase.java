package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain.*;
public abstract class CommandBase extends Command 
{

    /// This is a hub for Subsystem Initializations ///

    
    public static DriveTrainSubsystem driveSubsystem;

    public static void init() 
    {
        driveSubsystem = new DriveTrainSubsystem();
    }

}