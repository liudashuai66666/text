package application;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatDataApplication implements Serializable {
    private ArrayList<ChatData> chatList;//消息记录

    public ChatDataApplication(ArrayList<ChatData> chatList1) {
        this.chatList = chatList1;
    }

    public ChatDataApplication() {
    }

    public ArrayList<ChatData> getChatList1() {
        return chatList;
    }

    public void setChatList1(ArrayList<ChatData> chatList1) {
        this.chatList = chatList1;
    }
}
