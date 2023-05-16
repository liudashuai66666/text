package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupListData implements Serializable {
    private ArrayList<GroupApplication> groupList;

    public GroupListData(ArrayList<GroupApplication> groupList) {
        this.groupList = groupList;
    }

    public GroupListData() {
    }

    public ArrayList<GroupApplication> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<GroupApplication> groupList) {
        this.groupList = groupList;
    }
}
