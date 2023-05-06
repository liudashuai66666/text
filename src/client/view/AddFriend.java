package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddFriend extends Application {
    public static Stage stagex=new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../viewfxml/AddFriend.fxml"));
        stage.setTitle("添加好友");
        stage.setScene(new Scene(root,405,407));
        stage.show();
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
