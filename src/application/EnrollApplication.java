package application;

import java.io.Serializable;

public class EnrollApplication implements Serializable {
    private String uname;
    private String password;
    private String mailbox;

    public EnrollApplication() {
    }

    public EnrollApplication(String uname, String password, String mailbox) {
        this.uname = uname;
        this.password = password;
        this.mailbox = mailbox;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
