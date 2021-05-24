package sample;


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
    public boolean isDestroyed;
    public Timeline timeline;
    public StackPane stack;
    public Label cellLabel;
    public IntegerProperty cellValueForLabel;

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
        this.stack = new StackPane();
        this.cellLabel = new Label();
        this.cellValueForLabel = new SimpleIntegerProperty(cellDurability);
        cellLabel.setStyle("-fx-font-size: 2.5em;");
        cellLabel.textProperty().bind(cellValueForLabel.asString());
        stack.getChildren().addAll(this,cellLabel);
        stack.setLayoutX(cellRespCordX);
        stack.setPrefSize(this.cellSize,this.cellSize);
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

        this.isDestroyed=false;
        x=cellRespCordX;
        y=0;

        timeline= new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),new KeyValue(this.stack.layoutYProperty(),800-this.cellSize)));
        timeline.setOnFinished((e) -> {
            this.stack.setLayoutY(1200);
            ((Group)this.stack.getParent()).getChildren().remove(this.stack);
        });
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

}
