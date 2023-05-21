package client.control;

import application.*;
import client.controlList.GroupChatListCell;
import client.controlList.GroupListCell;
import client.controlList.GroupMemberCell;
import client.view.*;
import client.vo.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static client.control.HallButton.FilePan;

public class GroupChatButton implements Initializable {
    @FXML
    private Button groupDataButton;//群聊资料按钮
    @FXML
    private Button FriendsButton;//切换好友聊天
    @FXML
    private Button addFriendButton;//添加好友
    @FXML
    private Button GroupChatButton;//切换群聊按钮
    @FXML
    private Button MyMessageButton;//个人资料按钮
    @FXML
    private ImageView Avatar;//头像
    @FXML
    private Button createGroupButton;//创建群聊按钮

    @FXML
    private Button searchGroupButton;//搜索群聊按钮
    @FXML
    private TextField InputBox;//搜索群聊框
    @FXML
    private Button addGroupMemberButton;//添加群聊成员按钮
    @FXML
    private AnchorPane Pane;//遮挡板
    @FXML
    private Button emojis;//表情包

    @FXML
    private Button image;//图片发送

    @FXML
    private Button sendButton;//发送信息按钮

    @FXML
    private TextArea inputMessageBox;//输入消息框
    @FXML
    private ListView<GroupApplication> ChatList;//群聊链表，到时候不能用Friend
    @FXML
    private ListView<GroupChatData> ChatMessageList;//聊天记录
    @FXML
    private Button FriendApplicationButton;//切换好友申请按钮
    @FXML
    private ListView Emojis;//表情包链表
    @FXML
    private Button deleteGroupButton;//删除群聊
    @FXML
    private Button addButton;//拉人进群
    @FXML
    private Button deleteButton;//踢人出群
    @FXML
    private Button setManagerButton;//设置管理员
    @FXML
    private ListView<MemoryUserApplication> groupMember;//群成员列表
    public static String path;
    public int level;
    public final String dizhi="D:/QQ/2385272606/FileRecv/静态/";
    private int cnt=0;
    private int cnt1=0;
    @FXML
    void add(ActionEvent event) throws Exception {
        choseGroupUser.groupUser.clear();
        System.out.println("邀请成员");
        AddGroupUser addGroupUser=new AddGroupUser();
        if(!addGroupUser.stagex.isShowing()){
            addGroupUser.start(new Stage());
        }else {
            addGroupUser.stagex.toFront();
        }
        AddGroupUser.addGroupUserButton.flush();
    }

    @FXML
    void delete(ActionEvent event) throws Exception {
        System.out.println("踢人出群");
        choseGroupUser.groupUser.clear();
        DeleteGroupUser deleteGroupUser=new DeleteGroupUser("删除成员");
        if(!deleteGroupUser.stagex.isShowing()){
            deleteGroupUser.start(new Stage());
        }else {
            deleteGroupUser.stagex.toFront();
        }
        deleteGroupUser.deleteGroupUserButton.delete();
        DeleteGroupUser.deleteGroupUserButton.flush();
    }

