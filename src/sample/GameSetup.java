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

    public static void readFile() {
        File file = new File(System.getProperty("user.dir") + "/gameData/playerFunds.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (scanner.hasNextInt()) {
            //FUNDS = scanner.nextInt();
        }

    }

}
