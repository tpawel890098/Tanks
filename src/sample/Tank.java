package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tank extends Rectangle {

    private int playerPoints;
    private int activeBullets;
    private int shotBullets;
    public Barrel barrel;

    public Tank(double x, double y, double width, double height, Color color, boolean left)
    {
        super(x,y,width,height);
        this.setFill(color);
        barrel=new Barrel(x,y,width,height,left);
        playerPoints=0;
        activeBullets=0;
        shotBullets=0;
    }

    public void moveUp()
    {
        if(this.getY()>=10) {
            this.setY(this.getY() - 10);
            this.barrel.setY(barrel.getY() - 10);
        }
    }

    public void moveDown()
    {
        if(this.getY()<=590) {
            this.setY(this.getY() + 10);
            this.barrel.setY(barrel.getY() + 10);
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
