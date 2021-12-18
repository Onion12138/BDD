package app.item;



import core.Transfer;
import javafx.event.ActionEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.List;

@Slf4j(topic = "GUI")
public class TopMenu extends MenuBar {
    private Menu fileMenu;
    private Menu startMenu;
    private FileChooser fileChooser;
    private Stage stage;
    private TextArea textArea;
    private ImageView imageView;

    public TopMenu(Stage stage, TextArea textArea, ImageView iv){
        super();
        fileMenu = new Menu("File");
        startMenu = new Menu("Start");
        imageView = iv;
        this.getMenus().add(fileMenu);
        this.getMenus().add(startMenu);
        fileChooser = new FileChooser();
        this.stage = stage;
        this.textArea = textArea;
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction((ActionEvent e)->{openFile();});
        MenuItem startTransfer = new MenuItem("BeginTransfer");
        startTransfer.setOnAction((ActionEvent e)->{transfer();});
        MenuItem saveGraph = new MenuItem("SaveGraph");
        saveGraph.setOnAction((ActionEvent e)->{saveImage();});
        fileMenu.getItems().add(openFile);
        fileMenu.getItems().add(saveGraph);
        startMenu.getItems().add(startTransfer);
//        log.info("initialized complete");
    }

    private void transfer(){
        String text = textArea.getText();
        if (text.trim().length()==0&&text==null){
            log.info("Empty input, ignored");
            return;
        }
        File file = Transfer.run(text);
        if (file==null){
            log.warn("Missing tem image file");
            imageView.setImage(null);
            return;
        }
        try {
            Image image = new Image(new FileInputStream(file));
            imageView.setImage(image);

        } catch (FileNotFoundException e) {
            log.error("Error in opening tem image file: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private void saveImage(){
        fileChooser.setTitle("Save to");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File","*.png"));
        File output = fileChooser.showSaveDialog(stage);
        if(output==null) return;
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),null),"png",output);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Something wrong when saving png file");
        }
    }

    private void openFile(){
        fileChooser.setTitle("Open TXT file");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File inputFile = fileChooser.showOpenDialog(stage);
        if(inputFile==null) return;
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Something wrong with the file reading");
        }
        textArea.setText(sb.toString());
    }
}
