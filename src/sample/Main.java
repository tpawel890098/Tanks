package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sample.PanePreparer.*;

public class Main extends Application {

    private static BorderPane mainPane;
    private static Pane leftPlayerPane;
    private static Pane rightPlayerPane;
    private static Pane gamePane;
    private static GridPane scoresPane;
    private static GridPane bottomPane;
    private static Pane p1PointsPane;
    private static Pane p1BulletPane;
    private static Pane p2PointsPane;
    private static Pane p2BulletPane;
    private static BorderPane timePane;
    private static final Integer TIME =120;
    private IntegerProperty timeInSeconds = new SimpleIntegerProperty(TIME);
    public static Group root;
    private Timeline timeline;


    @Override
    public void start(Stage primaryStage){

        root=new Group();
        Scene scene= new Scene(root);

        Tank leftTank=new Tank(50,300,100,100,Color.RED, true);
        Tank rightTank=new Tank(50,300,100,100,Color.GREEN, false);

        AnimationTimer timer= new AnimationTimer(){

            @Override
            public void handle(long l) {
                update();
            }
        };

        timer.start();


        mainPane= prepareMainPane(1200,900);
        gamePane= preparePane(1000,700);
        leftPlayerPane= preparePane(200,700);
        rightPlayerPane= preparePane(200,700);
        scoresPane=prepareGridPane(1200,100);
        bottomPane=prepareGridPane(1200,100);


        leftPlayerPane.getChildren().add(leftTank);
        leftPlayerPane.getChildren().add(leftTank.barrel);
        rightPlayerPane.getChildren().add(rightTank);
        rightPlayerPane.getChildren().add(rightTank.barrel);



        scene.addEventHandler(KeyEvent.KEY_PRESSED,(key)-> {

            switch (key.getCode()) {
                case W:
                    leftTank.moveUp(true);
                    break;
                case S:
                    leftTank.moveDown(true);
                    break;
                case D:
                    leftTank.barrel.leftRotate(5);
                    break;
                case A:
                    leftTank.barrel.leftRotate(-5);
                    break;
                case SPACE:
                    leftTank.barrel.shoot(true);
                    break;
                case UP:
                    rightTank.moveUp(false);
                    break;
                case DOWN:
                    rightTank.moveDown(false);
                    break;
                case LEFT:
                    rightTank.barrel.rightRotate(-5);
                    break;
                case RIGHT:
                    rightTank.barrel.rightRotate(5);
                    break;
                case ENTER:
                    rightTank.barrel.shoot(false);
                    break;
            }
        });



        gamePane.setStyle("-fx-background-color:lightblue;");
        // leftPlayerPane.setStyle("-fx-background-color:yellow;");
        // rightPlayerPane.setStyle("-fx-background-color:green;");
        scoresPane.setStyle("-fx-background-color:black;");
        bottomPane.setStyle("-fx-background-color:black;");
        p1PointsPane = preparePane(250,100);
        p1BulletPane = preparePane(250,100);
        p2PointsPane = preparePane(250,100);
        p2BulletPane = preparePane(250,100);
        timePane = new BorderPane();

        Label timeLabel = new Label();
        timeLabel.setStyle("-fx-font-size: 5em;");
        timeLabel.textProperty().bind(timeInSeconds.asString());
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(TIME+1), new KeyValue(timeInSeconds,0)));
        timeline.playFromStart();
        timePane.setCenter(timeLabel);

        ColumnConstraints p1PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints timeColumn = prepareColumnConstraints(20);
        ColumnConstraints p2PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints p1BulletColumn = prepareColumnConstraints(20);
        ColumnConstraints p2BulletColumn = prepareColumnConstraints(20);
        p1PointsPane.setStyle("-fx-background-color:yellow;");
        timePane.setStyle("-fx-background-color:cyan");
        p1BulletPane.setStyle("-fx-background-color:green;");
        p2PointsPane.setStyle("-fx-background-color:blue;");
        p2BulletPane.setStyle("-fx-background-color:red;");
        scoresPane.setConstraints(p1PointsPane,0,0);
        scoresPane.setConstraints(p1BulletPane,1,0);
        scoresPane.setConstraints(timePane,2,0);
        scoresPane.setConstraints(p2PointsPane,3,0);
        scoresPane.setConstraints(p2BulletPane,4,0);
        scoresPane.getColumnConstraints().addAll(p1PointsColumn,p1BulletColumn,timeColumn,p2PointsColumn,p2BulletColumn);
        scoresPane.getChildren().addAll(p1PointsPane,p1BulletPane,timePane,p2BulletPane,p2PointsPane);

        mainPane.setLeft(leftPlayerPane);
        mainPane.setRight(rightPlayerPane);
        mainPane.setCenter(gamePane);
        mainPane.setTop(scoresPane);
        mainPane.setBottom(bottomPane);

        root.getChildren().add(mainPane);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Game of Tanks");

    }


    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<Bullet> bullets(){

        ArrayList<Bullet> nodes = new ArrayList<Bullet>();
        addAllDescendents(root, nodes);

        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Bullet> nodes) {

        for (Node node : parent.getChildrenUnmodifiable()) {
            if(node instanceof Bullet)
                nodes.add((Bullet) node);
        }

    }

    private void update(){

        bullets().forEach(s->{

            if (s.leftPlayersBullet) {

                s.leftBulletMovement();

                 if(s.getBoundsInParent().intersects(scoresPane.getBoundsInParent())){
                        s.isDestroyed=true;
                        s.setOpacity(0);
                    }
                 else if(s.getBoundsInParent().intersects(bottomPane.getBoundsInParent())){
                     s.isDestroyed=true;
                     s.setOpacity(0);
                 }
                 else if(s.getBoundsInParent().intersects(rightPlayerPane.getBoundsInParent())){
                     s.isDestroyed=true;
                     s.setOpacity(0);
                 }
            }
            else {

                s.rightBulletMovement();

                if(s.getBoundsInParent().intersects(scoresPane.getBoundsInParent())){
                    s.isDestroyed=true;
                    s.setOpacity(0);
                }
                else if(s.getBoundsInParent().intersects(bottomPane.getBoundsInParent())){
                    s.isDestroyed=true;
                    s.setOpacity(0);
                }
                else if(s.getBoundsInParent().intersects(leftPlayerPane.getBoundsInParent())){
                    s.isDestroyed=true;
                    s.setOpacity(0);
                }
            }
        });

    }
}
