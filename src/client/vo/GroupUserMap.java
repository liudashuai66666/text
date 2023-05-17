package client.vo;

import application.GroupChatData;
import application.GroupUserApplication;
import application.MemoryUserApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupUserMap {
    public static Map<String, ArrayList<MemoryUserApplication>> groupUser=new HashMap<>();//用户map
    public static Map<String,ArrayList<GroupChatData>> groupChatDataMap=new HashMap<>();//聊天记录map
}
