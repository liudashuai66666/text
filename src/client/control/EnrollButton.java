package client.control;

import application.AllApplication;
import application.EmailApplication;
import application.EnrollApplication;
import application.LoginApplication;
import client.tool.Clock;
import client.view.EnrollFace;
import client.view.LoginFace;
import client.vo.LoginUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.Time;

public class EnrollButton {
    @FXML
    private Label CountdownLabel;//验证码发送倒计时
    @FXML
    private Label NameWarn;//姓名输入警告
    @FXML
    private Label MailboxWarn;//邮箱输入警告
    @FXML
    private Label CaptchaWarn;//验证码输入警告
    @FXML
    private Label PassWordWarn1;//密码1输入警告
    @FXML
    private Label PassWordWarn2;//密码2输入警告
    @FXML
    private TextField Captcha;//验证码框
    @FXML
    private Button ReturnLoginButton;//返回登录界面按钮
    @FXML
    private Button SendCaptchaButton;//发送验证码按钮
    @FXML
    private Button EnrollConfirmButton;//注册确定按钮
    @FXML
    private TextField EnrollUsername;//注册用户名框
    @FXML
    private TextField EnrollEmail;//注册邮件框
    @FXML
    private TextField EnrollPassword1;//密码显示框
    @FXML
    private PasswordField EnrollPassword;//密码隐藏框
    @FXML
    private TextField ConfirmPassword1;//确定密码显示框
    @FXML
    private PasswordField ConfirmPassword;//确定密码隐藏框
    @FXML
    private Button close;//闭眼
    @FXML
    private Button open;//睁眼
    @FXML
    private ImageView openEye;//睁眼照片
    @FXML
    private ImageView closeEye;//闭眼照片
    private String yanzhengma;
    private String youxiang;

    @FXML
//确认点击事件
    void EnrollCLICK(ActionEvent event) throws Exception {
        NameWarn.setText("");
        PassWordWarn1.setText("");
        PassWordWarn2.setText("");
        MailboxWarn.setText("");
        CaptchaWarn.setText("");
        String password1;
        String password2 = ConfirmPassword.getText();
        if(!EnrollPassword.getText().equals("")){
            password1=EnrollPassword.getText();
        }else{
            password1=EnrollPassword1.getText();
        }
        String uname = EnrollUsername.getText();
        String mailbox = EnrollEmail.getText();
        //清空界面，得到输入值

        if (uname.equals("")) {
            NameWarn.setText("用户名不能为空");
            return;
        }
        if (password1.equals("")) {
            PassWordWarn1.setText("密码不能为空");
            return;
        }
        if (!password1.matches("^(?!\\d+$)(?![a-z]+$)(?![A-Z]+$)[\\da-zA-z]{8,16}$")) {
            PassWordWarn1.setText("请输入长度在8~12之间包含大小写字母和数字至少两种");
            return;
        }
        if (!password2.equals(password1)) {
            PassWordWarn2.setText("请确认密码");
            return;
        }
        if (mailbox.equals("")) {
            MailboxWarn.setText("邮箱不能为空");
            return;
        }
        if (!mailbox.matches("^[1-9][0-9]{4,}@qq.com$")) {
            MailboxWarn.setText("请输入正确的qq邮箱号");
            return;
        }
        if (Captcha.getText().equals("")) {
            CaptchaWarn.setText("验证码不能为空");
            return;
        }
        if (!Captcha.getText().equals(yanzhengma)) {
            CaptchaWarn.setText("验证码错误");
            return;
        }
        if (!youxiang.equals(mailbox)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请勿更换邮箱!!!" + "\n" + "如若要更换，则请重发验证码");
            alert.show();
            return;
        }

        String password = password1;
        EnrollApplication shuju = new EnrollApplication(uname, password, mailbox);
        //创建一个Socket对象
        Socket socket = new Socket("127.0.0.1", 7777);
        //发送消息
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送请求类型
        oos.writeObject(new AllApplication<>("注册", shuju));

        //得到反馈
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        byte[] bys = new byte[1024];
        int len = ois.read(bys);
        String s = new String(bys, 0, len);
        long flag = Long.parseLong(s);
        if (flag == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("该邮箱已经注册了用户!!!");
            alert.show();
        } else if (flag != 0) {
            LoginUser.Account=EnrollEmail.getText();
            LoginUser.password=password;
            Stage stage = (Stage) EnrollConfirmButton.getScene().getWindow();
            stage.close();//关闭当前窗口
            LoginFace loginFace = new LoginFace();
            loginFace.start(new Stage());
        }
        //关闭
        oos.flush();
        oos.close();
    }

