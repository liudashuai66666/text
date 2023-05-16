package client.control;

import application.GroupApplication;
import client.view.HallFace;
import client.vo.Friend;
import client.vo.FriendChatList;
import client.vo.FriendList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import toolkind.Friends;

import java.io.IOException;

public class GroupListCell extends ListCell<GroupApplication> {
    private FXMLLoader fxmlLoader;

    public GroupListCell() {
    }


    @Override
    protected void updateItem(GroupApplication item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../viewfxml/GroupList.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            GroupListButton groupListButton = fxmlLoader.getController();
            groupListButton.setGroupList(item);
            groupListButton.setClickEvent(unused -> {//点击事件
                HallFace.groupChatButton.startChat(item);
                System.out.println("你在"+item.getGroup_name()+"聊天");
                return null;
            });
            setText(null);
            setGraphic(groupListButton.getCellPane());
        }
    }
}