package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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



    @Override
    public void start(Stage primaryStage){

        Group root=new Group();
        Scene scene= new Scene(root);

        Rectangle rect =new Rectangle();
        rect.setHeight(50);
        rect.setWidth(50);
        rect.setFill(Color.BROWN);
        rect.setX(25);
        rect.setY(200);

        Tank leftTank=new Tank(50,300,100,100,Color.RED, true);
        Tank rightTank=new Tank(50,300,100,100,Color.GREEN, false);


        mainPane= prepareMainPane(1200,900);
        gamePane= prepareGameZonePane(1000,700);
        leftPlayerPane=prepareGameZonePane(200,700);
        rightPlayerPane=prepareGameZonePane(200,700);
        scoresPane=prepareGridPane(1200,100);
        bottomPane=prepareGridPane(1200,100);

        gamePane.getChildren().add(rect);

        leftPlayerPane.getChildren().add(leftTank);
        leftPlayerPane.getChildren().add(leftTank.barrel);
        rightPlayerPane.getChildren().add(rightTank);
        rightPlayerPane.getChildren().add(rightTank.barrel);

        scene.setOnKeyPressed(e-> {

                    switch (e.getCode()) {
                        case W:
                            leftTank.moveUp();
                            break;
                        case S:
                            leftTank.moveDown();
                            break;
                        case SPACE:
                            //shoot(player);
                            break;
                        case UP:
                            rightTank.moveUp();
                            break;
                        case DOWN:
                            rightTank.moveDown();
                            break;
                    }
                });

        gamePane.setStyle("-fx-background-color:lightblue;");
       // leftPlayerPane.setStyle("-fx-background-color:yellow;");
       // rightPlayerPane.setStyle("-fx-background-color:green;");
        scoresPane.setStyle("-fx-background-color:black;");
        bottomPane.setStyle("-fx-background-color:black;");

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
