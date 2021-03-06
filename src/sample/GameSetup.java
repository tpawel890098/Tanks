package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameSetup {

    private static double defaultBulletSpeed;
    private static double maxShotBullets;
    private static double bulletRadius;
    private static double cellFallingSpeed;
    private static double cellSize;
    private static double bulletAcceleration;
    private static double cellAcceleration;
    private static double radiusChange;
    private static double cellSizeChange;
    private static double timeUnit;
    private static double durabilityIncreaseTimeUnit;
    private static double gameTime;

    public static void setSettingsUp() {
        File file = new File(System.getProperty("user.dir") + "\\gameSetup.txt");

        try (Scanner scanner = new Scanner(file)) {

            if (scanner.hasNextDouble()) {
                defaultBulletSpeed=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                maxShotBullets=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                bulletRadius=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                cellFallingSpeed=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                cellSize=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                bulletAcceleration=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                cellAcceleration=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                radiusChange=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                cellSizeChange=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                timeUnit=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                durabilityIncreaseTimeUnit=scanner.nextDouble();
            }
            if(scanner.hasNextDouble()){
                gameTime=scanner.nextDouble();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





      /*  System.out.println(defaultBulletSpeed);
        System.out.println(maxShotBullets);
        System.out.println(bulletRadius);
        System.out.println(cellFallingSpeed);
        System.out.println(cellSize);
        System.out.println(bulletAcceleration);
        System.out.println(cellAcceleration);
        System.out.println(radiusChange);
        System.out.println(cellSizeChange);
        System.out.println(timeUnit);
        System.out.println(durabilityIncreaseTimeUnit);
        System.out.println(gameTime);

*/


   }

   public static double getBulletRadius()
   {

       if(bulletRadius<5)
           bulletRadius=5;
       else if(bulletRadius>30)
           bulletRadius=30;

       return bulletRadius;
   }

    public static double getBulletSpeed()
    {
        return defaultBulletSpeed;
    }

    public static double getBulletAcceleration()
    {
        return bulletAcceleration;
    }

    public static double getBulletRadiusChange()
    {
        return radiusChange;
    }

    public static double getTimeUnit() {

        if(timeUnit<0.05)
            timeUnit=0.05;
        else if(timeUnit>1)
            timeUnit=1;


        return timeUnit;


    }

    public static int getGameTime()
    {
        if(gameTime<10)
            gameTime=10;
        else if(gameTime>300)
            gameTime=300;

        int value=(int)gameTime;
        return value;
    }

    public static int getMaxShotBullets()
    {
        int value=(int)maxShotBullets;
        return value;
    }

    public static double getCellSize()
    {
        return cellSize;
    }

    public static double getCellSizeChange()
    {
        return cellSizeChange;
    }

    public static double getCellAcceleration()
    {
        return cellAcceleration;
    }

    public static double getCellFallingSpeed()
    {
        return cellFallingSpeed;
    }

    public static double getDurabilityIncreaseTimeUnit()
    {
        return durabilityIncreaseTimeUnit;
    }

}
