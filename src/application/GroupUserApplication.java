package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupUserApplication implements Serializable {
    private GroupApplication group;//群资料
    private ArrayList<MemoryUserApplication>user=new ArrayList<>();//群用户列表

    public GroupUserApplication() {
    }

    public GroupUserApplication(GroupApplication group, ArrayList<MemoryUserApplication> user) {
        this.group = group;
        this.user = user;
    }

    public GroupApplication getGroup() {
        return group;
    }

    public void setGroup(GroupApplication group) {
        this.group = group;
    }

    public ArrayList<MemoryUserApplication> getUser() {
        return user;
    }

    public void setUser(ArrayList<MemoryUserApplication> user) {
        this.user = user;
    }
}
