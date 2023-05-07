package application;

import client.control.ChangeDataButton;
import client.control.CustomListCell;
import client.control.NewFriendButton;
import client.control.PersonalDataButton;
import client.view.ChangeData;
import client.view.HallFace;
import client.vo.FindUser;
import client.vo.User;
import javafx.application.Platform;
import javafx.stage.Stage;
import toolkind.Friends;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

public class SendApplicationLong extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(User.socket);
            while (true) {
                System.out.println("等待服务端连接中...");
                ObjectInputStream ois = new ObjectInputStream(User.socket.getInputStream());
                Object obj1 = ois.readObject();
                System.out.println("连接到了...");
                receive(obj1);//处理反馈
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    private static void receive(Object obj) throws Exception {
        AllApplication application = (AllApplication) obj;
        String flag = application.getFlag();
        System.out.println(flag);
        switch (flag) {
            case "修改头像":
                System.out.println("修改成功！");
                User.avatar = "file:" + PersonalDataButton.selectedFile.getAbsolutePath();
                break;
            case "修改资料":
                System.out.println("修改成功！");
                ChangeData.changeDataButton.changeData();
                break;
            case "查找好友ok":
                System.out.println("查到的好友信息");
                MemoryUserApplication user= (MemoryUserApplication) application.getData();
                FindUser.uname = user.getUname();
                FindUser.account = user.getAccount();
                FindUser.mailbox = user.getMailbox();
                FindUser.sex = user.getSex();
                FindUser.age = user.getAge();
                FindUser.birthday = user.getBirthday();
                FindUser.signature = user.getSignature();
                FindUser.avatar = user.getAvatar();
                break;
            case "查找好友no":
                System.out.println("该用户不存在");
                break;
            case "加个好友no":
                System.out.println("该用户已经是你的好友");
                break;
            case "加个好友yes":
                MemoryUserApplication data= (MemoryUserApplication) application.getData();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Friends newfriends=new Friends(data.getAvatar(), data.getUname());
                        HallFace.newFriendButton.addListview1(newfriends);
                    }
                });
                break;
            case "有人想加你":
                data = (MemoryUserApplication) application.getData();
                System.out.println(data.getUname());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Friends newfriends=new Friends(data.getAvatar(), data.getUname());
                        HallFace.newFriendButton.addListview2(newfriends);
                    }
                });
                break;
        }
    }
}
