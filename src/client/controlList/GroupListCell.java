package client.controlList;

import application.AllApplication;
import application.GroupApplication;
import application.MemoryUserApplication;
import client.control.GroupListButton;
import client.view.HallFace;
import client.vo.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import toolkind.Friends;

import java.io.IOException;
import java.io.ObjectOutputStream;

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
                if(item.getGroup_level()==1){
                    HallFace.groupChatButton.qunzhu();
                } else if (item.getGroup_level() == 2) {
                    HallFace.groupChatButton.gly();
                } else if (item.getGroup_level() == 3) {
                    HallFace.groupChatButton.chengyuan();
                }
                Group.group=item;//记住当前的群聊
                HallFace.groupChatButton.level=item.getGroup_level();
                try {
                    //1.先传成员列表
                    if(GroupUserMap.groupUser.get(item.getGroup_id())==null){
                        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("群聊成员",item));
                    }else{
                        Group.user=GroupUserMap.groupUser.get(item.getGroup_id());//记住当前群聊的用户列表
                        HallFace.groupChatButton.flushUser();
                    }
                    //2.再传消息列表
                    if(GroupUserMap.groupChatDataMap.get(item.getGroup_id())==null){
                        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("群聊消息",item));
                    }else{
                        HallFace.groupChatButton.flushChat();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HallFace.groupChatButton.startChat(item);
                System.out.println("你在"+Group.group.getGroup_name()+"里聊天");
                return null;
            });
            setText(null);
            setGraphic(groupListButton.getCellPane());
        }
    }
}