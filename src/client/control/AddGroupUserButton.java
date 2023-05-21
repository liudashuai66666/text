
package client.control;
import application.AllApplication;
import application.GroupApplication;
import application.GroupUserApplication;
import application.MemoryUserApplication;
import client.controlList.AddGroupUserCall;
import client.controlList.CreateGroupListCell;
import client.view.AddGroupUser;
import client.view.CreateGroup;
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

public class AddGroupUserButton implements Initializable {

    @FXML
    private Button noCreateButton;

    @FXML
    private Button okCreateButton;

    @FXML
    private ListView<MemoryUserApplication> list;

    @FXML
    void noCreate(ActionEvent event) {
        if(AddGroupUser.stagex.isShowing()){
            AddGroupUser.stagex.close();
        }
    }
    @FXML
    void okCreate(ActionEvent event) throws IOException {
        if(!choseGroupUser.groupUser.isEmpty()){
            GroupApplication shuju=Group.group;
            GroupUserMap.groupUser.get(shuju.getGroup_id()).addAll(choseGroupUser.groupUser);
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            GroupUserApplication data=new GroupUserApplication(shuju,choseGroupUser.groupUser);
            oos.writeObject(new AllApplication<>("邀请成员",data));
            choseGroupUser.groupUser.clear();
            AddGroupUser.stagex.close();
        }else{
            System.out.println("请选择成员");
        }
    }
    public void flush(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(GroupList.groupList!=null){
                    list.getItems().clear();
                    Stream<MemoryUserApplication> stream1 = FriendList.friendList.stream();
                    Stream<MemoryUserApplication> filteredStream = stream1.filter(app -> !GroupUserMap.groupUser.get(Group.group.getGroup_id()).stream().anyMatch(app2 -> app.getMailbox().equals(app2.getMailbox())));
                    ArrayList<MemoryUserApplication> result = filteredStream.collect(Collectors.toCollection(ArrayList::new));
                    list.getItems().addAll(result);
                    list.refresh();
                }
            }
        });
    }//刷新好友列表
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setCellFactory(param -> new AddGroupUserCall());
        flush();
    }
}
