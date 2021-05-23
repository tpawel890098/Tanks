package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static sample.PanePreparer.*;

public class Main extends Application {

    private BorderPane mainPane;
    private Pane leftPlayerPane;
    private Pane rightPlayerPane;
    private Pane gamePane;
    private GridPane scoresPane;
    private GridPane bottomPane;
    private Pane p1PointsPane;
    private Pane p1BulletPane;
    private Pane p2PointsPane;
    private Pane p2BulletPane;
    private Pane timePane;


    @Override
    public void start(Stage primaryStage){

        Group root=new Group();
        Scene scene= new Scene(root);

        Tank leftTank=new Tank(50,300,100,100,Color.RED, true);
        Tank rightTank=new Tank(50,300,100,100,Color.GREEN, false);


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

        scene.setOnKeyPressed(e-> {

                    switch (e.getCode()) {
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
                            //shoot(player);
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
        timePane = preparePane(250,100);
        ColumnConstraints p1PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints timeColumn = prepareColumnConstraints(20);
        ColumnConstraints p2PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints p1BulletColumn = prepareColumnConstraints(20);
        ColumnConstraints p2BulletColumn = prepareColumnConstraints(20);
        timePane.setStyle("-fx-background-color:cyan");
        p1PointsPane.setStyle("-fx-background-color:yellow;");
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
}
