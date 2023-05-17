package client.view;

import client.control.CreateGroupListButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class CreateGroupList extends Application {
    public static CreateGroupListButton createGroupListButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/CreateGroupList.fxml"));
        Parent root = fxmlLoader.load();
        createGroupListButton=fxmlLoader.getController();
        stage.setTitle("大厅");
        stage.setScene(new Scene(root,381,146));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
