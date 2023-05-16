package client.control;

import application.AllApplication;
import application.FindFriendApplication;
import application.GroupApplication;
import application.MemoryUserApplication;
import client.view.AddFriend;
import client.view.HallFace;
import client.view.PersonalData;
import client.vo.FindUser;
import client.vo.User;
import client.vo.GroupList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GroupChatButton implements Initializable {
    @FXML
    private Button groupDataButton;//群聊资料按钮
    @FXML
    private Button FriendsButton;//切换好友聊天
    @FXML
    private Button addFriendButton;//添加好友
    @FXML
    private Button GroupChatButton;//切换群聊按钮
    @FXML
    private Button MyMessageButton;//个人资料按钮
    @FXML
    private ImageView Avatar;//头像
    @FXML
    private Button createGroupButton;//创建群聊按钮

    @FXML
    private Button searchGroupButton;//搜索群聊按钮
    @FXML
    private TextField InputBox;//搜索群聊框
    @FXML
    private Button addGroupMemberButton;//添加群聊成员按钮
    @FXML
    private AnchorPane Pane;//遮挡板
    @FXML
    private Button emojis;//表情包

    @FXML
    private Button image;//图片发送

    @FXML
    private Button sendButton;//发送信息按钮
    @FXML
    private ListView<GroupApplication> ChatList;//群聊链表，到时候不能用Friend
    @FXML
    private ListView<?> ChatMessageList;//聊天记录
    @FXML
    private Button FriendApplicationButton;//切换好友申请按钮
    @FXML
    void GroupChat(ActionEvent event) {
        System.out.println("群聊");
        Pane.setVisible(true);
        HallFace.groupChatButton.flush();
    }

    @FXML
    void Friends(ActionEvent event) {
        System.out.println("私聊");
        Pane.setVisible(true);
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
        Pane.setVisible(true);
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
    }//搜索群聊
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChatList.setCellFactory(param -> new GroupListCell());
        Avatar.setImage(new Image(User.avatar));
        flushed();
    }//初始化函数
    @FXML
    void MyMessage(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }//个人资料
    @FXML
    void send(ActionEvent event) {

    }//发送消息

    @FXML
    void onEmojis(ActionEvent event) {

    }//表情包

    @FXML
    void onFile(ActionEvent event) {

    }//文件

    @FXML
    void onImage(ActionEvent event) {

    }//图片
    public void flush(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(GroupList.groupList!=null){
                    ChatList.getItems().clear();
                    ChatList.getItems().addAll(GroupList.groupList);
                    ChatList.refresh();
                }
            }
        });
    }//刷新好友列表
    public void startChat(GroupApplication shuju){
        Pane.setVisible(false);
        groupDataButton.setText(shuju.getGroup_name());
    }//开始聊天
    protected void flushed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Avatar.setImage(new Image(User.avatar));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 1000);//定时器的延迟时间及间隔时间
    }
}
