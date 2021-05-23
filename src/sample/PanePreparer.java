package sample;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PanePreparer {

    public static BorderPane prepareBorderPane(int width, int height)
    {
        BorderPane pane=new BorderPane();
        pane.setPrefSize(width,height);

        return pane;
    }

    public static Pane preparePane(int width, int height)
    {
        Pane pane=new Pane();
        pane.setPrefSize(width,height);

        return pane;
    }

    public static GridPane prepareGridPane(int width, int height)
    {
        GridPane pane = new GridPane();
        pane.setPrefSize(width,height);

        return pane;
    }
    public static ColumnConstraints prepareColumnConstraints (int precentage){
        ColumnConstraints columnCon = new ColumnConstraints();
        columnCon.setPercentWidth(precentage);

        return columnCon;
    }
    public static Label prepareLabel (IntegerProperty i){
        Label label = new Label();
        label.textProperty().bind(i.asString());
        label.setStyle("-fx-font-size: 5em;");
        return label;
    }


}
