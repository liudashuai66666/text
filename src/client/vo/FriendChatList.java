package client.vo;

import application.ChatData;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FriendChatList {
    public static ArrayList<ChatData>chatDataList=null;//聊天记录;
    public static Map<String, ArrayList<ChatData> > map = new ConcurrentHashMap<>();
}
