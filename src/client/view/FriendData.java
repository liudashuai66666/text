package client.view;

import client.control.FriendDataButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FriendData extends Application {
    public static Stage stagex=new Stage();
    public static FriendDataButton friendDataButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/FriendData.fxml"));
        Parent root = fxmlLoader.load();
        friendDataButton=fxmlLoader.getController();
        stage.setTitle("好友资料");
        stage.setScene(new Scene(root,333,431));
        stage.show();
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
