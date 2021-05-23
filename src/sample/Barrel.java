package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Barrel extends Rectangle {

    private double width;
    private double height;
    public double currentAngle;

    public Barrel(double x, double y, double width, double height, boolean left)
    {
        super(x+width/2,y+height/2-height/12,width,height/6);

        if(!left)
        {
            this.setX(x-width/2);
        }

        this.setFill(Color.BLACK);

        this.width=width;
        this.height=height/6;
        this.currentAngle=0;

    }

    public void leftRotate(double angle)
    {

        if(currentAngle<60&&currentAngle>-60||currentAngle==60&&angle<0||currentAngle==-60&&angle>0) {
            double xRotate = this.getX();
            double yRotate = this.getY() + this.height / 2;

            System.out.println("CA: "+currentAngle);
            System.out.println("A: "+ angle);

            Rotate rotate = new Rotate();
            rotate.setAngle(angle);
            rotate.setPivotX(xRotate);
            rotate.setPivotY(yRotate);

            this.getTransforms().add(rotate);
            this.currentAngle = this.currentAngle + angle;
        }

    }

    public void rightRotate(double angle)
    {

        if(currentAngle<60&&currentAngle>-60||currentAngle==60&&angle<0||currentAngle==-60&&angle>0) {

            double xRotate = this.getX() + this.width;
            double yRotate = this.getY() + this.height / 2;

            Rotate rotate = new Rotate();
            rotate.setAngle(angle);
            rotate.setPivotX(xRotate);
            rotate.setPivotY(yRotate);

            this.getTransforms().add(rotate);
            this.currentAngle = this.currentAngle + angle;
        }

    }

    public void fakeRotate(boolean left, double angle)
    {
        double xRotate=this.getX();
        double yRotate=this.getY()+this.height/2;

        Rotate rotate=new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(xRotate);
        rotate.setPivotY(yRotate);
        this.getTransforms().add(rotate);

    }

    public void rightRotate()
    {

    }

}
