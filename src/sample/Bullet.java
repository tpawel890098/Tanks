package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

public class Bullet extends Circle {

    private static double bulletRadius=GameSetup.getBulletRadius();
    private double bulletSpeed=GameSetup.getBulletSpeed();
    private double currentBulletRadius;
    private double bulletAcceleration=GameSetup.getBulletAcceleration();
    private double bulletRadiusDowngrade=GameSetup.getBulletRadiusChange();
    private double timeUnit=GameSetup.getTimeUnit();
    private Timer timer;
    public boolean isDestroyed;
    public boolean leftPlayersBullet;

    public Bullet(double x, double y, boolean left)
    {
        super(x,y,bulletRadius, Color.GRAY);
        this.currentBulletRadius=bulletRadius;
        leftPlayersBullet=left;
        this.setStrokeWidth(4);
        if(leftPlayersBullet)
            this.setStroke(Color.RED);
        else
            this.setStroke(Color.GREEN);

        timer= new Timer();
        TimerTask task = new TimerTask() {

            public void run() {

                try {

                    if(this!=null) {
                        if (!isDestroyed) {
                            changeBulletSpeed();
                            //changeRadius();
                        } else {
                            timer.cancel();
                        }
                    }

                }
                catch(Exception e) {
                    timer.cancel();
                    System.out.println("TIMER PROBLEM");
                }



            }
        };

        int period= (int) (timeUnit*1000);

        timer.scheduleAtFixedRate(task,period,period);

    }

    public void leftBulletMovement()
    {
        if(!isDestroyed)
        this.setCenterX(this.getCenterX()+bulletSpeed);
    }

    public void rightBulletMovement()
    {
        if(!isDestroyed)
        this.setCenterX(this.getCenterX()-bulletSpeed);
    }

    public void changeBulletSpeed(){

        if(this!=null) {
            this.bulletSpeed += bulletAcceleration;
        }
    }

    public void changeRadius() {


            if (this.currentBulletRadius > 0 + bulletRadiusDowngrade) {
                this.currentBulletRadius -= bulletRadiusDowngrade;
                try {
                    this.setRadius(this.getRadius()-bulletRadiusDowngrade);
                }catch(Exception e){
                    System.out.println("CHANGE RADIUS ERROR");
                }
            }


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
