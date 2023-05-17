package client.control;

import application.MemoryUserApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.function.Function;

public class CreateGroupListButton {

    @FXML
    private AnchorPane Pane;

    @FXML
    private ImageView Avatar;

    @FXML
    private Text Name;

    @FXML
    private CheckBox selectButton;
    private Function<Void, Void> clickEvent;
    private MemoryUserApplication friends;//列表的用户数据；
    private int cnt=0;

    @FXML
    void select(ActionEvent event) {
        cnt++;
        if(cnt%2==1){
            CreateGroupButton.groupUser.add(friends);
        }else{
            CreateGroupButton.groupUser.remove(friends);
        }
    }
    public void jiemian(MemoryUserApplication user){
        friends=user;
        friends.setLevel(3);
        Avatar.setImage(new Image(friends.getAvatar()));
        Name.setText(friends.getUname());
    }

    public AnchorPane getCellPane() {
        Pane.setOnMouseClicked(event -> {
            if (clickEvent != null) {
                clickEvent.apply(null);
            }
        });
        return Pane;
    }
    public void setClickEvent(Function<Void, Void> clickEvent) {
        this.clickEvent = clickEvent;
    }
}
