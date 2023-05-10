package client.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import application.ChatData;

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
            setText(null);
            setGraphic(chatDataListButton.getCellPane());
        }
    }
}