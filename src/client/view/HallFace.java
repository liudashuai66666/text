package client.view;

import application.AllApplication;
import application.EmailApplication;
import client.control.GroupChatButton;
import client.control.HallButton;
import client.control.NewFriendButton;
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
    public static HallButton hallButton;
    public static GroupChatButton groupChatButton;
    public static NewFriendButton newFriendButton;
    public static Stage stagex;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1=new FXMLLoader(getClass().getResource("../viewfxml/Hall.fxml"));
        FXMLLoader fxmlLoader2=new FXMLLoader(getClass().getResource("../viewfxml/GroupChatFace.fxml"));
        FXMLLoader fxmlLoader3=new FXMLLoader(getClass().getResource("../viewfxml/NewFriend.fxml"));
        // 在构造函数中加载页面并初始化场景
        page1 = fxmlLoader1.load();
        hallButton=fxmlLoader1.getController();
        page2 = fxmlLoader2.load();
        groupChatButton=fxmlLoader2.getController();
        page3 = fxmlLoader3.load();
        newFriendButton=fxmlLoader3.getController();
        scene = new Scene(page1, 965, 661);

        stage.setTitle("大厅");
        stage.setScene(scene);
        stage.setResizable(false);
        stagex=stage;
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