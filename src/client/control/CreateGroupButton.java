package client.control;

import application.AllApplication;
import application.GroupApplication;
import application.GroupUserApplication;
import application.MemoryUserApplication;
import client.controlList.CreateGroupListCell;
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

public class CreateGroupButton implements Initializable {

    @FXML
    private Button noCreateButton;

    @FXML
    private Button okCreateButton;

    @FXML
    private ListView<MemoryUserApplication> list;
    public static ArrayList<MemoryUserApplication> groupUser = new ArrayList<>();

    @FXML
    void noCreate(ActionEvent event) {
        if(CreateGroup.stagex.isShowing()){
            CreateGroup.stagex.close();
        }
    }
    @FXML
    void okCreate(ActionEvent event) throws IOException {
        if(!groupUser.isEmpty()){
            User1.user.setLevel(1);
            groupUser.add(User1.user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            GroupApplication shuju=new GroupApplication();
            shuju.setGroup_id(String.valueOf(XueHua.id()));
            shuju.setGroup_name(User.uname+"创建的群聊");
            shuju.setGroup_avatar(User.avatar);
            shuju.setGroup_time(time);
            shuju.setGroup_level(1);
            GroupList.groupList.add(shuju);
            HallFace.groupChatButton.flush();
            GroupUserMap.groupUser.put(shuju.getGroup_id(),groupUser);
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            GroupUserApplication data=new GroupUserApplication(shuju,groupUser);
            oos.writeObject(new AllApplication<>("创建群聊",data));
            CreateGroup.stagex.close();
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
                    list.getItems().addAll(FriendList.friendList);
                    list.refresh();
                }
            }
        });
    }//刷新好友列表
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setCellFactory(param -> new CreateGroupListCell());
        flush();
    }
}
