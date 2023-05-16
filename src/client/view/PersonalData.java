package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PersonalData extends Application {
    public static Stage stagex=new Stage();
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../viewfxml/PersonalData.fxml"));
        stage.setTitle("个人资料");
        stage.setScene(new Scene(root, 333, 431));
        stage.show();
        stage.setResizable(false);
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
