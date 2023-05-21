package client.control;

import application.AllApplication;
import application.GroupApplication;
import application.GroupUserApplication;
import application.MemoryUserApplication;
import client.view.DeleteGroupUser;
import client.vo.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.function.Function;

public class GroupMemberButton {

    @FXML
    private Text Uname;

    @FXML
    private Text Level;

    @FXML
    private AnchorPane Pane;
    private int level;
    private Function<Void, Void> clickEvent;
    MemoryUserApplication user;//群聊列表里面的用户信息
    @FXML
    private ImageView Avatar;

    public void setFace(MemoryUserApplication user) {
        this.user = user;
        Avatar.setImage(new Image(user.getAvatar()));
        Uname.setText(user.getUname());
        if (user.getLevel() == 1) {
            Level.setText("群主");
        } else if (user.getLevel() == 2) {
            Level.setText("管理员");
        } else {
            Level.setText("");
        }
    }

    public AnchorPane getCellPane() {
        for (GroupApplication m : GroupList.groupList) {
            if(m.getGroup_id().equals(Group.group.getGroup_id())){
                level=m.getGroup_level();
                break;
            }
        }
        Pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                MenuItem delete = new MenuItem("删除该成员");
                MenuItem setManager = new MenuItem("设置管理员");
                MenuItem deleteManager = new MenuItem("解除管理员");
                MenuItem transfer = new MenuItem("转让群主");
                //删除成员点击事件
                delete.setOnAction((ActionEvent event1)->{
                    choseGroupUser.groupUser.clear();
                    choseGroupUser.groupUser.add(user);
                    try {
                        GroupUserApplication shuju=new GroupUserApplication(Group.group,choseGroupUser.groupUser);
                        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("删除成员", shuju));
                        DeleteGroupUser.stagex.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //设置管理员
                setManager.setOnAction((ActionEvent event1)->{
                    choseGroupUser.groupUser.clear();
                    choseGroupUser.groupUser.add(user);
                    try {
                        GroupUserApplication shuju=new GroupUserApplication(Group.group,choseGroupUser.groupUser);
                        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("设置管理员", shuju));
                        DeleteGroupUser.stagex.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //撤销管理员
                deleteManager.setOnAction((ActionEvent event1)->{
                    choseGroupUser.groupUser.clear();
                    choseGroupUser.groupUser.add(user);
                    try {
                        GroupUserApplication shuju=new GroupUserApplication(Group.group,choseGroupUser.groupUser);
                        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("撤销管理员", shuju));
                        DeleteGroupUser.stagex.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //转让群主
                transfer.setOnAction((ActionEvent event1)->{
                    choseGroupUser.groupUser.clear();
                    choseGroupUser.groupUser.add(User1.user);
                    choseGroupUser.groupUser.add(user);
                    try {
                        GroupUserApplication shuju=new GroupUserApplication(Group.group,choseGroupUser.groupUser);
                        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
                        oos.writeObject(new AllApplication<>("转让群主", shuju));
                        DeleteGroupUser.stagex.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                ContextMenu contextMenu = new ContextMenu();
                if (level == 1) {
                    if (user.getLevel() == 2) {
                        contextMenu.getItems().addAll(transfer, deleteManager, delete);
                    } else if (user.getLevel() == 3) {
                        contextMenu.getItems().addAll(transfer, setManager, delete);
                    }
                } else if (level == 2) {
                    if (user.getLevel() == 3) {
                        contextMenu.getItems().addAll(delete);
                    }
                }
                contextMenu.show(Uname, event.getScreenX(), event.getScreenY());
            }
//            if (clickEvent != null) {
//                clickEvent.apply(null);
//            }
        });
        return Pane;
    }

    public void setClickEvent(Function<Void, Void> clickEvent) {
        this.clickEvent = clickEvent;
    }

}
