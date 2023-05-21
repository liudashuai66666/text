
package client.control;

import application.AllApplication;
import application.GroupApplication;
import application.GroupUserApplication;
import application.MemoryUserApplication;
import client.controlList.AddGroupUserCall;
import client.controlList.CreateGroupListCell;
import client.controlList.DeleteGroupUserCall;
import client.controlList.GroupMemberCell;
import client.view.AddGroupUser;
import client.view.CreateGroup;
import client.view.DeleteGroupUser;
import client.view.HallFace;
import client.vo.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import toolkind.XueHua;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteGroupUserButton implements Initializable {

    @FXML
    private Button noCreateButton;

    @FXML
    private Button okCreateButton;

    @FXML
    private Button okSetButton;

    @FXML
    private ListView<MemoryUserApplication> list;

    public void delete(){
        okCreateButton.setVisible(true);
        okSetButton.setVisible(false);
    }
    public void set(){
        okCreateButton.setVisible(false);
        okSetButton.setVisible(true);
    }
    @FXML
    void noCreate(ActionEvent event) {
        if (DeleteGroupUser.stagex.isShowing()) {
            DeleteGroupUser.stagex.close();
        }
    }
    @FXML
    void okCreate(ActionEvent event) throws IOException {
        if (!choseGroupUser.groupUser.isEmpty()) {
            GroupApplication shuju = Group.group;
            GroupUserApplication data = new GroupUserApplication(shuju, choseGroupUser.groupUser);
            ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
            oos.writeObject(new AllApplication<>("删除成员", data));
            DeleteGroupUser.stagex.close();
        } else {
            System.out.println("请选择成员");
        }
    }
    @FXML
    void okSet(ActionEvent event) throws IOException {
        if (!choseGroupUser.groupUser.isEmpty()) {
            GroupApplication shuju = Group.group;
            GroupUserApplication data = new GroupUserApplication(shuju, choseGroupUser.groupUser);
            ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
            oos.writeObject(new AllApplication<>("设置管理员", data));
            DeleteGroupUser.stagex.close();
        } else {
            System.out.println("请选择成员");
        }
    }

    public void flush() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (GroupList.groupList != null) {
                    list.getItems().clear();
                    Stream<MemoryUserApplication> stream = GroupUserMap.groupUser.get(Group.group.getGroup_id()).stream();
                    Stream<MemoryUserApplication> filteredStream = stream.filter(app -> app.getLevel() > User.level);
                    ArrayList<MemoryUserApplication> result = filteredStream.collect(Collectors.toCollection(ArrayList::new));
                    for (MemoryUserApplication m : result) {
                        System.out.println(m.getUname());
                    }
                    list.getItems().addAll(result);
                    list.refresh();
                }
            }
        });
    }//刷新好友列表

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setCellFactory(param -> new DeleteGroupUserCall());
        flush();
    }
}
