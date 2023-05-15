package client.control;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import toolkind.Friends;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GroupChatButton implements Initializable {
    @FXML
    private Button groupDataButton;//创建群聊按钮
    @FXML
    private Button FriendsButton;//切换好友聊天
    @FXML
    private Button addFriendButton;//添加好友
    @FXML
    private Button GroupChatButton;//切换群聊按钮
    @FXML
    private Button MyMessageButton;//
    @FXML
    private ImageView Avatar;
    @FXML
    private Button createGroupButton;

    @FXML
    private Button searchGroupButton;
    @FXML
    private TextField InputBox;
    @FXML
    private Button addGroupMemberButton;
    @FXML
    private AnchorPane Pane;
    @FXML
    private Button emojis;

    @FXML
    private Button image;

    @FXML
    private Button sendButton;

    @FXML
    private ListView<Friends> ChatList;
    @FXML
    private Button FriendApplicationButton;

    @FXML
    void GroupChat(ActionEvent event) {
        System.out.println("群聊");
    }

    @FXML
    void Friends(ActionEvent event) {
        System.out.println("私聊");
        LoginButton.hall.switchToPage1();
        //转化到私聊界面
    }

    @FXML
    void addFriend(ActionEvent event) throws Exception {
        System.out.println("添加好友");
        AddFriend addFriend = new AddFriend();
        if(!addFriend.stagex.isShowing()){
            FindUser.findUser=null;
            addFriend.start(new Stage());
        }else{
            addFriend.stagex.toFront();
        }
    }
    @FXML
    void FriendApplication(ActionEvent event) {
        System.out.println("好友申请");
        LoginButton.hall.switchToPage3();
    }

    @FXML
    void addGroupMember(ActionEvent event) {
        System.out.println("邀请人进群");
    }

    @FXML
    void groupData(ActionEvent event) {
        System.out.println("群资料");
    }

    @FXML
    void createGroup(ActionEvent event) {
        System.out.println("创建群聊");
    }

    @FXML
    void searchGroup(ActionEvent event) {
        System.out.println("搜索群聊");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
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
    @FXML
    void send(ActionEvent event) {

    }

    @FXML
    void onEmojis(ActionEvent event) {

    }

    @FXML
    void onFile(ActionEvent event) {

    }

    @FXML
    void onImage(ActionEvent event) {

    }
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
