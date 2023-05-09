package client.control;

import application.AddFriendApplication;
import application.AllApplication;
import application.MemoryUserApplication;
import client.tool.FriendListDelete;
import client.view.FriendData;
import client.view.HallFace;
import client.vo.FindUser;
import client.vo.FriendList;
import client.vo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import toolkind.Friends;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Function;

public class ListFaceButton {
    @FXML
    private Text stateText;

    @FXML
    private Button noButton;
    @FXML
    private Button okButton;

    @FXML
    private AnchorPane Pane;

    @FXML
    private ImageView Avatar;

    @FXML
    private Text Name;


    private Function<Void, Void> clickEvent;
    private MemoryUserApplication friends;//列表的用户数据；

    @FXML
    void ok(ActionEvent event) throws IOException {
        //点击确定后要干三件事
        //1.将请求列表里面的那条数据删除，在数据库中将数据为3的变为1;
        //2.将同意的好友添加到自己的好友列表
        //3.将发送人的请求列表里面的那条待处理数据删除
        //4.在发送人的好友例表里面添加自己
        AddFriendApplication shuju = new AddFriendApplication(friends.getAccount(), User.account,friends.getMailbox(),User.mailbox);
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("同意",shuju));
        FriendList.friendList.add(friends);
        FriendListDelete.delete(FriendList.newFriendList2,friends.getAccount());
        //
        HallFace.hallButton.flush();
        HallFace.newFriendButton.flush();
    }

    @FXML
    void no(ActionEvent event) throws IOException {
        //拒绝就是将数据库的那条数据改为2,然后自己删除这条请求，别人那边的请求编程记录，已经拒绝；
        AddFriendApplication shuju = new AddFriendApplication(friends.getAccount(), User.account,friends.getMailbox(),User.mailbox);
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("拒绝",shuju));
        FriendListDelete.delete(FriendList.newFriendList2, friends.getAccount());
        //
        HallFace.newFriendButton.flush();
    }

    public void setListView(Friends friends){
        MemoryUserApplication user = friends.getUser();
        this.friends=user;
        Avatar.setImage(new Image(user.getAvatar()));
        Name.setText(user.getUname());
        if(friends.getFlag().equals("好友")){
            okButton.setOpacity(0);
            noButton.setOpacity(0);
            okButton.setDisable(true);
            noButton.setDisable(true);
            stateText.setDisable(true);
        } else if (friends.getFlag().equals("发送")) {
            okButton.setOpacity(0);
            noButton.setOpacity(0);
            okButton.setDisable(true);
            noButton.setDisable(true);
            stateText.setDisable(true);
            stateText.setText("待对方处理中");
        } else if (friends.getFlag().equals("接收")) {
            stateText.setDisable(true);
        } else if (friends.getFlag().equals("被拒绝")) {
            okButton.setOpacity(0);
            noButton.setOpacity(0);
            okButton.setDisable(true);
            noButton.setDisable(true);
            stateText.setDisable(true);
            stateText.setText("对方拒绝了你");
        }
    }
    public AnchorPane getCellPane() {
        Pane.setOnMouseClicked(event -> {
            if (clickEvent != null) {
                clickEvent.apply(null);
            }
        });
        return Pane;
    }
    public void setClickEvent(Function<Void, Void> clickEvent) {
        this.clickEvent = clickEvent;
    }
}
