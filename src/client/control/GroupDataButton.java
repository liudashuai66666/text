package client.control;

import application.AllApplication;
import application.GroupApplication;
import application.ReviseAvatarApplication;
import client.view.GroupData;
import client.vo.Group;
import client.vo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupDataButton implements Initializable {

    @FXML
    private TextArea groutData;//群简介

    @FXML
    private TextField groupName;//群名

    @FXML
    private Text groupID;//群ID

    @FXML
    private Button noChangeButton;//取消

    @FXML
    private Button okChangeButton;//确定
    @FXML
    private ImageView groupAvatar;//群头像

    @FXML
    private Button imageButton;//改变头像按钮
    public static File selectedFile;

    @FXML
    void noChange(ActionEvent event) {
        GroupData.stagex.close();
    }

    @FXML
    void okChange(ActionEvent event) throws IOException {
        Group.group.setGroup_name(groupName.getText());
        Group.group.setGroup_data(groutData.getText());
        Group.group.setGroup_avatar(groupAvatar.getImage().impl_getUrl());
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("修改群资料",Group.group));
        GroupData.stagex.close();
    }

    @FXML
    void imageButton(ActionEvent event) {
        System.out.println("换头像中...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");//设置该窗口的名字
        fileChooser.setInitialDirectory(new File("D:\\IDEA liu_da_shuai\\Q_Q\\src\\client\\photo\\avatar"));//打开指定位置的文件夹
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG","*.png","*.jpg","*.gif"));//用来指定文件类型
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("您选择的文件是：" + selectedFile.getAbsolutePath());
            groupAvatar.setImage(new Image("file:"+selectedFile.getAbsolutePath()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupID.setText(Group.group.getGroup_id());
        groupName.setText(Group.group.getGroup_name());
        groutData.setText(Group.group.getGroup_data());
        groupAvatar.setImage(new Image(Group.group.getGroup_avatar()));
    }
}
