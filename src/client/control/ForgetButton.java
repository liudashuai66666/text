package client.control;

import application.AllApplication;
import application.EmailApplication;
import application.ForgetApplication;
import application.ReviseAvatarApplication;
import client.tool.Clock;
import client.view.LoginFace;
import com.sun.scenario.effect.impl.prism.PrImage;
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

public class ForgetButton {
    @FXML
    private Label CountdownLabel;//验证码发送倒计时
    @FXML
    private TextField Email;//邮件输入框
    @FXML
    private Label MailboxWarn;//邮箱输入警告
    @FXML
    private Label CaptchaWarn;//验证码输入警告
    @FXML
    private Button ReturnLoginButton;//返回按钮
    @FXML
    private Button SendCaptchaButton;//发送验证码按钮
    @FXML
    private Button ConfirmButton;//确定按钮
    @FXML
    private TextField ConfirmPassword;//确认密码输入框
    @FXML
    private TextField Captcha;//验证码输入框
    @FXML
    private Label PassWordWarn1;//密码1格式警告
    @FXML
    private Label PassWordWarn2;//密码2格式警告
    @FXML
    private Button close;
    @FXML
    private Button open;
    @FXML
    private ImageView openEye;
    @FXML
    private ImageView closeEye;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Password1;
    private String yanzhengma;
    private String youxiang;

    @FXML
    void ForgetCLICK(ActionEvent event) throws Exception {
        {
            PassWordWarn1.setText("");
            PassWordWarn2.setText("");
            MailboxWarn.setText("");
            CaptchaWarn.setText("");
            String password1;
            String password2 = ConfirmPassword.getText();
            if (!Password.getText().equals("")) {
                password1 = Password.getText();
            } else {
                password1 = Password1.getText();
            }
            String mailbox = Email.getText();
            //清空界面，得到输入值
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
            String password = password1;
            ForgetApplication shuju = new ForgetApplication(mailbox, password);
            //创建一个Socket对象
            Socket socket = new Socket("127.0.0.1", 8888);
            //发送消息
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送请求对象
            oos.writeObject(new AllApplication<>("改密码", shuju));

            //得到反馈
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            byte[] bys = new byte[1024];
            int len = ois.read(bys);
            String s = new String(bys, 0, len);
            long flag = Long.parseLong(s);
            if (flag == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("改邮箱未注册");
                alert.show();
            } else if (flag != 0) {
               /* Stage stage = (Stage) ReturnLoginButton.getScene().getWindow();
                stage.close();
                LoginFace loginFace = new LoginFace();
                loginFace.start(new Stage());*/
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("修改密码成功！");
                alert.show();
            }
            //关闭
            oos.flush();
            oos.close();
        }
    }
    @FXML
    void ReturnLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) ReturnLoginButton.getScene().getWindow();
        stage.close();
        LoginFace loginFace = new LoginFace();
        loginFace.start(new Stage());
    }
    @FXML
    void Email(ActionEvent event) {

    }
    @FXML
    void Password2(ActionEvent event) {

    }
    @FXML
    void captcha(ActionEvent event) {

    }
    @FXML
    void Username(ActionEvent event) {

    }
    @FXML
    void SendCaptcha(ActionEvent event) throws IOException {
        System.out.println("发送邮件");
        youxiang = Email.getText();
        EmailApplication shuju = new EmailApplication(youxiang);
        Socket socket = new Socket("127.0.0.1", 8888);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(new AllApplication<>("邮箱", shuju));
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        byte[] bytes = new byte[1024];
        int len = ois.read(bytes);
        String s = new String(bytes, 0, len);
        if (s.equals("no")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请输入正确的邮箱!!!");
            alert.show();
        } else {
            yanzhengma = s;
            System.out.println(yanzhengma);
            Thread thread1 = new Thread(() -> {
                SendCaptchaButton.setDisable(true);//将按钮变为不可点击
                try {
                    Thread.sleep(60 * 1000);
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
    void Open(ActionEvent event) {
        System.out.println(Password.getText());
        Password1.setText(Password.getText());
        Password.setText("");
        Password1.setPromptText(Password.getPromptText());
        Password.setPromptText("");
        closeEye.setDisable(true);
        closeEye.setOpacity(0);
        Password.setOpacity(0);
        Password.setDisable(true);
        open.setDisable(true);
    }

    @FXML
    void Close(ActionEvent event) {
        Password.setText(Password1.getText());
        Password1.setText("");
        Password.setPromptText(Password1.getPromptText());
        Password1.setPromptText("");
        closeEye.setDisable(false);
        closeEye.setOpacity(1);
        Password.setOpacity(1);
        Password.setDisable(false);
        open.setDisable(false);
    }

}
