package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ForgetFace extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../viewfxml/ForgetFace.fxml"));
        stage.setTitle("忘记密码");
        stage.setScene(new Scene(root,581,799));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}