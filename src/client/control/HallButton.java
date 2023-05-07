package client.control;

import application.EmailApplication;
import application.MemoryUserApplication;
import client.view.AddFriend;
import client.view.HallFace;
import client.view.PersonalData;
import client.vo.FindUser;
import client.vo.FriendList;
import client.vo.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import toolkind.Friends;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.*;

//大厅
public class HallButton implements Initializable {
    @FXML
    private Button FriendsButton;
    @FXML
    private Button addFriendButton;
    @FXML
    private Button GroupChatButton;

    @FXML
    private Button MyMessageButton;
    @FXML
    private ImageView Avatar;
    @FXML
    private Button FriendApplicationButton;

    @FXML
    private ListView<Friends> ChatList;

    @FXML
    void GroupChat(ActionEvent event) {
        System.out.println("群聊");
        LoginButton.hall.switchToPage2();
    }
    @FXML
    void FriendApplication(ActionEvent event) throws IOException {
        System.out.println("好友申请");
        LoginButton.hall.switchToPage3();
    }
    @FXML
    void Friends(ActionEvent event) {
        System.out.println("私聊");
    }

    @FXML
    void addFriend(ActionEvent event) throws Exception {
        System.out.println("添加好友");
        AddFriend addFriend = new AddFriend();
        if(!addFriend.stagex.isShowing()){
            FindUser.uname=null;
            FindUser.account = null;
            FindUser.mailbox = null;
            FindUser.sex = null;
            FindUser.age = null;
            FindUser.birthday = null;
            FindUser.signature = null;
            FindUser.avatar = null;
            addFriend.start(new Stage());
        }else{
            addFriend.stagex.toFront();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
        ArrayList<Friends> data=new ArrayList<>();
        for (MemoryUserApplication m : FriendList.friendList) {
            Friends friends=new Friends(m.getAvatar(),m.getUname());
            data.add(friends);
        }
        ChatList.setCellFactory(param -> new CustomListCell("friend"));
        ChatList.getItems().addAll(data);
        flushed();
    }
    @FXML
    void MyMessage(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }



    //刷新界面
    protected void flushed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(User.avatar!=null) {
                                Avatar.setImage(new Image(User.avatar));
                            }else{
                                Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 1000);//定时器的延迟时间及间隔时间
    }
}




