    @FXML//返回登录界面按钮点击事件
    public void ReturnLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) ReturnLoginButton.getScene().getWindow();
        stage.close();
        LoginFace loginFace = new LoginFace();
        loginFace.start(new Stage());
    }

    @FXML
//邮件
    void Email(ActionEvent event) {

    }

    @FXML
//第一次密码
    void Password1(ActionEvent event) {

    }

    @FXML
//确认密码
    void Password2(ActionEvent event) {

    }

    @FXML
//验证码
    void captcha(ActionEvent event) {

    }

    @FXML
//起用户名
    void Username(ActionEvent event) {

    }

    @FXML
//发送邮件按钮点击事件
    void SendCaptcha(ActionEvent event) throws IOException {
        System.out.println("发送邮件");
        youxiang = EnrollEmail.getText();
        EmailApplication shuju = new EmailApplication(youxiang);
        //创建socket对象，与服务器建立连接
        Socket socket = new Socket("127.0.0.1", 7777);
        //创建流
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送邮箱指令
        oos.writeObject(new AllApplication<>("邮箱", shuju));
        //接受反馈
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        byte[] bytes = new byte[1024];
        int len = ois.read(bytes);
        String s = new String(bytes, 0, len);
        if (s.equals("no")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("网络错误!!!");
            alert.show();
        } else {
            yanzhengma = s;
            System.out.println(yanzhengma);
            Thread thread1 = new Thread(() -> {
                SendCaptchaButton.setDisable(true);//将按钮变为不可点击
                try {
                    Thread.sleep(60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SendCaptchaButton.setDisable(false);//将按钮恢复为可点击
            });
            thread1.setDaemon(true);//守护线程
            Thread thread = new Thread(() -> {
                int Time = 0;
                for (int i = 1; i <= 60; i++) {
                    Time++;
                    try {
                        Thread.sleep(1000);
                        System.out.println(Time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                yanzhengma = "";
            });
            thread.setDaemon(true);
            new Clock(CountdownLabel).timelabel();
            thread1.start();//开启线程
            thread.start();
            CountdownLabel.setText("");
        }
        //关闭
        ois.close();
        oos.flush();
        oos.close();
        socket.close();
    }
    @FXML
    void Close(ActionEvent event) {
        EnrollPassword.setText(EnrollPassword1.getText());
        EnrollPassword1.setText("");
        EnrollPassword.setPromptText(EnrollPassword1.getPromptText());
        EnrollPassword1.setPromptText("");
        closeEye.setDisable(false);
        closeEye.setOpacity(1);
        EnrollPassword.setOpacity(1);
        EnrollPassword.setDisable(false);
        open.setDisable(false);
    }
    @FXML
    void Open(ActionEvent event) {
        System.out.println(EnrollPassword.getText());
        EnrollPassword1.setText(EnrollPassword.getText());
        EnrollPassword.setText("");
        EnrollPassword1.setPromptText(EnrollPassword.getPromptText());
        EnrollPassword.setPromptText("");
        closeEye.setDisable(true);
        closeEye.setOpacity(0);
        EnrollPassword.setOpacity(0);
        EnrollPassword.setDisable(true);
        open.setDisable(true);
    }
}

