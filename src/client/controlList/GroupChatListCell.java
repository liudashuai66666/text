package client.controlList;

import application.AllApplication;
import application.GroupApplication;
import application.GroupChatData;
import client.control.ChatDataListButton;
import client.control.GroupListButton;
import client.view.HallFace;
import client.vo.Group;
import client.vo.GroupUserMap;
import client.vo.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import toolkind.Friends;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class GroupChatListCell extends ListCell<GroupChatData> {
    private FXMLLoader fxmlLoader;

    public GroupChatListCell() {

    }


    @Override
    protected void updateItem(GroupChatData item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../viewfxml/ChatDataList.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ChatDataListButton chatDataListButton = fxmlLoader.getController();
            chatDataListButton.setGroupChat(item);
            /*chatDataListButton.setClickEvent(unused -> {//点击事件
                return null;
            });*/
            setText(null);
            setGraphic(chatDataListButton.getCellPane());
        }
    }
}
