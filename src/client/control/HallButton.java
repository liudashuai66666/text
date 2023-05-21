package client.control;

import application.*;
import client.controlList.FriendListCell;
import client.controlList.messageListCell;
import client.view.AddFriend;
import client.view.HallFace;
import client.view.PersonalData;
import client.vo.*;
import application.ChatData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import toolkind.Friends;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//大厅
public class HallButton implements Initializable {
    @FXML
    private Button emojisButton;//表情包按钮
    @FXML
    private Button image;//图片按钮
    @FXML
    private TextArea inputBox;//输入框
    @FXML
    private Button sendButton;//发送按钮
    @FXML
    private ListView<ChatData> chatDataList;//聊天列表
    @FXML
    private Button file;//文件按钮
    @FXML
    private AnchorPane calico;//遮挡板
    private MemoryUserApplication friend;//该聊天界面的聊天对象
    @FXML
    private ListView Emojis;//表情包预览
    @FXML
    private Button FriendsButton;//切换私聊按钮
    @FXML
    private Button addFriendButton;//添加好友按钮
    @FXML
    private Button GroupChatButton;//切换群聊画面按钮
    @FXML
    private Button MyMessageButton;//查看个人资料按钮
    @FXML
    private ImageView Avatar;//自己头像
    @FXML
    private Button FriendApplicationButton;//切换好友申请面板按钮
    @FXML
    private ListView<Friends> ChatList;//好友列表
    private static int cnt=0;
    public final String dizhi="D:/QQ/2385272606/FileRecv/静态/";
    public static String path;
    public ListView<Friends> getChatList() {
        return ChatList;
    }
    public void setChatList(ListView<Friends> chatList) {
        ChatList = chatList;
    }
    @FXML
    void GroupChat(ActionEvent event) throws IOException {
        if(GroupList.groupList==null){
            System.out.println("初始化群聊列表");
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            FindFriendApplication shuju=new FindFriendApplication(User.mailbox);
            oos.writeObject(new AllApplication<>("群聊列表",shuju));
        }
        HallFace.groupChatButton.flush();
        System.out.println("群聊");
        LoginButton.hall.switchToPage2();
    }//切换群聊方法
    @FXML
    void FriendApplication(ActionEvent event) throws IOException {
        System.out.println("好友申请");
        LoginButton.hall.switchToPage3();
        if(GroupList.groupList==null){
            System.out.println("初始化群聊列表");
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            FindFriendApplication shuju=new FindFriendApplication(User.mailbox);
            oos.writeObject(new AllApplication<>("群聊列表",shuju));
        }
    }//切换好友申请面板方法
    @FXML
    void Friends(ActionEvent event) {
        calico.setVisible(true);
        System.out.println("私聊");
    }//切换私聊方法

    @FXML
    void addFriend(ActionEvent event) throws Exception {
        System.out.println("添加好友");
        AddFriend addFriend = new AddFriend();
        if(!addFriend.stagex.isShowing()){
            FindUser.findUser=null;
            addFriend.start(new Stage());
        }else{
            addFriend.stagex.toFront();
        }
    }//添加好友方法
    @FXML
    void onEmojis(ActionEvent event) {
        if(cnt%2==0){
            Emojis.setVisible(true);
            cnt++;
            System.out.println(cnt);
        }else{
            Emojis.setVisible(false);
            cnt++;
            System.out.println(cnt);
        }
    }//发送表情包

