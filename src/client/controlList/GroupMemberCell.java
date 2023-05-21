package client.controlList;

import application.ChatData;
import application.MemoryUserApplication;
import client.control.ChatDataListButton;
import client.control.GroupChatButton;
import client.control.GroupMemberButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class GroupMemberCell extends ListCell<MemoryUserApplication> {
    private FXMLLoader fxmlLoader;

    public GroupMemberCell(){

    }

    @Override
    protected void updateItem(MemoryUserApplication item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../viewfxml/GroupMember.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            GroupMemberButton groupMemberButton = fxmlLoader.getController();
            groupMemberButton.setFace(item);
            setText(null);
            setGraphic(groupMemberButton.getCellPane());
        }
    }
}
