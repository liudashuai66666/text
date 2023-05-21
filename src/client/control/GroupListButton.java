package client.control;

import application.*;
import client.tool.FriendListDelete;
import client.view.DeleteGroupUser;
import client.view.FriendData;
import client.view.HallFace;
import client.vo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
    public int level;
    public AnchorPane getCellPane() {
        Pane.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.SECONDARY){
                MenuItem quit=new MenuItem("退出群聊");
                quit.setOnAction((ActionEvent event1)->{
                    choseGroupUser.groupUser.clear();
                    choseGroupUser.groupUser.add(User1.user);
                    try {
                        GroupUserApplication shuju=new GroupUserApplication(Group.group,choseGroupUser.groupUser);
                        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("删除成员", shuju));
                        DeleteGroupUser.stagex.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                ContextMenu contextMenu=new ContextMenu();
                if(group.getGroup_level()!=1){
                    contextMenu.getItems().addAll(quit);
                    contextMenu.show(Avatar,event.getScreenX(),event.getScreenY());
                }
            }
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
