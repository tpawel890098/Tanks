package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Barrel extends Rectangle {

    public Barrel(double x, double y, double width, double height, boolean left)
    {
        super(x+width/2,y+height/2-height/12,width*2,height/6);

        if(!left)
        {
            this.setX(x-width);
        }

        this.setFill(Color.BLACK);
    }

}
