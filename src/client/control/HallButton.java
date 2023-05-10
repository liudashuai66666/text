package client.control;

import application.*;
import client.view.AddFriend;
import client.view.HallFace;
import client.view.PersonalData;
import client.vo.*;
import application.ChatData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import toolkind.Friends;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

//大厅
public class HallButton implements Initializable {
    @FXML
    private Button emojis;//表情包按钮
    @FXML
    private Button image;//图片按钮
    @FXML
    private TextArea inputBox;//输入框
    @FXML
    private Button sendButton;//发送按钮
    @FXML
    private ListView<ChatData> chatDataList;//聊天列表
    @FXML
    private Button file;//文件按钮
    @FXML
    private AnchorPane calico;//遮挡板
    private MemoryUserApplication friend;//该聊天界面的聊天对象


    @FXML
    private Button FriendsButton;//切换私聊按钮
    @FXML
    private Button addFriendButton;//添加好友按钮
    @FXML
    private Button GroupChatButton;//切换群聊画面按钮

    @FXML
    private Button MyMessageButton;//查看个人资料按钮
    @FXML
    private ImageView Avatar;//自己头像
    @FXML
    private Button FriendApplicationButton;//切换好友申请面板按钮

    @FXML
    private ListView<Friends> ChatList;//好友列表

    public ListView<Friends> getChatList() {
        return ChatList;
    }

    public void setChatList(ListView<Friends> chatList) {
        ChatList = chatList;
    }

    @FXML
    void GroupChat(ActionEvent event) {
        System.out.println("群聊");
        LoginButton.hall.switchToPage2();
    }//切换群聊方法
    @FXML
    void FriendApplication(ActionEvent event) throws IOException {
        System.out.println("好友申请");
        LoginButton.hall.switchToPage3();
    }//切换好友申请面板方法
    @FXML
    void Friends(ActionEvent event) {
        calico.setVisible(true);
        System.out.println("私聊");
    }//切换私聊方法

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
    }//添加好友方法
    @FXML
    void onEmojis(ActionEvent event) {

    }//发送表情包

    @FXML
    void onFile(ActionEvent event) {

    }//发送文件

    @FXML
    void onImage(ActionEvent event) {

    }//发送图片

    @FXML
    void send(ActionEvent event) throws IOException {
        //发送消息
        if(inputBox.getText().equals("")){
            return;
        }
        String massage=inputBox.getText();
        inputBox.clear();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        ChatData shuju = new ChatData(massage,time,User.mailbox,Friend.friend.getMailbox(), "文本");
        ObjectOutputStream oos =new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("发消息",shuju));
        FriendChatList.map.get(Friend.friend.getAccount()).add(shuju);
        HallFace.hallButton.flushChat();
    }//发送消息
    public void flushChat(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                calico.setVisible(false);
                chatDataList.getItems().clear();
                chatDataList.getItems().addAll(FriendChatList.map.get(Friend.friend.getAccount()));
            }
        });
    }
    public void flush(){
        ChatList.getItems().clear();
        ArrayList<Friends> data=new ArrayList<>();
        for (MemoryUserApplication m : FriendList.friendList) {
            Friends friends=new Friends("好友",m);
            data.add(friends);
        }
        ChatList.getItems().addAll(data);
        ChatList.refresh();
    }//刷新好友列表
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextSetOnKey();
        sendButton.setDefaultButton(true);
        ChatList.setCellFactory(param -> new FriendListCell());
        chatDataList.setCellFactory(param -> new messageListCell());
        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
        flush();
        flushed();
    }//界面初始化
    @FXML
    void MyMessage(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }//查看个人资料


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
    }//线程刷新头像

    public void beginChat(MemoryUserApplication user) throws IOException {
        calico.setVisible(false);
        AddFriendApplication shuju=new AddFriendApplication(User.account,user.getAccount(),User.mailbox, user.getMailbox());
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("开始聊天",shuju));

    }//开始聊天

    public MemoryUserApplication getFriend() {
        return friend;
    }

    public void setFriend(MemoryUserApplication friend) {
        this.friend = friend;
    }

    public void TextSetOnKey(){
        inputBox.setOnKeyPressed(event -> {
            if(event.isControlDown()&& event.getCode()== KeyCode.ENTER){
                //按下Ctrl+Enter，插入换行符
                inputBox.insertText(inputBox.getCaretPosition(),"\n");
            }else if(event.getCode()==KeyCode.ENTER){
                event.consume();
                try {
                    send(null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}




















