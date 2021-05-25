package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
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
import javafx.scene.shape.Circle;
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
    private static Pane bottomPane;
    private static BorderPane p1PointsPane;
    private static BorderPane p1BulletPane;
    private static BorderPane p2PointsPane;
    private static BorderPane p2BulletPane;
    private static BorderPane timePane;
    private static Integer TIME;
    private static IntegerProperty timeInSeconds;
    private static IntegerProperty p1PointsInt = new SimpleIntegerProperty(0);
    private static IntegerProperty p1ShotBulletsInt = new SimpleIntegerProperty(0);
    private static IntegerProperty p2PointsInt = new SimpleIntegerProperty(0);
    private static IntegerProperty p2ShotBulletsInt = new SimpleIntegerProperty(0);
    private static Label timeLabel;
    private static Label p1PointsLabel;
    private static Label p1ShotBulletsLabel;
    private static Label p2PointsLabel;
    private static Label p2ShotBulletsLabel;
    public static Group root;
    private static Timeline timeline;
    public static Tank leftTank;
    public static Tank rightTank;
    public static Circle circle;
    private static ArrayList<Circle> circleCol;
    private static ArrayList<Cell> cellCol;
    private double t;
    private static Label notificationsLabel;
    private StringProperty notifications = new SimpleStringProperty();
    private double cellSpeed=GameSetup.getCellFallingSpeed();


    @Override
    public void start(Stage primaryStage){

        circleCol=new ArrayList<Circle>();
        cellCol=new ArrayList<Cell>();

        root=new Group();
        Scene scene= new Scene(root);

        GameSetup.setSettingsUp();

        TIME=GameSetup.getGameTime();
        timeInSeconds= new SimpleIntegerProperty(TIME);

        leftTank=new Tank(50,300,100,100,Color.RED, true);
        rightTank=new Tank(50,300,100,100,Color.GREEN, false);

        AnimationTimer timer= new AnimationTimer(){

            @Override
            public void handle(long l) {
                try {
                    update();
                }
                catch(Exception e) {
                    System.out.println("UPDATE PROBLEM");

                }
            }
        };

        timer.start();


        mainPane= prepareBorderPane(1200,900);
        gamePane= preparePane(1000,700);
        leftPlayerPane= preparePane(200,700);
        rightPlayerPane= preparePane(200,700);
        scoresPane=prepareGridPane(1200,100);
        bottomPane=preparePane(1200,100);


        leftPlayerPane.getChildren().add(leftTank);
        leftPlayerPane.getChildren().add(leftTank.barrel);
        rightPlayerPane.getChildren().add(rightTank);
        rightPlayerPane.getChildren().add(rightTank.barrel);


        try {
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

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
                        root.getChildren().removeAll(circleCol);
                        circleCol.clear();
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
                        root.getChildren().removeAll(circleCol);
                        circleCol.clear();
                        break;
                }

            });
        }catch(Exception e) {
            System.out.println("KEY LISTENER PROBLEM");

        }



        gamePane.setStyle("-fx-background-color:lightblue;-fx-border-color: black ;-fx-border-width: 1 ;");
        leftPlayerPane.setStyle("-fx-background-color:#fce3e1;-fx-border-color: black ;-fx-border-width: 1 ;");
        rightPlayerPane.setStyle("-fx-background-color:#e7fce1;-fx-border-color: black ;-fx-border-width: 1 ;");
        scoresPane.setStyle("-fx-background-color:black;-fx-border-color: black ;-fx-border-width: 1 ;");
        bottomPane.setStyle("-fx-background-color:black;-fx-border-color: black ;-fx-border-width: 1 ;");
        p1PointsPane = prepareBorderPane(250,100);
        p1BulletPane = prepareBorderPane(250,100);
        p2PointsPane = prepareBorderPane(250,100);
        p2BulletPane = prepareBorderPane(250,100);
        timePane = new BorderPane();

        timeLabel = prepareLabel(timeInSeconds);
        p1PointsLabel = prepareLabel(p1PointsInt);
        p1ShotBulletsLabel = prepareLabel(p1ShotBulletsInt);
        p2PointsLabel = prepareLabel(p2PointsInt);
        p2ShotBulletsLabel = prepareLabel(p2ShotBulletsInt);

        p1PointsPane.setCenter(p1PointsLabel);
        p1BulletPane.setCenter(p1ShotBulletsLabel);
        p2PointsPane.setCenter(p2PointsLabel);
        p2BulletPane.setCenter(p2ShotBulletsLabel);

        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(TIME+1), new KeyValue(timeInSeconds,0)));
        timeline.playFromStart();
        timePane.setCenter(timeLabel);

        ColumnConstraints p1PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints timeColumn = prepareColumnConstraints(20);
        ColumnConstraints p2PointsColumn = prepareColumnConstraints(20);
        ColumnConstraints p1BulletColumn = prepareColumnConstraints(20);
        ColumnConstraints p2BulletColumn = prepareColumnConstraints(20);
        p1PointsPane.setStyle("-fx-background-color:red;-fx-border-color: black ;-fx-border-width: 1 ;");
        timePane.setStyle("-fx-background-color:#8ab5f2;-fx-border-color: black ;-fx-border-width: 1 ;");
        p1BulletPane.setStyle("-fx-background-color:#f28a8a;-fx-border-color: black ;-fx-border-width: 1 ;");
        p2PointsPane.setStyle("-fx-background-color:green;-fx-border-color: black ;-fx-border-width: 1 ;");
        p2BulletPane.setStyle("-fx-background-color:lightgreen;-fx-border-color: black ;-fx-border-width: 1 ;");
        scoresPane.setConstraints(p1PointsPane,0,0);
        scoresPane.setConstraints(p1BulletPane,1,0);
        scoresPane.setConstraints(timePane,2,0);
        scoresPane.setConstraints(p2PointsPane,4,0);
        scoresPane.setConstraints(p2BulletPane,3,0);
        scoresPane.getColumnConstraints().addAll(p1PointsColumn,p1BulletColumn,timeColumn,p2PointsColumn,p2BulletColumn);
        scoresPane.getChildren().addAll(p1PointsPane,p1BulletPane,timePane,p2BulletPane,p2PointsPane);
        notificationsLabel = new Label();
        notificationsLabel.setLayoutY(0);
        notificationsLabel.setLayoutX(200);
        notificationsLabel.setPrefSize(800,100);
        notificationsLabel.textProperty().bind(notifications);
        notificationsLabel.setAlignment(Pos.CENTER);
        notificationsLabel.setStyle("-fx-font-size: 3em; -fx-background-color: #db8af2;-fx-border-color: black ;-fx-border-width: 1 ;");


        bottomPane.getChildren().add(notificationsLabel);



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

        try {
            ArrayList<Bullet> nodes = new ArrayList<Bullet>();
            addAllDescendents(root, nodes);
            return nodes;
        }catch(Exception e)
        {
            System.out.println("bullet");
        }

        return new ArrayList<Bullet>();
    }

    private static void addAllDescendents(Parent parent, ArrayList<Bullet> nodes) {

        try {
            for (Node node : parent.getChildrenUnmodifiable()) {
                if (node instanceof Bullet)
                    nodes.add((Bullet) node);
            }
        }catch(Exception e){

            System.out.println("ADDALLDESCENDENTS");

        }

    }


    private void update(){

        t+=0.032;

        try {
        bullets().forEach(s->{

            Circle fakeBullet;
            fakeBullet=new Circle(0,0,0);
            circle=fakeBullet;

            for(Cell cell : cellCol )
            {
                if(s.getBoundsInParent().intersects(cell.stack.getBoundsInParent()))
                {
                    cell.getHit();

                    s.setOpacity(0);
                    s.isDestroyed=true;
                    root.getChildren().remove(s);

                    if(s.leftPlayersBullet)
                    {
                        leftTank.setActiveBullets(leftTank.getActiveBullets() - 1);

                        if(cell.getCellDurability()==0)
                        {
                            leftTank.setPlayerPoints(leftTank.getPlayerPoints()+cell.getCellValue());
                            notifications.set("Lewy gracz zdobywa +"+cell.getCellValue()+" pkt");
                            cell.stack.setOpacity(0);
                            cell.timeline.jumpTo(Duration.seconds(15-cellSpeed));
                            p1PointsInt.setValue(leftTank.getPlayerPoints());
                            cell.isDestroyed=true;

                            System.out.println("LEWY GRACZ PKT: "+leftTank.getPlayerPoints());
                        }
                    }
                    else
                    {
                        rightTank.setActiveBullets(rightTank.getActiveBullets() - 1);

                        if(cell.getCellDurability()==0)
                        {
                            rightTank.setPlayerPoints(rightTank.getPlayerPoints()+cell.getCellValue());
                            notifications.set("Prawy gracz zdobywa +"+cell.getCellValue()+" pkt");
                            cell.stack.setOpacity(0);
                            cell.timeline.jumpTo(Duration.seconds(15-cellSpeed));
                            p2PointsInt.setValue(rightTank.getPlayerPoints());
                            cell.isDestroyed=true;

                            System.out.println("PRAWY GRACZ PKT: "+rightTank.getPlayerPoints());
                        }

                    }





                }

            }

            if (s.leftPlayersBullet) {

                s.leftBulletMovement();

                 if(s.getBoundsInParent().intersects(scoresPane.getBoundsInParent())){

                     s.setOpacity(0);
                     if(s.isDestroyed==false) {
                         leftTank.setActiveBullets(leftTank.getActiveBullets() - 1);
                         s.isDestroyed=true;
                         root.getChildren().remove(s);
                         root.getChildren().add(fakeBullet);
                         circleCol.add(fakeBullet);
                     }
                 }
                 else if(s.getBoundsInParent().intersects(bottomPane.getBoundsInParent())){

                     s.setOpacity(0);
                     if(s.isDestroyed==false) {
                         leftTank.setActiveBullets(leftTank.getActiveBullets() - 1);
                         s.isDestroyed=true;
                         root.getChildren().remove(s);
                         root.getChildren().add(fakeBullet);
                         circleCol.add(fakeBullet);
                     }
                 }
                 else if(s.getBoundsInParent().intersects(rightPlayerPane.getBoundsInParent())){

                     s.setOpacity(0);
                     if(s.isDestroyed==false) {
                         leftTank.setActiveBullets(leftTank.getActiveBullets() - 1);
                         s.isDestroyed=true;
                         root.getChildren().remove(s);
                         root.getChildren().add(fakeBullet);
                         circleCol.add(fakeBullet);
                     }

                }


            }
            else {

                s.rightBulletMovement();

                if(s.getBoundsInParent().intersects(scoresPane.getBoundsInParent())){

                    s.setOpacity(0);
                    if(s.isDestroyed==false) {
                        rightTank.setActiveBullets(rightTank.getActiveBullets() - 1);
                        s.isDestroyed=true;
                        root.getChildren().remove(s);
                        root.getChildren().add(fakeBullet);
                        circleCol.add(fakeBullet);
                    }

                }
                else if(s.getBoundsInParent().intersects(bottomPane.getBoundsInParent())){

                    s.setOpacity(0);
                    if(s.isDestroyed==false) {
                        rightTank.setActiveBullets(rightTank.getActiveBullets() - 1);
                        s.isDestroyed=true;
                        root.getChildren().remove(s);
                        root.getChildren().add(fakeBullet);
                        circleCol.add(fakeBullet);
                    }

                }
                else if(s.getBoundsInParent().intersects(leftPlayerPane.getBoundsInParent())){

                    s.setOpacity(0);
                    if(s.isDestroyed==false) {
                        rightTank.setActiveBullets(rightTank.getActiveBullets() - 1);
                        s.isDestroyed=true;
                        root.getChildren().remove(s);
                        root.getChildren().add(fakeBullet);
                        circleCol.add(fakeBullet);
                    }

                }

            }

        });

        }
        catch(Exception e) {
            System.out.println("UPDATE PROBLEM");

        }


        if(t>2){
            if(Math.random()<0.5){
                createNewCell();
                t=0;
            }
        }

    }


    public static void setShotBulletsP1(){
        p1ShotBulletsInt.set(p1ShotBulletsInt.get()+1);
    }

    public static void setShotBulletsP2(){
        p2ShotBulletsInt.set(p2ShotBulletsInt.get()+1);
    }

    public static void createNewCell()
    {
        Cell cell = new Cell();
        cellCol.add(cell);
        root.getChildren().add(cell.stack);
    }


}
