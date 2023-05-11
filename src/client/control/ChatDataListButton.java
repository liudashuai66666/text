package client.control;

import application.MemoryUserApplication;
import client.view.FriendData;
import client.view.PersonalData;
import client.vo.Friend;
import client.vo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import application.ChatData;
import javafx.stage.Stage;

import java.util.function.Function;

public class ChatDataListButton {

    @FXML
    private ImageView myImage;//我的头像

    @FXML
    private ImageView friendImage;//朋友的头像

    @FXML
    private Text myMessage;//我发的消息

    @FXML
    private Text friendMessage;//朋友发的消息

    @FXML
    private AnchorPane Pane1;//背景布

    @FXML
    private Label sendTime;//发送时间

    @FXML
    private Button friendDataButton;//看朋友资料按钮

    @FXML
    private Button myDataButton;//看自己资料按钮，可以修改资料
    private Function<Void, Void> clickEvent;
    @FXML
    void friendData(ActionEvent event) throws Exception {
        FriendData friendData=new FriendData();
        if(!FriendData.stagex.isShowing()){
            friendData.start(new Stage());
            FriendData.friendDataButton.flush(Friend.friend,"好友");
        }else{
            FriendData.stagex.close();
            friendData.start(new Stage());
            FriendData.friendDataButton.flush(Friend.friend,"好友");
            FriendData.stagex.toFront();
        }
    }

    @FXML
    void myData(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }
    public void setChatDataListView(ChatData data){
        if(data.getSendUser().equals(User.mailbox)){
            friendImage.setVisible(false);
            friendMessage.setVisible(false);
            myImage.setVisible(true);
            myMessage.setVisible(true);
            myImage.setImage(new Image(User.avatar));
            myMessage.setText(data.getMessage());
            sendTime.setText(data.getSendTime());
        }else if(data.getSendUser().equals(Friend.friend.getMailbox())){
            myImage.setVisible(false);
            myMessage.setVisible(false);
            friendImage.setVisible(true);
            friendMessage.setVisible(true);
            friendImage.setImage(new Image(Friend.friend.getAvatar()));
            friendMessage.setText(data.getMessage());
            sendTime.setText(data.getSendTime());
        }
    }
    public AnchorPane getCellPane() {
        Pane1.setOnMouseClicked(event -> {
            if (clickEvent != null) {
                clickEvent.apply(null);
            }
        });
        return Pane1;
    }
    public void setClickEvent(Function<Void, Void> clickEvent) {
        this.clickEvent = clickEvent;
    }
}
