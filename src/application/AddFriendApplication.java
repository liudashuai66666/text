package application;

import java.io.Serializable;

public class AddFriendApplication implements Serializable {
    private String userAccount1;//自己的账号；
    private String userAccount2;//你要添加的人的账号；
    private String userMailbox1;//自己的邮箱
    private String userMailbox2;//你要添加的人的邮箱

    public AddFriendApplication() {
    }

    public AddFriendApplication(String userAccount1, String userAccount2, String userMailbox1, String userMailbox2) {
        this.userAccount1 = userAccount1;
        this.userAccount2 = userAccount2;
        this.userMailbox1 = userMailbox1;
        this.userMailbox2 = userMailbox2;
    }

    public String getUserAccount1() {
        return userAccount1;
    }

    public void setUserAccount1(String userAccount1) {
        this.userAccount1 = userAccount1;
    }

    public String getUserAccount2() {
        return userAccount2;
    }

    public void setUserAccount2(String userAccount2) {
        this.userAccount2 = userAccount2;
    }

    public String getUserMailbox1() {
        return userMailbox1;
    }

    public void setUserMailbox1(String userMailbox1) {
        this.userMailbox1 = userMailbox1;
    }

    public String getUserMailbox2() {
        return userMailbox2;
    }

    public void setUserMailbox2(String userMailbox2) {
        this.userMailbox2 = userMailbox2;
    }
}
