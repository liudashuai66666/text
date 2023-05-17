package client.control;

import application.AddFriendApplication;
import application.AllApplication;
import application.GroupApplication;
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

public class GroupListButton {
    @FXML
    private AnchorPane Pane;
    @FXML
    private ImageView Avatar;
    @FXML
    private Text Name;
    private Function<Void, Void> clickEvent;
    private GroupApplication group;//列表的用户数据；
    //public static int level;//自己在该群聊里面的身份
    public AnchorPane getCellPane() {
        Pane.setOnMouseClicked(event -> {
            if (clickEvent != null) {
                clickEvent.apply(null);
            }
        });
        return Pane;
    }
    public void setGroupList(GroupApplication group){
        this.group=group;
        Avatar.setImage(new Image(group.getGroup_avatar()));
        Name.setText(group.getGroup_name());
    }
    public void setClickEvent(Function<Void, Void> clickEvent) {
        this.clickEvent = clickEvent;
    }
}
