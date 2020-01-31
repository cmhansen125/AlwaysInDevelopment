package frc.robot.Spinsor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.*;

public class ColorSensorSubsystem extends Subsystem
{

    // Change the I2C port below to match the connection of your color sensor 
    public I2C.Port i2cPort = I2C.Port.kOnboard;

    // Declare Colorsensor
    public ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    public void printColor()
    {
      Color currentColor = colorSensor.getColor();

      String colorString = currentColor.toString();
      System.out.println("Current Color: " + colorString);

    }
  

  @Override
  protected void initDefaultCommand()
   {
    
    setDefaultCommand(new SpecialFunctions());

  }
}
