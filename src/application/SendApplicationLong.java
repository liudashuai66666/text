package application;

import client.control.PersonalDataButton;
import client.tool.FriendListDelete;
import client.view.ChangeData;
import client.view.HallFace;
import client.vo.*;
import javafx.application.Platform;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import static client.control.HallButton.FilePan;

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
        if (flag.equals("修改头像")) {
            System.out.println("修改成功！");
            User.avatar = "file:" + PersonalDataButton.selectedFile.getAbsolutePath();
        } else if (flag.equals("修改资料")) {
            System.out.println("修改成功！");
            ChangeData.changeDataButton.changeData();
        } else if (flag.equals("查找好友ok")) {
            System.out.println("查到的好友信息");
            MemoryUserApplication user = (MemoryUserApplication) application.getData();
            FindUser.findUser = user;
//                FindUser.uname = user.getUname();
//                FindUser.account = user.getAccount();
//                FindUser.mailbox = user.getMailbox();
//                FindUser.sex = user.getSex();
//                FindUser.age = user.getAge();
//                FindUser.birthday = user.getBirthday();
//                FindUser.signature = user.getSignature();
//                FindUser.avatar = user.getAvatar();
        } else if (flag.equals("查找好友no")) {
            System.out.println("该用户不存在");
        } else if (flag.equals("加个好友no")) {
            System.out.println("该用户已经是你的好友");
        } else if (flag.equals("加个好友yes")) {
            MemoryUserApplication data = (MemoryUserApplication) application.getData();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //添加修改数据
                    FriendList.newFriendList1.add(data);
                    //刷新
                    HallFace.newFriendButton.flush();
                }
            });
        } else if (flag.equals("有人想加你")) {
            MemoryUserApplication data;
            data = (MemoryUserApplication) application.getData();
            System.out.println(data.getUname() + "想加你");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //添加修改数据
                    FriendList.newFriendList2.add(data);
                    //刷新
                    HallFace.newFriendButton.flush();
                }
            });
        } else if (flag.equals("别人同意啦")) {
            MemoryUserApplication data;//添加修改数据

            data = (MemoryUserApplication) application.getData();
            System.out.println(data.getUname() + "已经是你的好友了！");
            FriendListDelete.delete(FriendList.newFriendList1, data.getAccount());
            FriendList.friendList.add(data);
            //刷新
            HallFace.hallButton.flush();
            HallFace.newFriendButton.flush();
        } else if (flag.equals("别人拒绝啦")) {
            MemoryUserApplication data;//修改数据

            data = (MemoryUserApplication) application.getData();
            System.out.println(data.getUname() + "拒绝了你！");
            FriendListDelete.delete(FriendList.newFriendList1, data.getAccount());
            FriendList.newFriendList3.add(data);
            //刷新
            HallFace.hallButton.flush();
            HallFace.newFriendButton.flush();
        } else if (flag.equals("初始化聊天记录")) {
            ChatDataApplication data = (ChatDataApplication) application.getData();
            FriendChatList.map.put(Friend.friend.getMailbox(), data.getChatList1());
            HallFace.hallButton.flushChat();
        } else if (flag.equals("接收消息")) {
            ChatData data = (ChatData) application.getData();
            if(FriendChatList.map.get(data.sendUser)!=null){
                FriendChatList.map.get(data.sendUser).add(data);
            }
            if(data.sendUser.equals(Friend.friend.getMailbox())){
                HallFace.hallButton.flushChat();
            }
        } else if(flag.equals("图片表情包")){
            ImageApplication shuju= (ImageApplication) application.getData();
            if(shuju.getFlag()==1){
                //表情包
                String dizhi="D:\\IDEA liu_da_shuai\\Q_Q\\src\\client\\photo\\emojis\\";
                if(!FilePan(dizhi+shuju.getImageName())){
                    System.out.println("这表情包你没有啊");
                    FileOutputStream fos=new FileOutputStream(dizhi+shuju.getImageName());
                    fos.write(shuju.getBytes());
                }else{
                    System.out.println("这表情包你有了");
                }
            }else{
                //图片
                String dizhi="D:\\图片\\";
                if(!FilePan(dizhi+shuju.getImageName())){
                    System.out.println("这图片你没有啊");
                    FileOutputStream fos=new FileOutputStream(dizhi+shuju.getImageName());
                    fos.write(shuju.getBytes());
                }else{
                    System.out.println("这图片你有了");
                }
            }
        }else if (flag.equals("把你删了")) {
            MemoryUserApplication shuju= (MemoryUserApplication) application.getData();
            FriendListDelete.delete(FriendList.friendList,shuju.getAccount());
            HallFace.hallButton.getCalico().setVisible(true);
            HallFace.hallButton.flush();
            FriendChatList.map.get(shuju.getMailbox()).clear();
        }
    }
}
