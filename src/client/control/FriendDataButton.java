package client.control;

import client.vo.FindUser;
import client.vo.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FriendDataButton implements Initializable {

    @FXML
    private Label Uname;

    @FXML
    private Label Account;

    @FXML
    private Label Mailbox;

    @FXML
    private Label Sex;

    @FXML
    private Label Signature;

    @FXML
    private Label Birthday;

    @FXML
    private ImageView Avatar;

    @FXML
    private Label Age;

    @FXML
    private Label OnOffLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Uname.setText(FindUser.uname);
        Account.setText(FindUser.account);
        Sex.setText(FindUser.sex);
        Signature.setText(FindUser.signature);
        Birthday.setText(FindUser.birthday);
        Age.setText(FindUser.age);
        Mailbox.setText(FindUser.mailbox);
        if(FindUser.avatar!=null) {
            Avatar.setImage(new Image(FindUser.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
    }
}
