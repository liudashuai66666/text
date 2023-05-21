package client.controlList;

import application.MemoryUserApplication;
import client.control.AddGroupUserButton;
import client.control.CreateGroupListButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class AddGroupUserCall extends ListCell<MemoryUserApplication> {
    private FXMLLoader fxmlLoader;

    public AddGroupUserCall() {
    }


    @Override
    protected void updateItem(MemoryUserApplication item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../viewfxml/CreateGroupList.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            CreateGroupListButton createGroupListButton = fxmlLoader.getController();
            createGroupListButton.jiemian(item);
            createGroupListButton.setClickEvent(unused -> {//点击事件
                //HallFace.groupChatButton.startChat(item);
                System.out.println("选择了他");
                return null;
            });
            setText(null);
            setGraphic(createGroupListButton.getCellPane());
        }
    }
}
