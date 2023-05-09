package client.view;

import client.control.ListFaceButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ListFace extends Application {
    public static ListFaceButton listFaceButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/ListFace.fxml"));
        Parent root = fxmlLoader.load();
        listFaceButton=fxmlLoader.getController();
        stage.setTitle("大厅");
        stage.setScene(new Scene(root,381,146));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
