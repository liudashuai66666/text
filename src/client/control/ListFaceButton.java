package client.control;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.function.Function;

public class ListFaceButton {
    @FXML
    private AnchorPane Pane;
    @FXML
    private ImageView Avatar;
    @FXML
    private Text Name;
    private Function<Void, Void> clickEvent;
    public void setImage(String imageUrl) {
        Avatar.setImage(new Image(imageUrl));
    }
    public void setUname(String uname) {
        Name.setText(uname);
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
