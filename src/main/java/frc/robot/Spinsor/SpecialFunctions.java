package frc.robot.Spinsor;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;

public class SpecialFunctions extends CommandBase
{
    public SpecialFunctions()
    {
        requires(spinnerSubsystem);
        requires(colorSensorSubsystem);

        if (RobotMap.debug)
        {
            System.out.println("Spinsor init");
        }
    }

    // Makes a controller called Spinsor that is equal to a controller defined in OI File
    private static XboxController spinsor = OI.specialController;

    // Declare speed variables as true or false
    public boolean spinnerRight;

    // Set color sensor printing on or off
    public boolean sensorOn;
    public boolean sensorOff;


    @Override
    protected void execute()
    {
        //gathers information on whether or not the X or B button has been pressed last
        if (spinsor.getRawButton(OI.xButton)){
            sensorOn = true;
            sensorOff = false;
        }
        if (spinsor.getRawButton(OI.bButton))
        {
            sensorOn = false;
            sensorOff = true;
        }

        checkSensor();

        motorSpinnerCode();
    }

    public void checkSensor()
    {
        //if X button is pressed, transmit color data from sensor to laptop
        if (sensorOn)
        {
            System.out.println("Starting transmission of Color Data.");
            colorSensorSubsystem.printColor();
        }
        

        //if B button is pressed, stop transmitting color data from sensor
        else if (sensorOff)
        {
            System.out.println("Finished Transmitting, Press X to start again.");
        }
        else
        {
            System.out.println("Sensor Not Activated");
        }
        
    }

    public void motorSpinnerCode()
    {
        //when right bumper is held down, motor is activated
        if (spinsor.getRawButton(OI.rightBumper));
        {
            spinnerSubsystem.setMotors(1, RobotMap.spinnerSafetySpeedMod);
        }

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end()
    {

    }
}