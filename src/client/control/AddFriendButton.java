package client.control;

import application.AddFriendApplication;
import application.AllApplication;
import application.FindFriendApplication;
import application.MemoryUserApplication;
import client.view.ChangeData;
import client.view.FriendData;
import client.vo.FindUser;
import client.vo.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class AddFriendButton implements Initializable {

    @FXML
    private Text FriendAccount;//最后展示的好友账号

    @FXML
    private ImageView FriendAvater;//最后展示的好友头像

    @FXML
    private Button FriendDataButton;//好友头像上面的按钮

    @FXML
    private Button FindFriendButton;//查找好友按钮

    @FXML
    private Button AddFriendButton;//添加好友按钮

    @FXML
    private TextField FindFriendText;//好友账号或者邮箱输入框

    @FXML
    private Text FriendName;//好友姓名

    @FXML
    //查找用户
    void FindFriend(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("查找用户");
        //创建Socket对象
        //Socket socket =new Socket("127.0.0.1",7777);
        FindFriendApplication shuju = new FindFriendApplication(FindFriendText.getText());
        //发送消息io流
        ObjectOutputStream oos = new ObjectOutputStream(User.socket.getOutputStream());
        //发送请求对象
        oos.writeObject(new AllApplication<>("查找用户", shuju));
    }

    @FXML
    //查看用户资料
    void FriendData(ActionEvent event) throws Exception {
        FriendData friendData=new FriendData();
        if(!FriendData.stagex.isShowing()){
            friendData.start(new Stage());
        }else{
            FriendData.stagex.toFront();
        }
    }

    @FXML
    //发送添加请求
    void AddFriend(ActionEvent event) throws IOException {
        System.out.println("添加好友");
        //1.发送过去的数据
        AddFriendApplication shuju=new AddFriendApplication(User.account,FindFriendText.getText());
        //2.创建发送消息io流
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        //发送请求
        oos.writeObject(new AllApplication<>("加个好友",shuju));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(FindUser.account!=null){
            FriendAccount.setText(FindUser.account);
            FriendName.setText(FindUser.uname);
            if(FindUser.avatar!=null) {
                FriendAvater.setImage(new Image(FindUser.avatar));
            }else{
                FriendAvater.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
            }
            FriendName.setOpacity(1);
            FriendAvater.setOpacity(1);
            AddFriendButton.setOpacity(1);
            FriendDataButton.setDisable(false);
            AddFriendButton.setDisable(false);
        }else{
            FriendName.setOpacity(0);
            FriendAvater.setOpacity(0);
            AddFriendButton.setOpacity(0);
            FriendDataButton.setDisable(true);
            AddFriendButton.setDisable(true);
        }
        flushed();
    }
    protected void flushed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(FindUser.account!=null){
                                FriendAccount.setText(FindUser.account);
                                FriendName.setText(FindUser.uname);
                                if(FindUser.avatar!=null) {
                                    FriendAvater.setImage(new Image(FindUser.avatar));
                                }else{
                                    FriendAvater.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
                                }
                                FriendName.setOpacity(1);
                                FriendAvater.setOpacity(1);
                                AddFriendButton.setOpacity(1);
                                FriendDataButton.setDisable(false);
                                AddFriendButton.setDisable(false);
                            }else{
                                FriendName.setOpacity(0);
                                FriendAvater.setOpacity(0);
                                AddFriendButton.setOpacity(0);
                                FriendDataButton.setDisable(true);
                                AddFriendButton.setDisable(true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 500, 500);//定时器的延迟时间及间隔时间
    }
}
