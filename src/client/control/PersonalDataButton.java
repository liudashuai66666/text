package client.control;

import application.AllApplication;
import application.LoginApplication;
import application.ReviseAvatarApplication;
import application.SendApplicationLong;
import client.view.ChangeData;
import client.view.EnrollFace;
import client.view.PersonalData;
import client.vo.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//个人资料展示
public class PersonalDataButton implements Initializable {
    @FXML
    private Label Uname;//用户名
    @FXML
    private Label Account;//账号
    @FXML
    private Label Sex;//性别
    @FXML
    private Text Signature;//个性签名
    @FXML
    private Label Birthday;//生日
    @FXML
    private Button ChangeInformationButton;//修改个人资料按钮
    @FXML
    private Button ChangeAvatarButton;//换头像按钮
    @FXML
    private ImageView Avatar;//头像
    @FXML
    private Label Age;//年龄
    @FXML
    private Label Mailbox;//年龄
    @FXML
    private Label OnOffLine;//是否在线
    public static File selectedFile;
    @FXML//修改个人资料
    void ChangeInformation(ActionEvent event) throws Exception {
        System.out.println("修改个人资料");
        ChangeData changeData = new ChangeData();
        if(!ChangeData.stagex.isShowing()){
            changeData.start(new Stage());
        }else{
            ChangeData.stagex.toFront();
        }
    }
    @FXML//换头像
    void Change(ActionEvent event) throws Exception {
        System.out.println("换头像中...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");//设置该窗口的名字
        fileChooser.setInitialDirectory(new File("D:\\IDEA liu_da_shuai\\Q_Q\\src\\client\\photo\\avatar"));//打开指定位置的文件夹
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG","*.png","*.jpg","*.gif"));//用来指定文件类型
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("您选择的文件是：" + selectedFile.getAbsolutePath());
            //创建流
            //发送数据
            ObjectOutputStream oos =new ObjectOutputStream(User.socket.getOutputStream());
            ReviseAvatarApplication shuju = new ReviseAvatarApplication(selectedFile.getAbsolutePath(),User.mailbox);
            oos.writeObject(new AllApplication<>("换头像", shuju));
        }
    }
    @Override//初始化
    public void initialize(URL location, ResourceBundle resources) {
        Uname.setText(User.uname);
        Account.setText(User.account);
        Sex.setText(User.sex);
        Signature.setText(User.signature);
        Birthday.setText(User.birthday);
        Age.setText(User.age);
        Mailbox.setText(User.mailbox);
        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
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
                            Uname.setText(User.uname);
                            Account.setText(User.account);
                            Sex.setText(User.sex);
                            Signature.setText(User.signature);
                            Birthday.setText(User.birthday);
                            Age.setText(User.age);
                            Mailbox.setText(User.mailbox);
                            if(User.avatar!=null) {
                                Avatar.setImage(new Image(User.avatar));
                            }else{
                                Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 1000);//定时器的延迟时间及间隔时间
    }
}
