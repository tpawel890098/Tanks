package sample;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Cell extends Rectangle {

    private int cellValue;
    private int cellDurability;
    private double cellSize=GameSetup.getCellSize();
    private double cellFallSpeed=GameSetup.getCellFallingSpeed();
    private double cellFallAcceleration=GameSetup.getCellAcceleration();
    private double cellSizeDecrease=GameSetup.getCellSizeChange();
    private double cellDurabilityIncreaseTime=GameSetup.getDurabilityIncreaseTimeUnit();
    private double x;
    private double y;
    private boolean isDestroyed;
    private Timeline timeline;

    private static final int[] durabilityArray = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8, 9};


    public Cell() {

        super();
        this.setWidth(cellSize);
        this.setHeight(cellSize);

        Random respawnZone=new Random();

        int cellRespCordX=respawnZone.nextInt(600)+250;

        this.setY(100);
        this.setX(cellRespCordX);

        Random random = new Random();
        int valueOfCell = random.nextInt(25);
        this.setStroke(Color.BLACK);
        cellValue = durabilityArray[valueOfCell];
        cellDurability = durabilityArray[valueOfCell];

        if (cellDurability < 2)
            this.setFill(Color.GREEN);
        else if (cellDurability < 4)
            this.setFill(Color.YELLOW);
        else if (cellDurability < 6)
            this.setFill(Color.ORANGE);
        else if (cellDurability < 8)
            this.setFill(Color.RED);
        else
            this.setFill(Color.GRAY);

        this.isDestroyed=false;
        x=cellRespCordX;
        y=0;

        timeline= new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(50),new KeyValue(this.yProperty(),1200)));
        timeline.playFromStart();


    }


    public void setCellDurability(int value)
    {
        this.cellDurability=value;
    }

    public int getCellDurability()
    {
        return this.cellDurability;
    }

    public void cellMovement()
    {
        this.setY(this.getY()+cellFallSpeed);
    }

}
