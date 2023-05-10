package client.view;

import client.control.ChatDataListButton;
import client.control.ListFaceButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatDataListFace extends Application {
    public static ChatDataListButton chatDataListButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/ChatDataList.fxml"));
        Parent root = fxmlLoader.load();
        chatDataListButton=fxmlLoader.getController();
        stage.setTitle("大厅");
        stage.setScene(new Scene(root,704,124));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
