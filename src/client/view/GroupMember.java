package client.view;

import client.control.GroupListButton;
import client.control.GroupMemberButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GroupMember extends Application {
    public static Stage stagex=new Stage();
    public static GroupMemberButton groupListButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/GroupMember.fxml"));
        Parent root = fxmlLoader.load();
        groupListButton=fxmlLoader.getController();
        stage.setTitle("大厅");
        stage.setResizable(false);
        stage.setScene(new Scene(root,381,146));
        stage.show();
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
