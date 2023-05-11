package client.control;

import application.AllApplication;
import application.MemoryUserApplication;
import client.view.ChangeData;
import client.vo.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeDataButton implements Initializable {

    @FXML
    private TextArea ChooseSignature;

    @FXML
    private DatePicker ChooseBirthday;

    @FXML
    private ChoiceBox<String> ChooseSex;

    @FXML
    private Button SaveButton;

    @FXML
    private Button CloseButton;

    @FXML
    private TextField ChooseName;

    @FXML
    void Close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();//关闭当前窗口
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        //1.将要传进服务器的数据进行打包成一个对象，等下要将这个对象发送给服务端
        MemoryUserApplication shuju = new MemoryUserApplication();
        shuju.setMailbox(User.mailbox);
        shuju.setUname(ChooseName.getText());
        shuju.setSignature(ChooseSignature.getText());
        shuju.setSex(ChooseSex.getValue());
        shuju.setBirthday(String.valueOf(ChooseBirthday.getValue()));
        //2.创建流
        ObjectOutputStream oos =new ObjectOutputStream(User.socket.getOutputStream());
        //3.发送打包好的数据对象
        oos.writeObject(new AllApplication<>("修改资料", shuju));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChooseSex.getItems().addAll("男");
        ChooseSex.getItems().addAll("女");
        ChooseSex.setValue(User.sex);
        ChooseName.setText(User.uname);
        LocalDate lo;
        if(User.birthday!=null) {
            lo = LocalDate.parse(User.birthday);
        }else{
            lo = LocalDate.parse("2023-01-01");
        }
        ChooseBirthday.setValue(lo);
        ChooseSignature.setText(User.signature);
    }
    public void changeData(){
        LocalDate dob = ChooseBirthday.getValue();//出生日期
        LocalDate today = LocalDate.now();//当前日期
        //计算年龄
        Period period = Period.between(dob, today);
        int age = period.getYears();//得到年龄
        User.age = String.valueOf(age) + "岁";
        User.sex = ChooseSex.getValue();
        User.birthday = String.valueOf(ChooseBirthday.getValue());
        User.signature = ChooseSignature.getText();
        User.uname = ChooseName.getText();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();//关闭当前窗口
            }
        });

    }
}
