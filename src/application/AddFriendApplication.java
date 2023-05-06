package application;

import java.io.Serializable;

public class AddFriendApplication implements Serializable {
    private String user1;//自己的账号；
    private String user2;//你要添加的人的账号；

    public AddFriendApplication() {
    }

    public AddFriendApplication(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
}
