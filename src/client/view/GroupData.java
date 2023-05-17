package client.view;

import client.control.FriendDataButton;
import client.control.GroupDataButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GroupData extends Application {
    public static Stage stagex=new Stage();
    public static GroupDataButton groupDataButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/GroupData.fxml"));
        Parent root = fxmlLoader.load();
        groupDataButton=fxmlLoader.getController();
        stage.setTitle("群聊资料");
        stage.setScene(new Scene(root,323,434));
        stage.setResizable(false);
        stage.show();
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
