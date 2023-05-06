package application;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendListData implements Serializable {
    private MemoryUserApplication user;
    private ArrayList<MemoryUserApplication> FriendList;

    public FriendListData() {
    }

    public FriendListData(MemoryUserApplication user, ArrayList<MemoryUserApplication> friendList) {
        this.user = user;
        FriendList = friendList;
    }

    public MemoryUserApplication getUser() {
        return user;
    }

    public void setUser(MemoryUserApplication user) {
        this.user = user;
    }

    public ArrayList<MemoryUserApplication> getFriendList() {
        return FriendList;
    }

    public void setFriendList(ArrayList<MemoryUserApplication> friendList) {
        FriendList = friendList;
    }
}
