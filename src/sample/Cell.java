package sample;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
    private double cellFallAcceleration=GameSetup.getCellAcceleration()/100;
    private double cellSizeDecrease=GameSetup.getCellSizeChange();
    private double cellDurabilityIncreaseTime=GameSetup.getDurabilityIncreaseTimeUnit();
    private double x;
    private double y;
    private double currentCellAcc;
    private Timer timer;
    public boolean isDestroyed;
    public Timeline timeline;
    public StackPane stack;
    public Label cellLabel;
    public IntegerProperty cellValueForLabel;
    private double timeUnit=GameSetup.getTimeUnit();
    private double currentScale;

    private static final int[] durabilityArray = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8, 9};


    public Cell() {

        super();

        Random respawnZone = new Random();

        int cellRespCordX = respawnZone.nextInt(600) + 250;

        if(this.cellFallAcceleration>0.1)
            this.cellFallAcceleration=0.1;
        else if(this.cellFallAcceleration<0)
            this.cellFallAcceleration=0;

        if(this.cellFallSpeed>10)
            this.cellFallSpeed=10;
        else if(this.cellFallSpeed<0)
            this.cellFallSpeed=0;

        if(this.cellDurabilityIncreaseTime>10)
            this.cellDurabilityIncreaseTime=10;
        else if(this.cellDurabilityIncreaseTime<0)
            this.cellDurabilityIncreaseTime=0;

        if(this.cellSize>60)
            this.cellSize=60;
        else if(this.cellSize<30)
            this.cellSize=30;

        if(this.cellSizeDecrease>3)
            this.cellSizeDecrease=3;
        else if(this.cellSizeDecrease<0)
            this.cellSizeDecrease=0;


        this.setWidth(cellSize);
        this.setHeight(cellSize);


        this.setY(100);
        this.setX(cellRespCordX);
        this.currentCellAcc=1;

        if(this.cellSizeDecrease==0) this.cellSizeDecrease=1;
        else this.cellSizeDecrease=(10-this.cellSizeDecrease)/10;

        this.currentScale=this.cellSizeDecrease;

        Random random = new Random();
        int valueOfCell = random.nextInt(25);
        this.setStroke(Color.BLACK);
        cellValue = durabilityArray[valueOfCell];
        cellDurability = durabilityArray[valueOfCell];
        this.stack = new StackPane();
        this.cellLabel = new Label();
        this.cellValueForLabel = new SimpleIntegerProperty(cellDurability);
        cellLabel.setStyle("-fx-font-size: 2.5em;");
        cellLabel.textProperty().bind(cellValueForLabel.asString());
        stack.getChildren().addAll(this, cellLabel);
        stack.setLayoutX(cellRespCordX);
        stack.setPrefSize(this.cellSize, this.cellSize);
        stack.setLayoutY(100);
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

        this.isDestroyed = false;
        x = cellRespCordX;
        y = 0;


        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(15-cellFallSpeed), new KeyValue(this.stack.layoutYProperty(), 800 - this.cellSize)));
        timeline.setRate(this.currentCellAcc);
        timeline.setOnFinished((e) -> {
            this.stack.setLayoutY(1200);
            this.timeline.stop();
            this.timer.cancel();
            ((Group) this.stack.getParent()).getChildren().remove(this.stack);
        });
        timeline.playFromStart();

        timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {

                try {

                    if (this != null) {
                        if (!isDestroyed) {
                            changeCellSpeed();
                        } else {
                            timer.cancel();
                        }
                    }

                } catch (Exception e) {
                    timer.cancel();
                    System.out.println("CELL TIMER PROBLEM");
                }
            }
        };

        int period= (int) (timeUnit*1000);
        timer.scheduleAtFixedRate(task,period,period);

        TimerTask task2 = new TimerTask() {

            public void run() {

                try {

                    if (this != null) {
                        if (!isDestroyed) {
                            changeCellDurability();
                            changeCellSize();
                        } else {
                            timer.cancel();
                        }
                    }

                } catch (Exception e) {
                    timer.cancel();
                    System.out.println("CELL DURABILITY TIMER PROBLEM");
                }
            }
        };

        int period2=(int) (cellDurabilityIncreaseTime*1000);

        if(period2>0)
        timer.scheduleAtFixedRate(task2,period2,period2);

    }




    public void setCellDurability(int value)
    {
        this.cellDurability=value;
    }

    public int getCellDurability()
    {
        return this.cellDurability;
    }

    public int getCellValue() { return this.cellValue; }

    public void cellMovement()
    {
        this.setY(this.getY()+cellFallSpeed);
    }

    public void getHit(){
        this.cellDurability--;
        if(cellDurability<2){
            this.setFill(Color.GREEN);
        }
        else if(cellDurability<4){
            this.setFill(Color.YELLOW);
        }
        else if(cellDurability<6){
            this.setFill(Color.ORANGE);
        }
        else if(cellDurability<8){
            this.setFill(Color.RED);
        }
        else{
            this.setFill(Color.GRAY);
        }
    }

    public void changeCellSpeed(){

            this.currentCellAcc+=this.cellFallAcceleration;

        this.timeline.setRate(this.currentCellAcc);


    }

    public void changeCellDurability(){

            if(this.cellDurability<9)
            this.cellDurability++;

        if(cellDurability<2){
            this.setFill(Color.GREEN);
        }
        else if(cellDurability<4){
            this.setFill(Color.YELLOW);
        }
        else if(cellDurability<6){
            this.setFill(Color.ORANGE);
        }
        else if(cellDurability<8){
            this.setFill(Color.RED);
        }
        else{
            this.setFill(Color.GRAY);
        }

    }

    public void changeCellSize(){


        this.stack.setScaleX(this.currentScale);
        this.stack.setScaleY(this.currentScale);

        if(this.cellSize>5) {
            this.cellSize*=this.currentScale;
            this.currentScale *= this.cellSizeDecrease;
        }
    }

    public double getCellFallAcceleration()
    {
        return cellFallAcceleration;
    }

    public double getCellFallSpeed()
    {
        return cellFallSpeed;
    }

    private void setCellFallSpeed(double value)
    {
        this.cellFallSpeed=value;
    }

}
