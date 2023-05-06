package application;

import client.control.ChangeDataButton;
import client.control.PersonalDataButton;
import client.view.ChangeData;
import client.vo.FindUser;
import client.vo.User;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;

public class SendApplicationLong extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(User.socket);
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(User.socket.getInputStream());
                System.out.println("等待服务端连接中...");
                Object obj1 = ois.readObject();
                System.out.println("连接到了...");
                receive(obj1);
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
                String book = (String) application.getData();
                System.out.println(book);
                System.out.println("修改成功！");
                User.avatar = "file:" + PersonalDataButton.selectedFile.getAbsolutePath();
                System.out.println(User.avatar);
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
            case "查找好友no":
                System.out.println("该用户不存在");
        }
    }
}
