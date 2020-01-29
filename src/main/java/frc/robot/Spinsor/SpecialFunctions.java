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
            System.out.println("Wheel Spinner Command Init");
            System.out.println("Color Sensor Command Init");
        }
    }

    // Makes a controller called Spinsor that is equal to a controller defined in OI File
    private static XboxController spinsor = OI.specialController;

    // Declare speed variables as null
    public boolean spinnerRight;

    // Set color sensor on switch to x button and off switch to b button
    public boolean sensorOn = spinsor.getRawButton(OI.xButton);
    public boolean sensorOff = spinsor.getRawButton(OI.bButton);


    @Override
    protected void execute()
    {
        spinnerRight = spinsor.getRawButton(OI.rightBumper);

        checkSensor();

        motorSpinnerCode();
    }

    public void checkSensor()
    {
        //if B button is pressed, transmit color data from sensor to laptop
        if (spinsor.getRawButton(OI.bButton))
        {
            System.out.println("Starting transmission of Color Data.");
            colorSensorSubsystem.printColor();
        }

        //if x button is pressed, stop transmitting color data from sensor
        if (spinsor.getRawButton(OI.bButton))
        {
            System.out.println("Finished Transmitting, Press X to start again.");
        }
    }

    public void motorSpinnerCode()
    {
        if (spinnerRight)
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