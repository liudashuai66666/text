package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupChatDataApplication implements Serializable {
    private String group_id;
    private ArrayList<GroupChatData>groupList=new ArrayList<>();

    public GroupChatDataApplication() {
    }

    public GroupChatDataApplication(String group_id, ArrayList<GroupChatData> groupList) {
        this.group_id = group_id;
        this.groupList = groupList;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public ArrayList<GroupChatData> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<GroupChatData> groupList) {
        this.groupList = groupList;
    }
}