    @FXML
    void setManager(ActionEvent event) throws Exception {
        System.out.println("设置管理员！");
        choseGroupUser.groupUser.clear();
        DeleteGroupUser deleteGroupUser=new DeleteGroupUser("设置管理员");
        if(!deleteGroupUser.stagex.isShowing()){
            deleteGroupUser.start(new Stage());
        }else {
            deleteGroupUser.stagex.toFront();
        }
        deleteGroupUser.deleteGroupUserButton.set();
        DeleteGroupUser.deleteGroupUserButton.flush();
    }
    public void close(){
        Pane.setVisible(true);
        HallFace.stagex.setWidth(985);
        cnt=0;
    }
    @FXML
    void deleteGroup(ActionEvent event) {
        System.out.println("你要删除该群聊!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认对话框");
        alert.setHeaderText("请确认您的操作");
        alert.setContentText("是否删除该群聊！");
// 添加“确认”和“取消”按钮
        ButtonType okButton = new ButtonType("确认", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("取消", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, cancelButton);
// 设置点击回调函数
        alert.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                //1.先在自己的列表里面将该用户的数据删除，然后发送请求给服务端，在服务端里面将该条好友消息清除，然后再反馈给你删除的那个好友删除记录
                try {
                    ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
                    oos.writeObject(new AllApplication<>("删除群聊",Group.group));
                    //刷新
                    Iterator<GroupApplication> it=GroupList.groupList.iterator();
                    while (it.hasNext()){
                        GroupApplication m=it.next();
                        if(m.getGroup_id().equals(Group.group.getGroup_id())){
                            it.remove();
                            break;
                        }
                    }
                    HallFace.groupChatButton.flush();
                    HallFace.groupChatButton.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (buttonType == cancelButton) {
                alert.close();
            }
            return null;
        });
        alert.show();

    }
    @FXML
    void GroupChat(ActionEvent event) {
        System.out.println("群聊");
        Pane.setVisible(true);
        HallFace.groupChatButton.flush();
    }

