package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ListFace extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../viewfxml/ListFace.fxml"));
        stage.setTitle("大厅");
        stage.setScene(new Scene(root,381,146));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
