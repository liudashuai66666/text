package client.controlList;

import client.control.ChatDataListButton;
import client.view.HallFace;
import client.vo.Friend;
import client.vo.FriendChatList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import application.ChatData;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class messageListCell extends ListCell<ChatData> {
    private FXMLLoader fxmlLoader;



    @Override
    protected void updateItem(ChatData item, boolean empty) {
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
            chatDataListButton.setChatDataListView(item);
            chatDataListButton.setClickEvent(unused -> {//点击事件
                if(item.getMessage_type().equals("文件")){
                    System.out.println("打开该文件");
                    File fileToOpen=new File("D:\\client_file\\"+item.getMessage());
                    if (fileToOpen.exists()) {
                        try {
                            Desktop.getDesktop().open(fileToOpen);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("指定的文件不存在.");
                    }
                }
                return null;
            });
            setText(null);
            setGraphic(chatDataListButton.getCellPane());
        }
    }
}