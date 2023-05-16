package client.view;

import client.control.ChangeDataButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeData extends Application {
    public static Stage stagex=new Stage();
    public  static ChangeDataButton changeDataButton;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../viewfxml/ChangeData.fxml"));
        Parent root =fxmlLoader.load();
        changeDataButton=fxmlLoader.getController();
        stage.setTitle("修改资料");
        stage.setScene(new Scene(root,511,542));
        stage.show();
        stage.setResizable(false);
        stagex=stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
