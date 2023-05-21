package client.controlList;

import client.control.ListFaceButton;
import client.view.HallFace;
import client.vo.Friend;
import client.vo.FriendChatList;
import client.vo.FriendList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import toolkind.Friends;

import java.io.IOException;

public class FriendListCell extends ListCell<Friends> {
    private FXMLLoader fxmlLoader;

    public FriendListCell() {
    }


    @Override
    protected void updateItem(Friends item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../viewfxml/ListFace.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ListFaceButton listFaceButton = fxmlLoader.getController();
            System.out.println(item.getUser().getOnline_status());
            listFaceButton.setListView(item);
            listFaceButton.setClickEvent(unused -> {//点击事件
                System.out.println(item.getUser().getUname()+"在和你聊天");
                try {
                    Friend.friend=item.getUser();
                    if(FriendChatList.map.get(Friend.friend.getMailbox())==null){
                        HallFace.hallButton.beginChat(item.getUser());
                    }else{
                        HallFace.hallButton.flushChat();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });
            setText(null);
            setGraphic(listFaceButton.getCellPane());
        }
    }
}