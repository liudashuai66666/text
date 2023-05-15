package client.control;

import application.AddFriendApplication;
import application.AllApplication;
import application.MemoryUserApplication;
import client.tool.FriendListDelete;
import client.view.FriendData;
import client.view.HallFace;
import client.vo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendDataButton {

    @FXML
    private Label Uname;

    @FXML
    private Label Account;

    @FXML
    private Label Mailbox;

    @FXML
    private Label Sex;

    @FXML
    private Label Signature;

    @FXML
    private Label Birthday;

    @FXML
    private ImageView Avatar;

    @FXML
    private Label Age;

    @FXML
    private Label OnOffLine;

    @FXML
    private Button DeleteFriendButton;//删除按钮

    @FXML
    void DeleteFriend(ActionEvent event) throws IOException {
        System.out.println("你要删除该好友!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认对话框");
        alert.setHeaderText("请确认您的操作");
        alert.setContentText("是否删除该好友！");
// 添加“确认”和“取消”按钮
        ButtonType okButton = new ButtonType("确认", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("取消", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, cancelButton);
// 设置点击回调函数
        alert.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                //1.先在自己的列表里面将该用户的数据删除，然后发送请求给服务端，在服务端里面将该条好友消息清除，然后再反馈给你删除的那个好友删除记录
                try {
                    ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
                    AddFriendApplication shuju=new AddFriendApplication(User.account,Friend.friend.getAccount(),User.mailbox,Friend.friend.getMailbox());
                    oos.writeObject(new AllApplication<>("删除好友",shuju));
                    //刷新
                    FriendListDelete.delete(FriendList.friendList,Friend.friend.getAccount());
                    HallFace.hallButton.getCalico().setVisible(true);
                    HallFace.hallButton.flush();
                    FriendData.stagex.close();
                    FriendChatList.map.get(Friend.friend.getMailbox()).clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (buttonType == cancelButton) {
                alert.close();
            }
            return null;
        });
        alert.show();

    }
    public void flush(MemoryUserApplication user,String flag){
        //DeleteFriendButton.setVisible(true);//删除好友按钮
        if(flag.equals("未添加")){
            DeleteFriendButton.setVisible(false);
        }else{
            DeleteFriendButton.setVisible(true);
        }
        Uname.setText(user.getUname());
        Account.setText(user.getAccount());
        Sex.setText(user.getSex());
        Signature.setText(user.getSignature());
        Birthday.setText(user.getBirthday());
        Age.setText(user.getAge());
        Mailbox.setText(user.getMailbox());
        Avatar.setImage(new Image(user.getAvatar()));
    }
}
