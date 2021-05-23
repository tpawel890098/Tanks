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




        System.out.println(defaultBulletSpeed);
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




   }

}
