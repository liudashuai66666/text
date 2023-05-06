package client.view;

import application.AllApplication;
import application.EmailApplication;
import client.vo.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Period;

public class HallFace extends Application {
    private Scene scene;
    private Parent page1;
    private Parent page2;
    private Parent page3;
    //public static Stage stagex;

    @Override
    public void start(Stage stage) throws IOException {
        // 在构造函数中加载页面并初始化场景
        page1 = FXMLLoader.load(getClass().getResource("../viewfxml/Hall.fxml"));
        page2 = FXMLLoader.load(getClass().getResource("../viewfxml/GroupChatFace.fxml"));
        page3 = FXMLLoader.load(getClass().getResource("../viewfxml/NewFriend.fxml"));
        scene = new Scene(page1, 965, 661);

        stage.setTitle("大厅");
        stage.setScene(scene);
        stage.show();
        //stagex = stage;
        Listenner(stage);//调用
    }

    public void switchToPage2() {
        if (scene != null && page2 != null) {
            // 切换页面时确保变量不为空
            scene.setRoot(page2);
            System.out.println("切换成功");
        }
    }

    public void switchToPage1() {
        if (scene != null && page1 != null) {
            // 切换页面时确保变量不为空
            scene.setRoot(page1);
            System.out.println("切换成功");
        }
    }
    public void switchToPage3() {
        if (scene != null && page1 != null) {
            // 切换页面时确保变量不为空
            scene.setRoot(page3);
            System.out.println("切换成功");
        }
    }
    public static void Listenner(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                //下线了；
                try {
                    System.out.println("下线了");
                    //Socket socket = new Socket("127.0.0.1", 7777);
                    ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                    EmailApplication shuju = new EmailApplication(User.mailbox);
                    oos.writeObject(new AllApplication<>("下线了", shuju));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}