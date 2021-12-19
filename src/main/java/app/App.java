package app;

import app.item.TopMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("BDD Converter @G2");
        BorderPane root = new BorderPane();
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPromptText("Please input here");
        textArea.setTooltip(new Tooltip("========Welcome to the BDD Diagram Generator System========\n" +
                "Please input the formula in the following format:\n" +
                "formula  := (formula)\n" +
                "          | ! formula\n" +
                "          | formula op formula\n" +
                "          | variable\n" +
                "variable := [a-zA-Z_] [a-zA-Z_0-9]*\n" +
                "op       := + | * | -> | ^"));
        textArea.setFocusTraversable(false);
        textArea.setPrefSize(400,600);
        textArea.setFont(textArea.getFont().font(22));
        root.setLeft(textArea);
        ScrollPane sp = new ScrollPane();
        sp.setScaleShape(true);
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#FFFFFF"),null, Insets.EMPTY);
        sp.setBackground(new Background(backgroundFill));
        sp.setPrefSize(500,600);
        ImageView iv = new ImageView();
        sp.setContent(iv);
        sp.pannableProperty().set(true);
        root.setCenter(sp);
        root.setTop(new TopMenu(stage,textArea,iv));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
