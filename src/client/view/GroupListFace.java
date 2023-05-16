package client.view;

import client.control.GroupListButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class GroupListFace extends Application {
    public static GroupListButton groupListButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/GroupList.fxml"));
        Parent root = fxmlLoader.load();
        groupListButton=fxmlLoader.getController();
        stage.setTitle("大厅");
        stage.setScene(new Scene(root,381,146));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
