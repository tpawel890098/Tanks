package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PanePreparer {

    public static BorderPane prepareMainPane(int width, int height)
    {
        BorderPane pane=new BorderPane();
        pane.setPrefSize(width,height);

        return pane;
    }

    public static Pane prepareGameZonePane(int width, int height)
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

}
