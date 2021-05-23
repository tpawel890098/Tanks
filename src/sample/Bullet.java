package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {

    private static double bulletRadius=GameSetup.getBulletRadius();
    private static double bulletSpeed=GameSetup.getBulletSpeed();
    private static double bulletAcceleration=GameSetup.getBulletAcceleration();
    private static double bulletRadiusDowngrade=GameSetup.getBulletRadiusChange();
    public boolean isDestroyed;
    public boolean leftPlayersBullet;

    public Bullet(double x, double y, boolean left)
    {
        super(x,y,bulletRadius, Color.GRAY);
        leftPlayersBullet=left;
        this.setStrokeWidth(4);
        if(leftPlayersBullet)
            this.setStroke(Color.RED);
        else
            this.setStroke(Color.GREEN);

    }

    public void leftBulletMovement()
    {
        this.setCenterX(this.getCenterX()+10);
    }

    public void rightBulletMovement()
    {
        this.setCenterX(this.getCenterX()-10);
    }

    public double getBulletRadius() {
        return bulletRadius;
    }

    public static void setBulletRadius(double bulletRadius) {
        bulletRadius = bulletRadius;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public static void setBulletSpeed(double bulletSpeed) {
       bulletSpeed = bulletSpeed;
    }

    public double getBulletAcceleration() {
        return bulletAcceleration;
    }

    public static void setBulletAcceleration(double bulletAcceleration) {
        bulletAcceleration = bulletAcceleration;
    }

    public double getBulletRadiusDowngrade() {
        return bulletRadiusDowngrade;
    }

    public static void setBulletRadiusDowngrade(double bulletRadiusDowngrade) {
        bulletRadiusDowngrade = bulletRadiusDowngrade;
    }
}
