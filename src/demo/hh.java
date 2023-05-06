package demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalTime;

public class hh extends Application {

    // 创建一个可观察的消息列表
    private ObservableList<String> messages = FXCollections.observableArrayList();

    // 创建一个 ListView 用于显示聊天消息
    private ListView<String> messageListView = new ListView<>(messages);

    // 创建一个 TextField 用于输入聊天消息
    private TextField inputField = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // 设置 ListView 中每个单元格的显示内容
        messageListView.setCellFactory(param -> new ListCell<String>() {
            private final Label label;
            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                label = new Label();
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    String[] tokens = item.split(":", 2);
                    label.setText(tokens[1]);
                    HBox hbox = new HBox();
                    hbox.setPadding(new Insets(5, 10, 5, 10));
                    if (tokens[0].equals("我")) {
                        hbox.getChildren().addAll(new Label(), label);
                        hbox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
                    } else {
                        hbox.getChildren().addAll(label, new Label());
                        hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    }
                    setGraphic(hbox);
                }
            }
        });
        // 创建输入 GridPane
        GridPane inputPane = new GridPane();
        inputPane.setHgap(5);
        inputPane.setVgap(5);
        inputPane.setPadding(new Insets(10));

        // 添加标签和文本框到 GridPane 中
        inputPane.add(new Label("消息："), 0, 0);
        inputPane.add(inputField, 1, 0);

        // 绑定 TextField 的回车事件
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String message = inputField.getText();
                // 将新的消息添加到消息列表中
                messages.add("我: " + message + " - " + LocalTime.now().toString().substring(0, 5));
                // 清空 TextField 中的文本内容
                inputField.clear();
                event.consume();
            }
        });

        // 将 ListView 和输入 GridPane 添加到布局中
        root.setCenter(messageListView);
        root.setBottom(inputPane);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("聊天");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}