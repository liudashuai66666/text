package application;

import java.io.Serializable;

public class ReviseAvatarApplication implements Serializable {
    private String avatar;//头像
    private String mailbox;//邮箱

    public ReviseAvatarApplication(String avatar, String mailbox) {
        this.avatar = avatar;
        this.mailbox = mailbox;
    }

    public ReviseAvatarApplication() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
