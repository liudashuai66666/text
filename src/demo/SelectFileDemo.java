package demo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class SelectFileDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            System.out.println("您选择的文件是：" + selectedFile.getAbsolutePath().toUpperCase());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}