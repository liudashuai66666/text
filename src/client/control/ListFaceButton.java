package client.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.function.Function;

public class ListFaceButton {
    @FXML
    private Text stateText;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane Pane;

    @FXML
    private ImageView Avatar;

    @FXML
    private Text Name;

    @FXML
    private Button noButton;

    private Function<Void, Void> clickEvent;

    @FXML
    void ok(ActionEvent event) {

    }

    @FXML
    void no(ActionEvent event) {

    }
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
    public void friend(){
        okButton.setOpacity(0);
        noButton.setOpacity(0);
        okButton.setDisable(true);
        noButton.setDisable(true);
        stateText.setDisable(true);
    }
    public void newFriend1(){
        okButton.setOpacity(0);
        noButton.setOpacity(0);
        okButton.setDisable(true);
        noButton.setDisable(true);
        stateText.setDisable(true);
        stateText.setText("待处理");
    }
    public void newFriend2(){
        stateText.setDisable(true);
    }
}
