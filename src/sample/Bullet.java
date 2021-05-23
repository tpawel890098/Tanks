package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {

    private double bulletRadius;
    private double bulletSpeed;
    private double bulletAcceleration;
    private double bulletRadiusDowngrade;
    public boolean isDestroyed;
    public boolean leftPlayersBullet;

    public Bullet(double x, double y, double radius, boolean left)
    {
        super(x,y,radius, Color.GRAY);
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

}
