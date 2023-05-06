package application;

import java.io.Serializable;

public class EmailApplication implements Serializable {
    private String email;

    public EmailApplication() {
    }

    public EmailApplication(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
