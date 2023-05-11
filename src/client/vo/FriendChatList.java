package client.vo;

import application.ChatData;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FriendChatList {
    public static Map<String, ArrayList<ChatData> > map = new ConcurrentHashMap<>();
}
