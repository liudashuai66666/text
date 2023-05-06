package client.tool;

import client.control.PersonalDataButton;
import client.vo.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
//这是用来进行不停的刷新界面的方法
public class Flushed {
    @FXML
    protected void flushed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //这里放要进行刷新的代码
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 5000);//定时器的延迟时间及间隔时间
    }
}


