package client.control;

import application.GroupChatData;
import application.ImageApplication;
import application.MemoryUserApplication;
import client.view.FriendData;
import client.view.PersonalData;
import client.vo.Friend;
import client.vo.User;
import com.sun.org.apache.bcel.internal.generic.FSUB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import application.ChatData;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatDataListButton implements Initializable {

    @FXML
    private ImageView myImage;//我的头像

    @FXML
    private ImageView friendImage;//朋友的头像

    @FXML
    private TextFlow myMessage;//我发的消息

    @FXML
    private TextFlow friendMessage;//朋友发的消息

    @FXML
    private AnchorPane Pane1;//背景布

    @FXML
    private Label sendTime;//发送时间

    @FXML
    private Button friendDataButton;//看朋友资料按钮

    @FXML
    private Button myDataButton;//看自己资料按钮，可以修改资料
    private ChatData chatData;
    private GroupChatData groupChatData;

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
        this.chatData=data;
        if(data.getSendUser().equals(User.mailbox)){
            friendDataButton.setVisible(false);
            friendImage.setVisible(false);
            friendMessage.setVisible(false);
            myDataButton.setVisible(true);
            myImage.setVisible(true);
            myMessage.setVisible(true);
            myImage.setImage(new Image(User.avatar));
            //
            myMessage.getChildren().clear();
            setMessage(myMessage,data.getMessage());
            //
            sendTime.setText(data.getSendTime());
        }else if(data.getSendUser().equals(Friend.friend.getMailbox())){
            myDataButton.setVisible(false);
            myImage.setVisible(false);
            myMessage.setVisible(false);
            friendDataButton.setVisible(true);
            friendImage.setVisible(true);
            friendMessage.setVisible(true);
            friendImage.setImage(new Image(Friend.friend.getAvatar()));
            //
            friendMessage.getChildren().clear();
            setMessage(friendMessage,data.getMessage());
            //
            sendTime.setText(data.getSendTime());
        }
    }
    public void setGroupChat(GroupChatData shuju){
        groupChatData=shuju;
        if(shuju.getSender().getMailbox().equals(User.mailbox)){
            friendDataButton.setVisible(false);
            friendImage.setVisible(false);
            friendMessage.setVisible(false);
            myDataButton.setVisible(true);
            myImage.setVisible(true);
            myMessage.setVisible(true);
            myImage.setImage(new Image(User.avatar));
            //
            myMessage.getChildren().clear();
            setGroupMessage(myMessage,shuju.getSendMessage());
            //
            sendTime.setText(shuju.getSendTime());
        }else{
            myDataButton.setVisible(false);
            myImage.setVisible(false);
            myMessage.setVisible(false);
            friendDataButton.setVisible(true);
            friendImage.setVisible(true);
            friendMessage.setVisible(true);
            friendImage.setImage(new Image(shuju.getSender().getAvatar()));
            //
            friendMessage.getChildren().clear();
            setGroupMessage(friendMessage, shuju.getSendMessage());
            //
            sendTime.setText(shuju.getSendTime());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myMessage.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            Pane1.setPrefWidth(newValue.getWidth()-15);
            Pane1.setPrefHeight(newValue.getHeight() + 70);
        });
        friendMessage.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            Pane1.setPrefWidth(newValue.getWidth()-15);
            Pane1.setPrefHeight(newValue.getHeight() + 70);
        });
        //自适应大小
    }
    public void setMessage(TextFlow textFlow,String message){
        if(chatData.getMessage_type().equals("文本")){
            String weizhi="file:D:\\QQ\\2385272606\\FileRecv\\静态\\";
            String regex="\\[([^\\[\\]]+)\\]|([^\\[\\]]+)";//正则表达式
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher=pattern.matcher(message);
            while(matcher.find()){
                String matchText = matcher.group();
                if(matchText.matches("\\[(.*?)\\]")){
                    String imageName=matchText.substring(1, matchText.length()-1);
                    //判断该表情包存在不
                    ImageView imageView=new ImageView(new Image(weizhi+imageName,30,30,false,true));
                    textFlow.getChildren().add(imageView);
                }else {
                    Text text =new Text(matchText);
                    text.setFont(new Font(20));
                    textFlow.getChildren().add(text);
                }
            }
        } else if (chatData.getMessage_type().equals("图片")) {
            String weizhi="file:D:\\图片\\";
            ImageView imageView=new ImageView(new Image(weizhi+chatData.getMessage(),160,160,false,true));
            textFlow.getChildren().add(imageView);
        }
    }
    public void setGroupMessage(TextFlow textFlow,String message){
        friendDataButton.setVisible(false);
        if(groupChatData.getMessageType().equals("文本")){
            String weizhi="file:D:\\QQ\\2385272606\\FileRecv\\静态\\";
            String regex="\\[([^\\[\\]]+)\\]|([^\\[\\]]+)";//正则表达式
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher=pattern.matcher(message);
            while(matcher.find()){
                String matchText = matcher.group();
                if(matchText.matches("\\[(.*?)\\]")){
                    String imageName=matchText.substring(1, matchText.length()-1);
                    //判断该表情包存在不
                    ImageView imageView=new ImageView(new Image(weizhi+imageName,30,30,false,true));
                    textFlow.getChildren().add(imageView);
                }else {
                    Text text =new Text(matchText);
                    text.setFont(new Font(20));
                    textFlow.getChildren().add(text);
                }
            }
        } else if (groupChatData.getMessageType().equals("图片")) {
            String weizhi="file:D:\\图片\\";
            ImageView imageView=new ImageView(new Image(weizhi+groupChatData.getSendMessage(),160,160,false,true));
            textFlow.getChildren().add(imageView);
        }
    }
}
