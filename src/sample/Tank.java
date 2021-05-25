package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tank extends Rectangle {

    public static int maxActiveBullets=GameSetup.getMaxShotBullets();
    private int playerPoints;
    private int activeBullets;
    private int shotBullets;
    public Barrel barrel;

    public Tank(double x, double y, double width, double height, Color color, boolean left)
    {
        super(x,y,width,height);
        this.setFill(color);
        this.setStroke(Color.BLACK);
        barrel=new Barrel(x,y,width,height,left);
        playerPoints=0;
        activeBullets=0;
        shotBullets=0;

        if(this.maxActiveBullets<1)
            this.maxActiveBullets=1;
        else if(this.maxActiveBullets>20)
            this.maxActiveBullets=20;


    }

    public void moveUp(boolean left)
    {
        if(this.getY()>=10) {
            this.setY(this.getY() - 10);

            if(left) {
                this.barrel.fakeRotate(true,-this.barrel.currentAngle);
                this.barrel.setY(barrel.getY() - 10);
                this.barrel.fakeRotate(true,this.barrel.currentAngle);
            }
            else
            {
                this.barrel.fakeRotate(false,-this.barrel.currentAngle);
                this.barrel.setY(barrel.getY() - 10);
                this.barrel.fakeRotate(false,this.barrel.currentAngle);
            }
        }
    }

    public void moveDown(boolean left)
    {
        if(this.getY()<=590) {
            this.setY(this.getY() + 10);

            if(left) {
                this.barrel.fakeRotate(true,-this.barrel.currentAngle);
                this.barrel.setY(barrel.getY() + 10);
                this.barrel.fakeRotate(true,this.barrel.currentAngle);
            }
            else
            {
                this.barrel.fakeRotate(false,-this.barrel.currentAngle);
                this.barrel.setY(barrel.getY() + 10);
                this.barrel.fakeRotate(false,this.barrel.currentAngle);
            }
        }
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getActiveBullets() {
        return activeBullets;
    }

    public void setActiveBullets(int activeBullets) {
        this.activeBullets = activeBullets;
    }

    public int getShotBullets() {
        return shotBullets;
    }

    public void setShotBullets(int shotBullets) {
        this.shotBullets = shotBullets;
    }
}
