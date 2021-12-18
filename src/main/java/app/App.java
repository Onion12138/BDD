package app;

import app.item.TopMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
        textArea.setText("Please input imp program here \nor open a text file that contains a simp program.");
        textArea.setPrefSize(400,600);
        root.setLeft(textArea);
        ScrollPane sp = new ScrollPane();
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
