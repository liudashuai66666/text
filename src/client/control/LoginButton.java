package client.control;

import application.*;
import client.view.EnrollFace;
import client.view.ForgetFace;
import client.view.HallFace;
import client.vo.FindUser;
import client.vo.FriendList;
import client.vo.LoginUser;
import client.vo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginButton implements Initializable {
    @FXML
    private Label LabelDisplay;
    @FXML
    private Button EnrollButton;
    @FXML
    private Button LoginButtonConfirm;
    @FXML
    private TextField LoginAccount;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginPassword1;

    @FXML
    private Button close;

    @FXML
    private Button open;
    @FXML
    private Button ForgetButton;
    @FXML
    private ImageView openEye;
    @FXML
    private ImageView closeEye;
    public static HallFace hall;
    //方便转换页面的时候进行调用；
    @FXML
    public void LoginCLICK(ActionEvent event) throws Exception {
        String account;
        String password;
        if (LoginAccount.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("账号或者邮箱不能为空！！！");
            alert.show();
        } else {
            account = LoginAccount.getText();
            if (!LoginPassword.getText().equals("")) {
                password = LoginPassword.getText();
            } else {
                password = LoginPassword1.getText();
            }
            //创建登录请求对象
            LoginApplication shuju = new LoginApplication(account, password);
            //1.创建Socket对象
            //细节：在创建对象的同时会链接服务端
            //  如果连接不上，代码会报错；
            Socket socket = new Socket("127.0.0.1", 9999);
            //2.发送消息IO流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送请求类型
            //发送请求对象
            oos.writeObject(new AllApplication<>("登录", shuju));
            //oos.flush();
            //oos.close();
            //得到反馈
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            byte[] bys = new byte[1024];
            int len = ois.read(bys);
            String s = new String(bys, 0, len);
            int flag = Integer.parseInt(s);
            if (flag == 1) {

                //得到服务端传回的用户信息；
                //ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                FriendListData friendListData = (FriendListData) ois.readObject();
                MemoryUserApplication user = friendListData.getUser();
                User.uname = user.getUname();
                User.account = user.getAccount();
                User.mailbox = user.getMailbox();
                User.sex = user.getSex();
                User.age = user.getAge();
                User.birthday = user.getBirthday();
                User.signature = user.getSignature();
                User.avatar = user.getAvatar();
                //
                FriendList.friendList= friendListData.getFriendList();//好友列表
                FriendList.newFriendList1=friendListData.getNewFriendList1();//等待别人处理
                FriendList.newFriendList2=friendListData.getNewFriendList2();//你处理你收到的好友请求
                FriendList.newFriendList3=friendListData.getNewFriendList3();//你被拒绝了
                for (MemoryUserApplication memoryUserApplication : FriendList.newFriendList2) {
                    System.out.println(memoryUserApplication.getUname()+"想加你");
                }
                //
                Stage stage = (Stage) EnrollButton.getScene().getWindow();
                stage.close();//关闭当前窗口
                hall = new HallFace();
                hall.start(new Stage());
                System.out.println(User.avatar);
                System.out.println(User.mailbox);
                User.socket=socket;
                System.out.println("登录成功！");
                SendApplicationLong sendApplicationLong = new SendApplicationLong();
                sendApplicationLong.start();//开启长连接
            } else if (flag == 2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("密码错误或者该账号已经登录");
                alert.show();
                System.out.println("密码错误！");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("该账号或者该邮箱不存在!!!");
                alert.show();
                System.out.println("该账号或者该邮箱不存在！");
            }
        }

    }

    public void JudgmentAccount(ActionEvent event) {

    }

    public void JudgmentPassword(ActionEvent event) {

    }

    //跳转注册界面
    public void EnrollAccount(ActionEvent event) throws Exception {
        Stage stage = (Stage) EnrollButton.getScene().getWindow();
        stage.close();//关闭当前窗口
        EnrollFace enrollFace = new EnrollFace();
        enrollFace.start(new Stage());
    }

    @FXML
    //忘记密码
    void ForgetAccount(ActionEvent event) throws Exception {
        Stage stage = (Stage) ForgetButton.getScene().getWindow();
        stage.close();
        ForgetFace forgetFace = new ForgetFace();
        forgetFace.start(new Stage());
    }

    @FXML
    void Close(ActionEvent event) {
        LoginPassword.setText(LoginPassword1.getText());
        LoginPassword1.setText("");
        LoginPassword.setPromptText(LoginPassword1.getPromptText());
        LoginPassword1.setPromptText("");
        closeEye.setDisable(false);
        closeEye.setOpacity(1);
        LoginPassword.setOpacity(1);
        LoginPassword.setDisable(false);
        open.setDisable(false);
    }

    @FXML
    void Open(ActionEvent event) {
        System.out.println(LoginPassword.getText());
        LoginPassword1.setText(LoginPassword.getText());
        LoginPassword.setText("");
        LoginPassword1.setPromptText(LoginPassword.getPromptText());
        LoginPassword.setPromptText("");
        closeEye.setDisable(true);
        closeEye.setOpacity(0);
        LoginPassword.setOpacity(0);
        LoginPassword.setDisable(true);
        open.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginAccount.setText(LoginUser.Account);
        LoginPassword.setText(LoginUser.password);
    }
}