    @FXML
    void Friends(ActionEvent event) {
        System.out.println("私聊");
        Pane.setVisible(true);
        LoginButton.hall.switchToPage1();
        //转化到私聊界面
    }

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
    }
    @FXML
    void FriendApplication(ActionEvent event) {
        System.out.println("好友申请");
        Pane.setVisible(true);
        LoginButton.hall.switchToPage3();
    }

    @FXML
    void addGroupMember(ActionEvent event) {
        flushUser();
        xiugaiLevel();
        System.out.println("邀请人进群");
        choseGroupUser.groupUser.clear();
        cnt++;
        User1.user.setLevel(level);
        User.level=level;
        if(cnt%2==1){
            if(level==2){
                gly();
            } else if (level == 3) {
                chengyuan();
            } else if (level == 1) {
                qunzhu();
            }
            HallFace.stagex.setWidth(1252);
        }else{
            HallFace.stagex.setWidth(985);
        }
    }
    public void xiugaiLevel(){
        for (GroupApplication m : GroupList.groupList) {
            if(m.getGroup_id().equals(Group.group.getGroup_id())){
                level=m.getGroup_level();
                break;
            }
        }
    }
    public void qunzhu(){
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        deleteGroupButton.setVisible(true);
        setManagerButton.setVisible(true);
    }
    public void gly(){
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        deleteGroupButton.setVisible(false);
        setManagerButton.setVisible(false);
    }
    public void chengyuan(){
        addButton.setVisible(true);
        deleteButton.setVisible(false);
        deleteGroupButton.setVisible(false);
        setManagerButton.setVisible(false);
    }
    @FXML
    void groupData(ActionEvent event) throws Exception {
        System.out.println("群资料");
        GroupData groupData=new GroupData();
        if(!groupData.stagex.isShowing()){
            groupData.start(new Stage());
        }else{
            groupData.stagex.toFront();
        }
    }

    @FXML
    void createGroup(ActionEvent event) throws Exception {
        System.out.println("创建群聊");
        choseGroupUser.groupUser.clear();
        CreateGroup cGroup=new CreateGroup();
        if(!cGroup.stagex.isShowing()){
            cGroup.start(new Stage());
        }else {
            cGroup.stagex.toFront();
        }
    }

    @FXML
    void searchGroup(ActionEvent event) {
        System.out.println("搜索群聊");
    }//搜索群聊
    @FXML
    void MyMessage(ActionEvent event) throws Exception {
        PersonalData personalData = new PersonalData();
        if(!PersonalData.stagex.isShowing()){
            personalData.start(new Stage());
        }else{
            PersonalData.stagex.toFront();
        }
    }//个人资料
    @FXML
    void send(ActionEvent event) throws IOException {
        System.out.println("发消息");
        //发送消息
        if(inputMessageBox.getText().equals("")){
            return;
        }
        String massage=inputMessageBox.getText();
        inputMessageBox.clear();
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
                oos.writeObject(new AllApplication<>("图片",new ImageApplication(3,FileName,fileBytes,User.mailbox,Group.group.getGroup_id())));
            }
        }
        GroupChatData shuju = new GroupChatData(Group.group.getGroup_id(), User1.user,time,massage,"文本");
        ObjectOutputStream oos =new ObjectOutputStream(User.socket.getOutputStream());
        oos.writeObject(new AllApplication<>("发群聊消息",shuju));
        GroupUserMap.groupChatDataMap.get(Group.group.getGroup_id()).add(shuju);
        HallFace.groupChatButton.flushChat();
    }//发送消息
    @FXML
    void onEmojis(ActionEvent event) {
        if(cnt1%2==0){
            Emojis.setVisible(true);
            cnt1++;
        }else{
            Emojis.setVisible(false);
            cnt1++;
        }
    }//表情包

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
        GroupChatData shuju = new GroupChatData(Group.group.getGroup_id(),User1.user,time,uuid+selectedFile.getName(),"文件");
        oos.writeObject(new AllApplication<>("发群聊消息",shuju));
        GroupUserMap.groupChatDataMap.get(Group.group.getGroup_id()).add(shuju);
        HallFace.groupChatButton.flushChat();
    }//文件

    @FXML
    void onImage(ActionEvent event) throws IOException {
        //System.out.println("发图片");
        UUID uuid;
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
            oos.writeObject(new AllApplication<>("图片",new ImageApplication(4,uuid+".jpg",bytes,User.mailbox,Friend.friend.getMailbox())));
            FileOutputStream fos=new FileOutputStream("D:\\图片\\"+uuid+".jpg");
            fos.write(bytes);
        }else {
            return;
        }
        ObjectOutputStream oos=new ObjectOutputStream(User.socket.getOutputStream());
        GroupChatData shuju = new GroupChatData(Group.group.getGroup_id(),User1.user,time,uuid+".jpg", "图片");
        oos.writeObject(new AllApplication<>("发群聊消息",shuju));
        GroupUserMap.groupChatDataMap.get(Group.group.getGroup_id()).add(shuju);
        HallFace.groupChatButton.flushChat();
    }//图片

    public void flush(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(GroupList.groupList!=null){
                    ChatList.getItems().clear();
                    ChatList.getItems().addAll(GroupList.groupList);
                    ChatList.refresh();
                }
            }
        });
    }//刷新好友列表
    public void flushChat(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(GroupList.groupList!=null){
                    ChatMessageList.getItems().clear();
                    ChatMessageList.getItems().addAll(GroupUserMap.groupChatDataMap.get(Group.group.getGroup_id()));
                    ChatMessageList.refresh();
                }
            }
        });
    }//刷新聊天列表
    public void flushUser(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(GroupList.groupList!=null){
                    groupMember.getItems().clear();
                    try {
                        ArrayList<MemoryUserApplication>list=GroupUserMap.groupUser.get(Group.group.getGroup_id());
                        Collections.sort(list, new MemoryUserApplicationComparator());//排序
                        groupMember.getItems().addAll(list);
                        groupMember.refresh();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }//刷新聊天列表
    public void startChat(GroupApplication shuju){
        Pane.setVisible(false);
        groupDataButton.setText(shuju.getGroup_name());
    }//开始聊天
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
    }
    public void TextSetOnKey(){
        inputMessageBox.setOnKeyPressed(event -> {
            if(event.isControlDown()&& event.getCode()== KeyCode.ENTER){
                //按下Ctrl+Enter，插入换行符
                inputMessageBox.insertText(inputMessageBox.getCaretPosition(),"\n");
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User1.user.setLevel(level);
        User.level=level;
        Emojis.setVisible(false);
        TextSetOnKey();
        ChatList.setCellFactory(param -> new GroupListCell());
        ChatMessageList.setCellFactory(param -> new GroupChatListCell());
        groupMember.setCellFactory(param -> new GroupMemberCell());
        Avatar.setImage(new Image(User.avatar));
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
                        inputMessageBox.insertText(inputMessageBox.getCaretPosition(),"["+file1.getName()+"]");
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

    }//初始化函数
}