    @FXML
    void onFile(ActionEvent event) throws IOException {
        System.out.println("发文件");
        UUID uuid;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("发送文件");//设置窗口名字
        fileChooser.setInitialDirectory(new File("D:\\client_file"));//打开的位置
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("所有文件","*.*"));//选择指定文件类型
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile!=null){
            path=selectedFile.getAbsolutePath();
            System.out.println("您选择的文件是："+selectedFile.getName());
            byte[] bytes=Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
            uuid=UUID.nameUUIDFromBytes(bytes);
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            oos.writeObject(new AllApplication<>("发文件",new FileApplication(1,uuid+selectedFile.getName(),bytes,User.mailbox,Friend.friend.getMailbox())));
            FileOutputStream fos=new FileOutputStream("D:\\client_file\\"+uuid+selectedFile.getName());
            fos.write(bytes);
        }else {
            return;
        }
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        ChatData shuju = new ChatData(uuid+selectedFile.getName(),time,User.mailbox,Friend.friend.getMailbox(), "文件");
        oos.writeObject(new AllApplication<>("发消息",shuju));
        FriendChatList.map.get(Friend.friend.getMailbox()).add(shuju);
        HallFace.hallButton.flushChat();
    }//发送文件

    @FXML
    void onImage(ActionEvent event) throws IOException {
        UUID uuid;
        //System.out.println("发图片");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("发送图片");//设置窗口名字
        fileChooser.setInitialDirectory(new File("D:\\图片"));//打开的位置
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG","*.jpg","*.gif","*.png"));//选择指定文件类型
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile!=null){
            path=selectedFile.getAbsolutePath();
            System.out.println("您选择的图片是："+selectedFile.getName());
            byte[] bytes=Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
            uuid=UUID.nameUUIDFromBytes(bytes);
            ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
            oos.writeObject(new AllApplication<>("图片",new ImageApplication(2,uuid+".jpg",bytes,User.mailbox,Friend.friend.getMailbox())));
            FileOutputStream fos=new FileOutputStream("D:\\图片\\"+uuid+".jpg");
            fos.write(bytes);
        }else {
            return;
        }
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        ChatData shuju = new ChatData(uuid+".jpg",time,User.mailbox,Friend.friend.getMailbox(), "图片");
        oos.writeObject(new AllApplication<>("发消息",shuju));
        FriendChatList.map.get(Friend.friend.getMailbox()).add(shuju);
        HallFace.hallButton.flushChat();
    }//发送图片

    @FXML
    void send(ActionEvent event) throws IOException {
        //发送消息
        if(inputBox.getText().equals("")){
            return;
        }
        String massage=inputBox.getText();
        inputBox.clear();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String s="\\[(.*?)\\]";
        Pattern pattern=Pattern.compile(s);
        Matcher matcher=pattern.matcher(massage);
        while(matcher.find()){
            String FileName=matcher.group(1);
            if(FilePan(dizhi+FileName)){
                File file=new File(dizhi+FileName);
                System.out.println(file);
                byte[] fileBytes= Files.readAllBytes(Paths.get(dizhi+FileName));
                ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
                oos.writeObject(new AllApplication<>("图片",new ImageApplication(1,FileName,fileBytes,User.mailbox,Friend.friend.getMailbox())));
            }
        }
        ChatData shuju = new ChatData(massage,time,User.mailbox,Friend.friend.getMailbox(), "文本");
        ObjectOutputStream oos =new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("发消息",shuju));
        if(FriendChatList.map.get(Friend.friend.getMailbox())!=null){
            FriendChatList.map.get(Friend.friend.getMailbox()).add(shuju);
        }
        HallFace.hallButton.flushChat();
    }//发送消息
    public void flushChat(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                calico.setVisible(false);
                chatDataList.getItems().clear();
                chatDataList.getItems().addAll(FriendChatList.map.get(Friend.friend.getMailbox()));
            }
        });
    }
    public void flush(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ChatList.getItems().clear();
                ArrayList<Friends> data=new ArrayList<>();
                for (MemoryUserApplication m : FriendList.friendList) {
                    Friends friends=new Friends("好友",m);
                    data.add(friends);
                }
                ChatList.getItems().addAll(data);
                ChatList.refresh();
            }
        });

    }//刷新好友列表
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextSetOnKey();
        Emojis.setVisible(false);
        sendButton.setDefaultButton(true);
        ChatList.setCellFactory(param -> new FriendListCell());
        chatDataList.setCellFactory(param -> new messageListCell());

        if(User.avatar!=null) {
            Avatar.setImage(new Image(User.avatar));
        }else{
            Avatar.setImage(new Image("File:D://IDEA liu_da_shuai//Q_Q//src//client//photo//qq.png"));
        }
        flush();
        flushed();
        Emojis.setStyle("-fx-background-color: #ffffff; -fx-selection-bar: transparent; -fx-cell-focus-inner-border: transparent;");
        String weizhi="D:/QQ/2385272606/FileRecv/静态";
        File file=new File(weizhi);
        File[] filesInFolder = file.listFiles();
        int i=0;
        HBox hBox = null;
        for(File file1 :filesInFolder)
        {
            if(i==0)
            {
                hBox=new HBox();
            }
            if(i<20) {
                ImageView imageView = new ImageView(new Image(file1.toURI().toString(),30,30,false,true));
                EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {//表情包的点击事件
                        inputBox.insertText(inputBox.getCaretPosition(),"["+file1.getName()+"]");
                    }
                };
                imageView.setOnMouseClicked(eventHandler);
                hBox.getChildren().add(imageView);
            }
            i++;
            if(i==20)
            {
                i=0;
                Emojis.getItems().add(hBox);
            }
        }
        if(i>0)
        {
            Emojis.getItems().add(hBox);
        }

    }//界面初始化
    @FXML
    void MyMessage(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }//查看个人资料


    protected void flushed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Avatar.setImage(new Image(User.avatar));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 1000);//定时器的延迟时间及间隔时间
    }//线程刷新头像

    public void beginChat(MemoryUserApplication user) throws IOException {
        calico.setVisible(false);
        AddFriendApplication shuju=new AddFriendApplication(User.account,user.getAccount(),User.mailbox, user.getMailbox());
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("开始聊天",shuju));

    }//开始聊天

    public MemoryUserApplication getFriend() {
        return friend;
    }

    public AnchorPane getCalico() {
        return calico;
    }

    public void setCalico(AnchorPane calico) {
        this.calico = calico;
    }

    public void setFriend(MemoryUserApplication friend) {
        this.friend = friend;
    }

    public void TextSetOnKey(){
        inputBox.setOnKeyPressed(event -> {
            if(event.isControlDown()&& event.getCode()== KeyCode.ENTER){
                //按下Ctrl+Enter，插入换行符
                inputBox.insertText(inputBox.getCaretPosition(),"\n");
            }else if(event.getCode()==KeyCode.ENTER){
                event.consume();
                try {
                    send(null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static boolean FilePan(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}




















