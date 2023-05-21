package client.view;

import client.control.AddGroupUserButton;
import client.control.ChangeDataButton;
import client.control.DeleteGroupUserButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteGroupUser extends Application {
    String flag;
    public static Stage stagex=new Stage();
    public static DeleteGroupUserButton deleteGroupUserButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/DeleteGroupUser.fxml"));
        Parent root =fxmlLoader.load();
        deleteGroupUserButton=fxmlLoader.getController();
        stage.setTitle(flag);
        stage.setScene(new Scene(root,600,400));
        stage.show();
        stage.setResizable(false);
        stagex=stage;
    }

    public DeleteGroupUser(String flag) {
        this.flag=flag;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
