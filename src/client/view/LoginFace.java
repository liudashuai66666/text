package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFace extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginFace.class.getResource("../viewfxml/LoginFace.fxml"));
        stage.setTitle("登录");
        stage.setScene(new Scene(root, 772, 455));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}