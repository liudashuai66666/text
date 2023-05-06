package application;

import java.io.Serializable;

public class ForgetApplication implements Serializable {
    private String mailbox;
    private String password;

    public ForgetApplication() {
    }
    public ForgetApplication(String mailbox, String password) {
        this.mailbox = mailbox;
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
