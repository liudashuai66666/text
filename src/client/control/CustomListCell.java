package client.control;

import client.view.ListFace;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import toolkind.Friends;

import java.io.IOException;
import java.util.function.Function;

public class CustomListCell extends ListCell<Friends> {
    private FXMLLoader fxmlLoader;
    private String flag;

    public CustomListCell() {
    }

    public CustomListCell(String flag) {
        this.flag = flag;
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
            listFaceButton.setImage(item.getAvatar());
            listFaceButton.setUname(item.getUname());
            if(flag.equals("friend")){
                listFaceButton.friend();
            } else if (flag.equals("newFriend1")) {
                listFaceButton.newFriend1();
            }else if(flag.equals("newFriend2")){
                listFaceButton.newFriend2();
            }
            listFaceButton.setClickEvent(unused -> {//点击事件
                System.out.println("Clicked: " + item);
                return null;
            });
            setText(null);
            setGraphic(listFaceButton.getCellPane());
        }
    }
}