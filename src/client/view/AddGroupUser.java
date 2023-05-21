package client.view;

import client.control.AddGroupUserButton;
import client.control.ChangeDataButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddGroupUser extends Application {
    public static Stage stagex=new Stage();
    public static AddGroupUserButton addGroupUserButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/AddGroupUser.fxml"));
        Parent root =fxmlLoader.load();
        addGroupUserButton=fxmlLoader.getController();
        stage.setTitle("邀请成员");
        stage.setScene(new Scene(root,600,400));
        stage.show();
        stage.setResizable(false);
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
