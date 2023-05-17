package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateGroup extends Application {
    public static Stage stagex=new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../viewfxml/CreateGroup.fxml"));
        stage.setTitle("创建群聊");
        stage.setScene(new Scene(root,600,400));
        stage.show();
        stage.setResizable(false);
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
