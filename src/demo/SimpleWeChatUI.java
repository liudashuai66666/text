package demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;

public class SimpleWeChatUI extends Application {

    private ListView<ChatMessage> chatListView = new ListView<>();
    private TextField inputTextField = new TextField();
    private Button sendButton = new Button("发送");
    private StringProperty username = new SimpleStringProperty("小明");
    private ObservableList<ChatMessage> messageList = FXCollections.observableArrayList();
    private LinkedList<ChatMessage> messageCache = new LinkedList<>();

    public void start(Stage primaryStage) {
        // 初始化UI控件
        chatListView.setCellFactory(param -> new ChatMessageCell());
        chatListView.setItems(messageList);
        inputTextField.setOnAction(event -> sendMessage());
        sendButton.setOnAction(event -> sendMessage());

        // 组装主界面
        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(new Label("输入消息:"), inputTextField, sendButton);
        HBox.setHgrow(inputTextField, Priority.ALWAYS);
        HBox.setHgrow(sendButton, Priority.NEVER);

        BorderPane root = new BorderPane();
        root.setCenter(chatListView);
        root.setBottom(inputBox);
        Scene scene = new Scene(root, 400, 600);

        // 模拟消息源
        Thread messageSource = new Thread(() -> {
            try {
                Random random = new Random();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                while (true) {
                    int type = random.nextInt(2);
                    String sender = random.nextBoolean() ? "小明" : "小红";
                    LocalDateTime timestamp = LocalDateTime.now();
                    String content;
                    if (type == 0) {
                        content = "这是一条纯文本消息。";
                    } else {
                        content = "这是一张图片：";
                    }
                    ChatMessage message = new ChatMessage(sender, getUsername(), type, timestamp, content);
                    synchronized (messageCache) {
                        messageCache.add(message);
                        messageCache.notify();
                    }
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        messageSource.setDaemon(true);
        messageSource.start();

        // 缓存更新线程
        Thread cacheUpdater = new Thread(() -> {
            try {
                while (true) {
                    synchronized (messageCache) {
                        if (messageCache.isEmpty()) {
                            messageCache.wait();
                        }
                        messageList.addAll(messageCache);
                        messageCache.clear();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cacheUpdater.setDaemon(true);
        cacheUpdater.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("简易微信聊天界面");
        primaryStage.show();
    }

    // 发送消息
    private void sendMessage() {
        String content = inputTextField.getText().trim();
        if (!content.isEmpty()) {
            ChatMessage message = new ChatMessage(getUsername(), getUsername(), 0, LocalDateTime.now(), content);
            synchronized (messageCache) {
                messageCache.add(message);
                messageCache.notify();
            }
            inputTextField.setText("");
        }
    }

    // 获取当前用户的用户名
    private String getUsername() {
        return username.get();
    }

    // 聊天消息类
    private static class ChatMessage {
        private String sender;
        private String receiver;
        private int type;
        private LocalDateTime timestamp;
        private String content;

        public ChatMessage(String sender, String receiver, int type, LocalDateTime timestamp, String content) {
            this.sender = sender;
            this.receiver = receiver;
            this.type = type;
            this.timestamp = timestamp;
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public int getType() {
            return type;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getContent() {
            return content;
        }
    }

    // 自定义单元格工厂
    private class ChatMessageCell extends ListCell<ChatMessage> {
        private Label senderLabel = new Label();
        private Label timeLabel = new Label();
        private Label contentLabel = new Label();
        private ImageView imageView = new ImageView();
        private BorderPane borderPane = new BorderPane();

        @Override
        protected void updateItem(ChatMessage item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (item.getType() == 0) {
                    // 文本消息
                    senderLabel.setText(item.getSender());
                    timeLabel.setText(item.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    contentLabel.setText(item.getContent());
                    borderPane.setLeft(senderLabel);
                    borderPane.setRight(timeLabel);
                    borderPane.setCenter(contentLabel);
                } else {
                    // 图片消息
                    senderLabel.setText(item.getSender());
                    timeLabel.setText(item.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    borderPane.setLeft(senderLabel);
                    borderPane.setRight(timeLabel);
                    borderPane.setCenter(imageView);
                }
                setGraphic(borderPane);
            }
        }
    }
}