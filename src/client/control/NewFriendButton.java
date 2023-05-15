package client.control;

import application.MemoryUserApplication;
import client.view.AddFriend;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class NewFriendButton implements Initializable {
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
    private ListView<Friends> ChatList;
    @FXML
    private Button FriendApplicationButton;

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
    }
    public void flush(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ChatList.getItems().clear();
                ArrayList<Friends> data2=new ArrayList<>();
                for (MemoryUserApplication m : FriendList.newFriendList3) {
                    Friends friends=new Friends("被拒绝",m);
                    data2.add(friends);
                }
                ChatList.getItems().addAll(data2);
                ArrayList<Friends> data=new ArrayList<>();
                for (MemoryUserApplication m : FriendList.newFriendList1) {
                    Friends friends=new Friends("发送",m);
                    data.add(friends);
                }
                ChatList.getItems().addAll(data);
                ArrayList<Friends> data1=new ArrayList<>();
                for (MemoryUserApplication m : FriendList.newFriendList2) {
                    Friends friends=new Friends("接收",m);
                    data1.add(friends);
                    System.out.println(friends.getUser().getUname()+"想加你");
                }
                ChatList.getItems().addAll(data1);
                ChatList.refresh();
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
        ChatList.setCellFactory(param -> new FriendListCell());
        flush();
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
        }, 500, 500);//定时器的延迟时间及间隔时间
    }
}
