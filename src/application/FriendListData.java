package application;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendListData implements Serializable {
    private MemoryUserApplication user;
    private ArrayList<MemoryUserApplication> FriendList;
    private ArrayList<MemoryUserApplication> newFriendList1;
    private ArrayList<MemoryUserApplication> newFriendList2;
    private ArrayList<MemoryUserApplication> newFriendList3;

    public FriendListData() {
    }

    public FriendListData(MemoryUserApplication user, ArrayList<MemoryUserApplication> friendList, ArrayList<MemoryUserApplication> newFriendList1, ArrayList<MemoryUserApplication> newFriendList2, ArrayList<MemoryUserApplication> newFriendList3) {
        this.user = user;
        FriendList = friendList;
        this.newFriendList1 = newFriendList1;
        this.newFriendList2 = newFriendList2;
        this.newFriendList3 = newFriendList3;
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

    public ArrayList<MemoryUserApplication> getNewFriendList1() {
        return newFriendList1;
    }

    public void setNewFriendList1(ArrayList<MemoryUserApplication> newFriendList1) {
        this.newFriendList1 = newFriendList1;
    }

    public ArrayList<MemoryUserApplication> getNewFriendList2() {
        return newFriendList2;
    }

    public void setNewFriendList2(ArrayList<MemoryUserApplication> newFriendList2) {
        this.newFriendList2 = newFriendList2;
    }

    public ArrayList<MemoryUserApplication> getNewFriendList3() {
        return newFriendList3;
    }

    public void setNewFriendList3(ArrayList<MemoryUserApplication> newFriendList3) {
        this.newFriendList3 = newFriendList3;
    }
}
