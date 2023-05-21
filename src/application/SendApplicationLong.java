package application;

import client.control.PersonalDataButton;
import client.tool.FriendListDelete;
import client.view.ChangeData;
import client.view.HallFace;
import client.vo.*;
import javafx.application.Platform;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;

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
        } else if (flag.equals("你的好友上线了")) {
            System.out.println(flag);
            MemoryUserApplication shuju= (MemoryUserApplication) application.getData();
            for (MemoryUserApplication m : FriendList.friendList) {
                if(m.getMailbox().equals(shuju.getMailbox())){
                    m.setOnline_status(shuju.getOnline_status());
                    break;
                }
            }
            HallFace.hallButton.flush();
        } else if (flag.equals("你的好友下线了")) {
            System.out.println(flag);
            MemoryUserApplication shuju= (MemoryUserApplication) application.getData();
            for (MemoryUserApplication m : FriendList.friendList) {
                if(m.getMailbox().equals(shuju.getMailbox())){
                    m.setOnline_status(shuju.getOnline_status());
                    break;
                }
            }
            HallFace.hallButton.flush();
        } else if (flag.equals("查找好友ok")) {
            System.out.println("查到的好友信息");
            MemoryUserApplication user = (MemoryUserApplication) application.getData();
            FindUser.findUser = user;
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
            if (FriendChatList.map.get(data.sendUser) != null) {
                FriendChatList.map.get(data.sendUser).add(data);
            }
            if (data.sendUser.equals(Friend.friend.getMailbox())) {
                HallFace.hallButton.flushChat();
            }
        } else if (flag.equals("接收群聊消息")) {
            GroupChatData shuju= (GroupChatData) application.getData();
            if (GroupUserMap.groupChatDataMap.get(shuju.groupId) != null) {
                GroupUserMap.groupChatDataMap.get(shuju.groupId).add(shuju);
            }
            if (shuju.groupId.equals(Group.group.getGroup_id())) {
                HallFace.groupChatButton.flushChat();
            }
        } else if (flag.equals("图片表情包")) {
            ImageApplication shuju = (ImageApplication) application.getData();
            if (shuju.getFlag() == 1||shuju.getFlag()==3) {
                //表情包
                String dizhi = "D:\\IDEA liu_da_shuai\\Q_Q\\src\\client\\photo\\emojis\\";
                if (!FilePan(dizhi + shuju.getImageName())) {
                    System.out.println("这表情包你没有啊");
                    FileOutputStream fos = new FileOutputStream(dizhi + shuju.getImageName());
                    fos.write(shuju.getBytes());
                } else {
                    System.out.println("这表情包你有了");
                }
            } else {
                //图片
                String dizhi = "D:\\图片\\";
                if (!FilePan(dizhi + shuju.getImageName())) {
                    System.out.println("这图片你没有啊");
                    FileOutputStream fos = new FileOutputStream(dizhi + shuju.getImageName());
                    fos.write(shuju.getBytes());
                } else {
                    System.out.println("这图片你有了");
                }
            }
        } else if (flag.equals("文件")) {
            FileApplication shuju= (FileApplication) application.getData();
            String dizhi="D:\\client_file\\";
            if (!FilePan(dizhi + shuju.getFileName())) {
                System.out.println("这文件你没有啊");
                FileOutputStream fos = new FileOutputStream(dizhi + shuju.getFileName());
                fos.write(shuju.getBytes());
            } else {
                System.out.println("这文件你有了");
            }
        } else if (flag.equals("群聊列表")) {
            GroupListData groupList = (GroupListData) application.getData();
            GroupList.groupList = groupList.getGroupList();
        } else if (flag.equals("把你删了")) {
            MemoryUserApplication shuju = (MemoryUserApplication) application.getData();
            FriendListDelete.delete(FriendList.friendList, shuju.getAccount());
            HallFace.hallButton.getCalico().setVisible(true);
            HallFace.hallButton.flush();
            FriendChatList.map.get(shuju.getMailbox()).clear();
        } else if (flag.equals("别人建群了")) {
            GroupUserApplication shuju= (GroupUserApplication) application.getData();
            GroupList.groupList.add(shuju.getGroup());
            HallFace.groupChatButton.flushUser();
            HallFace.groupChatButton.flush();
        } else if (flag.equals("这个群被删了")) {
            GroupApplication shuju= (GroupApplication) application.getData();
            Iterator<GroupApplication> it= null;
            try {
                it = GroupList.groupList.iterator();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (it.hasNext()){
                GroupApplication m=it.next();
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    it.remove();
                    break;
                }
            }
            HallFace.groupChatButton.flush();
            HallFace.groupChatButton.close();
        } else if (flag.equals("群聊成员")) {
            GroupUserApplication shuju= (GroupUserApplication) application.getData();
            Group.user=shuju.getUser();
            GroupUserMap.groupUser.put(shuju.getGroup().getGroup_id(),shuju.getUser());
            HallFace.groupChatButton.flushUser();
        } else if (flag.equals("群聊消息")) {
            GroupChatDataApplication shuju= (GroupChatDataApplication) application.getData();
            GroupUserMap.groupChatDataMap.put(shuju.getGroup_id(),shuju.getGroupList());
            HallFace.groupChatButton.flushChat();
        } else if (flag.equals("别人拉你进群了")) {
            GroupApplication shuju= (GroupApplication) application.getData();
            shuju.setGroup_level(3);
            GroupList.groupList.add(shuju);
            HallFace.groupChatButton.flush();
        } else if (flag.equals("别人把你删了")) {
            GroupApplication shuju= (GroupApplication) application.getData();
            Iterator<GroupApplication> it=GroupList.groupList.iterator();
            while (it.hasNext()){
                GroupApplication m=it.next();
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    it.remove();
                    break;
                }
            }
            HallFace.groupChatButton.flush();
            HallFace.groupChatButton.close();
        } else if (flag.equals("你被设置成了管理员")) {
            System.out.println(flag);
            GroupApplication shuju= (GroupApplication) application.getData();
            if(Group.group.getGroup_id().equals(shuju.getGroup_id())){
                HallFace.groupChatButton.gly();
            }
            for (GroupApplication m : GroupList.groupList) {
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    m.setGroup_level(2);
                    break;
                }
            }
        }else if (flag.equals("你被撤销了管理员")) {
            System.out.println(flag);
            GroupApplication shuju= (GroupApplication) application.getData();
            if(Group.group.getGroup_id().equals(shuju.getGroup_id())){
                HallFace.groupChatButton.chengyuan();
            }
            for (GroupApplication m : GroupList.groupList) {
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    m.setGroup_level(3);
                    break;
                }
            }
        }else if (flag.equals("你被设置成了群主")) {
            System.out.println(flag);
            GroupApplication shuju= (GroupApplication) application.getData();
            if(Group.group.getGroup_id().equals(shuju.getGroup_id())){
                HallFace.groupChatButton.qunzhu();
            }
            for (GroupApplication m : GroupList.groupList) {
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    m.setGroup_level(1);
                    break;
                }
            }
            User1.user.setLevel(1);
        } else if (flag.equals("你转让了群主")) {
            System.out.println(flag);
            GroupApplication shuju= (GroupApplication) application.getData();
            if(Group.group.getGroup_id().equals(shuju.getGroup_id())){
                HallFace.groupChatButton.gly();
            }
            for (GroupApplication m : GroupList.groupList) {
                if(m.getGroup_id().equals(shuju.getGroup_id())){
                    m.setGroup_level(2);
                    break;
                }
            }
            User1.user.setLevel(2);
        } else if (flag.equals("群聊成员发生了改变")) {
            System.out.println(flag);
            GroupUserApplication shuju= (GroupUserApplication) application.getData();
            GroupUserMap.groupUser.put(shuju.getGroup().getGroup_id(),shuju.getUser());
            HallFace.groupChatButton.flushUser();
            HallFace.groupChatButton.flush();
        }
    }
}
